$(function() {
    function getWeekNumber(date) {
        date.setHours(0, 0, 0, 0);
        date.setDate(date.getDate() + 4 - (date.getDay() || 7));
        var yearStart = new Date(date.getFullYear(), 0, 1);
        var weekNo = Math.ceil((((date - yearStart) / 86400000) + 1) / 7);
        return weekNo;
    };

    function round(valueToRound) {
        return Math.round(valueToRound * 100) / 100;
    }

    (function () {
        jQuery.get('resources/export/worktime.txt', function(data) {
            var startWeek = 7;
            var weeklyHourTarget = 8.6;
            var targetWorkTime = (getWeekNumber(new Date()) - startWeek) * weeklyHourTarget;
            var workTimeChartData = JSON.parse(data);

            var rzimmerm = 'Raphael Zimmermann';
            var r1suter = 'Robin Suter';
            var pscherl = 'Patrick Scherler';
            var jmatter = 'Jonas Matter';

            Highcharts.chart('workTimeChart', {
                chart: {
                    type: 'column'
                },
                title: {
                    text: 'Stand: ' + moment(workTimeChartData['export_date']).format('DD.MM.YYYY')
                },
                xAxis: {
                    categories: [
                        rzimmerm,
                        r1suter,
                        pscherl,
                        jmatter
                    ]
                },
                yAxis: [{
                    min: 0,
                    title: {
                        text: 'Arbeitsstunden'
                    }
                }],
                legend: {
                    shadow: false
                },
                tooltip: {
                    shared: true
                },
                plotOptions: {
                    column: {
                        grouping: false,
                        shadow: false,
                        borderWidth: 0
                    }
                },
                series: [{
                    name: 'Ist',
                    color: '#6b8fc6',
                    data: [round(workTimeChartData[rzimmerm]), round(workTimeChartData[r1suter]),
                           round(workTimeChartData[pscherl]), round(workTimeChartData[jmatter])],
                    pointPadding: 0.3,
                    tooltip: {
                        valueSuffix: 'h'
                    }
                }, {
                    name: 'Soll',
                    color: '#f2f2f2',
                    data: [targetWorkTime, targetWorkTime, targetWorkTime, targetWorkTime],
                    pointPadding: 0.4,
                    tooltip: {
                        valueSuffix: 'h'
                    }
                }]
            });
        });
    })();
});
