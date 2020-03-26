<template>
  <div>
    <a-card style="background-color: RGB(2,31,73);height: 957px">
      <a-row>
        <a-row>
          <span style="color: RGB(123,202,244);font-size: 18px">
            {{ "成绩概括" }}
          </span>
        </a-row>
        <a-row type="flex" style="margin-top: 20px">
          <a-col :span="4">
            <a-card style="background-color: RGB(4,40,94);color: #ffffff">
              <div style="text-align: center;">
                <a-row style="font-size: 24px">
                  {{'试卷得分'}}
                </a-row>
                <a-row style="font-size: 30px">
                  {{achievement}}
                </a-row>
              </div>
            </a-card>
          </a-col>
          <a-col :span="4">
            <a-card style="background-color: RGB(4,40,94);color: #ffffff">
              <div style="text-align: center;">
                <a-row style="font-size: 24px">
                  {{'当前排名'}}
                </a-row>
                <a-row style="font-size: 30px">
                  {{ranking}}
                </a-row>
              </div>
            </a-card>
          </a-col>
        </a-row>
        <a-row style="color: RGB(123,202,244);font-size: 18px">
          {{'综合成绩分析'}}
        </a-row>
        <a-divider type="horizontal"/>
        <a-row>
          <div id="echartsBar" :style="{width: '600px', height: '280px'}"></div>
        </a-row>
      </a-row>
    </a-card>
  </div>
</template>

<script>
  const tableColums = [{
    title: '序号',
    align: "center",
    dataIndex: 'No'
  },
    {
      title: '试卷名称',
      align: 'center',
      dataIndex: 'title'
    }, {
      title: '上传时间',
      align: 'center',
      dataIndex: 'update'
    },
    {
      title: '操作',
      align: 'center',
      scopedSlots: {customRender: 'operation'}
    }]
  const tableData = [
    {
      No: 1,
      title: '模型试卷',
      update: '2019-11-07 11:11:11',
    }, {
      No: 2,
      title: '测试试卷',
      update: '2019-11-11 11:11:11'
    },
    {
      No: 3,
      title: '测试试卷',
      update: '2019-11-11 11:11:11'
    },
    {
      No: 4,
      title: '测试试卷',
      update: '2019-11-11 11:11:11'
    }, {
      No: 5,
      title: '测试试卷',
      update: '2019-11-11 11:11:11'
    }, {
      No: 6,
      title: '测试试卷',
      update: '2019-11-11 11:11:11'
    }
  ]

  export default {
    name: "Achievement",
    data() {
      return {
        achievement: 98,
        ranking: 1,
        tableColums,
        tableData
      }
    },
    methods:{
      initEcharts() {
        var dom = document.getElementById('echartsBar')
        var myChart = this.echarts.init(dom)
        // 绘制图表
        myChart.setOption({
          title: {
            text: ''
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          legend: {
            data: []
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
          },
          yAxis: {
            type: 'category',
            data: ['我的成绩', '小组平局分', '小组最高分', '全员平均分', '全员最高分']
          },
          series: [
            {
              name: '',
              type: 'bar',
              data: [98, 60, 70, 78, 98]
            }
          ]
        });
      }
    },
    mounted() {
      this.initEcharts()
    }
  }
</script>

<style scoped>

</style>