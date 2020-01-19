<template>
  <div id="practiseOperation">
    <a-card style="background-color: RGB(10,19,49)">
      <a-row type="flex">
        <a-col :span="8">
          <a-card>
            <span slot="title">
              <img src=".././assets/img/countDown.png" style="width: 18px;height: 18px;margin-top: -2px">
              <span style="color: rgb(12,152,220);margin-left: 5px">
                {{ "考试时间倒计时" }}
              </span>
            </span>
            <down-time></down-time>
          </a-card>
          <a-card style="margin-top: 20px">
            <span slot="title">
              <a-row type="flex">
                <a-col :span="6">
                  <img src=".././assets/img/answerCard.png" style="width: 18px;height: 18px;margin-top: -2px">
                  <span style="color: rgb(12,152,220);margin-left: 5px">
                    {{ "答题卡" }}
                  </span>
                </a-col>
                <a-col :span="12">
                  <a-progress :percent="30" :showInfo="false"/>
                </a-col>
                <a-col>
                  <span style="color: #ffffff;margin-left: 5px">
                    {{ completionNumber }}
                  </span>
                  <span style="color: RGB(24,111,164)">
                    /
                  </span>
                  <span style="color: RGB(24,111,164)">
                    {{ total }}
                  </span>
                </a-col>
              </a-row>
            </span>
            <div>
              <span v-for="item in btnNum" :key="item">
                <a-button style="margin-left: 13px;margin-top: 13px">
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
                  <img src=".././assets/img/jiaoxuejindu.png" style="width: 18px;height: 18px;margin-top: -2px">
                  <span style="color: rgb(12,152,220);margin-left: 5px">
                    {{ "在线考试" }}
                  </span>
                </a-col>
                <a-col :span="6">
                  <a-progress :percent="30" :showInfo="false"/>
                </a-col>
                <a-col :span="10">
                  <span style="color: #ffffff;margin-left: 5px">
                    {{ completionNumber }}
                  </span>
                  <span style="color: RGB(24,111,164)">
                    /
                  </span>
                  <span style="color: RGB(24,111,164)">
                    {{ total }}
                  </span>
                </a-col>
                <a-col :span="4">
                  <a-checkbox @change="onChange">{{ "自动跳转下一题" }}</a-checkbox>
                </a-col>
              </a-row>
            </span>
            <div style="margin-top: 30px;">
              <span style="color: RGB(109,175,212);margin-left: 20px">
                <span>
                  {{ singleChoiceQuestion.titleNumber }}
                </span>
                <span>
                  、
                </span>
                <span>
                  {{ singleChoiceQuestion.title }}
                </span>
              </span>
              <div style="margin-left: 50px;margin-top: 20px">
                <a-radio-group @change="onChange" v-model="value">
                  <a-row>
                    <a-col>
                      <a-radio :value="1">{{ "A:"+singleChoiceQuestion.plainOptions[0] }}</a-radio>
                    </a-col>
                  </a-row>
                  <a-row>
                    <a-col>
                      <a-radio :value="2">{{ "B:"+singleChoiceQuestion.plainOptions[1] }}</a-radio>
                    </a-col>
                  </a-row>
                  <a-row>
                    <a-col>
                      <a-radio :value="3">{{ "C:"+singleChoiceQuestion.plainOptions[2] }}</a-radio>
                    </a-col>
                  </a-row>
                  <a-row>
                    <a-col>
                      <a-radio :value="4">{{ "D:"+singleChoiceQuestion.plainOptions[3] }}</a-radio>
                    </a-col>
                  </a-row>
                </a-radio-group>
              </div>
            </div>
          </a-card>
        </a-col>
      </a-row>

    </a-card>
  </div>
</template>

<script>
  import downTime from './DownTime'
  import './PractiseOperation.less'
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
      }
    }
</script>

<style scoped>

</style>