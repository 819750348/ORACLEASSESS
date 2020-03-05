<template>
  <div id="practise">
    <a-card style="background-color: RGB(10,19,49)">
      <a-row style="padding: 30px 0 10px 0">
        <span>
          <a-input placeholder="请输入试卷名称" style="width: 180px;"/>
        </span>
        <span style="margin-left: 20px">
          <a-date-picker
            :disabledDate="disabledStartDate"
            showTime
            format="YYYY-MM-DD HH:mm:ss"
            v-model="startValue"
            placeholder="开始时间"
            @openChange="handleStartOpenChange"
          />
          <span style="margin-left:5px;margin-right: 5px;color: white">{{ "-" }}</span>
          <a-date-picker
            :disabledDate="disabledEndDate"
            showTime
            format="YYYY-MM-DD HH:mm:ss"
            placeholder="结束时间"
            v-model="endValue"
            :open="endOpen"
            @openChange="handleEndOpenChange"
          />
        </span>
        <span>
          <a-button style="margin-left: 10px">
            <a-icon type="search" />
          </a-button>
        </span>
        <span style="float: right;">
          <a-button>{{ "错题练习" }}</a-button>
        </span>
      </a-row>
      <a-row type="flex" style="margin-top: 20px;margin-bottom: 30px" justify="center" align="center">
        <a-col :span="24">
          <a-table :dataSource="examinationPaperData" :pagination="false" :columns="examinationPaperColumns">
            <span slot="status" slot-scope="text,record">
              <span v-if="text ==='1'">
                <img src=".././assets/img/yiwancheng.png" style="position:relative;bottom:2px;width: 14px;height: 14px;">
                <span style="margin-left: 6px">{{ "已完成" }}</span>
              </span>
              <span v-else>
                <img src=".././assets/img/weiwancheng.png" style="position:relative;bottom:2px;width: 14px;height: 14px;">
                <sapn style="margin-left: 6px">{{ "未完成" }}</sapn>
              </span>
            </span>
            <span slot="operation" slot-scope="text,record">
              <a v-if="record.status ==='0'" href="javascript:;">
                <img src=".././assets/img/lijidati.png" style="position:relative;bottom:2px;width: 70px;height: 24px;">
              </a>
              <a v-else href="javascript:;">
                <img src=".././assets/img/chakanshijuan.png" style="position:relative;bottom:2px;width: 70px;height: 24px;">
              </a>
            </span>
            <span slot="footer">
              <a-pagination
                size="small"
                style="text-align: center;"
                :total="50"
                :showSizeChanger="false"
                showQuickJumper
                :showTotal="total => `共 ${total} 条`"/>
            </span>
          </a-table>
        </a-col>
      </a-row>
    </a-card>
  </div>
</template>

<script>
  import './Practise.less'
  const examinationPaperColumns = [
    {
       title:'序号',
      dataIndex: 'No',
      scopedSlots:{ customRender: 'No'},
      align: 'center'
    },
    {
      title: '试卷名称',
      dataIndex: 'nameOfExaminationPaper',
      scopedSlots: {customRender: 'nameOfExaminationPaper'},
      align: 'center'
    },
    {
      title:'上传时间',
      dataIndex: 'updateTime',
      scopedSlots: { customRender: 'updateTime'},
      align: 'center'
    },
    {
      title: '状态',
      align: 'center',
      dataIndex: 'status',
      scopedSlots:{customRender: 'status'}
    },
    {
      title: '操作',
      dataIndex: 'operation',
      scopedSlots: {customRender:'operation'},
      align: 'center'
    }
  ]
  const examinationPaperData=[
    {
      No: '1',
      nameOfExaminationPaper: 'xxxx试卷1',
      updateTime: '2019-02-28 13:22:12',
      status: '0'
    },
    {
      No: '2',
      nameOfExaminationPaper: 'xxxx试卷1',
      updateTime: '2019-02-28 13:22:12',
      status: '0'
    },
    {
      No: '3',
      nameOfExaminationPaper: 'xxxx试卷1',
      updateTime: '2019-02-28 13:22:12',
      status: '0'
    },
    {
      No: '4',
      nameOfExaminationPaper: 'xxxx试卷1',
      updateTime: '2019-02-28 13:22:12',
      status: '0'
    },
    {
      No: '5',
      nameOfExaminationPaper: 'xxxx试卷1',
      updateTime: '2019-02-28 13:22:12',
      status: '0'
    },
    {
      No: '6',
      nameOfExaminationPaper: 'xxxx试卷1',
      updateTime: '2019-02-28 13:22:12',
      status: '0'
    },
    {
      No: '7',
      nameOfExaminationPaper: 'xxxx试卷1',
      updateTime: '2019-02-28 13:22:12',
      status: '1'
    },
    {
      No: '8',
      nameOfExaminationPaper: 'xxxx试卷1',
      updateTime: '2019-02-28 13:22:12',
      status: '1'
    },
    {
      No: '9',
      nameOfExaminationPaper: 'xxxx试卷1',
      updateTime: '2019-02-28 13:22:12',
      status: '1'
    },
    {
      No: '10',
      nameOfExaminationPaper: 'xxxx试卷1',
      updateTime: '2019-02-28 13:22:12',
      status: '1'
    }
  ]




  import moment from 'moment';
    export default {
        name: "Practise",
      data(){
          return{
            startValue: null,
            endValue: null,
            endOpen: false,
            examinationPaperColumns,
            examinationPaperData
          }
      },
      methods:{
        moment,
        disabledStartDate (startValue) {
          const endValue = this.endValue;
          if (!startValue || !endValue) {
            return false;
          }
          return startValue.valueOf() > endValue.valueOf();
        },
        disabledEndDate (endValue) {
          const startValue = this.startValue;
          if (!endValue || !startValue) {
            return false;
          }
          return startValue.valueOf() >= endValue.valueOf();
        },
        handleStartOpenChange (open) {
          if (!open) {
            this.endOpen = true;
          }
        },
        handleEndOpenChange (open) {
          this.endOpen = open;
        },
      }
    }
</script>

<style scoped>

</style>