<template>
  <div id="ExaminationPapers">
    <a-card style="background-color: RGB(10,19,49);height:1058px">
      <a-row style="padding: 30px 0 10px 0">
        <a-tabs @tabClick="tabsChang">
          <a-tab-pane tab="制定试卷" key="1"/>
          <a-tab-pane tab="考核评价" key="2"/>
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
          width="1200px"
        >
              <span slot="title">
                <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">
                <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "设置" }}</span>
              </span>
          <a-row type="flex" style="margin-top: 30px">
            <a-col :span="7">
              <span style="color: #ffffff;font-size: 18px"> {{"试卷名称:"}}</span>
              <span>
                 <a-input style="width: 220px"></a-input>
               </span>
            </a-col>
            <a-col :span="7">
              <span style="color: #ffffff;font-size: 18px"> {{"考试时间:"}}</span>
              <span>
                 <a-input value='120' disabled="true" style="width: 220px"></a-input>
               </span>
            </a-col>
            <a-col :span="7" style="margin-left: 10px">
               <span style="color: #ffffff;font-size: 18px">
                 {{"本卷分数:"}}
               </span>
              <span>
                 <a-input value='100' disabled="true" style="width: 220px"></a-input>
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
          <div style="margin-top: 20px">
            <a-radio-group  @change="equipmentSelection" :default-value="1">
              <a-radio :value="1" style="font-size: 18px">
                 武器系统
              </a-radio>
              <a-radio :value="2" style="font-size: 18px">
                  指挥车
              </a-radio>
              <a-radio :value="3" style="font-size: 18px">
                   发射车
              </a-radio>
              <a-radio :value="4" style="font-size: 18px">
                   雷达车
              </a-radio>
            </a-radio-group>
          </div>
          <a-row type="flex" style="margin-top: 30px">
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"题型分值:"}}
              </span>
            </a-col>
          </a-row>
          <a-row tyle="flex" style="margin-top: 30px">

            <span v-if="equipment1">
              <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"单选:"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"40"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"分"}}
              </span>
            </a-col>
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"多选:"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"40"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"分"}}
              </span>
            </a-col>
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"判断:"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"20"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"分"}}
              </span>
            </a-col>
            </span>


            <span v-if="equipment2">
              <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"单选:"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"20"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"分"}}
              </span>
            </a-col>
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"多选:"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"20"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"分"}}
              </span>
            </a-col>
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"判断:"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"20"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"分"}}
              </span>
            </a-col>
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"操作:"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"40"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"分"}}
              </span>
            </a-col>
            </span>


            <span v-if="equipment3">
              <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"单选:"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"20"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"分"}}
              </span>
            </a-col>
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"多选:"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"20"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"分"}}
              </span>
            </a-col>
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"判断:"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"20"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"分"}}
              </span>
            </a-col>
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"操作:"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"40"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"分"}}
              </span>
            </a-col>
            </span>


            <span v-if="equipment4">
              <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"单选:"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"20"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"分"}}
              </span>
            </a-col>
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"多选:"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"20"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"分"}}
              </span>
            </a-col>
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"判断:"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"20"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"分"}}
              </span>
            </a-col>
            <a-col :span="6">
              <span style="color: #ffffff;font-size: 18px">
                {{"操作:"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"40"}}
              </span>
              <span style="color: #ffffff;font-size: 18px">
                {{"分"}}
              </span>
            </a-col>
            </span>

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
  import './ExaminationPapers.less'

  const plainOptions = [
    '武器系统', '指控车', '雷达车','发射车'
  ]

  const developTestPapersColumns = [
    {
      title: "序号",
      dataIndex: 'NO',
      align: 'center'
    },
    {
      title: "试卷描述",
      dataIndex: 'description',
      align: 'center'
    },
    {
      title: '适用岗位',
      dataIndex: 'equipPosition',
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
      description: 'xxx',
      equipPosition: '武器系统_总体',
      totalTime: '120',
      passStandard: '60'
    },
    {
      NO: 2,
      description: 'xxx',
      equipPosition: '武器系统_总体',
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
      title: '所属岗位',
      dataIndex: 'equipPosition',
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
      name: '李杰',
      equipPosition:'武器系统_指挥',
      achievement: '通过(60)'
    },
    {
      NO: '2',
      name: '张啦啦',
      equipPosition:'武器系统_指挥',
      achievement: '通过(60)'
    },
    {
      NO: '3',
      name: '王玲玲',
      equipPosition:'武器系统_指挥',
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
        equipment1:true,
        equipment2:false,
        equipment3:false,
        equipment4:false,

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
      },
      /**
       * @Author:     风中的那朵云
       * @Description:  ${description}
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      equipmentSelection(e){
        console.log(e)
        if(e.target.value===1){
          this.equipment1 = true
          this.equipment2 = false
          this.equipment3 = false
          this.equipment4 = false
        }else if(e.target.value===2){
          this.equipment1 = false
          this.equipment2 = true
          this.equipment3 = false
          this.equipment4 = false
        }else if(e.target.value===3){
          this.equipment1 = false
          this.equipment2 = false
          this.equipment3 = true
          this.equipment4 = false
        }else if(e.target.value===4){
          this.equipment1 = false
          this.equipment2 = false
          this.equipment3 = false
          this.equipment4 = true
        }

      }
    }
  }
</script>

<style scoped>

</style>