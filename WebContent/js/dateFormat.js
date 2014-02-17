
			var formatDates=function(){
				var prevTime="";
				$.each($('.postTime'),function(index, element){
					var element = $(element)
					var time = element.html();
					time = time.split(' ')[0];
					if(time==prevTime)
						element.html('');
					else{
						prevTime=time;
						time=time.split("-");
						var months="Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec".split(" ");
						var year = $('<div>'+time[0]+'</div>').addClass('year');
						console.log(months);
						var month = months[Number(time[1])];
						var month = $('<div>'+month+'</div>').addClass('month');
						
						var day = $('<div>'+time[2]+'</div>').addClass('day');
						element.html('')
								.append(day,month,year);
					
					}
						
				});
			}
				