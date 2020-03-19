<template>
  <div id="facultyManagement" class="facultyManagement">
    <a-card style="background-color: RGB(10,19,49)">
      <a-row style="padding: 30px 0 10px 0">
        <a-tabs @tabClick="tabsChang">
          <a-tab-pane tab="教员管理" key="1"/>
          <a-tab-pane tab="学员管理" key="2"/>
        </a-tabs>
      </a-row>
      <a-row
        type="flex"
        style="margin-top: 20px;margin-bottom: 30px"
        justify="center"
        align="center"
        v-if="tabsVisible1 ===true ">
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
                  <span @click="importData">
                    <a href="javascript:;">
                      <img
                        src=".././assets/img/tianjia.png"
                        style="width: 16px;height: 16px;float: right;margin-right: 20px;position:relative;top: 4px">
                    </a>
                  </span>
                </a-col>
              </a-row>
            </span>
          </a-table>
        </a-col>
      </a-row>


      <a-row
        type="flex"
        style="margin-top: 20px;margin-bottom: 30px"
        justify="center"
        align="center"
        v-if="tabsVisible2 ===true ">
        <a-col :span="24">
          <a-table :dataSource="facultyEstablishmentData2" :pagination="false" :columns="facultyEstablishmentColumns">
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
                  <router-link to="/testLibrary">
                    <span>
                      <a href="javascript:;">
                        <img
                          src=".././assets/img/tianjia.png"
                          style="width: 16px;height: 16px;float: right;margin-right: 20px;position:relative;top: 4px">
                      </a>
                    </span>
                  </router-link>
                </a-col>
              </a-row>
            </span>
          </a-table>
        </a-col>
      </a-row>
    </a-card>
    <a-modal
      v-model="modelVisible"
      width="800px"
    >
      <span slot="title">
        <img src="@/assets/img/tianjiaB.png" style="width: 26px;height: 26px;">
        <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "添加" }}</span>
      </span>
      <div>
        <a-row type="flex" justify="center" style="margin-top: 30px">
          <a-col :span="14">
            <a-input style="width: 280px"></a-input>
            <a-upload action="http://192.168.43.160:8080/jantd-boot/teacher/staff/importExcel?staffType=1" directory>
              <a-button style="margin-left: 20px">
                <a-icon type="upload"/>
                选择
              </a-button>
            </a-upload>
          </a-col>
        </a-row>
        <a-row type="flex" justify="center" style="margin-top:40px;">
          <a-col :span="14">
            <span>
              <span style="color: RGB(12,126,212);font-size: 18px">{{ "所属装备" }}</span>
              <span style="margin-left: 5px;margin-right: 10px;color: RGB(12,126,212);">{{ ":" }}</span>
            </span>
            <span>
              <a-select defaultValue="指挥控制车" style="width: 220px" @change="handleChange">
                <a-select-option value="指挥控制车">指挥控制车</a-select-option>
                <a-select-option value="指挥官">指挥官</a-select-option>
                <a-select-option value="指挥官">指挥控制车</a-select-option>
              </a-select>
            </span>
          </a-col>
        </a-row>
        <a-row type="flex" justify="center" style="margin-top:40px;">
          <a-col :span="14">
            <span>
              <span style="color: RGB(12,126,212);font-size: 18px">{{ "所属岗位" }}</span>
              <span style="margin-left: 5px;margin-right: 10px;color: RGB(12,126,212);">{{ ":" }}</span>
            </span>
            <span>
              <a-select defaultValue="指挥控制车" style="width: 220px" @change="handleChange">
                <a-select-option value="指挥控制车">指挥控制车</a-select-option>
                <a-select-option value="指挥控制车">指挥控制车</a-select-option>
                <a-select-option value="指挥控制车">指挥控制车</a-select-option>
              </a-select>
            </span>
          </a-col>
        </a-row>
        <a-row type="flex" justify="center" style="margin-top:40px;">
          <a-col :span="14">
            <span>
              <span style="color: RGB(12,126,212);font-size: 18px">{{ "题型" }}</span>
              <span style="margin-left: 5px;margin-right: 10px;color: RGB(12,126,212);">{{ ":" }}</span>
            </span>
            <a-radio-group @change="radioChange" v-model="qType" :defaultValue="1">
              <a-row tyle="flex">
                <a-radio value="equip">
                  <span style="font-size: 18px;position: relative;bottom:2px">
                    {{ "单选题" }}
                  </span>
                </a-radio>
                <a-radio value="staff_group">
                  <span style="font-size: 18px;position: relative;bottom:2px">
                    {{ "多选题" }}
                  </span>
                </a-radio>
                <a-radio value="position">
                  <span style="font-size: 18px;position: relative;bottom:2px">
                    {{ "判断题" }}
                  </span>
                </a-radio>
              </a-row>
            </a-radio-group>
          </a-col>
        </a-row>
      </div>
      <div>
        <a-checkbox-group :options="plainOptions" v-model="checkedList" @change="onChange"/>
      </div>
      <span slot="footer">
        <a-row type="flex" justify="center">
          <a-col :span="4">
            <a-button>
              <a-icon type="upload"/>
              确定导入
            </a-button>
          </a-col>
        </a-row>
      </span>
    </a-modal>
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
      userIP: '24243512',
      loginName: '清风',
      userName: '张杰',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '2',
      userIP: '45367924',
      loginName: '飘雪',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '3',
      userIP: '16675675',
      loginName: '李玲玲',
      userName: '李玲玲',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '4',
      userIP: '13424567',
      loginName: '大路啊',
      userName: '王文文',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '5',
      userIP: '22445234',
      loginName: '星星',
      userName: '李晶晶',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '6',
      userIP: '23478975',
      loginName: '爱啥啥',
      userName: '吴瑞',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '7',
      userIP: '45634589',
      loginName: '凌凌漆',
      userName: '王珊珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '8',
      userIP: '76453423',
      loginName: '飓风',
      userName: '李瑞',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '9',
      userIP: '45234675',
      loginName: '叔叔',
      userName: '谢琳',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '10',
      userIP: '21341456',
      loginName: 'ggg',
      userName: '周杰',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    }
  ]


  const facultyEstablishmentData2 = [
    {
      No: '1',
      userIP: '123',
      loginName: '无极',
      userName: '张文',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '2',
      userIP: '123',
      loginName: '丫丫',
      userName: '于文雅',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '3',
      userIP: '123',
      loginName: 'sss',
      userName: '王伟',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '4',
      userIP: '123',
      loginName: '皮皮',
      userName: '张洁洁',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '5',
      userIP: '123',
      loginName: '李瑞',
      userName: '李瑞',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '6',
      userIP: '123',
      loginName: '姐姐',
      userName: '张亚亚',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '7',
      userIP: '456',
      loginName: '风暴',
      userName: '王星星',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '8',
      userIP: '8888',
      loginName: '雪花',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '9',
      userIP: '999999999',
      loginName: '冬天的瓜',
      userName: '李蕊蕊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '10',
      userIP: '00000888',
      loginName: '沙哈',
      userName: '张伟',
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
        facultyEstablishmentData,
        facultyEstablishmentData2,
        tabsVisible1: true,
        tabsVisible2: false,
        modelVisible: false,
        qType: ''
      }
    },
    methods: {
      tabsChang(key) {
        if (key === '1') {
          this.tabsVisible1 = true
          this.tabsVisible2 = false
        } else if (key === '2') {
          this.tabsVisible2 = true
          this.tabsVisible1 = false
        }

      },
      importData() {
        this.modelVisible = true
      }
    }
  }
</script>

<style scoped>

</style>