<template>
  <div>
    <a-card style="background-color: RGB(2,31,73);height: 957px">
      <a-row type="flex" align="middle" justify="center" style="color: RGB(122,202,245)">
        <img src="@/assets/img/paper.png" style="width: 26px;height: 26px;">
        <sapn style="font-size: 30px;color:RGB(122,202,245);margin-left: 10px;">
          {{paperName}}
        </sapn>
        <span style="font-size: 24px;margin-left: 30px">
          {{'考试时间:'}}
        </span>
        <span style="font-size: 24px;margin-left: 15px">
          {{paperTime}}
        </span>
      </a-row>
      <a-row style="color: RGB(122,202,245);font-size: 24px;margin-top: 60px">
        <span>
          {{"一、"}}
        </span>
        <span>
          {{paper.questionType}}
        </span>
        <span>
          {{"。"}}
        </span>
        <span>
          {{'（共'}}
        </span>
        <span>
          {{paper.numbenOfQuestions}}
        </span>
        <span>
          {{'题'}}
        </span>
        <span>
          {{'每题'}}
        </span>
        <span>
          {{paper.fraction}}
        </span>
        <span>
          {{'分）'}}
        </span>
      </a-row>
      <a-row v-for='(item,key) in paper.data' :key='key'
             style="color: RGB(122,202,245);font-size: 18px;margin-left: 55px;margin-top: 70px">
        <a-row>
        <span>
          {{item.serialNumber}}
        </span>
          <span>
          {{'.'}}
        </span>
          <span>
          {{item.title}}
        </span>
        </a-row>
        <a-row style="color: RGB(122,202,245);font-size: 16px;margin-left: 5px;margin-top: 20px">
        <span v-for="(i,key) in item.option" :key="key" style="margin-left: 20px">
          {{i}}
        </span>
        </a-row>
        <a-row style="color: RGB(122,202,245);font-size: 18px;margin-top: 20px;">
        <span>
          {{'正确答案:'}}
        </span>
          <span>
          {{item.rightKey}}
        </span>
          <span style="margin-left: 40px;color: RGB(233,122,129)">
          {{'错误答案:'}}
        </span>
          <span style="color:RGB(233,122,129)">
          {{item.stuKey}}
        </span>
        </a-row>
      </a-row>

    </a-card>
  </div>
</template>

<script>
  export default {
    name: "paper",
    data() {
      return {
        paperName: '集成测试试卷',
        paperTime: '2020-02-02 02:02:02',
        paper: {
          questionType: '单选题',
          numbenOfQuestions: '30',
          fraction: '2',
          data: [
            {
              serialNumber: '1',
              title: '路中心的双黄实线，属于哪一类标线？',
              option: [
                'A: 指示标线', 'B:辅助标线', 'C:警告标线', 'D:禁止标线'
              ],
              rightKey: 'C',
              stuKey: 'A'
            },
            {
              serialNumber: '2',
              title: '上道路行驶的机动车有哪种情形交通警察可依法扣留车辆？',
              option: [
                'A:未携带机动车登记证书', 'B:未携带保险合同', 'C:未放置保险标志', 'D:未放置城市环保标志'
              ],
              rightKey: 'A',
              stuKey: 'B'
            },
            {
              serialNumber: '3',
              title: '驾驶机动车在高速公路上行驶，遇低能见度气象条件时，能见度在200米以下，车速不得超过每小时多少公里，与同车道前车至少保持多少米的距离？',
              option: [
                'A:60，100', 'B:70，100', 'C:40，80', 'D:30，80'
              ],
              rightKey: 'B',
              stuKey: 'A'
            },
            {
              serialNumber: '4',
              title: '假如您在高速公路上不小心错过了准备驶出的路口，正确的操作应该是？',
              option: [
                'A:紧急刹车，倒车至想要驶出的路口', 'B:继续前行，到下一路口驶离高速公路掉头', 'C:在应急停车道上停车，等待车辆较少的时候再伺机倒车', 'D:借用应急车道进行掉头，逆向行驶'
              ],
              rightKey: 'D',
              stuKey: 'B'
            }
          ]

        }
      }
    }
  }
</script>

<style scoped>

</style>