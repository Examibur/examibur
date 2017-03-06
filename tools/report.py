#!/usr/bin/env python3
import tarfile
import json
import csv
import sys
import collections

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
        print("Usage: ./report.py <path-to-gitlab-export-to-analyze>")
