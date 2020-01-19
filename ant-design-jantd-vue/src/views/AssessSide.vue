<template>
  <div style="width: 100px">
    <a-row>
      <a-col>
        <a-button style="transform-origin:60px 0;transform:rotate(90deg);margin-top: 80px;background-color: #257DCE;padding-right:25px; padding-left: 25px;color: white">首页</a-button>
      </a-col>
    </a-row>
    <a-row>
      <a-col>
        <!--<div style=" transform-origin:60px 0;transform:rotate(90deg); width: 55px;border-width: 0 10px 8px 10px;border-style: none solid solid;border-color: transparent transparent #105a9f;position: absolute;right: 10px;"></div>-->
        <a-button style="transform-origin:60px 0;transform:rotate(90deg);margin-top: 80px;background-color: #257DCE;color: white" @click="teachingManagement">教学管理</a-button>
      </a-col>
    </a-row>
    <a-row>
      <a-col>
        <a-button style="transform-origin:60px 0;transform:rotate(90deg);margin-top: 80px;background-color: #257DCE;color: white">试卷设置</a-button>
      </a-col>
    </a-row>
  </div>
</template>

<script>
    export default {
      name: "AssessSide",
      methods:{
        teachingManagement(){
            this.$store.push({
              name:'teachingManagement',
              params:{

              }
            })
        }
      }
    }
</script>

<style scoped>

</style>