<template>
  <div id="examinationPaper">
    <a-card style="background-color: rgba( 0,0,0,0.2)" >
      <span slot="title">
        <img src="@/assets/img/countDown.png" style="width: 26px;height: 26px;margin-top: -12px">
        <span style="color: rgb(12,152,220);margin-left: 12px;font-size: 30px">
          {{ "考试" }}
        </span>
      </span>
      <template>
        <a-table :columns="columns" :dataSource="data" :pagination="false">
          <!--<a-table-column title="标题" dataIndex="title" key="title" />-->
          <!--<a-table-column title="总分" dataIndex="totalScore" key="totalScore" />-->
          <!--<a-table-column title="指定学员" dataIndex="designatedStudent" key="designatedStudent" />-->
          <!--<a-table-column title="状态" dataIndex="status" key="status" />-->
          <!--<a-table-column title="查看" key="lookUp" >-->
          <!--<img src=".././assets/img/countDown.png" style="width: 18px;height: 18px;margin-top: -2px">-->
          <!--</a-table-column>-->
          <!--<a-table-column title="操作" dataIndex="operation" key="operation" />-->
          <span slot="customTitle">标题</span>
          <a slot="lookUp" slot-scope="text,record" href="javascript:;">
            <img src="@/assets/img/bianji.png" style="width: 20px;height: 20px;margin-top: -2px">
          </a>
          <a slot="operation" slot-scope="text,record" href="javascript:;">
            <img src="@/assets/img/shanchu.png" style="width: 16px;height: 20px;margin-top: -2px">
          </a>
        </a-table>
      </template>
    </a-card>
  </div>
</template>

<script>
  const columns = [
    {
      dataIndex: 'title',
      slots: { title: 'customTitle' },
      scopedSlots: { customRender: 'title' },
      align:'center'
    },
    {
      title: '总分',
      dataIndex: 'totalScore',
      align:'center'
    },
    {
      title: '指定学员',
      dataIndex: 'designatedStudent',
      align:'center'
    },
    {
      title:'状态',
      dataIndex: 'status',
      align:'center'
    },
    {
      title:'查看',
      dataIndex: 'lookUp',
      scopedSlots: {customRender : 'lookUp'},
      align:'center'
    },
    {
      title:'操作',
      dataIndex: 'operation',
      scopedSlots: {customRender : 'operation'},
      align:'center'
    }
  ];

  const data = [
    {
      key: '1',
      title: '(车型+专业)卷子',
      totalScore: 100,
      designatedStudent: '一啊啊',
      status: '已发送'
    },
    {
      key: '1',
      title: '(车型+专业)卷子',
      totalScore: 100,
      designatedStudent: '一啊啊',
      status: '已发送'
    },
    {
      key: '1',
      title: '(车型+专业)卷子',
      totalScore: 100,
      designatedStudent: '一啊啊',
      status: '已发送'
    },
    {
      key: '1',
      title: '(车型+专业)卷子',
      totalScore: 100,
      designatedStudent: '一啊啊',
      status: '已发送'
    },
  {
    key: '1',
      title: '(车型+专业)卷子',
    totalScore: 100,
    designatedStudent: '一啊啊',
    status: '已发送'
  }
  ];


import './ExaminationPaper.less'
  import './antTable.less'
    export default {
        name: "Kaoshi",
      data(){
          return{
            data,
            columns
          }
      }

    }
</script>

<style scoped>

</style>