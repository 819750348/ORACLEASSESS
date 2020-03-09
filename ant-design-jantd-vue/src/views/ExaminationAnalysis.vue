<template>
  <div id="examinationAnalysis">
    <a-card style="background-color: rgba( 0,0,0,0.2); margin-top: 30px">
      <template>
        <span slot="title">
          <!--<a-icon type="question-circle" style="color: RGB(46,110,164)"/>-->
          <img src="@/assets/img/answerCard.png" style="width: 26px;height: 26px;margin-top: -12px">
          <span style="color: rgb(12,152,220);margin-left: 12px;font-size: 30px">
            {{ "考试分析" }}
          </span>
        </span>
        <div id="echarts" :style="{width: '600px', height: '280px'}"></div>
      </template>
    </a-card>
  </div>
</template>
<script>
  import './ExaminationAnalysis.less'
  import {getExaminationAnalysis} from  '@/api/assessment.js'
    export default {
      name: "Kaoshifenxi",
      mounted() {
        //初始化考试分析
        getExaminationAnalysis({

        }).then(function (res) {
          console.log(res)
        }).catch(function (err) {
          console.log(err)
        })

        var dom = document.getElementById('echarts')
        var myChart = this.echarts.init(dom)
        // 绘制图表
        myChart.setOption({
            xAxis: {
              type: 'category',   // 还有其他的type，可以去官网喵两眼哦
              data: ['11-7', '11-8', '11-9', '11-10', '11-11', '11-12', '11-13'],   // x轴数据
              // x轴名称样式
              nameTextStyle: {
                fontWeight: 600,
                fontSize: 18
              },
              axisLine:{
                lineStyle:{
                  color: 'RGB(20,66,115)'

                }
              },
              },
            yAxis: {
              type: 'value',
              // y轴名称样式
              nameTextStyle: {
                fontWeight: 600,
                fontSize: 18
              },
              axisLine:{
                lineStyle:{
                  color: 'RGB(20,66,115)'

                }
              },


              //设置网格线颜色
              splitLine: {
                show: true,
                lineStyle: {
                  color: ['#315070'],
                  width: 1,
                  type: 'solid'
                },

              }
            },
            label: {},
            tooltip: {
              trigger: 'axis'   // axis   item   none三个值
            },
            series: [
              {
                name: '车辆正确率',
                data: [40, 80, 40, 60, 40, 90, 100],
                type: 'line',
                smooth: true,
                areaStyle: {
                  normal: {
                    color: 'RGB(18,111,149)' //改变区域颜色
                  }
                },
                itemStyle: {
                  normal: {
                    color: '#ffffff', //改变折线点的颜色
                    lineStyle: {
                      color: 'RGB(23,179,251)' //改变折线颜色
                    }
                  }
                }
              }
            ],
          grid:{
            x: 30,
            y: 6,
            x2: 1,
            y2:20
          }
        });
      }
    }
</script>

<style scoped>

</style>