<template>
  <div id="drawPlan">
    <a-card style="background-color: RGB(10,19,49);height:1058px">
      <a-row style="padding: 30px 0 10px 0">
        <a-tabs @tabClick="tabsChang">
          <a-tab-pane tab="课表配置" key="1"/>
          <a-tab-pane tab="学习和训练进度" key="2"/>
        </a-tabs>
      </a-row>
      <div v-if="tabsVisible1 ===true " class="examination">
        <a-row
          type="flex"
          style="margin-top: 20px;margin-bottom: 30px"
          justify="center"
          align="center">
          <a-col :span="24">
            <a-table :dataSource="courseConfigurationData" :pagination="false" :columns="courseConfigurationColumns" :rowSelection="{onChange:onChangeRow}">
              <span slot="footer">
                <a-row type="flex">
                  <a-col :span="2" style="margin-left: 25px">
                    <a-button @click="sendCourse">
                      {{"课时推送"}}
                    </a-button>
                  </a-col>
                  <a-col :span="2" style="margin-left: 25px">
                    <a-button @click="createExam">
                      {{"生成试卷"}}
                    </a-button>
                  </a-col>
                  <a-col :span="2" style="margin-left: 25px">
                    <a-button @click="batchDeleteStudyCourse">
                      {{"批量删除"}}
                    </a-button>
                  </a-col>
                  <a-col :span="14">
                    <a-pagination
                      size="small"
                      style="text-align: center;"
                      @change="courseSetPage"
                      v-model="courseConfigurationPage"
                      :total="courseConfigurationTotal"
                      :showSizeChanger="false"
                      showQuickJumper
                      :showTotal="total => `共 ${courseConfigurationTotal} 条`"/>
                  </a-col>
                  <a-col :span="1">
                    <span>
                      <img src="@/assets/img/tianjia.png" style="width: 26px;height: 26px;position: relative;bottom: 3px;cursor: pointer" @click="addTimetable"/>
                    </span>
                  </a-col>
                </a-row>
              </span>
            </a-table>
          </a-col>
        </a-row>


<!--        添加课表-->
        <a-modal
          v-model="addTimetableModal"
          width="800px"
        >
          <span slot="title">
            <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">
            <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "新增" }}</span>
          </span>
          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"学习和操作训练项目:"}}
            </span>
            </a-col>
            <a-col :span="4" style="margin-left: 30px">
              <span>
                <a-select style="width: 220px" @change="onChangeStudySelect">
                  <a-select-option v-for="item in selectAllData" :key="item.name" :value="item.id">{{ item.name }}</a-select-option>
                </a-select>
              </span>
            </a-col>
          </a-row>

          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"课时:"}}
            </span>
            </a-col>
            <a-col :span="4" style="margin-left: 30px">
                <span>
                  <a-input style="width: 220px" v-model="studyCourseTemp.studyTime"></a-input>
                </span>
            </a-col>
          </a-row>
          <span slot="footer">
                <a-row type="flex" justify="center">
                  <a-col :span="4">
                    <a-button @click="submitStudyCourse">
                      <a-icon type="upload"/>
                      确定
                    </a-button>
                  </a-col>
                </a-row>
              </span>
        </a-modal>
      </div>
      <div v-if="tabsVisible2 ===true " class="examination">
        <div>
        <a-row
          type="flex"
          style="margin-top: 20px;margin-bottom: 30px"
          justify="center"
          align="center">
          <a-col :span="24">
            <a-table :dataSource="LATPData" :pagination="false" :columns="LATPColumns">
              <template slot="details" slot-scope="text,record">
                <span style="font-size: 18px;color: #0ca5ec;cursor: pointer" @click="personalCompletion(record)">{{"查看"}}</span>
              </template>
              <span slot="footer">
                <a-row type="flex">
                  <a-col :span="2" style="margin-left: 25px"></a-col>
                  <a-col :span="20">
                    <a-pagination
                      size="small"
                      style="text-align: center;"
                      @change="LATPSetPage"
                      v-model="LATPPage"
                      :total="LATPTotal"
                      :showSizeChanger="false"
                      showQuickJumper
                      :showTotal="total => `共 ${LATPTotal} 条`"/>
                  </a-col>
                  <a-col :span="1"></a-col>
                </a-row>
              </span>
            </a-table>
          </a-col>
        </a-row>

          <!--        详情页面-->
          <a-modal
            v-model="showPersonalCompletionModal"
            width="800px"
          >
            <a-table :dataSource="personalCompletionData" :pagination="false" :columns="personalCompletionColumns">
              <span slot="footer">
                <a-row type="flex">
                  <a-col :span="2" style="margin-left: 25px"></a-col>
                  <a-col :span="20">
                    <a-pagination
                      size="small"
                      style="text-align: center;"
                      @change="personalCompletionSetPage"
                      v-model="personalCompletionPage"
                      :total="personalCompletionTotal"
                      :showSizeChanger="false"
                      showQuickJumper
                      :showTotal="total => `共 ${personalCompletionTotal} 条`"/>
                  </a-col>
                  <a-col :span="1"></a-col>
                </a-row>
              </span>
            </a-table>

            <span slot="footer">
              <!--<a-row type="flex" justify="center">
                <a-col :span="4">
                  <a-button>
                    <a-icon type="upload"/>
                    确定
                  </a-button>
                </a-col>
              </a-row>-->
            </span>
          </a-modal>
        </div>>
      </div>

    </a-card>
  </div>
</template>

<script>


  const courseConfigurationColumns = [
    {
      title: "序号",
      dataIndex: 'No',
      align: 'center'
    },
    {
      title: '学习和操作训练项目',
      dataIndex: 'studyName',
      align: 'center'
    },
    {
      title: '课时',
      dataIndex: 'studyTime',
      align: 'center'
    },
    {
      title: '适用岗位',
      dataIndex: 'equipPositionStr',
      align: 'center'
    }
  ];
  const LATPColumns = [
    {
      title: '适用岗位',
      dataIndex: 'equipPosition',
      align: 'center'
    },
    {
      title: '学习与训练项目',
      dataIndex: 'name',
      align: 'center'
    },
    {
      title: '完成率',
      dataIndex: 'percentage',
      align: 'center'
    },
    {
      title: '操作',
      scopedSlots: {customRender: 'details'},
      align: 'center'
    }
  ];
  const personalCompletionColumns = [
    {
      title: '姓名',
      dataIndex: 'staffName',
      align: 'center'
    },
    {
      title: '完成率',
      dataIndex: 'percentage',
      align: 'center'
    }
  ];


  import './DrawPlam.less'
  import { initCourseConfigurationData,deleteStudyCourseData,
    sendCourse,getStudySelect,editStudyCourse,
    initRateQueryPageList,initPersonalCompletionData,
    createExam } from '@/api/DrawPlan.js'
  export default {
    name: "DrawPlan",
    data() {
      return {
        //切换选项卡
        tabsVisible1: true,
        tabsVisible2: false,
        //课表配置列表
        courseConfigurationColumns,
        courseConfigurationData: [],
        courseConfigurationPage: 1,
        courseConfigurationTotal: 0,
        courseConfigurationSelectIndex: [],
        //新增计划
        addTimetableModal: false,
        selectAllData: [], //下拉选项
        studyCourseTemp: {
          studyId: '',
          studyTime: ''
        },
        //学习和训练进度
        LATPColumns,
        LATPData: [],
        LATPPage: 1,
        LATPTotal: 0,
        //进度查看详情列表
        showPersonalCompletionModal: false,
        personalCompletionData: [],
        personalCompletionColumns,
        personalCompletionPage: 1,
        personalCompletionTotal: 0,
      }
    },
    methods: {
      //选项卡切换
      tabsChang(key) {
        if (key === '1') {
          this.initCourseConfiguration();
          this.tabsVisible1 = true
          this.tabsVisible2 = false
        } else if (key === '2') {
          this.initRateQueryPageList();
          this.tabsVisible2 = true
          this.tabsVisible1 = false
        }
      },
      /*updateTimetableModal() {
        this.timetableModal = true
      },*/
      //打开计划新增弹窗
      addTimetable(){
        this.addTimetableModal = true
      },
      //进度查看详情弹窗
      personalCompletion(id){
        this.initPersonalCompletion(id);
        this.showPersonalCompletionModal = true
      },
      //课表分页跳转
      courseSetPage(page) {
        console.log(page)
        this.courseConfigurationPage = page
        this.initCourseConfiguration()
      },
      //进度分页跳转
      LATPSetPage(page) {
        console.log(page)
        this.LATPPage = page
        this.initCourseConfiguration()
      },
      //进度查看详情分页跳转
      personalCompletionSetPage(page) {
        console.log(page)
        this.personalCompletionPage = page
        this.initPersonalCompletion()
      },
      //批量删除课表
      batchDeleteStudyCourse() {
        let that = this;
        let ids = '';
        that.courseConfigurationSelectIndex.map((item) => {
          ids += item+",";
        });
        deleteStudyCourseData({
          ids: ids.substr(0,ids.length-1)

        }).then(function (res) {
          console.log(res)
          that.initCourseConfiguration();
        }).catch(function (err) {
          console.log(err)
        })
      },
      //初始化课表配置列表
      initCourseConfiguration() {
        let that = this
        initCourseConfigurationData({
          pageNo: that.courseConfigurationPage
        }).then(function (res) {
          console.log(res)
          that.courseConfigurationData = res.personnelList
          that.courseConfigurationTotal = res.total
          let i = 1
          that.courseConfigurationData.map(item => {
            item.No = i
            i++
          })
        }).catch(function (err) {
          console.log(err)
        })
      },
      //行选择事件
      onChangeRow(selectedRowKeys, selectedRows) {
        this.courseConfigurationSelectIndex = [];
        selectedRows.map((item) => {
          this.courseConfigurationSelectIndex.push(item.id)
        });
      },
      //初始化学习和训练进度列表
      initRateQueryPageList() {
        let that = this
        initRateQueryPageList({
          pageNo: that.LATPPage
        }).then(function (res) {
          console.log(res)
          that.LATPData = res.personnelList
          that.LATPTotal = res.total
        }).catch(function (err) {
          console.log(err)
        })
      },
      //初始化进度详情列表
      initPersonalCompletion(record) {
        //console.log("aL:               " + record.id);
        let that = this
        initPersonalCompletionData({
          pageNo: that.personalCompletionPage,
          studyId: record.id
        }).then(function (res) {
          console.log(res)
          that.personalCompletionData = res.personnelList
          that.personalCompletionTotal = res.total
        }).catch(function (err) {
          console.log(err)
        })
      },
      //初始项目下拉列表
      getStudySelect(){
        let that = this
        that.selectAllData = [];
        getStudySelect({}).then(function (res) {
          //console.log("res:            "+res.result.records)
          if(res.success == true){
            that.selectAllData = res.result.records
          }
        }).catch(function (err) {
          console.log(err)
        })
      },
      //下拉选择项目事件
      onChangeStudySelect(data){
        let that = this;
        //console.log("afafafafaf:    "+data);
        that.studyCourseTemp.studyId = data;
      },
      //新增事件
      submitStudyCourse(){
        //editStudyCourse
        let that = this;
        editStudyCourse({
          studyCourseTemp: that.studyCourseTemp
        }).then(function (res) {
          console.log(res)
          if(res.success){
            that.initCourseConfiguration();
            that.getStudySelect();
            that.addTimetableModal = false
          }
          //that.$message.info("a");
        }).catch(function (err) {
          console.log(err)
        })
      },
      //推送课程
      sendCourse(){
        let that = this;
        sendCourse({}).then(function (res) {
          console.log(res)
          if(res.success){
            that.$message.info(res.message);
            that.initRateQueryPageList();
          }else{
            that.$message.info(res.message);
          }
        }).catch(function (err) {
          console.log(err)
        })
      },
      createExam(){
        let that = this;
        createExam({}).then(function (res) {
          console.log(res)
          if(res.success){
            that.$message.info(res.message);
          }else{
            that.$message.info(res.message);
          }
        }).catch(function (err) {
          console.log(err)
        })
      }
    },
    mounted() {
      this.initCourseConfiguration();
      //this.initRateQueryPageList();
      this.getStudySelect();
    }
  }
</script>

<style scoped>

</style>