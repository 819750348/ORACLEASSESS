<template>
  <div id="examinationPaperSettings">
    <a-card style="background-color: RGB(10,19,49);height:1058px">
      <a-row style="padding: 30px 0 10px 0">
        <a-tabs @tabClick="tabsChang">
          <a-tab-pane tab="人员配置管理" key="1"/>
          <a-tab-pane tab="学习和训练项目管理" key="2"/>
          <a-tab-pane tab="试题管理" key="3"/>
        </a-tabs>
      </a-row>
      <div v-if="tabsVisible1 ===true " class="examination">
        <a-row type="flex">
          <a-col :span="5">
            <span style="color: #ffffff;font-size: 20px">
              {{"所属装备:"}}
            </span>
            <span>
                <a-select style="width: 220px" placeholder="全部" @change="">
                    <a-select-option value="...">
                      ...
                    </a-select-option>
                    <a-select-option value="武器系统">
                      武器系统
                    </a-select-option>
                    <a-select-option value="指控车">
                      指控车
                    </a-select-option>
                    <a-select-option value="发射车">
                      发射车
                    </a-select-option>
                    <a-select-option value="雷达车">
                      雷达车
                    </a-select-option>
                </a-select>
            </span>
          </a-col>
          <a-col :span="1"></a-col>
          <a-col :span="5">
            <span style="color: #ffffff;font-size: 20px">
              {{"岗位:"}}
            </span>
            <span>
                <a-select style="width: 220px" placeholder="全部" @change="">
                    <a-select-option value="全部">
                      全部
                    </a-select-option>
                    <a-select-option value="总体">
                        总体
                    </a-select-option>
                    <a-select-option value="指挥">
                        指挥
                      </a-select-option>
                    <a-select-option value="操作">
                      操作
                    </a-select-option>
                    <a-select-option value="维修">
                        维修
                    </a-select-option>
                </a-select>
            </span>
          </a-col>
        </a-row>
        <a-row
          type="flex"
          style="margin-top: 20px;margin-bottom: 30px"
          justify="center"
          align="center">
          <a-col :span="24">
            <a-table :dataSource="staffingManagementData" :pagination="false" :columns="staffingManagementColumns">
              <template slot="system" slot-scope="text,record">
                <span>
                  <span v-if="personnelSet">
                      <a-select style="width: 220px" v-model="text" @change="">
                        <a-select-option value="武器系统">
                          武器系统
                        </a-select-option>
                        <a-select-option value="指控车">
                          指控车
                        </a-select-option>
                        <a-select-option value="发射车">
                          发射车
                        </a-select-option>
                        <a-select-option value="雷达车">
                          雷达车
                        </a-select-option>
                      </a-select>
                  </span>
                  <sapn v-else>
                      {{text}}
                  </sapn>
                </span>
              </template>
              <template slot="operation">
                <span v-if="personnelSet">
                  <span style="font-size: 18px;color: #0ca5ec;cursor: pointer" >
                      {{"确定"}}
                    </span>
                  <span style="font-size: 18px;color: #ff0000;cursor: pointer;margin-left: 20px"  @click="personnelSettings">
                      {{"取消"}}
                    </span>
                </span>
                <span v-else>
                  <span style="font-size: 18px;color: #0ca5ec;cursor: pointer" @click="personnelSettings">{{"设置"}}</span>
                </span>
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


      <!--      指挥训练学习-->
      <div v-if="tabsVisible2 ===true " class="lowerHair">
        <a-row
          type="flex"
          style="margin-top: 20px;margin-bottom: 30px"
          justify="center"
          align="center">
          <a-col :span="24">
            <a-table :dataSource="learningManagementData" :pagination="false" :columns="learningManagementColumns">
              <template slot="station" slot-scope="text,record">
                <span>
                  <span v-if="learningManagementSet">
                    <a-select style="width: 220px" @change="" v-model="text">
                      <a-select-option value="总体">
                          <a-checkbox @change="" slot="suffixIcon">
                          总体
                          </a-checkbox>
                      </a-select-option>
                      <a-select-option value="指挥">
                        <a-checkbox @change="" slot="suffixIcon">
                          指挥
                        </a-checkbox>
                        </a-select-option>
                      <a-select-option value="操作">
                        <a-checkbox @change="">
                          操作
                        </a-checkbox>
                      </a-select-option>
                      <a-select-option value="维修">
                        <a-checkbox @change="">
                          维修
                        </a-checkbox>
                      </a-select-option>
                  </a-select>
                  </span>
                  <span v-else>
                    {{text}}
                  </span>
                </span>
              </template>
              <template slot="operation" slot-scope="text,record">
                <sapn v-if="learningManagementSet">
                  <span style="font-size: 18px;color: #0ca5ec;cursor: pointer" >
                      {{"确定"}}
                    </span>
                  <span style="font-size: 18px;color: #ff0000;cursor: pointer;margin-left: 20px"  @click="learningManagementSetUp">
                      {{"取消"}}
                    </span>
                </sapn>
                <span v-else>
                  <span style="font-size: 18px;color: #0ca5ec;cursor: pointer" @click="learningManagementSetUp">
                      {{"设置"}}
                    </span>
                      <span style="font-size: 18px;color: #ff0000;cursor: pointer;margin-left: 20px">
                      {{"删除"}}
                    </span>
                </span>

              </template>
              <span slot="footer">
                <a-row type="flex">
                  <a-col :span="20" offset="2">
                    <a-pagination
                      size="small"
                      style="text-align: center;"
                      :total="10"
                      :showSizeChanger="false"
                      showQuickJumper
                      :showTotal="total => `共 ${total} 条`"/>
                  </a-col>
                </a-row>
              </span>
            </a-table>
          </a-col>
        </a-row>
      </div>



      <div v-if="tabsVisible3 ===true " class="examination">
        <a-row type="flex">
            <a-col :span="5">
            <span style="color: #ffffff;font-size: 20px">
              {{"所属装备:"}}
            </span>
              <span>
                <a-select style="width: 220px" placeholder="全部" @change="">
                    <a-select-option value="...">
                      ...
                    </a-select-option>
                    <a-select-option value="武器系统">
                      武器系统
                    </a-select-option>
                    <a-select-option value="指控车">
                      指控车
                    </a-select-option>
                    <a-select-option value="发射车">
                      发射车
                    </a-select-option>
                    <a-select-option value="雷达车">
                      雷达车
                    </a-select-option>
                </a-select>
            </span>
            </a-col>

          <a-col :span="1"></a-col>
          <a-col :span="5">
            <span style="color: #ffffff;font-size: 20px">
              {{"岗位:"}}
            </span>
            <span>
                <a-select style="width: 220px" placeholder="全部" @change="">
                    <a-select-option value="全部">
                      全部
                    </a-select-option>
                    <a-select-option value="总体">
                        总体
                    </a-select-option>
                    <a-select-option value="指挥">
                        指挥
                      </a-select-option>
                    <a-select-option value="操作">
                      操作
                    </a-select-option>
                    <a-select-option value="维修">
                        维修
                    </a-select-option>
                </a-select>
            </span>
          </a-col>



          <a-col :span="3">
            <span>
                          <a-upload
                            action="http://192.168.5.253:8080/jantd-boot/teacher/problem/importExcel"
                            accept=".xlsx"
                            @change="handleImportExcel">
                                <a-button @click="batchImport">
                                  {{'批量导入'}}
                                </a-button>
                          </a-upload>
            </span>
          </a-col>
          <a-col :span="2">
            <span>
                <a-button>
                  {{"删除"}}
                </a-button>
            </span>
          </a-col>
        </a-row>
        <a-row
          type="flex"
          style="margin-top: 20px;margin-bottom: 30px"
          justify="center"
          align="center">
          <a-col :span="24">
            <a-table :dataSource="testManagementData" :pagination="false" :columns="testManagementColumns"
                     :rowSelection="rowSelection">
              <template slot="station" slot-scope="text,record">
                <span>
                  <span v-if="testManagementSet">
                      <a-select style="width: 220px" v-model="text" @change="">
                          <a-select-option value="总体">
                              总体
                          </a-select-option>
                          <a-select-option value="指挥">
                              指挥
                          </a-select-option>
                          <a-select-option value="操作">
                              操作
                          </a-select-option>
                          <a-select-option value="维修">
                              维修
                          </a-select-option>
                      </a-select>
                  </span>
                  <span v-else>
                    {{text}}
                  </span>
                </span>
              </template>
              <template slot="operation">

                <sapn v-if="testManagementSet">
                  <span style="font-size: 18px;color: #0ca5ec;cursor: pointer" >
                      {{"确定"}}
                    </span>
                  <span style="font-size: 18px;color: #ff0000;cursor: pointer;margin-left: 20px"  @click="testManagementSettings">
                      {{"取消"}}
                    </span>
                </sapn>
                <span v-else>
                  <span style="font-size: 18px;color: #0ca5ec;cursor: pointer" @click="testManagementSettings">
                      {{"设置"}}
                    </span>
                      <span style="font-size: 18px;color: #ff0000;cursor: pointer;margin-left: 20px">
                      {{"删除"}}
                    </span>
                </span>
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
  const testManagementColumns = [
    {
      title: "序号",
      dataIndex: 'NO',
      align: 'center'
    },
    {
      title: "试题描述",
      dataIndex: 'description',
      align: 'center'
    },
    {
      title: '试题类型',
      dataIndex: 'type',
      align: 'center'
    },
    {
      title: '所属系统',
      dataIndex: 'system',
      align: 'center'
    },
    {
      title: '适用岗位',
      dataIndex: 'station',
      scopedSlots: {customRender: 'station'},
      align: 'center'
    },
    {
      title: '题型',
      dataIndex: 'questionType',
      align: 'center'
    },
    {
      title: '答案',
      dataIndex: 'answer',
      align: 'center'
    },
    {
      title: '操作',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ];

  // const rowSelection = {
  //   onChange: (selectedRowKeys, selectedRows) => {
  //     console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
  //   },
  //   onSelect: (record, selected, selectedRows) => {
  //     console.log(record, selected, selectedRows);
  //   },
  //   onSelectAll: (selected, selectedRows, changeRows) => {
  //     console.log(selected, selectedRows, changeRows);
  //   },
  // };


  const testManagementData = [
    {
      NO: 1,
      description: 'xxx',
      type: '武器系统学习 ',
      system: '武器系统',
      station: '指挥',
      questionType: '单选',
      answer: 'B'
    },
    {
      NO: 2,
      description: 'xxx',
      type: '指挥车维修训练学习',
      system: '指挥车',
      station: '总体',
      questionType: '单选',
      answer: 'A'
    },
    {
      NO: 3,
      description: 'xxx',
      type: '雷达车维修训练学习',
      system: '雷达车',
      station: '发射车',
      questionType: '单选',
      answer: 'C'
    },
    {
      NO: 4,
      description: 'xxx',
      type: '发射车维修训练学习',
      system: '发射车',
      station: '总体',
      questionType: '单选',
      answer: 'A'
    },
    {
      NO: 5,
      description: 'xxx',
      type: '指挥车理论试题',
      system: '指挥车',
      station: '操作',
      questionType: '单选',
      answer: 'D'
    },
    {
      NO: 6,
      description: 'xxx',
      type: '雷达车理论试题',
      system: '雷达车',
      station: '总体',
      questionType: '单选',
      answer: 'A'
    },
    {
      NO: 7,
      description: 'xxx',
      type: '发射车理论试题',
      system: '发射车',
      station: '维修',
      questionType: '多选',
      answer: 'B、D'
    },
    {
      NO: 8,
      description: 'xxx',
      type: '雷达车维修训练学习',
      system: '雷达车',
      station: '操作',
      questionType: '单选',
      answer: 'D'
    },
    {
      NO: 9,
      description: 'xxx',
      type: '指挥车理论学习',
      system: '指挥车',
      station: '指挥',
      questionType: '单选',
      answer: 'A'
    },
    {
      NO: 10,
      description: 'xxx',
      type: ' 发射车操作训练学习',
      system: '发射车',
      station: '指挥',
      questionType: '多选',
      answer: 'A、B'
    }
  ]
  const learningManagementColumns = [
    {
      title: "序号",
      dataIndex: 'NO',
      align: 'center'
    },
    {
      title: '学习与培训项目',
      dataIndex: 'learningAndTrainingPrograms',
      align: 'center'
    },
    {
      title: '所属装备',
      dataIndex: 'system',
      align: 'center'

    },
    {
      title: '学习类型',
      dataIndex: 'typesOfLearning',
      align: 'center'

    },
    {
      title: '适用岗位',
      dataIndex: 'station',
      scopedSlots: {customRender: 'station'},
      align: 'center'
    },
    {
      title: '学时',
      dataIndex: 'classHour',
      align: 'center'
    },
    {
      title: '操作',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ];

  const learningManagementData = [
    {
      NO: '1',
      learningAndTrainingPrograms: '武器系统和导弹概述',
      system: '指挥车',
      typesOfLearning: '指挥车操作训练学习',
      station: ' 指挥、操作',
      classHour: '2'
    },
    {
      NO: '2',
      learningAndTrainingPrograms: '武器系统和导弹概述',
      system: '武器系统',
      typesOfLearning: '发射车维修训练学习',
      station: ' 操作、维修',
      classHour: '2'
    },
    {
      NO: '3',
      learningAndTrainingPrograms: '作战拦截操作手',
      system: '雷达车',
      typesOfLearning: '指挥车操作训练学习',
      station: ' 操作',
      classHour: '2'
    },
    {
      NO: '4',
      learningAndTrainingPrograms: '指挥车工作过程和工作原理',
      system: '武器系统',
      typesOfLearning: '雷达车理论学习',
      station: ' 维修',
      classHour: '2'
    },
    {
      NO: '5',
      learningAndTrainingPrograms: '机电专业',
      system: '雷达车',
      typesOfLearning: '指挥车操作训练学习',
      station: ' 指挥、维修',
      classHour: '2'
    },
    {
      NO: '6',
      learningAndTrainingPrograms: '空勤处理操作手',
      system: '发射车',
      typesOfLearning: '雷达车理论学习',
      station: ' 操作',
      classHour: '2'
    },
    {
      NO: '7',
      learningAndTrainingPrograms: '指挥车工作过程和工作原理',
      system: '武器系统',
      typesOfLearning: '理论学习',
      station: ' 维修',
      classHour: '2'
    },
    {
      NO: '8',
      learningAndTrainingPrograms: '指挥车战机指标',
      system: '指挥车',
      typesOfLearning: '指挥车操作训练学习',
      station: ' 指挥',
      classHour: '2'
    },
    {
      NO: '9',
      learningAndTrainingPrograms: '武器系统工作原理、作战方式',
      system: '武器系统',
      typesOfLearning: '武器系统学习',
      station: ' 操作、维修',
      classHour: '2'
    },
    {
      NO: '10',
      learningAndTrainingPrograms: '作战拦截操作手',
      system: '武器系统',
      typesOfLearning: '指挥车操作训练学习',
      station: ' 维修',
      classHour: '2'
    }
  ];

  //人员配置管理
  const staffingManagementData = [
    {
      No: '1',
      name: '王杰',
      system: ' 雷达车',
      station: '总体'
    },
    {
      No: '2',
      name: '李兰',
      system: ' 雷达车',
      station: '维修'
    }, {
      No: '3',
      name: '谢风',
      system: '指挥车',
      station: '维修'
    }
    , {
      No: '4',
      name: '王瑞',
      system: '武器系统',
      station: '指挥'
    }, {
      No: '5',
      name: '刘解',
      system: '指挥车',
      station: '操作'
    }, {
      No: '6',
      name: '赵玲',
      system: '武器系统',
      station: '操作'
    }, {
      No: '7',
      name: '张站',
      system: '雷达车',
      station: '总体'
    }, {
      No: '8',
      name: '谢玲玲',
      system: '武器系统',
      station: '维修'
    }, {
      No: '9',
      name: '李杰',
      system: '发射车',
      station: '总体'
    }, {
      No: '10',
      name: '李禅茶',
      system: '武器系统',
      station: '总体'
    }
  ]

  const staffingManagementColumns = [
    {
      title: '姓名',
      dataIndex: 'name',
      align: 'center'
    },
    {
      title: '所属装备',
      dataIndex: 'system',
      scopedSlots: {customRender: 'system'},
      align: 'center'
    },
    {
      title: '岗位',
      dataIndex: 'station',
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
        /**
         * @Author:     风中的那朵云
         * @Description:  ${description}
         * @Date:    2020/5/6
         * @Version:    1.0
         */
        learningManagementData,
        learningManagementColumns,

        staffingManagementData,
        staffingManagementColumns,

        testManagementColumns,
        testManagementData,

        LMM: false,


        testManagementSet: false,

        personnelModal: false,
        learningManagementSet: false,
        tabsVisible3: false,
        tabsVisible1: true,
        tabsVisible2: false,

        batchImportModal: false,

        examinationC,
        examinationD,
        rowSelection,


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
        tabsStation: [],
        CV: false,
        equipments: ['xxx系统', 'xxx车', 'xxxx车'],
        /**
         * @Author:     风中的那朵云
         * @Description:  人员设置
         * @Date:    2020/5/6
         * @Version:    1.0
         */
        personnelSet: false
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
      /**
       * @Author:     风中的那朵云
       * @Description:  ${description}
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      tabsChang(key) {
        if (key === '1') {
          this.tabsVisible1 = true
          this.tabsVisible2 = false
          this.tabsVisible3 = false
        } else if (key === '2') {
          this.tabsVisible2 = true
          this.tabsVisible1 = false
          this.tabsVisible3 = false
        } else if (key === '3') {
          this.tabsVisible2 = false
          this.tabsVisible1 = false
          this.tabsVisible3 = true
        }
      },
      /**
       * @Author:     风中的那朵云
       * @Description:  ${description}
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      batchImport() {
        this.batchImportModal = true
      },
      random() {
        this.CV = true
      },
      custom() {
        this.CV = false
        this.optionalModal = true
      },
      //人员配置管理设置
      personnelSettings() {
        if (this.personnelSet === false) {
          this.personnelSet = true
        } else {
          this.personnelSet = false
        }
      },
      //试题管理
      testManagementSettings() {
        if (this.testManagementSet === false) {
          this.testManagementSet = true
        } else {
          this.testManagementSet = false
        }
      },
      /**
       * @Author:     风中的那朵云
       * @Description:  ${description}
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      learningManagementSetUp() {
        if (this.learningManagementSet === false) {
          this.learningManagementSet = true
        } else {
          this.learningManagementSet = false
        }

      },
      addLearningManagement() {
        this.LMM = true
      }
    }
  }
</script>

<style scoped>

</style>