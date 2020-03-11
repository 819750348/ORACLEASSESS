<template>
  <div id="testLibrary">
    <a-card style="background-color: RGB(10,19,49)">
      <a-row style="padding: 30px 0 10px 0">
        <a-tabs>
          <a-tab-pane tab="指挥控制车复习题" key="1"/>
          <a-tab-pane tab="下发试卷" key="2"/>
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
                <a-col :span="2" style="margin-left: 30px">
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
      title: '题目',
      dataIndex: 'title',
      scopedSlots: {customRender: 'title'},
      align: 'center'
    },
    {
      title: '所属装备',
      dataIndex: 'equip',
      scopedSlots: {customRender: 'equip'},
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
      title: '作战控制衣柜安装在那个机柜',
      equip: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '2',
      title: '作战控制衣柜安装在那个机柜',
      equip: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '3',
      title: '作战控制衣柜安装在那个机柜',
      equip: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '4',
      title: '作战控制衣柜安装在那个机柜',
      equip: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '5',
      title: '作战控制衣柜安装在那个机柜',
      equip: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '6',
      title: '作战控制衣柜安装在那个机柜',
      equip: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '7',
      title: '作战控制衣柜安装在那个机柜',
      equip: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '8',
      title: '作战控制衣柜安装在那个机柜',
      equip: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '9',
      title: '作战控制衣柜安装在那个机柜',
      equip: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '10',
      title: '作战控制衣柜安装在那个机柜',
      equip: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    }
  ]
  import './TestLibrary.less'

  export default {
    name: "testLibrary",
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