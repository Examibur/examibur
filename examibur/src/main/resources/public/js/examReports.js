/*jslint browser: true*/
window.jQuery(function() {
	(function($, Highcharts) {
		$.get('json/?report=passedparticipationcomparisonreport', function(data) {
			Highcharts.chart('passedParticipationComparisonReport', {
				chart: {
                    type: 'pie'
                },
				title : {
					text : ''
				},
				tooltip: {
			        pointFormat: '<b>{point.percentage:.1f}%</b>'
			    },
			    plotOptions: {
			    	pie: {
			    		dataLabels: {
			                enabled: true,
			                format: '<b>{point.name}</b>: {point.percentage:.1f} %'
			    		}
			    	}
			    },
				series : [ {
					name : 'Bestanden und Durchgefallen im Vergleich',
					data: [{
			            name: 'bestanden',
			            y: data.percentageOfSuccessfulParticipations
			        }, {
			            name: 'nicht bestanden',
			            y: data.percentageOfUnsuccessfulParticipations
			        }]
				}]
			});
		}, 'json').fail(function() {
			$('#passedParticipationComparisonReport .no-export').show();
		});
		
		function prepareExerciseAverageMaxPointsComparisonReportData(dataToMap) {
            var keys = [];
            var maxPoints = [];
            var averagePoints = [];

            for (var key in dataToMap) {
                // skip loop if the property is from prototype
                if (!dataToMap.hasOwnProperty(key)) {
                	continue;
                }

                var obj = dataToMap[key];
                keys.push(obj.title);
                maxPoints.push(obj.maxPoints);
                averagePoints.push(obj.averagePoints);
            }
            return {
                keys: keys,
                maxPoints: maxPoints,
                averagePoints: averagePoints
            };
        }
        
		$.get('json/?report=exerciseaveragemaxpointscomparisonreport', function(data) {
			var mappedData = prepareExerciseAverageMaxPointsComparisonReportData(data);
			Highcharts.chart('exerciseAverageMaxPointsComparisonReport', {
				chart: {
			        type: 'column'
			    },
			    title: {
			        text: ''
			    },
			    tooltip: {
			    	pointFormat: '{series.name}: {point.y:.1f}'
			    },
			    xAxis: {
			        categories: mappedData.keys
			    },
			    yAxis: {
			        min: 0,
			        title: {
			            text: 'Punkte'
			        }
			    },
			    series: [{
			        name: 'Maximale Punktzahl',
			        data: mappedData.maxPoints
			    }, {
			        name: 'Durchschnittliche Punktzahl',
			        data: mappedData.averagePoints
			    }]
			});
		}, 'json').fail(function() {
			$('#exerciseAverageMaxPointsComparisonReport .no-export').show();
		});
		
		
	})(window.jQuery, window.Highcharts);
});