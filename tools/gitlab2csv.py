#!/usr/bin/env python3
import tarfile
import json
import csv
import sys
import os
import collections
import re
from urllib.request import Request, HTTPCookieProcessor, build_opener
from urllib.parse import urlencode
import time
from http.cookiejar import CookieJar


GITLAB_CSRF_BASE = 'https://gitlab.com/engineering-projekt/examibur/edit'
GITLAB_EXPORT_URL = 'https://gitlab.com/engineering-projekt/examibur/download_export'
GITLAB_GENERATE_EXPORT_URL = 'https://gitlab.com/engineering-projekt/examibur/generate_new_export'
TIMEOUT_WAIT_FOR_EXPORT_IN_SECONDS = 60*3


def generate_and_download_export():
    # Load access token from environment
    token = os.getenv('EXAMIBUR_ACCESS_TOKEN')
    if token is None:
        raise Exception("EXAMIBUR_ACCESS_TOKEN environment variable is not set")

    print("Requesting new export...")

    # start a new session (required to generate a new export)
    cj = CookieJar()
    opener = build_opener(HTTPCookieProcessor(cj))

    # Fetch csrf_token (required to generate a new export)
    csrf_token = _fetch_authenticity_token(opener, token)

    # Generate a new export
    encoded_args = urlencode({'_method': 'post', 'authenticity_token': csrf_token}).encode()
    request = Request(GITLAB_GENERATE_EXPORT_URL,
                      headers={'PRIVATE-TOKEN': token,
                               'Content-Type': 'application/x-www-form-urlencoded'},
                      data=encoded_args, method='POST')
    with opener.open(request) as response:
        if response.geturl() != GITLAB_CSRF_BASE:
            print(response.headers)
            print(response.read())
            raise Exception("Export did not work!")

    print("Check for export to be downloaded...")

    # Download the export
    request = Request(GITLAB_EXPORT_URL, headers={'PRIVATE-TOKEN': token}, method='GET')
    timeout = time.time()+TIMEOUT_WAIT_FOR_EXPORT_IN_SECONDS
    while timeout > time.time():
        time.sleep(2)
        with opener.open(request) as response:
            if response.geturl() == GITLAB_EXPORT_URL:
                file_name = response.getheader('Content-Disposition')[22:-1]
                print("Downloading {}...".format(file_name))
                with open(file_name, 'wb') as f:
                    f.write(response.read())
                print("Download completed!")
                return file_name
            else:
                print("Download not ready yet...will try again in 2s")

    raise Exception("Export generation has timed out!")


def _fetch_authenticity_token(opener, token):
    request = Request(GITLAB_CSRF_BASE, headers={'PRIVATE-TOKEN': token}, method='GET')
    with opener.open(request) as response:
        return next(re.finditer('<meta name="csrf-token" content="(.*)" />',
                    response.read().decode())).group(1)


def title_of_milestone(issue):
    if issue.get('milestone') is not None:
        return issue['milestone']['title']
    return ''


def main(filename):
    t = tarfile.open(filename)
    parsed = json.loads(t.extractfile('./project.json').read().decode())
    time_logs = []

    users = {
        893283: 'Raphael Zimmermann',
        1134363: 'Jonas Matter',
        1134328: 'Patrick Scherler',
        897664: 'Robin Suter'
    }
    print('{}result.csv'.format(filename[:-7]))

    issues = []
    for issue in parsed['issues']:
        total = 0
        for log in issue['timelogs']:
            time_logs.append({
                'time_spent': log['time_spent']/3600,
                'user': users.get(log['user_id'], log['user_id']),
                'date': log['created_at'],
                'issue_id': issue['iid'],
                'issue_name': issue['title']
            })
            total += log['time_spent']
        issues.append({
            'issue_id': issue['iid'],
            'issue_name': issue['title'],
            'assigned': users.get(issue['assignee_id'], issue['assignee_id']),
            'estimated': issue['time_estimate']/3600,
            'spent': total/3600,
            'duedate': issue['due_date'],
            'state': issue['state'],
            'milestone': title_of_milestone(issue),
            'use_case': ','.join([label['label']['title'] for label in issue['label_links'] if label['label']['title'][:2] == 'UC']),
            'labels': ','.join([label['label']['title'] for label in issue['label_links']])
        })
    with open('{}_issue_overview.csv'.format(filename[:-7]), 'w') as f:
        writer = csv.DictWriter(f, ['issue_id', 'issue_name', 'assigned', 'estimated', 'spent', 'duedate', 'milestone', 'use_case', 'labels', 'state'])
        writer.writeheader()
        for issue in issues:
            writer.writerow(issue)

    with open('{}_result.csv'.format(filename[:-7]), 'w') as f:
        writer = csv.DictWriter(f, ['time_spent', 'user', 'date', 'issue_id', 'issue_name'])
        writer.writeheader()
        for log in time_logs:
            writer.writerow(log)

    stats = collections.defaultdict(int)
    for log in time_logs:
        stats[log['user']] = stats[log['user']] + log['time_spent']

    with open('{}_stats.csv'.format(filename[:-7]), 'w') as f:
        writer = csv.DictWriter(f, ['name', 'total_spend'])
        writer.writeheader()
        [writer.writerow({'name': name, 'total_spend': stat}) for name, stat in stats.items()]


if __name__ == "__main__":
    if len(sys.argv) == 2:
        main(sys.argv[1])
    else:
        generate_and_download_export()
