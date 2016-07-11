define(function(require) {

	 jy.innertHead('我的账单');
	  
     jy.innertFood(4);

    //设置条形图box宽度
    var wid = $(".icon-tab-list").width() + 40;
    $(".myBillBar").width(wid);

    ZHData.get("myBillMSG",function(data){
		
    	  
			var X=data.X;
			
			var Y=data.Y;
			
			if(X && (X.length>0))
			{
			    // 基于准备好的dom，初始化echarts图表
			    var myChart = echarts.init(document.getElementById('main'));
			    //绘制条形图
			    option = {
			        title : {
			            x: "right",
			            subtext: '单位:元'
			        },
			        color:["#1790cf"],
			        animation: 'true',
			        xAxis : [
			            {
			                type : 'category',
			                data : X,
			                position : 'left',
			                splitLine: {
			                    lineStyle: {
			                        color: '#ebebeb'
			                    }
			                },
			                axisLine: {
			                    lineStyle:{
			                        color: '#1790cf'
			                    }
			                }
			            }
			        ],
			        yAxis : [
			            {
			                type : 'value',
			                min : 0,
			                max :120,
			                splitNumber : 3,
			                splitLine: {
			                    lineStyle: {
			                        color: '#ebebeb'
			                    }
			                },
			                axisLine: {
			                    lineStyle:{
			                        color: '#1790cf'
			                    }
			                }
			            }
			        ],
			        series : [
			            {
			                name:'消费金额',
			                type:'bar',
			                data:Y,
			                markLine : {
			                    data : [
			                        {type : 'average', name: '平均值'}
			                    ]
			                },
	                        itemStyle: {
	                            normal: {
	                                label : {
	                                    show: true, position: 'top'
	                                }
	                            }
	                        }
			            }
			        ],
			        grid : {
			                width: "66%"
			            }
			    };

			    // 为echarts对象加载数据
			    myChart.setOption(option);
			}else
			{
				$("#main").hide();
				$(".myBillEcharts").html('<div class="noMain">亲~暂无您的消费数据！</div>');
			}
			

		    

     });

});