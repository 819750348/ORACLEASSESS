<template>
  <div id="examinationPaperSettings">
    <a-card style="background-color: RGB(10,19,49);height:958px">
      <div class="examination">
        <a-row
          type="flex"
          style="margin-top: 20px;margin-bottom: 30px"
          justify="center"
          align="center">
          <a-col :span="24">
            <a-table :dataSource="studyScheduleData" :pagination="false" :columns="studyScheduleColumns">
              <template slot="operation">
                <span style="font-size: 18px;color: #0ca5ec;cursor: pointer" @click="">{{"进入"}}</span>
              </template>
              <span slot="footer">
                <a-row type="flex">
                  <a-col :span="2" style="margin-left: 25px"></a-col>
                  <a-col :span="20">
                    <a-pagination
                      size="small"
                      style="text-align: center;"
                      :total="10"
                      :showSizeChanger="false"
                      showQuickJumper
                      :showTotal="total => `共 ${total} 条`"/>
                  </a-col>
                  <a-col :span="1"></a-col>
                </a-row>
              </span>
            </a-table>
          </a-col>
        </a-row>
      </div>
    </a-card>
  </div>
</template>

<script>
  const studyScheduleColumns = [
    {
      title:'序号',
      dataIndex: 'NO',
      align: 'center'
    },
    {
      title:'培训内容',
      dataIndex: 'trainingContent',
      align: 'center'
    },
    {
      title: '培训课时',
      dataIndex: 'courseHours',
      align:'center'
    },
    {
      title: '学习状态',
      dataIndex: 'state',
      align:'center'
    },
    {
      title: '操作',
      scopedSlots: {customRender: 'operation'},
      align:'center'
    }
  ];
  const studyScheduleData = [
    {
      NO: '1',
      trainingContent: 'xxx学习原理',
      courseHours: '2',
      state: '已完成'
    },
    {
      NO: '2',
      trainingContent: 'xxx学习原理',
      courseHours: '2',
      state: '已完成'
    },
    {
      NO: '3',
      trainingContent: 'xxx学习原理',
      courseHours: '2',
      state: '已完成'
    },
    {
      NO: '4',
      trainingContent: 'xxx学习原理',
      courseHours: '2',
      state: '已完成'
    },
    {
      NO: '5',
      trainingContent: 'xxx学习原理',
      courseHours: '2',
      state: '已完成'
    },
    {
      NO: '6',
      trainingContent: 'xxx学习原理',
      courseHours: '2',
      state: '已完成'
    },
    {
      NO: '7',
      trainingContent: 'xxx学习原理',
      courseHours: '2',
      state: '已完成'
    },    {
      NO: '8',
      trainingContent: 'xxx学习原理',
      courseHours: '2',
      state: '已完成'
    },
    {
      NO: '9',
      trainingContent: 'xxx学习原理',
      courseHours: '2',
      state: '已完成'
    },    {
      NO: '10',
      trainingContent: 'xxx学习原理',
      courseHours: '2',
      state: '已完成'
    }


  ]
  import './ExaminationPaperSettings.less'
  export default {
    name: "StudySchedule",
    data(){
      return{
        studyScheduleData,
        studyScheduleColumns
      }
    }
  }
</script>

<style scoped>

</style>