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
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
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
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
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
              <template slot="operation">
                <span style="font-size: 18px;color: #0ca5ec;cursor: pointer" @click="personnelSettings">{{"设置"}}</span>
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
        <a-modal
          v-model="personnelModal"
          width="800px"
        >
              <span slot="title">
                <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">
                <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "设置" }}</span>
              </span>
          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"所属系统:"}}
                  </span>
            </a-col>
            <a-col :span="4">
               <span>
                <a-select style="width: 220px" placeholder="全部" @change="">
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
                      </a-select-option>
                </a-select>
              </span>
            </a-col>
          </a-row>
          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"岗位:"}}
            </span>
            </a-col>
            <a-col :span="4">
                          <span>
                <a-select style="width: 220px" placeholder="全部" @change="">
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
                      </a-select-option>
                </a-select>
            </span>
            </a-col>


          </a-row>
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
      </div>
      <div v-if="tabsVisible2 ===true " class="lowerHair">
        <a-row
          type="flex"
          style="margin-top: 20px;margin-bottom: 30px"
          justify="center"
          align="center">
          <a-col :span="24">
            <a-table :dataSource="learningManagementData" :pagination="false" :columns="learningManagementColumns">
              <template slot="operation">
                <span style="font-size: 18px;color: #0ca5ec;cursor: pointer" @click="learningManagementSetUp">{{"设置"}}
                </span>
                <span style="font-size: 18px;color: #ff0000;cursor: pointer;margin-left: 20px">
                  {{"删除"}}
                </span>
              </template>
              <span slot="footer">
                <a-row type="flex">
                  <a-col :span="20">
                    <a-pagination
                      size="small"
                      style="text-align: center;"
                      :total="10"
                      :showSizeChanger="false"
                      showQuickJumper
                      :showTotal="total => `共 ${total} 条`"/>
                  </a-col>
                  <a-col :span="2">
                    <span>
                      <img src="@/assets/img/tianjia.png" style="width: 26px;height: 26px;position: relative;bottom: 3px;cursor: pointer"
                           @click="addLearningManagement"/>
                    </span>
                  </a-col>
                </a-row>
              </span>
            </a-table>
          </a-col>
          <a-modal
            v-model="learningManagementModal"
            width="800px"
          >
              <span slot="title">
                <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">
                <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "设置" }}</span>
              </span>
            <a-row type="flex" style="margin-top: 30px" justify="center">
              <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"所属系统:"}}
                  </span>
              </a-col>
              <a-col :span="4">
               <span>
                <a-select style="width: 220px" placeholder="全部" @change="">
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
                      </a-select-option>
                </a-select>
              </span>
              </a-col>
            </a-row>
            <a-row type="flex" style="margin-top: 30px" justify="center">
              <a-col :span="3">
                <span style="color: #ffffff;font-size: 18px">
                  {{"岗位:"}}
                </span>
              </a-col>
              <a-col :span="4">
                <span>
                    <a-select style="width: 220px" placeholder="全部" @change="">
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
                      </a-select-option>
                     </a-select>
                </span>
              </a-col>
            </a-row>
            <a-row type="flex" style="margin-top: 30px" justify="center">
              <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"学习类型:"}}
            </span>
              </a-col>
              <a-col :span="4">
                          <span>
                <a-select style="width: 220px" placeholder="全部" @change="">
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
                      </a-select-option>
                </a-select>
            </span>
              </a-col>
            </a-row>
            <a-row type="flex" style="margin-top: 30px" justify="center">
              <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"学时:"}}
            </span>
              </a-col>
              <a-col :span="4">
                 <span>
                   <a-input style="width: 220px">

                   </a-input>
                 </span>
              </a-col>
            </a-row>


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



          <a-modal
            v-model="LMM"
            width="800px"
          >
              <span slot="title">
                <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">
                <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "添加学习项目" }}</span>
              </span>

            <a-row type="flex" style="margin-top: 30px" justify="center">
              <a-col :span="4">
                <span style="color: #ffffff;font-size: 18px">
                  {{"学习项目名称:"}}
                </span>
              </a-col>
              <a-col :span="4">
                 <span>
                   <a-input style="width: 220px">
                   </a-input>
                 </span>
              </a-col>
            </a-row>

            <a-row type="flex" style="margin-top: 30px" justify="center">
              <a-col :span="4">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"所属系统:"}}
                  </span>
              </a-col>
              <a-col :span="4">
               <span>
                <a-select style="width: 220px" placeholder="全部" @change="">
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
                      </a-select-option>
                </a-select>
              </span>
              </a-col>
            </a-row>
            <a-row type="flex" style="margin-top: 30px" justify="center">
              <a-col :span="4">
                <span style="color: #ffffff;font-size: 18px">
                  {{"岗位:"}}
                </span>
              </a-col>
              <a-col :span="4">
                <span>
                    <a-select style="width: 220px" placeholder="全部" @change="">
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
                      </a-select-option>
                     </a-select>
                </span>
              </a-col>
            </a-row>
            <a-row type="flex" style="margin-top: 30px" justify="center">
              <a-col :span="4">
            <span style="color: #ffffff;font-size: 18px">
              {{"学习类型:"}}
            </span>
              </a-col>
              <a-col :span="4">
                          <span>
                <a-select style="width: 220px" placeholder="全部" @change="">
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
                      </a-select-option>
                </a-select>
            </span>
              </a-col>
            </a-row>
            <a-row type="flex" style="margin-top: 30px" justify="center">
              <a-col :span="4">
            <span style="color: #ffffff;font-size: 18px">
              {{"学时:"}}
            </span>
              </a-col>
              <a-col :span="4">
                 <span>
                   <a-input style="width: 220px">

                   </a-input>
                 </span>
              </a-col>
            </a-row>


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
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
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
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
                      </a-select-option>
                </a-select>
            </span>
          </a-col>
          <a-col :span="5">
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
        </a-row>
        <a-row
          type="flex"
          style="margin-top: 20px;margin-bottom: 30px"
          justify="center"
          align="center">
          <a-col :span="24">
            <a-table :dataSource="testManagementData" :pagination="false" :columns="testManagementColumns">
              <template slot="operation">
                <span style="font-size: 18px;color: #0ca5ec;cursor: pointer" @click="personnelSettings">{{"设置"}}</span>
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
        <a-modal
          v-model="personnelModal"
          width="800px"
        >
              <span slot="title">
                <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">
                <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "设置" }}</span>
              </span>
          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"所属系统:"}}
                  </span>
            </a-col>
            <a-col :span="4">
               <span>
                <a-select style="width: 220px" placeholder="全部" @change="">
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
                      </a-select-option>
                </a-select>
              </span>
            </a-col>
          </a-row>
          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"岗位:"}}
            </span>
            </a-col>
            <a-col :span="4">
                          <span>
                <a-select style="width: 220px" placeholder="全部" @change="">
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
                      </a-select-option>
                </a-select>
            </span>
            </a-col>


          </a-row>
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
      title: '试题系统',
      dataIndex: 'system',
      align: 'center'
    },
    {
      title: '适用岗位',
      dataIndex: 'station',
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
  const testManagementData = [
    {
      NO: 1,
      description: 'xxx',
      type: '理论试题',
      system: 'xxx系统',
      station: '总体',
      questionType: '单选',
      answer: 'A'
    },
    {
      NO: 2,
      description: 'xxx',
      type: '理论试题',
      system: 'xxx系统',
      station: '总体',
      questionType: '单选',
      answer: 'A'
    },
    {
      NO: 3,
      description: 'xxx',
      type: '理论试题',
      system: 'xxx系统',
      station: '总体',
      questionType: '单选',
      answer: 'A'
    },
    {
      NO: 4,
      description: 'xxx',
      type: '理论试题',
      system: 'xxx系统',
      station: '总体',
      questionType: '单选',
      answer: 'A'
    },
    {
      NO: 5,
      description: 'xxx',
      type: '理论试题',
      system: 'xxx系统',
      station: '总体',
      questionType: '单选',
      answer: 'A'
    },
    {
      NO: 6,
      description: 'xxx',
      type: '理论试题',
      system: 'xxx系统',
      station: '总体',
      questionType: '单选',
      answer: 'A'
    },
    {
      NO: 7,
      description: 'xxx',
      type: '理论试题',
      system: 'xxx系统',
      station: '总体',
      questionType: '单选',
      answer: 'A'
    },
    {
      NO: 8,
      description: 'xxx',
      type: '理论试题',
      system: 'xxx系统',
      station: '总体',
      questionType: '单选',
      answer: 'A'
    },
    {
      NO: 9,
      description: 'xxx',
      type: '理论试题',
      system: 'xxx系统',
      station: '总体',
      questionType: '单选',
      answer: 'A'
    },
    {
      NO: 10,
      description: 'xxx',
      type: '理论试题',
      system: 'xxx系统',
      station: '总体',
      questionType: '单选',
      answer: 'A'
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
      title: '所属系统',
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
      learningAndTrainingPrograms: 'xxx理论学习',
      system: 'xxx系统',
      typesOfLearning: '理论学习',
      station: ' xxx岗位',
      classHour: '2'
    },
    {
      NO: '2',
      learningAndTrainingPrograms: 'xxx理论学习',
      system: 'xxx系统',
      typesOfLearning: '理论学习',
      station: ' xxx岗位',
      classHour: '2'
    },
    {
      NO: '3',
      learningAndTrainingPrograms: 'xxx理论学习',
      system: 'xxx系统',
      typesOfLearning: '理论学习',
      station: ' xxx岗位',
      classHour: '2'
    },
    {
      NO: '4',
      learningAndTrainingPrograms: 'xxx理论学习',
      system: 'xxx系统',
      typesOfLearning: '理论学习',
      station: ' xxx岗位',
      classHour: '2'
    },
    {
      NO: '5',
      learningAndTrainingPrograms: 'xxx理论学习',
      system: 'xxx系统',
      typesOfLearning: '理论学习',
      station: ' xxx岗位',
      classHour: '2'
    },
    {
      NO: '6',
      learningAndTrainingPrograms: 'xxx理论学习',
      system: 'xxx系统',
      typesOfLearning: '理论学习',
      station: ' xxx岗位',
      classHour: '2'
    },
    {
      NO: '7',
      learningAndTrainingPrograms: 'xxx理论学习',
      system: 'xxx系统',
      typesOfLearning: '理论学习',
      station: ' xxx岗位',
      classHour: '2'
    },
    {
      NO: '8',
      learningAndTrainingPrograms: 'xxx理论学习',
      system: 'xxx系统',
      typesOfLearning: '理论学习',
      station: ' xxx岗位',
      classHour: '2'
    },
    {
      NO: '9',
      learningAndTrainingPrograms: 'xxx理论学习',
      system: 'xxx系统',
      typesOfLearning: '理论学习',
      station: ' xxx岗位',
      classHour: '2'
    },
    {
      NO: '10',
      learningAndTrainingPrograms: 'xxx理论学习',
      system: 'xxx系统',
      typesOfLearning: '理论学习',
      station: ' xxx岗位',
      classHour: '2'
    }
  ];

  //人员配置管理
  const staffingManagementData = [
    {
      No: '1',
      name: '张三',
      system: 'xxxx系统',
      station: '总体'
    },
    {
      No: '2',
      name: '张三',
      system: 'xxxx系统',
      station: '总体'
    }, {
      No: '3',
      name: '张三',
      system: 'xxxx系统',
      station: '总体'
    }
    , {
      No: '4',
      name: '张三',
      system: 'xxxx系统',
      station: '总体'
    }, {
      No: '5',
      name: '张三',
      system: 'xxxx系统',
      station: '总体'
    }, {
      No: '6',
      name: '张三',
      system: 'xxxx系统',
      station: '总体'
    }, {
      No: '7',
      name: '张三',
      system: 'xxxx系统',
      station: '总体'
    }, {
      No: '8',
      name: '张三',
      system: 'xxxx系统',
      station: '总体'
    }, {
      No: '9',
      name: '张三',
      system: 'xxxx系统',
      station: '总体'
    }, {
      No: '10',
      name: '张三',
      system: 'xxxx系统',
      station: '总体'
    }
  ]

  const staffingManagementColumns = [
    // {
    //   title: '',
    //   dataIndex: 'checkBox',
    //   scopedSlots: {customRender: 'checkBox'},
    //   align: 'center'
    // },
    // {
    //   dataIndex: 'No',
    //   title: '序号',
    //   // slots: {title: 'customTitle'},
    //   // scopedSlots: {customRender: 'name'},
    //   align: 'center'
    // },
    {
      title: '姓名',
      dataIndex: 'name',
      align: 'center'
    },
    {
      title: '所属系统',
      dataIndex: 'system',
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


        personnelModal: false,
        learningManagementModal: false,
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
        equipments: ['xxx系统', 'xxx车', 'xxxx车']
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
        this.personnelModal = true
      },
      /**
       * @Author:     风中的那朵云
       * @Description:  ${description}
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      learningManagementSetUp() {
        this.learningManagementModal = true
      },
      addLearningManagement(){
        this.LMM = true
      }
    }
  }
</script>

<style scoped>

</style>