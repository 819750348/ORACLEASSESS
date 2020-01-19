<template>
  <div id="teachProgree ">
    <a-layout>
      <a-layout style="background-color: RGB(10,19,49)">
        <a-row type="flex">
          <a-col :span="13">
            <a-layout-content>
              <a-row>
                <a-col>
                  <assess-user-detail></assess-user-detail>
                </a-col>
                <a-col>
                </a-col>
              </a-row>
              <a-row>
                <a-col>
                  <teach-progress></teach-progress>
                </a-col>
              </a-row>
            </a-layout-content>
          </a-col>
          <a-col :span="9" :offset="1">
            <a-row type="flex">
              <a-col :span="24">
                <examination-paper></examination-paper>
              </a-col>
            </a-row>
            <a-row type="flex">
              <a-col :span="24">
                <examination-analysis></examination-analysis>
              </a-col>
            </a-row>
          </a-col>
        </a-row>
      </a-layout>
    </a-layout>
  </div>
</template>
<script>
  import assessUserDetail from '@/views/AssessUserDetail'
  import teachProgress from '@/views/TeachProgress'
  import examinationPaper from '@/views/ExaminationPaper'
  import examinationAnalysis from '@/views/ExaminationAnalysis'
    export default {
      name: "Assessment",
      components: {

        assessUserDetail,
        teachProgress,
        examinationPaper,
        examinationAnalysis
      },
      data(){
          return{

          }
        }
    }
</script>

<style scoped>

</style>