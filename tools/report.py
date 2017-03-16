#!/usr/bin/env python3
import tarfile
import json
import csv
import sys
import collections
import os
import datetime

USERS = {
    893283: 'Raphael Zimmermann',
    1134363: 'Jonas Matter',
    1134328: 'Patrick Scherler',
    897664: 'Robin Suter'
}

TARGETS = ['worktime', 'worktime_per_issue']


def worktime(project, export_date, output_directory):
    result = {
                'export_date': _datetime_in_ms(export_date),
                'team': collections.defaultdict(int)
              }

    for issue in project['issues']:
        for log in issue['timelogs']:
                user = USERS.get(log['user_id'], log['user_id'])
                time_spent = log['time_spent']/3600
                result['team'][user] += time_spent

    _dump_json('worktime', output_directory, result)
    _dump_csv('worktime', output_directory, ['user', 'total_spent'],
              result['team'].items())


def worktime_per_issue(project, export_date, output_directory):
    result = {
                  'export_date': _datetime_in_ms(export_date),
                  'data': {}
             }
    for issue in project['issues']:
        total_spent = 0
        for log in issue['timelogs']:
            total_spent += log['time_spent']
        result['data'][issue['iid']] = {
                'name': issue['title'],
                'estimated': issue['time_estimate']/3600,
                'spent': total_spent/3600,
                'milestone': issue.get('milestone', {'title': None})['title']
                }
    _dump_json('worktimePerIssue', output_directory, result)
    _dump_csv('worktimePerIssue', output_directory,
              ['issue_id', 'issue_title', 'estimated', 'spent', 'milestone'],
              [[iid] + list(data.values()) for iid, data in result['data'].items()])


def _datetime_in_ms(date_to_convert):
    epoch = datetime.datetime.utcfromtimestamp(0)
    return int((date_to_convert - epoch).total_seconds() * 1000)


def _load_project_json(path_to_backup):
    _tarfile = tarfile.open(path_to_backup)
    raw = _tarfile.extractfile('./project.json').read().decode()
    return json.loads(raw)


def _dump_csv(report_name, output_directory, colum_names, rows):
    destination = os.path.join(output_directory, '{}.csv'.format(report_name))
    with open(destination, 'w') as f:
        writer = csv.writer(f)
        writer.writerow(colum_names)
        for row in rows:
            writer.writerow(row)


def _dump_json(report_name, output_directory, data):
    destination = os.path.join(output_directory, '{}.json'.format(report_name))
    with open(destination, 'w') as f:
        json.dump(data, f)


def main():
    if len(sys.argv) != 4:
        print("Usage ./report.py BACKUP_FILE DESTINATION_DIR TARGET ...")
        print("      ./report.py BACKUP_FILE DESTINATION_DIR all")
    else:
        source = sys.argv[1]
        if os.path.isdir(source):
            source = os.path.join(source, sorted(os.listdir(source))[-1])
        project = _load_project_json(source)
        export_date_str = os.path.basename(source)[:16]
        export_date = datetime.datetime.strptime(export_date_str, '%Y-%m-%d_%H-%M')
        output_directory = sys.argv[2]
        os.makedirs(output_directory, exist_ok=True)
        if sys.argv[3] == 'all':
            for action in TARGETS:
                globals()[action](project, export_date, output_directory)
        else:
            for action in sys.argv[3:]:
                if action not in TARGETS:
                    print("Skipping unknown task {}".format(action))
                else:
                    globals()[action](project, export_date, output_directory)


if __name__ == "__main__":
    main()
