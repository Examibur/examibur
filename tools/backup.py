#!/usr/bin/env python3
import sys
import os
import json
import re
import time
import tarfile
from urllib.error import HTTPError
from urllib.request import Request, urlopen, build_opener, HTTPCookieProcessor
from urllib.parse import urlencode
from http.cookiejar import CookieJar


TARGETS = ['gitlab', 'ci_artifacts', 'projekteserver']

BACKUP_LOCATION = os.path.join(os.path.dirname(__file__), '..', 'backups/')
BACKUP_CI_LOCATION = os.path.join(BACKUP_LOCATION, 'ci')
BACKUP_GL_LOCATION = os.path.join(BACKUP_LOCATION, 'gitlab')

GL_PROJECT_URL = 'https://gitlab.com/engineering-projekt/examibur'
GL_API_URL = 'https://gitlab.com/api/v4/'
GL_PROJECT_ID = '2751206'
GL_EXPORT_TIMEOUT_IN_SECONDS = 3*60

GL_API_BUILDS_URL = '{}/projects/{}/builds/'.format(GL_API_URL, GL_PROJECT_ID)
GL_API_ARTIFACT_URL = GL_PROJECT_URL + '/builds/{}/artifacts/download'
GL_EXPORT_CSRF_BASE_URL = '{}/edit'.format(GL_PROJECT_URL)
GL_EXPORT_TRIGGER_URL = '{}/generate_new_export'.format(GL_PROJECT_URL)
GL_EXPORT_DOWNLOAD_URL = '{}/download_export'.format(GL_PROJECT_URL)


def gitlab():
    """
    Generate and download a Gitlab export archive.
    This can currently not be achieved via API (the Website is used
    instead)
    """
    token = _examibur_token()
    os.makedirs(BACKUP_GL_LOCATION, exist_ok=True)
    _gitlab_trigger_export(token)
    _gitlab_download_export(token)


def _gitlab_trigger_export(token):
    """
    Trigger Gitlab to generate a new export. This can't be
    achieved via API. The process is also protected
    against CSRF - which makes the process verbose.
    """
    print("Triggering export")
    cj = CookieJar()
    opener = build_opener(HTTPCookieProcessor(cj))

    # Fetch csrf token
    request = Request(GL_EXPORT_CSRF_BASE_URL, headers={'PRIVATE-TOKEN': token})

    with opener.open(request) as response:
        csrf_token = next(re.finditer('<meta name="csrf-token" content="(.*)" />',
                          response.read().decode())).group(1)

    # Request export
    encoded_args = urlencode({'_method': 'post', 'authenticity_token': csrf_token}).encode()
    request = Request(GL_EXPORT_TRIGGER_URL, data=encoded_args, method='POST', headers={
                               'PRIVATE-TOKEN': token,
                               'Content-Type': 'application/x-www-form-urlencoded'})

    with opener.open(request) as response:
        if response.geturl() != GL_EXPORT_CSRF_BASE_URL:
            print(response.headers)
            print(response.read())
            raise Exception("Export did not work!")


def _gitlab_download_export(token):
    request = Request(GL_EXPORT_DOWNLOAD_URL, headers={'PRIVATE-TOKEN': token})

    timeout = time.time() + GL_EXPORT_TIMEOUT_IN_SECONDS
    while timeout > time.time():
        time.sleep(2)
        try:
            with urlopen(request) as response:
                if response.geturl() == GL_EXPORT_DOWNLOAD_URL:
                    file_name = response.getheader('Content-Disposition')[22:-1]
                    destination = os.path.join(BACKUP_GL_LOCATION, file_name)
                    print("Downloading export {}...".format(destination))
                    with open(destination, 'wb') as f:
                        f.write(response.read())
                    print("Download completed!")
                    print("Verifying tarfile....")
                    tf = tarfile.open(destination)
                    print("Tarfile is OK!")
                    return
                else:
                    print("Export not ready yet...will try again in 2s")
        except tarfile.ReadError:
            print("Invalid tar File...will try again in 2s...")
        except HTTPError as e:
            if 'The HTTP server returned a redirect error that would lead to an infinite loop.' in str(e):
                print('infinite loop occured - will try again in 2s...')
            else:
                break
    raise Exception("Export generation has timed out!")


def ci_artifacts():
    """
    Download all Build Artifacts from Gitlab-CI. The export (see `gitlab`
    function) does not contain them.
    """

    token = _examibur_token()
    os.makedirs(BACKUP_CI_LOCATION, exist_ok=True)

    print("fetching build artifacts....")
    request = Request(GL_API_BUILDS_URL, headers={'PRIVATE-TOKEN': token})
    with urlopen(request) as response:
        builds = json.load(response)
        for build in builds:
            if build.get('artifacts_file') is not None:
                _ci_artifacts_download(build, token)
            else:
                print('Build {} has no Artifacts'.format(build['id']))


def _ci_artifacts_download(build, token):
    """
    Downloads and the artifacts of the given build into the BACKUP_CI_LOCATION
    if not present yet. A Minimal integrity check is performed by checking
    the file size.
    """
    artifact = build['artifacts_file']
    file_name = '{}-{}'.format(build['pipeline']['id'], artifact['filename'])
    destination = os.path.join(BACKUP_CI_LOCATION, file_name)

    if os.path.exists(destination):
        return

    request = Request(GL_API_ARTIFACT_URL.format(build['id']),
                      headers={'PRIVATE-TOKEN': token})
    with urlopen(request) as response, open(destination, 'wb') as f:
            f.write(response.read())
    assert os.path.getsize(destination) == build['artifacts_file']['size']
    print('Downloaded {} to {}'.format(build['pipeline']['id'], destination))


def projekteserver():
    print("WARNING: projekteserver NOT YET IMPLEMENTED!")


def _examibur_token():
    """
    Returns value of the EXAMIBUR_ACCESS_TOKEN environment variable.
    This value is required to access the Gitlab API/Website. If not present,
    an exception is thrown,
    """
    token = os.getenv('EXAMIBUR_ACCESS_TOKEN')
    if token is None:
        raise Exception("EXAMIBUR_ACCESS_TOKEN environment variable is not set")
    return token


def main():
    if len(sys.argv) == 2 and sys.argv[1] == 'all':
        for action in TARGETS:
            globals()[action]()
    elif len(sys.argv) > 1:
        for action in sys.argv[1:]:
            if action not in TARGETS:
                print("skipping unknown target {}".format(action))
            else:
                globals()[action]()
    else:
        print("Usage: ./backup.py TARGET...")
        print("       ./backup.py all")


if __name__ == "__main__":
    main()
