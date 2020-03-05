<template>
  <div id="facultyManagement">
    <a-card style="background-color: RGB(10,19,49)">
      <a-row style="padding: 30px 0 10px 0">
        <a-tabs>
          <a-tab-pane tab="教员管理" key="1"/>
          <a-tab-pane tab="学员管理" key="2"/>
        </a-tabs>
      </a-row>
      <a-row type="flex" style="margin-top: 20px;margin-bottom: 30px" justify="center" align="center">
        <a-col :span="24">
          <a-table :dataSource="facultyEstablishmentData" :pagination="false" :columns="facultyEstablishmentColumns">
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
                <a-col :span="1">
                  <a href="javascript:;">
                    <img
                      src=".././assets/img/tianjia.png"
                      style="width: 16px;height: 16px;float: right;margin-right: 20px;position:relative;top: 4px">
                  </a>
                </a-col>
              </a-row>
            </span>
          </a-table>
        </a-col>
        <a-row>
        </a-row>
      </a-row>
    </a-card>
  </div>
</template>

<script>
  const facultyEstablishmentColumns = [
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
      title: '用户IP',
      dataIndex: 'userIP',
      scopedSlots: {customRender: 'userName'},
      align: 'center'
    },
    {
      title: '登入名称',
      dataIndex: 'loginName',
      scopedSlots: {customRender: 'loginName'},
      align: 'center'
    },
    {
      title: '用户名称',
      dataIndex: 'userName',
      scopedSlots: {customRender: 'userName'},
      align: 'center'
    },
    {
      title: '创建时间',
      dataIndex: 'creationTime',
      scopedSlots: {customRender: 'creationTime'},
      align: 'center'
    },
    {
      title: "所属装备",
      dataIndex: 'equid',
      scopedSlots: {customRender: 'equid'},
      align: 'center'
    },
    {
      title: '岗位',
      dataIndex: 'station',
      scopedSlots: {customRender: 'station'},
      align: 'center'
    },
    {
      title: '人员分组',
      dataIndex: 'personnelGrouping',
      scopedSlots: {customRender: 'personnelGrouping'},
      align: 'center'
    },
    {
      title: '操作',
      dataIndex: 'operation',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ]

  const facultyEstablishmentData = [
    {
      No: '1',
      userIP: '2134145676',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '2',
      userIP: '2134145676',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '3',
      userIP: '2134145676',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '4',
      userIP: '2134145676',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '5',
      userIP: '2134145676',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '6',
      userIP: '2134145676',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '7',
      userIP: '2134145676',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '8',
      userIP: '2134145676',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '9',
      userIP: '2134145676',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '10',
      userIP: '2134145676',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    }
  ]
  import './FacultyManagement.less'

  export default {
    name: "FacultyManagement",
    data() {
      return {
        facultyEstablishmentColumns,
        facultyEstablishmentData
      }
    }
  }
</script>

<style scoped>

</style>