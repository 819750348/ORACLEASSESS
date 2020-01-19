<template>
  <div>
    <span style="color: white;font-weight: bold;font-size: 20px">
      {{ "武器演练可视化培训系统" }}
    </span>
    <a-button style="background-color: #257DCE"><a-icon type="question-circle" />理论学习</a-button>
    <a-button style="background-color: #257DCE;margin-left: 20px"><a-icon type="question-circle" />操作培训</a-button>
    <a-button style="background-color: #257DCE;margin-left: 20px"><a-icon type="question-circle" />考核评估</a-button>
    <assess-user></assess-user>
  </div>
</template>

<script>
  import { assessUser } from '@/views/AssessUser'
    export default {
      name: "Assesshead",
      data(){
        return{

        }
      },
      components:{
        assessUser
      }
    }
</script>

<style scoped>

</style>