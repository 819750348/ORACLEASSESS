<template>
  <div id="examinationPaperSettings">
    <a-card style="background-color: RGB(10,19,49);height:958px">
      <a-row style="padding: 30px 0 10px 0">
        <a-tabs @tabClick="tabsChang">
          <a-tab-pane tab="考试设置" key="1"/>
          <a-tab-pane tab="下发试卷" key="2"/>
        </a-tabs>
      </a-row>
      <div v-if="tabsVisible1 ===true " class="examination">
        <a-row type="flex" style="margin-top: 20px;margin-bottom: 30px" justify="center" align="center">
          <a-col :span="24">
            <span style="color: RGB(2,144,215);font-size: 24px">
              {{ "指定所属装备" }}
            </span>
            <span style="color: RGB(2,144,215);margin: 0 10px 0 10px;font-size: 24px">
              {{ ":" }}
            </span>
            <span style="margin-left: 10px">
              <a-select
                mode="multiple"
                style="width: 210px"
                placeholder="全部"
                maxTagCount="0"
                notFoundContent="空"
                maxTagPlaceholder="全部"
              >
                <a-select-option
                  v-for="(item, i) in equipment"
                  :key="item + i"
                >
                  <a-checkbox @change="changeSelect(i)" :checked="item.visible">
                    {{ item.name }}
                  </a-checkbox>
                </a-select-option>
              </a-select>
            </span>
            <span style="margin-left: 10px">
              <span v-for="(item , i) in tabsEquipment" :key="item + 1">
                <a-tag closable v-model="item.visible" @close="closeTags(i)">
                  {{ item.name }}
                </a-tag>
              </span>
            </span>
          </a-col>
        </a-row>
        <a-row type="flex" style="margin-top: 20px;margin-bottom: 30px" justify="center" align="center">
          <a-col :span="24">
            <span style="color: RGB(2,144,215);font-size: 24px;margin-left: 48px">
              {{ "指定岗位" }}
            </span>
            <span style="color: RGB(2,144,215);margin: 0 10px 0 10px;font-size: 24px">
              {{ ":" }}
            </span>
            <span style="margin-left: 10px">
              <a-select
                mode="multiple"
                style="width: 210px"
                placeholder="全部"
                maxTagCount="0"
                notFoundContent="空"
                maxTagPlaceholder="全部"
              >
                <a-select-option
                  v-for="(item, i) in stations"
                  :key="item + i"
                >
                  <a-checkbox @change="changeStation(i)" :checked="item.visible">
                    {{ item.name }}
                  </a-checkbox>
                </a-select-option>
              </a-select>
            </span>
            <span style="margin-left: 10px">
              <span v-for="(item , i) in tabsStation" :key="item + i">
                <a-tag closable v-model="item.visible" @close="closeTagsStation(i)">
                  {{ item.name }}
                </a-tag>
              </span>
            </span>
          </a-col>
        </a-row>
        <!--<a-row type="flex" style="margin-top: 20px;margin-bottom: 30px" justify="center" align="center">-->
        <!--<a-col :span="24">-->
        <!--<span style="color: RGB(2,144,215);font-size: 24px;margin-left: 48px">-->
        <!--{{ "考试时间" }}-->
        <!--</span>-->
        <!--<span style="color: RGB(2,144,215);margin: 0 10px 0 10px;font-size: 24px">-->
        <!--{{ ":" }}-->
        <!--</span>-->
        <!--<span style="margin-left: 10px" class="write">-->
        <!--<a-input style="width: 210px" placeholder=""/>-->
        <!--<span style="font-size: 14px;color: white">-->
        <!--{{ "分钟" }}-->
        <!--</span>-->
        <!--</span>-->
        <!--<span style="color: RGB(2,144,215);font-size: 24px;margin-left: 48px">-->
        <!--{{ "本卷总分" }}-->
        <!--</span>-->
        <!--<span style="color: RGB(2,144,215);margin: 0 10px 0 10px;font-size: 24px">-->
        <!--{{ ":" }}-->
        <!--</span>-->
        <!--<span style="margin-left: 10px">-->
        <!--<a-input style="width: 210px" class="read" disabled placeholder="100"/>-->
        <!--<span style="font-size: 14px;color: white">-->
        <!--{{ "分" }}-->
        <!--</span>-->
        <!--</span>-->
        <!--</a-col>-->
        <!--</a-row>-->
        <a-row type="flex" style="margin-top: 20px;margin-bottom: 30px">
          <a-col :span="3">
            <span style="color: RGB(2,144,215);font-size: 24px;margin-left: 48px">
              {{ "题型占比" }}
            </span>
            <span style="color: RGB(2,144,215);margin: 0 10px 0 10px;font-size: 24px">
              {{ ":" }}
            </span>
          </a-col>
          <a-col :span="19">
            <a-table :columns="columns" bordered :dataSource="tableData" :pagination="false">
              <template slot="title">
                <a style="color: white;margin-left: 58px" @click="optional">
                  {{ "自选考题" }}
                </a>
                <a style="color: white;position: relative;margin-left:150px;">
                  {{ "随机考题" }}
                </a>
                <div
                  style="position: relative;margin-left:160px;width: 88px;border-bottom:1px solid RGB(19,61,110);-moz-transform: rotate(90deg);-webkit-transform: rotate(90deg);">
                </div>
              </template>
              <span slot="single" slot-scope="text,record">
                <a-select
                  style="width: 120px"
                  defaultValue="10"
                >
                  <a-select-option value="10">10%</a-select-option>
                  <a-select-option value="20">20%</a-select-option>
                  <a-select-option value="30">30%</a-select-option>
                  <a-select-option value="40">40%</a-select-option>
                  <a-select-option value="50">50%</a-select-option>
                  <a-select-option value="60">60%</a-select-option>
                  <a-select-option value="70">70%</a-select-option>
                  <a-select-option value="80">80%</a-select-option>
                  <a-select-option value="90">90%</a-select-option>
                  <a-select-option value="100">100%</a-select-option>
                </a-select>
              </span>
              <span slot="multiple" slot-scope="text,record">
                <a-select
                  style="width: 120px"
                  defaultValue="10"
                >
                  <a-select-option value="10">10%</a-select-option>
                  <a-select-option value="20">20%</a-select-option>
                  <a-select-option value="30">30%</a-select-option>
                  <a-select-option value="40">40%</a-select-option>
                  <a-select-option value="50">50%</a-select-option>
                  <a-select-option value="60">60%</a-select-option>
                  <a-select-option value="70">70%</a-select-option>
                  <a-select-option value="80">80%</a-select-option>
                  <a-select-option value="90">90%</a-select-option>
                  <a-select-option value="100">100%</a-select-option>
                </a-select>
              </span>
              <span slot="judge" slot-scope="text,record">
                <a-select
                  style="width: 120px"
                  defaultValue="10"
                >
                  <a-select-option value="10">10%</a-select-option>
                  <a-select-option value="20">20%</a-select-option>
                  <a-select-option value="30">30%</a-select-option>
                  <a-select-option value="40">40%</a-select-option>
                  <a-select-option value="50">50%</a-select-option>
                  <a-select-option value="60">60%</a-select-option>
                  <a-select-option value="70">70%</a-select-option>
                  <a-select-option value="80">80%</a-select-option>
                  <a-select-option value="90">90%</a-select-option>
                  <a-select-option value="100">100%</a-select-option>
                </a-select>
              </span>
              <span slot="operation" slot-scope="text,record">
                <a-select
                  style="width: 120px"
                  defaultValue="10"
                >
                  <a-select-option value="10">10%</a-select-option>
                  <a-select-option value="20">20%</a-select-option>
                  <a-select-option value="30">30%</a-select-option>
                  <a-select-option value="40">40%</a-select-option>
                  <a-select-option value="50">50%</a-select-option>
                  <a-select-option value="60">60%</a-select-option>
                  <a-select-option value="70">70%</a-select-option>
                  <a-select-option value="80">80%</a-select-option>
                  <a-select-option value="90">90%</a-select-option>
                  <a-select-option value="100">100%</a-select-option>
                </a-select>
              </span>
            </a-table>
          </a-col>
        </a-row>
        <a-row type="flex" justify="center" style="margin-top: 100px;margin-bottom: 50px">
          <a-col>
            <img
              src="@/assets/img/shengchengshijuan.png"
              style="width: 140px;height: 50px;margin-top: -2px"
            >
          </a-col>
        </a-row>
      </div>
      <div v-if="tabsVisible2 ===true " class="lowerHair">
        <a-row
          type="flex"
          style="margin-top: 20px;margin-bottom: 30px"
          justify="center"
          align="center">
          <a-col :span="24">
            <a-table :dataSource="examinationData" :pagination="false" :columns="examinationColumns">
              <span slot="checkBox" slot-scope="text,record">
                <a-checkbox @change="onChange"></a-checkbox>
              </span>
              <span slot="userName" slot-scope="text,record">
                <!--<span style="color: white">{{text}}</span>-->
                <span v-if="text.userName===''">
                  <img src=".././assets/img/zhidingxueyuan.png" style="width: 16px;height: 16px;margin-bottom: 5px">
                </span>
                <span v-if="text.userName !==''">
                  <span style="margin-left: 5px">{{ text.userName }}</span>
                </span>
              </span>
              <span slot="sendOut" slot-scope="text,record">
                <img src=".././assets/img/fasong.png" style="width: 16px;height: 16px;margin-bottom: 5px">
              </span>
              <span slot="state" slot-scope="text,record">
                <span v-if="text.state=='已完成'">
                  <img src=".././assets/img/yiwancheng.png" style="width: 16px;height: 16px;margin-bottom: 5px">
                  <span style="margin-left: 5px">{{ "已完成" }}</span>
                </span>
                <span v-if="text.state=='未完成'">
                  <img src=".././assets/img/weiwancheng.png" style="width: 16px;height: 16px;margin-bottom: 5px">
                  <span style="margin-left: 5px">{{ "未完成" }}</span>
                </span>
              </span>
              <a slot="operation" href="javascript:;">
                <img src=".././assets/img/bianji.png" style="width: 14px;height: 16px;">
                <img src=".././assets/img/shanchu.png" style="width: 12px;height: 16px;margin-left: 10px">
              </a>
              <span slot="footer">
                <a-row type="flex">
                  <a-col :span="2" style="margin-left: 25px">
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
                      :total="10"
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
      </div>
      <!--自选考题-->
      <a-modal
        v-model="optionalModal"
        width="1200px"
      >
        <span slot="title">
          <a-select defaultValue="全部" style="width: 220px" @change="handleChange">
            <a-select-option value="指挥控制车">指挥控制车</a-select-option>
            <a-select-option value="指挥官">指挥官</a-select-option>
            <a-select-option value="全部">全部</a-select-option>
          </a-select>
          <a-input placeholder="" style="width:220px;margin-left: 20px;position: relative;top: 3px"></a-input>
          <a-button style="margin-left: 20px">{{ "搜索" }}</a-button>
          <!--<a-button style="margin-left: 20px">{{ "选择" }}</a-button>-->
        </span>
        <div>
          <a-table
            :dataSource="examinationD"
            :pagination="false"
            :columns="examinationC"
            :rowSelection="rowSelection">
            <a slot="chakan" href="javascript:;">
              <img src="@/assets/img/bianji.png" style="width: 16px;height: 16px;">
            </a>
            <span slot="footer">
              <a-pagination
                size="small"
                style="text-align: center;"
                :total="5"
                :showSizeChanger="false"
                showQuickJumper
                :showTotal="total => `共 ${total} 条`"/>
            </span>
          </a-table>
        </div>
        <div>
          <a-checkbox-group :options="plainOptions" v-model="checkedList" @change="onChange"/>
        </div>
        <span slot="footer">
          <a-row type="flex" justify="center">
            <a-col :span="4">
              <a-button>
                <a-icon type="upload"/>
                确定
              </a-button>
            </a-col>
          </a-row>
        </span>
      </a-modal>
    </a-card>

  </div>
</template>

<script>
  import './ExaminationPaperSettings.less'
  const rowSelection = {
    onChange: (selectedRowKeys, selectedRows) => {
      console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
    },
    onSelect: (record, selected, selectedRows) => {
      console.log(record, selected, selectedRows);
    },
    onSelectAll: (selected, selectedRows, changeRows) => {
      console.log(selected, selectedRows, changeRows);
    },
  };
  const examinationC = [{
    title: '题库分类',
    dataIndex: 'tikufenlei',
    scopedSlots: {customRender: 'tikufenlei'},
    align: 'center'
  }, {
    title: '题目',
    dataIndex: 'timu',
    scopedSlots: {customRender: 'timu'},
    align: 'center'
  },
    {
      title: '题型',
      dataIndex: 'tixing',
      scopedSlots: {customRender: 'tixing'},
      align: 'center'
    },
    {
      title: '查看',
      scopedSlots: {customRender: 'chakan'},
      align: 'center'
    }
  ]
  const examinationD = [
    {
      tikufenlei: '指控控制车',
      timu: '多目标操作流程',
      tixing: '选择题',
    },
    {
      tikufenlei: '指挥车作战使命',
      timu: '多目标操作流程',
      tixing: '选择题',
    },
    {
      tikufenlei: '空勤处理操作手',
      timu: '多目标操作流程',
      tixing: '选择题',
    }, {
      tikufenlei: '指挥车系统概述',
      timu: '多目标操作流程',
      tixing: '选择题',
    }, {
      tikufenlei: '指挥车战机指标',
      timu: '多目标操作流程',
      tixing: '选择题',
    }
  ]

  const columns = [
    {
      title: '',
      dataIndex: 'none',
      align: 'center'
    },
    {
      title: '单选',
      scopedSlots: {customRender: 'single'},
      align: 'center'

    },
    {
      title: '多选',
      scopedSlots: {customRender: 'multiple'},
      align: 'center'

    },
    {
      title: '判断',
      scopedSlots: {customRender: 'judge'},
      align: 'center'

    },
    {
      title: '操作',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ];

  const tableData = [
    {
      none: '比例设置'
    }
  ];

  //下发试卷
  const examinationData = [
    {
      No: '1',
      title: '武器系统和导弹概述试卷',
      totalScore: '100',
      userName: '梅文文，张杰，吴风',
      sendOut: '',
      updateTime: '2019-11-12 11:10:12',
      state: '已完成'
    },
    {
      No: '2',
      title: '武器系统工作状态试卷',
      totalScore: '100',
      userName: '',
      sendOut: '',
      updateTime: '2019-11-12 14:14:12',
      state: '已完成'
    }, {
      No: '3',
      title: '指挥车作战使命试卷',
      totalScore: '100',
      userName: '张军，王岚',
      sendOut: '',
      updateTime: '2019-11-12 15:15:12',
      state: '已完成'
    }
    , {
      No: '4',
      title: '武器系统基本组成试卷',
      totalScore: '100',
      userName: '',
      sendOut: '',
      updateTime: '2019-11-12 08:12:12',
      state: '已完成'
    }, {
      No: '5',
      title: '武器系统作战试卷',
      totalScore: '100',
      userName: '',
      sendOut: '',
      updateTime: '2019-11-12 09:13:12',
      state: '未完成'
    }, {
      No: '6',
      title: '空勤处理操作试卷',
      totalScore: '100',
      userName: '',
      sendOut: '',
      updateTime: '2019-11-12 12:12:12',
      state: '已完成'
    }, {
      No: '7',
      title: '电子雷达专业试卷',
      totalScore: '100',
      userName: '',
      sendOut: '',
      updateTime: '2019-11-12 12:12:12',
      state: '已完成'
    }, {
      No: '8',
      title: '机电专业试卷',
      totalScore: '100',
      userName: '徐军，李璐',
      sendOut: '张三',
      updateTime: '2019-11-12 11:13:12',
      state: '已完成'
    }, {
      No: '9',
      title: '指挥车控制操作试卷',
      totalScore: '100',
      userName: '赵玲，李瑞',
      sendOut: '',
      updateTime: '2019-11-12 10:12:10',
      state: '已完成',
      operation: '操作'
    }, {
      No: '10',
      title: '武器系统工作原理试卷',
      totalScore: '100',
      userName: '王杰，李冰冰',
      sendOut: '张三',
      updateTime: '2019-11-12 12:12:12',
      state: '已完成'
    }
  ]

  const examinationColumns = [
    {
      title: '',
      dataIndex: 'checkBox',
      scopedSlots: {customRender: 'checkBox'},
      align: 'center'
    },
    {
      dataIndex: 'No',
      title: '序号',
      // slots: {title: 'customTitle'},
      // scopedSlots: {customRender: 'name'},
      align: 'center'
    },
    {
      title: '标题',
      dataIndex: 'title',
      align: 'center'
    },
    // {
    //   title: '总分',
    //   dataIndex: 'totalScore',
    //   align: 'center'
    // },
    {
      title: '上传时间',
      dataIndex: 'updateTime',
      align: 'center'
    },
    {
      title: '指定学员',
      scopedSlots: {customRender: 'userName'},
      align: 'center'
    },
    {
      title: '发送',
      scopedSlots: {customRender: 'sendOut'},
      align: 'center'
    },
    {
      title: '状态',
      scopedSlots: {customRender: 'state'},
      align: 'center'
    },
    {
      title: '操作',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ]


  export default {
    name: "ExaminationPaperSettings",
    data() {
      return {
        tableData,
        columns,
        examinationData,
        examinationColumns,
        examinationC,
        examinationD,
        rowSelection,
        tabsVisible1: true,
        tabsVisible2: false,
        tabsEquipment: [],
        optionalModal: false,
        equipment: [
          {
            name: '指挥控制操作手',
            visible: false
          },
          {
            name: '作战拦截操作手',
            visible: false
          },
          {
            name: '空情处理操作手',
            visible: false
          }
        ],
        stations: [
          {
            name: '指挥控制操作手',
            visible: false
          },
          {
            name: '作战拦截操作手',
            visible: false
          },
          {
            name: '空情处理操作手',
            visible: false
          }
        ],
        tabsStation: []
      }
    },
    methods: {
      closeTags(value) {
        this.tabsEquipment[value].visible = false
        console.log(value)
      },
      changeSelect(i) {
        if (this.equipment[i].visible === false) {
          this.equipment[i].visible = true
        } else if (this.equipment[i].visible === true) {
          this.equipment[i].visible = false
        }
        this.tabsEquipment = this.equipment
      },
      closeTagsStation(value) {
        this.tabsStation[value].visible = false
        console.log(value)
      },
      changeStation(i) {
        if (this.stations[i].visible === false) {
          this.stations[i].visible = true
        } else if (this.stations[i].visible === true) {
          this.stations[i].visible = false
        }
        this.tabsStation = this.stations
      },
      tabsChang(key) {
        if (key === '1') {
          this.tabsVisible1 = true
          this.tabsVisible2 = false
        } else if (key === '2') {
          this.tabsVisible2 = true
          this.tabsVisible1 = false
        }
      },
      optional() {
        this.optionalModal = true
      }
    }
  }
</script>

<style scoped>

</style>