<template>
  <div id="ExaminationPapers">
    <a-card style="background-color: RGB(10,19,49);height:1058px">
      <a-row style="padding: 30px 0 10px 0">
        <a-tabs @tabClick="tabsChang">
          <a-tab-pane tab="制定试卷" key="1"/>
          <a-tab-pane tab="考核评价" key="2"/>
        </a-tabs>
      </a-row>
      <!-- 制定试卷 -->
      <div v-if="tabsVisible1 === true" class="examination">
        <a-row type="flex" style="margin-top: 30px" justify="left">
          <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"适用岗位:"}}
            </span>
          </a-col>
          <a-col :span="4">
            <span>
              <a-select style="width: 220px" @change="onChangeExamSelect">
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>武器系统</span>
                  <a-select-option value="1">
                    总体
                  </a-select-option>
                </a-select-opt-group>
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>指控车</span>
                  <a-select-option value="2">
                    总体
                  </a-select-option>
                  <a-select-option value="3">
                    指挥
                  </a-select-option>
                </a-select-opt-group>
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>发射车</span>
                  <a-select-option value="4">
                    操作
                  </a-select-option>
                </a-select-opt-group>
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>雷达车</span>
                  <a-select-option value="5">
                    维修
                  </a-select-option>
                </a-select-opt-group>
              </a-select>
            </span>
          </a-col>
          <a-col :span="2">
            <span style="margin-left:30px;">
              <a-button @click="initExam">
                {{"查询"}}
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
              <template slot="operation" slot-scope="text,record">
                <span style="font-size: 18px;color: #0ca5ec;cursor: pointer" @click="initPaper(record)">{{"详情"}}</span>
                <span style="font-size: 18px;color: #0ca5ec;cursor: pointer;margin-left: 30px" @click="sendExam(record)">{{"推送"}}</span>
              </template>
              <span slot="footer">
                <a-row type="flex">
                  <a-col :span="2" style="margin-left: 25px"></a-col>
                  <a-col :span="20">
                    <a-pagination
                      size="small"
                      style="text-align: center;"
                      @change="developTestPapersSetPage"
                      v-model="developTestPapersPage"
                      :total="developTestPapersTotal"
                      :showSizeChanger="false"
                      showQuickJumper
                      :showTotal="total => `共 ${developTestPapersTotal} 条`"/>
                  </a-col>
                  <a-col :span="1"></a-col>
                </a-row>
              </span>
            </a-table>
          </a-col>
        </a-row>
      </div>
      <!-- 考核评价 -->
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
                      @change="assessmentAndEvaluationSetPage"
                      v-model="assessmentAndEvaluationPage"
                      :total="assessmentAndEvaluationTotal"
                      :showSizeChanger="false"
                      showQuickJumper
                      :showTotal="total => `共 ${assessmentAndEvaluationTotal} 条`"/>
                  </a-col>
                </a-row>
              </span>
            </a-table>
          </a-col>
        </a-row>
      </div>
    </a-card>



    <!-- 试卷 -->
    <div>
      <a-modal v-model="paperModal" width="800px" style="overflow-y: scroll;" :footer="null">
        <a-card style="background-color: RGB(2,31,73);">
          <a-row type="flex" align="middle" justify="center" style="color: RGB(122,202,245)">
            <img src="@/assets/img/paper.png" style="width: 26px;height: 26px;">
            <sapn style="font-size: 30px;color:RGB(122,202,245);margin-left: 10px;">
              {{result.examTitle}}
            </sapn>
            <span style="font-size: 24px;margin-left: 30px">
          {{'考试时间:'}}
        </span>
            <span style="font-size: 24px;margin-left: 15px">
          {{result.timing}}
        </span>
          </a-row>
          <template v-for="(item,index) in result.paper">
            <a-row style="color: RGB(122,202,245);font-size: 24px;margin-top: 60px">
              <span>
                {{item.questionType}}
              </span>
              <span>
                {{"。"}}
              </span>
              <span>
                {{'（共'}}
              </span>
              <span>
                {{item.numbenOfQuestions}}
              </span>
              <span>
                {{'题'}}
              </span>
              <span>
                {{'每题'}}
              </span>
              <span>
                {{item.fraction}}
              </span>
              <span>
                {{'分）'}}
              </span>
            </a-row>
            <a-row v-for='(suboptions,ix,key) in item.data' :key='key' style="color: RGB(122,202,245);font-size: 18px;margin-left: 55px;margin-top: 70px">
            <a-row>
            <span>
              {{ix + 1}}
            </span>
                    <span>
              {{'.'}}
            </span>
                    <span>
              {{suboptions.title}}
            </span>
                  </a-row>
                  <a-row style="color: RGB(122,202,245);font-size: 16px;margin-left: 5px;margin-top: 20px">
            <span v-for="(i,key) in suboptions.option" :key="key" style="margin-left: 20px">
              {{i}}
            </span>
                  </a-row>
                  <a-row style="color: RGB(122,202,245);font-size: 18px;margin-top: 20px;">
            <span>
              {{'正确答案:'}}
            </span>
                    <span>
              {{suboptions.rightKey}}
            </span>
                  </a-row>
                </a-row>

          </template>

        </a-card>
      </a-modal>
    </div>



  </div>
</template>

<script>
  const developTestPapersColumns = [
    {
      title: "序号",
      dataIndex: 'No',
      align: 'center'
    },
    {
      title: "试卷名称",
      dataIndex: 'name',
      align: 'center'
    },
    {
      title: '适用岗位',
      dataIndex: 'equipPositionStr',
      align: 'center'
    },
    {
      title: '考试时间',
      dataIndex: 'timing',
      align: 'center'
    },
    {
      title: '通过标准',
      dataIndex: 'passScores',
      align: 'center'
    },
    {
      title: '操作',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ];
  const assessmentAndEvaluationColumns = [
    {
      title: "序号",
      dataIndex: 'No',
      align: 'center'
    },
    {
      title: '姓名',
      dataIndex: 'staffName',
      align: 'center'
    },
    {
      title: '所属岗位',
      dataIndex: 'equipPositionStr',
      align: 'center'
    },
    {
      title: '成绩',
      dataIndex: 'scoresStr',
      align: 'center'
    }
  ];
  import './ExaminationPapers.less'
  import './ExaminationPaperSettings.less'
  import { initQueryExamPageList,initEvaluationList,sendExamById,initPaper } from '@/api/AssessmentAndEvaluation.js'
  export default {
    data() {
      return {
        //切换选项卡
        tabsVisible1: true,
        tabsVisible2: false,
        //制定试卷
        developTestPapersColumns,
        developTestPapersData: [],
        developTestPapersPage: 1,
        developTestPapersTotal: 0,
        developTestPapersSelected: '',
        //考核评价
        assessmentAndEvaluationColumns,
        assessmentAndEvaluationData: [],
        assessmentAndEvaluationPage: 1,
        assessmentAndEvaluationTotal: 0,

        //  试卷
        paperModal: false,
        result: {}
      }
    },
    methods: {
      //切换选项卡
      tabsChang(key) {
        if (key === '1') {
          this.initExam();
          this.tabsVisible1 = true
          this.tabsVisible2 = false
        } else if (key === '2') {
          this.initEvaluation();
          this.tabsVisible2 = true
          this.tabsVisible1 = false
        }
      },
      //制定试卷列表跳页
      developTestPapersSetPage(page){
        console.log(page);
        this.developTestPapersPage = page;
        this.initExam();
      },
      //考核评价列表跳页
      assessmentAndEvaluationSetPage(page){
        console.log(page);
        this.assessmentAndEvaluationPage = page;
        this.initEvaluation();
      },

      //下拉选择项目事件
      onChangeExamSelect(data){
        let that = this;
        //console.log("afafafafaf:    "+data);
        that.developTestPapersSelected = data;
      },
      //推送试卷到学员
      sendExam(record){
        let that = this
        sendExamById({
          examId: record.id
        }).then(function (res) {
          if(res.success){
            that.$message.info(res.message);
          }else{
            that.$message.info(res.message);
          }
        }).catch(function (err) {
          console.log(err)
        })
      },
      //初始化试卷列表
      initExam(){
        let that = this
        initQueryExamPageList({
          pageNo: that.developTestPapersPage,
          equipPosition: that.developTestPapersSelected
        }).then(function (res) {
          console.log(res)
          that.developTestPapersData = res.personnelList
          that.developTestPapersTotal = res.total
          let i = 1
          that.developTestPapersData.map(item => {
            item.No = i
            i++
          })
        }).catch(function (err) {
          console.log(err)
        })
      },
      //初始化考核评价列表
      initEvaluation(){
        let that = this
        initEvaluationList({
          pageNo: that.assessmentAndEvaluationPage
        }).then(function (res) {
          console.log(res)
          that.assessmentAndEvaluationData = res.personnelList
          that.assessmentAndEvaluationTotal = res.total
          let i = 1
          that.assessmentAndEvaluationData.map(item => {
            item.No = i
            i++
          })
          this.initEvaluation();
        }).catch(function (err) {
          console.log(err)
        })
      },

      //  试卷
      initPaper(record){
        let that = this;
        that.paperModal = true;
        initPaper({
          examId: record.id
        }).then(function (res) {
          console.log(res)
          that.result = res.result
        }).catch(function (err) {
          console.log(err)
        })
      }

    },
    mounted() {
      this.initExam();
      //this.initEvaluation();
    }
  }
</script>

<style scoped>

</style>