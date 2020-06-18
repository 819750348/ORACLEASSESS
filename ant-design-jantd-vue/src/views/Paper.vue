<template>
  <div>
    <a-modal
      v-model="paperModal"
      width="800px"
      :footer="null"
    >
    <a-card style="background-color: RGB(2,31,73);overflow-y: scroll">
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
        <a-row v-for='(suboptions,ix,key) in item.data' :key='key'
               style="color: RGB(122,202,245);font-size: 18px;margin-left: 55px;margin-top: 70px">
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
</template>

<script>
  import {initPaper} from '@/api/paper.js'
  export default {
    name: "paper",
    data() {
      return {
        paperModal:false,
        result: {
          examTitle: '试卷标题',
          timing: '120', //答题计时
          paper: [
            {
              No: '1',//编号
              questionType: '一、单选题',//题型
              numbenOfQuestions: '30',//总分
              fraction: '2',//每题分值
              data: [ //题目数组
                {
                  serialNumber: '1', //序号
                  title: '路中心的双黄实线，属于哪一类标线？', //题目
                  option: ['A: 指示标线', 'B:辅助标线', 'C:警告标线', 'D:禁止标线'], //答案数组
                  rightKey: 'C' //正确答案
                },
                {
                  serialNumber: '2', //序号
                  title: '路中心的线，属于哪一类标线？', //题目
                  option: ['A: 指示线', 'B:辅助线', 'C:警告线', 'D:禁止线'], //答案数组
                  rightKey: 'C' //正确答案
                },
                {
                  serialNumber: '3', //序号
                  title: '路线，属于哪标线？', //题目
                  option: ['A: 指示', 'B:辅助', 'C:警告', 'D:禁止'], //答案数组
                  rightKey: 'C' //正确答案
                }
              ]
            },
            {
              No: '2',//编号
              questionType: '二、复选题',//题型
              numbenOfQuestions: '30',//总分
              fraction: '2',//每题分值
              data: [ //题目数组
                {
                  serialNumber: '4', //序号
                  title: '路中心的双黄实线，属于哪一类标线？', //题目
                  option: ['A: 指示标线', 'B:辅助标线', 'C:警告标线', 'D:禁止标线'], //答案数组
                  rightKey: 'C' //正确答案
                },
                {
                  serialNumber: '5', //序号
                  title: '路中心的线，属于哪一类标线？', //题目
                  option: ['A: 指示线', 'B:辅助线', 'C:警告线', 'D:禁止线'], //答案数组
                  rightKey: 'C' //正确答案
                },
                {
                  serialNumber: '6', //序号
                  title: '路线，属于哪标线？', //题目
                  option: ['A: 指示', 'B:辅助', 'C:警告', 'D:禁止'], //答案数组
                  rightKey: 'C' //正确答案
                }
              ]
            },
            {
              No: '3',//编号
              questionType: '三、判断题',//题型
              numbenOfQuestions: '30',//总分
              fraction: '2',//每题分值
              data: [ //题目数组
                {
                  serialNumber: '7', //序号
                  title: '路中心的双黄实线，属于哪一类标线？', //题目
                  option: ['A: 指示标线', 'B:辅助标线', 'C:警告标线', 'D:禁止标线'], //答案数组
                  rightKey: 'C' //正确答案
                },
                {
                  serialNumber: '8', //序号
                  title: '路中心的线，属于哪一类标线？', //题目
                  option: ['A: 指示线', 'B:辅助线', 'C:警告线', 'D:禁止线'], //答案数组
                  rightKey: 'C' //正确答案
                },
                {
                  serialNumber: '9', //序号
                  title: '路线，属于哪标线？', //题目
                  option: ['A: 指示', 'B:辅助', 'C:警告', 'D:禁止'], //答案数组
                  rightKey: 'C' //正确答案
                }
              ]
            }
          ]
        }
      }
    },
    methods:{
      initPaper(){
        let that = this
        initPaper({
        }).then(function (res) {
          console.log(res)
          that.result = res
        }).catch(function (err) {
          console.log(err)
        })

      }
    },
    mounted() {
      this.initPaper();
    }
  }
</script>

<style scoped>

</style>