import tarfile
import json
import csv
import sys
import collections


def main(filename):
    _tarfile = tarfile.open(filename)
    project_file = _tarfile.getmember('./project.json')
    parsed = json.load(_tarfile.extractfile(project_file))
    time_logs = []

    users = {
        893283: 'Raphael Zimmermann',
        1134363: 'Jonas Matter',
        1134328: 'Patrick Scherler',
        897664: 'Robin Suter'
    }
    print('{}result.csv'.format(filename[:-7]))
    for issue in parsed['issues']:
        for log in issue['timelogs']:
            time_logs.append({
                'time_spent': log['time_spent']/3600,
                'user': users.get(log['user_id'], log['user_id']),
                'date': log['created_at'],
                'issue_id': issue['iid'],
                'issue_name': issue['title']
            })
    with open('{}result.csv'.format(filename[:-7]), 'w') as f:
        writer = csv.DictWriter(f, ['time_spent', 'user', 'date', 'issue_id', 'issue_name'])
        writer.writeheader()
        for log in time_logs:
            writer.writerow(log)

    stats = collections.defaultdict(int)
    for log in time_logs:
        stats[log['user']] = stats[log['user']] + log['time_spent']

    with open('{}stats.csv'.format(filename[:-7]), 'w') as f:
        writer = csv.DictWriter(f, ['name', 'total_spend'])
        writer.writeheader()
        [writer.writerow({'name': name, 'total_spend': stat}) for name, stat in stats.items()]


if __name__ == "__main__":
    if len(sys.argv) == 2:
        main(sys.argv[1])
    else:
        print("Usage: python gitlab2csv.py <backup.tar.gz>")
