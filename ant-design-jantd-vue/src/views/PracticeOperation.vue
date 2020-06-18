<template>
  <div id="practiseOperation">
    <a-card style="background-color: RGB(10,19,49)">
      <a-row type="flex" style="padding: 30px 0 10px 0">
        <a-col :span="8">
          <a-card>
            <span slot="title">
              <img src=".././assets/img/countDown.png" style="width: 28px;height: 28px;margin-top: -14px">
              <span style="color: rgb(12,152,220);margin-left: 5px;font-size: 30px">
                {{ "考试时间倒计时" }}
              </span>
              <a-divider style="background-color: RGB(19,48,93);" />
            </span>
            <down-time></down-time>
          </a-card>
          <a-card style="margin-top: 20px">
            <span slot="title">
              <a-row type="flex">
                <a-col :span="8">
                  <img src=".././assets/img/answerCard.png" style="width: 28px;height: 28px;margin-top: -14px">
                  <span style="color: rgb(12,152,220);margin-left: 5px;font-size: 30px">
                    {{ "答题卡" }}
                  </span>
                </a-col>
                <a-col :span="11">
                  <a-progress :percent="30" :showInfo="false" strokeWidth="15" style="margin-top: 12px"/>
                </a-col>
                <a-col style="margin-top: 4px">
                  <span style="color: #ffffff;margin-left: 5px;font-size: 24px">
                    {{ completionNumber }}
                  </span>
                  <span style="color: RGB(24,111,164);font-size: 24px">
                    /
                  </span>
                  <span style="color: RGB(24,111,164);font-size: 24px">
                    {{ total }}
                  </span>
                </a-col>
                <a-divider style="background-color: RGB(19,48,93);" />
              </a-row>
            </span>
            <div>
              <span v-for="item in btnNum" :key="item">
                <a-button style="height: 43px;width: 44px;margin-left: 24px;margin-bottom: 24px">
                  {{ item }}
                </a-button>
              </span>
            </div>
          </a-card>
        </a-col>
        <a-col :span="1"></a-col>
        <a-col :span="15">
          <a-card>
            <span slot="title">
              <a-row type="flex">
                <a-col :span="4">
                  <img src=".././assets/img/jiaoxuejindu.png" style="width: 28px;height: 28px;margin-top: -14px">
                  <span style="color: rgb(12,152,220);margin-left: 5px;font-size: 30px">
                    {{ "在线考试" }}
                  </span>
                </a-col>
                <a-col :span="7" style="margin-left: 28px">
                  <a-progress :percent="30" :showInfo="false" strokeWidth="15" style="margin-top: 12px"/>
                </a-col>
                <a-col :span="7" style="margin-top: 4px">
                  <span style="color: #ffffff;margin-left: 5px;font-size: 24px">
                    {{ completionNumber }}
                  </span>
                  <span style="color: RGB(24,111,164);font-size: 24px">
                    /
                  </span>
                  <span style="color: RGB(24,111,164);font-size: 24px">
                    {{ total }}
                  </span>
                </a-col>
                <a-col :span="4">
                  <a-checkbox @change="onChange">
                    <span style="color: RGB(34,161,227);font-size: 24px">
                      {{ "自动跳转下一题" }}
                    </span>
                  </a-checkbox>
                </a-col>
              </a-row>
              <a-divider style="background-color: RGB(19,48,93);" />
            </span>
            <div style="margin-top: 66px;">
              <span style="color: RGB(109,175,212);margin-left: 46px">
                <span style="font-size: 34px">
                  {{ singleChoiceQuestion.titleNumber }}
                </span>
                <span style="font-size: 34px">
                  、
                </span>
                <span style="font-size: 34px">
                  {{ singleChoiceQuestion.title }}
                </span>
              </span>
              <div style="margin-left: 50px;margin-top: 66px">
                <a-radio-group @change="onChange" v-model="value">
                  <a-row style="margin-left: 66px;margin-bottom:36px">
                    <a-col >
                      <a-radio :value="1">
                        <span style="font-size: 30px;color: #85d3fd">
                          <span>
                            {{ "A" }}
                          </span>
                          <span style="margin-left: 5px">
                            {{ ":" }}
                          </span>
                          <span>
                            {{ singleChoiceQuestion.plainOptions[0] }}
                          </span>
                        </span>
                      </a-radio>
                    </a-col>
                  </a-row>
                  <a-row style="margin-left: 66px;margin-bottom:36px">
                    <a-col>
                      <a-radio :value="2">
                        <span style="font-size: 30px;color: #85d3fd">
                          <span>
                            {{ "B" }}
                          </span>
                          <span style="margin-left: 5px">
                            {{ ":" }}
                          </span>

                            {{ singleChoiceQuestion.plainOptions[1] }}
                               <span></span>
                        </span>
                      </a-radio>
                    </a-col>
                  </a-row>
                  <a-row style="margin-left: 66px;margin-bottom:36px">
                    <a-col>
                      <a-radio :value="3">
                        <span style="font-size: 30px;color: #85d3fd">
                          <span>
                            {{ "C" }}
                          </span>
                          <span style="margin-left: 5px">
                            {{ ":" }}
                          </span>
                          <span>
                            {{ singleChoiceQuestion.plainOptions[2] }}
                          </span>
                        </span>
                      </a-radio>
                    </a-col>
                  </a-row>
                  <a-row style="margin-left: 66px;">
                    <a-col>
                      <a-radio :value="4">
                        <span style="font-size: 30px;color: #85d3fd">
                          <span>
                            {{ "D" }}
                          </span>
                          <span style="margin-left: 5px">
                            {{ ":" }}
                          </span>
                          <span>
                            {{ singleChoiceQuestion.plainOptions[3] }}
                          </span>
                        </span>
                      </a-radio>
                    </a-col>
                  </a-row>
                </a-radio-group>
              </div>
            </div>
            <div style="margin-top: 135px;margin-bottom: 178px">
              <a-row type="flex">
                <a-col :span="4" offset="5">
                  <img src=".././assets/img/shangyiti.png" style="width: 220px;height: 68px; cursor:pointer;">
                </a-col>
                <a-col :span="4" offset="4">
                  <img src=".././assets/img/xiayiti.png" style="width: 220px;height: 68px; cursor:pointer;">
                </a-col>
              </a-row>
            </div>
          </a-card>
        </a-col>
      </a-row>

    </a-card>
  </div>
</template>

<script>
  import downTime from './DownTime'
  import {toEditByExamAndStaff} from '@/api/PracticeOperation'
  import './PracticeOperation.less'
    export default {
      name: "PracticeOperation",
      data(){
        return{
          completionNumber:'10',
          total: '50',
          btnNum: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,
          41,42,43,44,45,46,47,48,49,50],
          singleChoiceQuestion:{
            titleNumber: '1',
            title:'在实习期间驾驶机动车的，应该车未悬挂那种标志？',
            plainOptions : ['注意新手标志', '统一样式的标志', '注意避标志','注意车标志']
          },
          value:''
        }
      },
      components: {
        downTime
      },
      methods: {
        initToEditExam(){
          let that = this
          toEditByExamAndStaff({
            examId: '4cff867e808aa65787daa767e336cb33',
            staffId: '72'
          }).then(function (res) {
            that.testQuestionsModal = false
            console.log(res)
            if (res === 1) {
              that.initTestManagement();
              that.$message.success("添加成功");
            } else {
              that.$message.warning("添加失败");
            }

          }).catch(function (err) {
            console.log(err)
          })
        }
      },
      mounted() {
        this.initEditExam()
      }
    }
</script>

<style scoped>

</style>