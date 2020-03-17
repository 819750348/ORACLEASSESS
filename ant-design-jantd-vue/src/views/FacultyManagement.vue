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
        v-if="tabsVisible1 ===true">
        <a-col :span="24">
          <!--<a-input v-model="staffList.records[0].visibleCheck"></a-input>-->
          <a-table :dataSource="staffList.records" :pagination="false" :columns="facultyEstablishmentColumns">
            <span slot="checkBox" slot-scope="text,record">
              <a-checkbox
                @change="checkRow(record)"
                :checked="text"
              >
              </a-checkbox>
            </span>
            <a slot="operation" href="javascript:;" slot-scope="text,record">
              <img src=".././assets/img/bianji.png" style="width: 14px;height: 16px;">
              <img
                @click="deleteRow(record)"
                src=".././assets/img/shanchu.png"
                style="width: 12px;height: 16px;margin-left: 10px">
            </a>
            <span slot="footer">
              <a-row type="flex">
                <a-col :span="2" style="margin-left: 16px">
                  <a-checkbox @change="checkAll" style="color: #ffffff;">全选</a-checkbox>
                  <a href="javascript:;">
                    <img
                      src=".././assets/img/shanchu.png"
                      style="width: 12px;height: 16px;margin-left: 10px;position:relative;bottom:2px">
                  </a>
                </a-col>
                <a-col :span="20">
                  <a-pagination
                    @change="setPage"
                    size="small"
                    style="text-align: center;"
                    :total="staffList.total"
                    v-model="page"
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
          <a-table :dataSource="studentList.records" :pagination="false" :columns="facultyEstablishmentColumns">
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
                  <a-checkbox @change="onChange" style="color: #ffffff">全选</a-checkbox>
                  <a href="javascript:;">
                    <img
                      @click="deleteAll"
                      src=".././assets/img/shanchu.png"
                      style="width: 12px;height: 16px;margin-left: 10px;position:relative;bottom:2px">
                  </a>
                </a-col>
                <a-col :span="20">
                  <a-pagination
                    @change="setStudentPage"
                    size="small"
                    style="text-align: center;"
                    :total="studentList.total"
                    :showSizeChanger="false"
                    showQuickJumper
                    v-model="studentPage"
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

    </a-card>
    <a-modal
      v-model="modelVisible"
      width="800px"
    >
      <span slot="title">
        <img src="@/assets/img/tianjiaB.png" style="width: 26px;height: 26px;">
        <span style="font-size: 30px;position: relative;left: 12px;top:5px;">{{ "添加" }}</span>
      </span>
      <div>
        <a-row type="flex" justify="center" style="margin-top: 30px">
          <a-col :span="14">
            <a-input style="width: 280px"></a-input>
            <a-upload
              action="http://192.168.43.160:8080/jantd-boot/teacher/staff/importExcel?staffType=1"
              accept=".xlsx"
              @change="handleImportExcel">
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
    // {
    //   title: '用户IP',
    //   dataIndex: 'userIP',
    //   scopedSlots: {customRender: 'userName'},
    //   align: 'center'
    // },
    {
      title: '登入名称',
      dataIndex: 'name',
      scopedSlots: {customRender: 'name'},
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
      dataIndex: 'createTime',
      scopedSlots: {customRender: 'createTime'},
      align: 'center'
    },
    {
      title: "所属装备",
      dataIndex: 'equipStr',
      scopedSlots: {customRender: 'equipStr'},
      align: 'center'
    },
    {
      title: '岗位',
      dataIndex: 'positionStr',
      scopedSlots: {customRender: 'positionStr'},
      align: 'center'
    },
    {
      title: '人员分组',
      dataIndex: 'staffGroupStr',
      scopedSlots: {customRender: 'staffGroupStr'},
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


  const facultyEstablishmentData2 = [
    {
      No: '1',
      userIP: '123',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '2',
      userIP: '123',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '3',
      userIP: '123',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '4',
      userIP: '123',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '5',
      userIP: '123',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '6',
      userIP: '123',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '7',
      userIP: '456',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '8',
      userIP: '8888',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '9',
      userIP: '999999999',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    },
    {
      No: '10',
      userIP: '00000888',
      loginName: '登入名',
      userName: '张珊',
      creationTime: '2019-12-12 12:12:12',
      equid: '指挥指控车',
      station: '指挥军官',
      personnelGrouping: '指挥指控组'
    }
  ]
  import './FacultyManagement.less'
  import {getStaffList, deleteRow, deleteAll, editRow} from '@/api/facultyManagement'

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
        qType: '',
        page: 1,
        studentPage: 1,
        staffList: {},
        studentList: {},
        checkList: []
      }
    },
    methods: {
      tabsChang(key) {
        if (key === '1') {
          this.tabsVisible1 = true
          this.page = 1
          this.tabsVisible2 = false
          this.initStaffList()
        } else if (key === '2') {
          this.studentPage = 1
          this.tabsVisible2 = true
          this.tabsVisible1 = false
          this.initStudentList()
        }
      },
      importData() {
        this.modelVisible = true
      },
      handleImportExcel(info) {
        if (info.file.status !== 'uploading') {
          console.log(info.file, info.fileList);
        }
        if (info.file.status === 'done') {
          if (info.file.response.success) {
            this.$message.success(`${info.file.name} 文件上传成功`);
          } else {
            this.$message.error(`${info.file.name} ${info.file.response.message}.`);
          }
        } else if (info.file.status === 'error') {
          this.$message.error(`文件上传失败: ${info.file.msg} `);
        }
      },
      initStaffList() {
        let that = this
        getStaffList({
          staffType: 1,
          pageNo: that.page
        }).then(function (res) {
          console.log(res)
          that.staffList = res.result
          that.staffList.isCheckAll = false
          let i = 1
          that.staffList.records.map(item => {
            item.visibleCheck = false
            item.No = i
            i++
          })
        }).catch(function (err) {
          console.log(err)
        })
      },
      initStudentList() {
        let that = this
        getStaffList({
          staffType: 2,
          pageNo: that.studentPage
        }).then(function (res) {
          console.log(res)
          that.studentList = res.result
          let i = 1
          that.studentList.records.map(item => {
            item.No = i
            i++
          })
        }).catch(function (err) {
          console.log(err)
        })
      },
      setPage(page) {
        this.page = page
        this.initStaffList()
      },
      setStudentPage(page) {
        this.studentPage = page
        this.initStudentList()
      },
      deleteRow(row) {
        console.log(row)
        deleteRow({
          id: row.id
        }).then(function (res) {
          console.log(res)
          this.$message.success("上传成功");
        }).catch(function (err) {
          this.$message.error("上传失败")
          console.log(err)
        })
      },
      deleteAll() {
        deleteAll({}).then(function (res) {
          console.log(res)
        }).catch(function (err) {
          console.log(err)
        })
      },
      editRow() {
        editRow({}).then(function (res) {
          console.log(res)
        }).catch(function (err) {
          console.log(err)
        })
      },
      checkRow(row) {
        if (row.visibleCheck === false) {
          this.staffList.records.map(item => {
            if (item.id === row.id) {
              item.visibleCheck = true
            }
          })
        } else if (row.visibleCheck === true) {
          this.staffList.records.map(item => {
            if (item.id === row.id) {
              item.visibleCheck = false
            }
          })
        }
        console.log(this.staffList.records)
        console.log(this.staffList.records)
      },
      checkAll() {
        console.log(this.staffList)
        console.log(this.staffList)
      }
    },
    mounted() {
      this.page = 1
      this.studentPage = 1
      this.initStaffList()
      this.initStudentList()
    }
  }
</script>

<style scoped>

</style>