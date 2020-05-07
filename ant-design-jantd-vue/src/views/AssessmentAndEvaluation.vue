<template>
  <div id="examinationPaperSettings">
    <a-card style="background-color: RGB(10,19,49);height:1058px">
      <a-row style="padding: 30px 0 10px 0">
        <a-tabs @tabClick="tabsChang">
          <a-tab-pane tab="制定试卷" key="1"/>
          <a-tab-pane tab="考核评估" key="2"/>
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
          <a-col :span="2">
            <span>
              <a-button @click="newTestPaper">
                {{"新建试卷"}}
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
            <a-table :dataSource="developTestPapersData" :pagination="false" :columns="developTestPapersColumns">
              <template slot="operation">
                <span style="font-size: 18px;color: #0ca5ec;cursor: pointer" @click="">{{"详情"}}</span>
                <span style="font-size: 18px;color: #0ca5ec;cursor: pointer;margin-left: 30px" @click="">{{"推送"}}</span>
                <span style="font-size: 18px;color: #0ca5ec;cursor: pointer;margin-left: 30px" @click="">{{"删除"}}</span>
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
          v-model="newTestPaperModal"
          width="1000px"
        >
              <span slot="title">
                <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">
                <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "设置" }}</span>
              </span>
          <a-row type="flex" style="margin-top: 30px">
            <a-col :span="8">
              <span style="color: #ffffff;font-size: 18px"> {{"考试时间:"}}</span>
              <span>
                 <a-input value='120' style="width: 220px"></a-input>
               </span>
            </a-col>
            <a-col :span="8" style="margin-left: 10px">
               <span style="color: #ffffff;font-size: 18px">
                 {{"本卷分数:"}}
               </span>
              <span>
                 <a-input value='100' style="width: 220px"></a-input>
               </span>
            </a-col>
          </a-row>
          <a-row type="flex" style="margin-top: 30px" >
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"系统或所属装备:"}}
              </span>
            </a-col>
          </a-row>
          <div>
            <a-checkbox-group :options="plainOptions" v-model="checkedList" @change=""/>
          </div>

          <a-row type="flex" style="margin-top: 30px">
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"题型分值:"}}
              </span>
            </a-col>
          </a-row>
          <a-row tyle="flex" style="margin-top: 30px">
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"单选:"}}
              </span>
              <a-input style="width: 180px">
              </a-input>
            </a-col>
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"多选:"}}
              </span>
              <a-input style="width: 180px">
              </a-input>
            </a-col>
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"判断:"}}
              </span>
              <a-input style="width: 180px">
              </a-input>
            </a-col>
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"操作:"}}
              </span>
              <a-input style="width: 180px">
              </a-input>
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
            <a-table :dataSource="assessmentAndEvaluationData" :pagination="false"
                     :columns="assessmentAndEvaluationColumns">
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

  const plainOptions = [
    '武器系统和导弹概述', '武器系统工作状态', '指挥车作战使命',
    '武器系统基本组成', '武器系统工作原理、作战方式', '指挥车战机指标',
    '武器系统作战使命', '指挥车系统概述', '指挥车工作过程和工作原理',
    '指挥车控制操作手', '作战拦截操作手', '空勤处理操作手',
    '通信专业', '电子雷达专业', '机电专业'
  ]

  const developTestPapersColumns = [
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
      title: '所属系统',
      dataIndex: 'system',
      align: 'center'
    },
    {
      title: '适用岗位',
      dataIndex: 'station',
      align: 'center'
    },
    {
      title: '考试时间',
      dataIndex: 'totalTime',
      align: 'center'
    },
    {
      title: '通过标准',
      dataIndex: 'passStandard',
      align: 'center'
    },
    {
      title: '操作',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ];
  const developTestPapersData = [
    {
      NO: 1,
      description: 'xxx实体',
      system: 'xxx系统',
      station: '总体',
      totalTime: '120',
      passStandard: '60'
    },
    {
      NO: 2,
      description: 'xxx实体',
      system: 'xxx系统',
      station: '总体',
      totalTime: '120',
      passStandard: '60'
    },
    {
      NO: 3,
      description: 'xxx实体',
      system: 'xxx系统',
      station: '总体',
      totalTime: '120',
      passStandard: '60'
    },
    {
      NO: 4,
      description: 'xxx实体',
      system: 'xxx系统',
      station: '总体',
      totalTime: '120',
      passStandard: '60'
    },
    {
      NO: 5,
      description: 'xxx实体',
      system: 'xxx系统',
      station: '总体',
      totalTime: '120',
      passStandard: '60'
    },
    {
      NO: 6,
      description: 'xxx实体',
      system: 'xxx系统',
      station: '总体',
      totalTime: '120',
      passStandard: '60'
    },
    {
      NO: 7,
      description: 'xxx实体',
      system: 'xxx系统',
      station: '总体',
      totalTime: '120',
      passStandard: '60'
    },
    {
      NO: 8,
      description: 'xxx实体',
      system: 'xxx系统',
      station: '总体',
      totalTime: '120',
      passStandard: '60'
    },
    {
      NO: 9,
      description: 'xxx实体',
      system: 'xxx系统',
      station: '总体',
      totalTime: '120',
      passStandard: '60'
    },
    {
      NO: 10,
      description: 'xxx实体',
      system: 'xxx系统',
      station: '总体',
      totalTime: '120',
      passStandard: '60'
    }
  ]
  const assessmentAndEvaluationColumns = [
    {
      title: "序号",
      dataIndex: 'NO',
      align: 'center'
    },
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
      title: '成绩',
      dataIndex: 'achievement',
      align: 'center'
    }
  ];

  const assessmentAndEvaluationData = [
    {
      NO: '1',
      name: '张三',
      system: 'xxx系统',
      station: ' xxx岗位',
      achievement: '通过(60)'
    },
    {
      NO: '2',
      name: '张三',
      system: 'xxx系统',
      station: ' xxx岗位',
      achievement: '通过(60)'
    },
    {
      NO: '3',
      name: '张三',
      system: 'xxx系统',
      station: ' xxx岗位',
      achievement: '通过(60)'
    },
    {
      NO: '4',
      name: '张三',
      system: 'xxx系统',
      station: ' xxx岗位',
      achievement: '通过(60)'
    },
    {
      NO: '5',
      name: '张三',
      system: 'xxx系统',
      station: ' xxx岗位',
      achievement: '通过(60)'
    },
    {
      NO: '6',
      name: '张三',
      system: 'xxx系统',
      station: ' xxx岗位',
      achievement: '通过(60)'
    },
    {
      NO: '7',
      name: '张三',
      system: 'xxx系统',
      station: ' xxx岗位',
      achievement: '通过(60)'
    },
    {
      NO: '8',
      name: '张三',
      system: 'xxx系统',
      station: ' xxx岗位',
      achievement: '通过(60)'
    },
    {
      NO: '9',
      name: '张三',
      system: 'xxx系统',
      station: ' xxx岗位',
      achievement: '通过(60)'
    },
    {
      NO: '10',
      name: '张三',
      system: 'xxx系统',
      station: ' xxx岗位',
      achievement: '通过(60)'
    }
  ];
  import './ExaminationPaperSettings.less'

  export default {
    data() {
      return {
        /**
         * @Author:     风中的那朵云
         * @Description:  ${description}
         * @Date:    2020/5/6
         * @Version:    1.0
         */
        developTestPapersColumns,
        developTestPapersData,

        assessmentAndEvaluationColumns,
        assessmentAndEvaluationData,



        tabsVisible1: true,
        tabsVisible2: false,
        newTestPaperModal: false,

        plainOptions,

        checkedList: '',

      }
    },
    methods: {

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
        } else if (key === '2') {
          this.tabsVisible2 = true
          this.tabsVisible1 = false
        }
      },
      /**
       * @Author:     风中的那朵云
       * @Description:  新建试卷
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      newTestPaper() {
        this.newTestPaperModal = true
      }
    }
  }
</script>

<style scoped>

</style>