<template>
  <div id="examinationPaper" style="height: 440px">
    <a-card style="background-color: rgba( 0,0,0,0.2)">
      <span slot="title">
        <img src="@/assets/img/countDown.png" style="width: 26px;height: 26px;margin-top: -12px">
        <span style="color: rgb(12,152,220);margin-left: 12px;font-size: 30px">
          {{ "考试" }}
        </span>
      </span>
      <template>
        <a-table :columns="columns" :dataSource="exaData" :pagination="false">
          <!--<a-table-column title="标题" dataIndex="title" key="title" />-->
          <!--<a-table-column title="总分" dataIndex="totalScore" key="totalScore" />-->
          <!--<a-table-column title="指定学员" dataIndex="designatedStudent" key="designatedStudent" />-->
          <!--<a-table-column title="状态" dataIndex="status" key="status" />-->
          <!--<a-table-column title="查看" key="lookUp" >-->
          <!--<img src=".././assets/img/countDown.png" style="width: 18px;height: 18px;margin-top: -2px">-->
          <!--</a-table-column>-->
          <!--<a-table-column title="操作" dataIndex="operation" key="operation" />-->
          <span slot="name" slot-scope="text,record">
            <span style="cursor:pointer" @click="paperDetails">
              {{text}}
            </span>
          </span>
          <span slot="students" slot-scope="text,record">
            <span style="cursor:pointer" @click="selectStudents">
              {{text}}
            </span>
          </span>
          <span slot="sendStatusStr" slot-scope="text,record">
            <span v-if="text === '已完成'" style="color: #18af1f">{{ text }}</span>
            <span v-if="text === '未完成'" style="color: red">{{ text }}</span>
          </span>
          <a slot="lookUp" href="javascript:;">
            <img src="@/assets/img/bianji.png" style="width: 20px;height: 20px;margin-top: -2px" @click="lowerHair">
          </a>
          <a slot="operation" href="javascript:;">
            <img src="@/assets/img/shanchu.png" style="width: 16px;height: 20px;margin-top: -2px">
          </a>
          <span slot="footer">
        <a-row>
          <a-col :span="24">
            <a-pagination
              style="text-align: center;"
              size="small"
              :total="5"
              :showSizeChanger="false"
              showQuickJumper
              :showTotal="total => `共 ${total} 条`"/>
          </a-col>
        </a-row>
      </span>
        </a-table>
      </template>

      <a-modal
        v-model="visible"
        width="1200px"
        class="eModalIowerHair"
        :footer="null"
      >
        <a-table :dataSource="lowerHairData" :pagination="false" :columns="lowerHairColumns">
          <span slot="checkBox" slot-scope="text,record">
            <a-checkbox @change="onChange"></a-checkbox>
          </span>
          <a slot="operation" href="javascript:;">
            <img src=".././assets/img/bianji.png" style="width: 14px;height: 16px;">
            <img src=".././assets/img/shanchu.png" style="width: 12px;height: 16px;margin-left: 10px">
          </a>
          <span slot="footer">
            <a-row type="flex">
              <a-col :span="2" style="margin-left: 16px">
                <a-checkbox @change="onChange">全选</a-checkbox>
                <a href="javascript:;">
                  <img
                    src=".././assets/img/shanchu.png"
                    style="width: 12px;height: 16px;margin-left: 10px;position:relative;bottom:2px">
                </a>
              </a-col>
              <a-col :span="20">
                <a-pagination
                  size="small"
                  style="text-align: center;"
                  :total="50"
                  :showSizeChanger="false"
                  showQuickJumper
                  :showTotal="total => `共 ${total} 条`"/>
              </a-col>
            </a-row>
          </span>
        </a-table>
      </a-modal>
    </a-card>
  </div>
</template>

<script>
  const columns = [
    {
      title: '标题',
      dataIndex: 'name',
      // slots: {title: 'customTitle'},
      scopedSlots: {customRender: 'name'},
      align: 'center'
    },
    // {
    //   title: '总分',
    //   dataIndex: 'totalScore',
    //   align: 'center'
    // },
    {
      title: '指定学员',
      dataIndex: 'students',
      scopedSlots: {customRender: 'students'},
      align: 'center'
    },
    {
      title: '状态',
      dataIndex: 'sendStatusStr',
      scopedSlots: {customRender: 'sendStatusStr'},
      align: 'center'
    },
    {
      title: '查看',
      dataIndex: 'lookUp',
      scopedSlots: {customRender: 'lookUp'},
      align: 'center'
    },
    {
      title: '操作',
      dataIndex: 'operation',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ];

  const data = [
    {
      key: '1',
      title: '(车型+专业)卷子',
      totalScore: 100,
      designatedStudent: '于杰',
      status: '已发送'
    },
    {
      key: '1',
      title: '(车型+专业)卷子',
      totalScore: 100,
      designatedStudent: '王学勇',
      status: '已发送'
    },
    {
      key: '1',
      title: '(车型+专业)卷子',
      totalScore: 100,
      designatedStudent: '李瑞',
      status: '已发送'
    },
    {
      key: '1',
      title: '(车型+专业)卷子',
      totalScore: 100,
      designatedStudent: '张伟',
      status: '已发送'
    },
    {
      key: '1',
      title: '(车型+专业)卷子',
      totalScore: 100,
      designatedStudent: '李荣荣',
      status: '已发送'
    }
  ];

  const lowerHairColumns = [
    {
      title: '',
      dataIndex: 'checkBox',
      scopedSlots: {customRender: 'checkBox'},
      align: 'center'
    },
    {
      title: '序号',
      dataIndex: 'No',
      scopedSlots: {customRender: 'No'},
      align: 'center'
    },
    {
      title: '标题',
      dataIndex: 'title',
      scopedSlots: {customRender: 'title'},
      align: 'center'
    },
    {
      title: '总分',
      dataIndex: 'totalScore',
      scopedSlots: {customRender: 'totalScore'},
      align: 'center'
    },
    {
      title: '指定学员',
      dataIndex: 'userName',
      scopedSlots: {customRender: 'userName'},
      align: 'center'
    },
    {
      title: '发送',
      dataIndex: 'sendOut',
      scopedSlots: {customRender: 'sendOut'},
      align: 'center'
    },
    {
      title: "状态",
      dataIndex: 'state',
      scopedSlots: {customRender: 'state'},
      align: 'center'
    },
    {
      title: '操作',
      dataIndex: 'operation',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ]

  const lowerHairData = [
    {
      No: '1',
      title: '基础设置试卷',
      totalScore: '100',
      userName: '王玲玲',
      sendOut: '张三',
      state: '发送',
      operation: '操作'
    },
    {
      No: '2',
      title: '指挥试卷',
      totalScore: '100',
      userName: '余文雅',
      sendOut: '张三',
      state: '发送',
      operation: '操作'
    },
    {
      No: '3',
      title: 'xxx试卷1',
      totalScore: '100',
      userName: '张珊',
      sendOut: '张三',
      state: '发送',
      operation: '操作'
    },
    {
      No: '4',
      title: 'xxx试卷1',
      totalScore: '100',
      userName: '张珊',
      sendOut: '张三',
      state: '发送',
      operation: '操作'
    },
    {
      No: '5',
      title: 'xxx试卷1',
      totalScore: '100',
      userName: '张珊',
      sendOut: '张三',
      state: '发送',
      operation: '操作'
    },
    {
      No: '6',
      title: 'xxx试卷1',
      totalScore: '100',
      userName: '张珊',
      sendOut: '张三',
      state: '发送',
      operation: '操作'
    },
    {
      No: '7',
      title: 'xxx试卷1',
      totalScore: '100',
      userName: '张珊',
      sendOut: '张三',
      state: '发送',
      operation: '操作'
    },
    {
      No: '8',
      title: 'xxx试卷1',
      totalScore: '100',
      userName: '张珊',
      sendOut: '张三',
      state: '发送',
      operation: '操作'
    },
    {
      No: '9',
      title: 'xxx试卷1',
      totalScore: '100',
      userName: '张珊',
      sendOut: '张三',
      state: '发送',
      operation: '操作'
    },
    {
      No: '10',
      title: 'xxx试卷1',
      totalScore: '100',
      userName: '张珊',
      sendOut: '张三',
      state: '发送',
      operation: '操作'
    }
  ]


  import './ExaminationPaper.less'
  import './antTable.less'
  import {getExaminationPaper} from '@/api/assessment.js'

  export default {
    name: "Kaoshi",
    data() {
      return {
        exaData: [],
        columns,
        lowerHairColumns,
        lowerHairData,
        visible: false
      }
    },
    methods: {
      lowerHair() {
        this.visible = true
      },
      initData() {
        let that = this
        getExaminationPaper({
          id: '1'
        }).then(function (res) {
          that.exaData = res.result
          console.log(res)
        }).catch(function (err) {
          console.log(err)
        })
      },
      paperDetails(){
        this.$route.push({
          name: 'Paper'
        })
      },
      selectStudents(){

      }
    },
    mounted() {
      this.initData()
    }

  }
</script>

<style scoped>

</style>