<template>
  <div>
    <a-row>
      <a-col :span="2">
        <assess-side/>
      </a-col>
      <a-row>
        <a-col :span="20">
          <route-view></route-view>
          <!--<assessment></assessment>-->
          <teaching-management></teaching-management>
        </a-col>
      </a-row>
    </a-row>
  </div>
</template>

<script>
  import RouteView from "../components/layouts/RouteView";
  import AssessSide from  './AssessSide'
  import Assessment from './Assessment'
  import TeachingManagement from  './TeachingManagement.vue'
  export default {
    components: {
      RouteView,
      AssessSide,
      Assessment,
      TeachingManagement
    }
  }
</script>

<style scoped>

</style>