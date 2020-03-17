<template>
  <div id="teachProgree" class="teachProgree" style="height: 706px">
    <a-card style="background-color: rgba( 0,0,0,0.2);margin-top: 30px">
      <span slot="title">
        <img src="@/assets/img/jiaoxuejindu.png" style="width: 26px;height: 26px;margin-top: -10px">
        <apn style="color: rgb(12,152,220);margin-left: 12px;font-size: 30px;">
          {{ "教学进度" }}
        </apn>
      </span>
      <template>
        <a-radio-group @change="radioChange" v-model="dictCode" :defaultValue="1">
          <a-row tyle="flex">
            <a-radio value="equip">
              <span style="font-size: 18px;position: relative;bottom:2px">
                {{ "所属装备" }}
              </span>
            </a-radio>
            <a-radio value="staff_group">
              <span style="font-size: 18px;position: relative;bottom:2px">
                {{ "人员分组" }}
              </span>
            </a-radio>
            <a-radio value="position">
              <span style="font-size: 18px;position: relative;bottom:2px">
                {{ "岗位" }}
              </span>
            </a-radio>
          </a-row>
        </a-radio-group>
        <a-select
          style="width: 220px;margin-left: 10px;position: relative;bottom:4px"
          @change="selectChange"
          placeholder="全部"
        >
          <a-select-option v-for="item in selectData" :key="item.itemText">{{ item.itemText }}</a-select-option>
        </a-select>


        <!--<a-row type="flex">-->
        <!--<a-col>-->
        <!--<span>-->
        <!--<span style="color: white;font-size: 18px">{{ "所属装备:" }}</span>-->
        <!--<a-select defaultValue="全部" style="width: 188px;margin-left: 13px;font-size: 18px" @change="handleChange">-->
        <!--<a-select-option value="全部">全部</a-select-option>-->
        <!--<a-select-option value="1">武器系统基本组成</a-select-option>-->
        <!--<a-select-option value="3">通讯专业</a-select-option>-->
        <!--</a-select>-->
        <!--</span>-->
        <!--<span style="margin-left: 20px">-->
        <!--<span style="color: white;font-size: 18px">{{ "人员分组:" }}</span>-->
        <!--<a-select defaultValue="全部" style="width: 188px;margin-left: 13px;font-size: 18px" @change="handleChange">-->
        <!--<a-select-option value="全部">全部</a-select-option>-->
        <!--<a-select-option value="1">武器系统基本组成</a-select-option>-->
        <!--<a-select-option value="3">通讯专业</a-select-option>-->
        <!--</a-select>-->
        <!--</span>-->
        <!--<span style="margin-left: 20px;">-->
        <!--<span style="color: white;font-size: 18px">{{ "岗位:" }}</span>-->
        <!--<a-select defaultValue="全部" style="width: 188px;margin-left: 13px;font-size: 18px" @change="handleChange">-->
        <!--<a-select-option value="全部">全部</a-select-option>-->
        <!--<a-select-option value="1">武器系统基本组成</a-select-option>-->
        <!--<a-select-option value="3">通讯专业</a-select-option>-->
        <!--</a-select>-->
        <!--</span>-->
        <!--</a-col>-->
        <!--</a-row>-->
        <a-row type="flex" style="margin-top: 45px" v-for="item in studentInfos" :key="item">
          <a-col :span="3">
            <span style="color: white;font-size: 24px">
              {{ item.studentName + ":" }}
            </span>
          </a-col>
          <a-col :span="21">
            <a-steps :current="1">
              <a-popover slot="progressDot" slot-scope="{index, status, prefixCls}">
                <template slot="content">
                  <!--<span>step {{ index }} status: {{ status }}</span>-->
                  <span>{{ item.stepInfo[index] }}</span>
                </template>
                <span :class="`${prefixCls}-icon-dot`"></span>
              </a-popover>
              <a-steps :current="item.stepProgree">
                <a-step/>
                <a-step/>
                <a-step/>
                <a-step/>
                <a-step/>
                <a-step/>
                <a-step/>
                <a-step/>
              </a-steps>
            </a-steps>
          </a-col>
        </a-row>
      </template>
    </a-card>
  </div>
</template>

<script>
  import ARadioGroup from "ant-design-vue/es/radio/Group";
  import './TeachProgree.less'
  import {getTeachProgress, getSelectData} from '@/api/assessment.js'

  export default {
    name: "TeachProgram",
    data() {
      return {
        studentInfos: [],
        visible: true,
        selectData: [],
        selectAllData: [],
        dictCode: 'equip',
        itemValue:'1'

      }
    },
    components: {
      ARadioGroup,
    },
    mounted() {
      this.getSelectData()
      this.getTeachProgress(this.dictCode,this.itemValue)
    },
    methods: {
      /**
       * 获取进度条数据
       *
       * @Author 尘埃Friend
       * @date 2019-12-03
       */
      getSelectData() {
        let that = this
        getSelectData({

        }).then(function (res) {
          that.selectAllData = res
          that.radioChange()
          console.log(res)
        }).catch(function (err) {
          console.log(err)
        })
      },
      radioChange() {
        let that = this
        that.selectData = that.selectAllData.filter(function (item) {
          return item.dictCode === that.dictCode.toString()
        })

      },
      selectChange(data){
        let that = this
        that.getTeachProgress(data.dictCode,data.itemValue)
      },
      getTeachProgress(dictCode,itemValue){
        let that = this
        getTeachProgress({
          dictCode: dictCode,
          itemValue:itemValue
        }).then(function (res) {
          console.log(res)
          that.studentInfos = res.result
        }).catch(function (err) {
          console.log(err)
        })
      }
    }
  }
</script>

<style scoped>

</style>