$(function() {
	(function() {
		jQuery.get('json/?report=passedparticipationcomparisonreport', function(data) {
			Highcharts.chart('passedParticipationComparisonReport', {
				chart: {
                    type: 'pie'
                },
				title : {
					text : 'Bestanden und Durchgefallen im Vergleich'
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
			            y: data['percentageOfSuccessfulParticipations']
			        }, {
			            name: 'nicht bestanden',
			            y: data['percentageOfUnsuccessfulParticipations']
			        }]
				}]
			});
		}, 'json').fail(function() {
			$('#passedParticipationComparisonReport .no-export').show();
		});
	})();
});
