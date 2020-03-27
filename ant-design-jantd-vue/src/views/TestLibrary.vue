<template>
  <div id="testLibrary">
    <a-card style="background-color: RGB(10,19,49);height:958px">
      <!--      <a-row style="padding: 30px 0 10px 0">-->
      <!--        <a-tabs>-->
      <!--          <a-tab-pane tab="指挥控制车复习题" key="1"/>-->
      <!--          &lt;!&ndash;<a-tab-pane tab="下发试卷" key="2"/>&ndash;&gt;-->
      <!--        </a-tabs>-->
      <!--      </a-row>-->
      <a-row type="flex" style="margin-top: 20px;margin-bottom: 30px" justify="center" align="center">
        <a-col :span="24">
          <a-table :dataSource="testLibraryData.records" :pagination="false" :columns="facultyEstablishmentColumns">
            <span slot="visibleCheck" slot-scope="text,record">
              <a-checkbox
                @change="checkRow(record)"
                :checked="text"
              >
              </a-checkbox>
            </span>
            <span slot="problemTitle" slot-scope="text,record">
              <span :title="text" v-if="text.length >20">
                {{text.substr(0,19)+ '...'}}
              </span>
              <span :title="text" v-else>
                 {{text}}
              </span>
            </span>
            <a slot="operation" slot-scope="text,record" href="javascript:;">
              <span @click="see(record)">
                  <img src=".././assets/img/bianji.png" style="width: 14px;height: 16px;">
              </span>
              <a-popconfirm
                title='确定删除吗？'
                @confirm="deleteRow(record)"
                okText="是"
                cancelText="否"
              >
                <img src=".././assets/img/shanchu.png" style="width: 12px;height: 16px;margin-left: 10px">
              </a-popconfirm>
            </a>
            <span slot="footer">
              <a-row type="flex">
                <a-col :span="2" style="margin-left: 30px">
                  <a-checkbox @change="CkAll" :checked="CkA" style="color: #ffffff;">全选</a-checkbox>
                                                  <a-popconfirm
                                                    title='确定删除吗？'
                                                    @confirm="DeleteAll"
                                                    okText="是"
                                                    cancelText="否"
                                                  >
                    <img
                      src=".././assets/img/shanchu.png"
                      style="width: 12px;height: 16px;margin-left: 10px;position:relative;bottom:2px">
                                                  </a-popconfirm>
                </a-col>
                <a-col :span="20">
                  <a-pagination
                    @change="setPage"
                    size="small"
                    style="text-align: center;"
                    :total="testLibraryData.total"
                    v-model="page"
                    :showSizeChanger="false"
                    showQuickJumper
                    :showTotal="total => `共 ${total} 条`"/>
                </a-col>
                <a-col :span="1">
                  <a @click="addShow" href="javascript:;">
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
    <a-modal
      v-model="seeModal"
      width="800px"
    >
      <span slot="title">
        <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">
        <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "题目详情" }}</span>
      </span>
      <div>
        <div style="margin-top: 30px;margin-left: 30px">
          <span style="color: RGB(87,153,248);font-size: 20px">
            {{"题目:"}}
          </span>
          <span style="margin-left: 10px;color: #ffffff;font-size: 20px">
            {{titleDetails.problemTitle}}
          </span>
        </div>
        <div style="margin-top: 30px;margin-left: 30px">
          <span style="color: RGB(87,153,248);font-size: 20px">
            {{"选项:"}}
          </span>
          <span v-for="(item,key) in titleDetails.answer" :key="key"
                style="margin-left: 10px;color: #ffffff;font-size: 20px">
            <span v-if="key === 0">
              <span>
                {{'A、'}}
              </span>
              <span>
                {{item}}
              </span>
            </span>
              <span v-if="key === 1">
              <span>
                {{'B、'}}
              </span>
              <span>
                {{item}}
              </span>
            </span>
              <span v-if="key === 2">
              <span>
                {{'C、'}}
              </span>
              <span>
                {{item}}
              </span>
            </span>
            <span v-if="key === 3">
              <span>
                {{'D、'}}
              </span>
              <span>
                {{item}}
              </span>
            </span>
          </span>
        </div>
      </div>
      <span slot="footer">
        <a-row type="flex" justify="center">
          <a-col :span="4">
             <span style="color: RGB(87,153,248);font-size: 20px">
               {{'正确答案:'}}
             </span>
            <span style="color: #ffffff;font-size: 22px">
              {{titleDetails.isRight}}
            </span>
          </a-col>
        </a-row>
      </span>
    </a-modal>
    <a-modal
      v-model="addModal"
      width="800px"
    >
      <span slot="title">
        <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">
        <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "添加" }}</span>
      </span>
        <a-row type="flex" justify="center" style="margin-top: 30px">
          <a-col :span="14">
            <a-input style="width: 280px"></a-input>
            <a-upload
              action="http://192.168.5.253:8080/jantd-boot/teacher/problem/importExcel"
              accept=".xlsx"
              @change="handleImportExcel">
              <a-button style="margin-left: 20px">
                <a-icon type="upload"/>
                选择
              </a-button>
            </a-upload>
          </a-col>
        </a-row>
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
      dataIndex: 'visibleCheck',
      scopedSlots: {customRender: 'visibleCheck'},
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
      dataIndex: 'problemTitle',
      scopedSlots: {customRender: 'problemTitle'},
      align: 'center'
    },
    {
      title: '所属装备',
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
  import {getInitData, deleteRow, deleteAll, seeRightkey} from '@/api/testLibrary'

  export default {
    name: "testLibrary",
    data() {
      return {
        facultyEstablishmentColumns,
        page: 1,
        testLibraryData: [],
        visibleCheck: false,
        CkA: false,
        seeModal: false,
        examinnation: '',
        titleDetails: '',
        addModal: false
      }
    },
    mounted() {
      this.initData()
    },
    methods: {
      initData() {
        let that = this
        getInitData({
          pageNo: that.page
        }).then(function (res) {
          console.log(res)
          that.testLibraryData = res.result
          let i = 1
          that.testLibraryData.records.map(item => {
            item.No = i
            i++
          })
        }).catch(function (err) {
          console.log(err)
        })
      },
      deleteRow(row) {
        let that = this
        deleteRow({
          id: row.id
        }).then(function (res) {
          console.log(res)
          that.$message.success("删除成功")
          that.initData()
        }).catch(function (err) {
          console.log(err)
          that.$message.error("删除失败")
        })
      },
      setPage(page) {
        console.log(page)
        this.page = page
        this.initData()
      },
      checkRow(row) {
        if (row.visibleCheck === false) {
          this.testLibraryData.records.map(item => {
            if (item.id === row.id) {
              item.visibleCheck = true
            }
          })
        } else if (row.visibleCheck === true) {
          this.testLibraryData.records.map(item => {
            if (item.id === row.id) {
              item.visibleCheck = false
            }
          })
        }
      },
      CkAll() {
        let that = this
        if (this.CkA === false) {
          that.CkA = true
          that.testLibraryData.records.map(function (item) {
            item.visibleCheck = true
          })
        } else if (this.CkA === true) {
          that.CkA = false
          that.testLibraryData.records.map(function (item) {
            item.visibleCheck = false
          })
        }
      },
      DeleteAll() {
        let that = this
        let ids = []
        this.testLibraryData.records.map(item => {
          if (item.visibleCheck === true) {
            ids.push(item.id)
          }
        })
        if (ids.length === 0) {
          this.$message.error("请选择删除项")
          return
        }
        deleteAll({
          ids: ids.toString()
        }).then(function (res) {
          that.$message.success("删除成功");
          that.page = 1
          that.CkA = false
          that.initData()
          console.log(res)
        }).catch(function (err) {
          that.$message.success("删除失败");
          console.log(err)
        })
      },
      see(row) {
        let that = this
        seeRightkey({
          id: row.id
        }).then(function (res) {
          that.titleDetails = res.result
          console.log(res)
        }).catch(function (err) {
          console.log(err)
        })
        this.seeModal = true
      },
      addShow(){
        this.addModal = true
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
      }
    }

  }
</script>

<style scoped>

</style>