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
  import {getExaminationAnalysis} from '@/api/assessment.js'

  export default {
    name: "Kaoshifenxi",
    data() {
      return {
        chartsDate: [],
        chartsData: []
      }
    },
    mounted() {
      //初始化考试分析
      this.getExaminationAnalysis()
    },
    methods: {
      getExaminationAnalysis() {
        let that = this
        getExaminationAnalysis({})
          .then(function (res) {
            that.chartsData = res.resultValue
            that.chartsDate = res.resultDate
            that.initEcharts()
            console.log(res)
          }).catch(function (err) {
          console.log(err)
        })
      },
      initEcharts() {
        var dom = document.getElementById('echarts')
        var myChart = this.echarts.init(dom)
        // 绘制图表
        myChart.setOption({
          xAxis: {
            type: 'category',   // 还有其他的type，可以去官网喵两眼哦
            data: this.chartsDate,   // x轴数据
            // x轴名称样式
            nameTextStyle: {
              fontWeight: 600,
              fontSize: 18
            },
            axisLine: {
              lineStyle: {
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
            axisLine: {
              lineStyle: {
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
              data: this.chartsData,
              type: 'line',
              smooth: true,
              areaStyle: {
                normal: {
                  // color: 'RGB(18,111,149)' //改变区域颜色
                    color: {
                      type: 'linear',
                      x: 0,
                      y: 0,
                      x2: 0,
                      y2: 1,
                      colorStops: [{
                        offset: 0, color: 'RGB(18,119,158)' // 0% 处的颜色
                      }, {
                        offset: 1, color: 'RGBA(17,42,69,0.01)' // 100% 处的颜色
                      }],
                      globalCoord: false // 缺省为 false
                    }

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
          grid: {
            x: 30,
            y: 6,
            x2: 1,
            y2: 20
          }
        });
      }
    },

  }
</script>

<style scoped>

</style>