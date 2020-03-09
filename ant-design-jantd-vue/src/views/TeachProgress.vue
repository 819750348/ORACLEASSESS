<template>
  <div id="teachProgree">
    <a-card style="background-color: rgba( 0,0,0,0.2);margin-top: 30px">
      <span slot="title">
        <img src="@/assets/img/jiaoxuejindu.png" style="width: 26px;height: 26px;margin-top: -10px">
        <apn style="color: rgb(12,152,220);margin-left: 12px;font-size: 30px;" >
          {{ "教学进度" }}
        </apn>
      </span>
      <template>
        <a-row type="flex">
          <a-col>
            <span>
              <span style="color: white;font-size: 18px">{{ "所属装备:" }}</span>
              <a-select defaultValue="全部" style="width: 188px;margin-left: 13px;font-size: 18px" @change="handleChange">
                <a-select-option value="全部">全部</a-select-option>
                <a-select-option value="1">武器系统基本组成</a-select-option>
                <a-select-option value="3">通讯专业</a-select-option>
              </a-select>
            </span>
            <span style="margin-left: 20px">
              <span style="color: white;font-size: 18px">{{ "人员分组:" }}</span>
              <a-select defaultValue="全部" style="width: 188px;margin-left: 13px;font-size: 18px" @change="handleChange">
                <a-select-option value="全部">全部</a-select-option>
                <a-select-option value="1">武器系统基本组成</a-select-option>
                <a-select-option value="3">通讯专业</a-select-option>
              </a-select>
            </span>
            <span style="margin-left: 20px;">
              <span style="color: white;font-size: 18px">{{ "岗位:" }}</span>
              <a-select defaultValue="全部" style="width: 188px;margin-left: 13px;font-size: 18px" @change="handleChange">
                <a-select-option value="全部">全部</a-select-option>
                <a-select-option value="1">武器系统基本组成</a-select-option>
                <a-select-option value="3">通讯专业</a-select-option>
              </a-select>
            </span>
          </a-col>
        </a-row>
        <a-row type="flex" style="margin-top: 45px" v-for="item in studentInfos" :key="item" >
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
                <a-step />
                <a-step />
                <a-step />
                <a-step />
                <a-step />
                <a-step />
                <a-step />
                <a-step />
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
    import {getTeachProgress} from '@/api/assessment.js'
    export default {
        name: "TeachProgram",
      data(){
          return{
            studentInfos:[{
                studentName:'张英达',
                stepInfo: ['当前正在1学习','当前正在2学习','当前正在3学习','当前正在4学习','当前正在5学习','当前正在6学习','当前正在7学习','当前正在8学习'],
                stepProgree:1
            },
              {
                  studentName:'张英达',
                  stepInfo: ['当前正在1学习','当前正在2学习','当前正在3学习','当前正在4学习','当前正在5学习','当前正在6学习','当前正在7学习','当前正在8学习'],
                  stepProgree:2
              },
              {
                  studentName:'张英达',
                  stepInfo: ['当前正在1学习','当前正在2学习','当前正在3学习','当前正在4学习','当前正在5学习','当前正在6学习','当前正在7学习','当前正在8学习'],
                  stepProgree:4
              },
              {
                studentName:'张英达',
                stepInfo: ['当前正在1学习','当前正在2学习','当前正在3学习','当前正在4学习','当前正在5学习','当前正在6学习','当前正在7学习','当前正在8学习'],
                stepProgree:4
              },
              {
                studentName:'张英达',
                stepInfo: ['当前正在1学习','当前正在2学习','当前正在3学习','当前正在4学习','当前正在5学习','当前正在6学习','当前正在7学习','当前正在8学习'],
                stepProgree:4
              },
              {
                studentName:'张英达',
                stepInfo: ['当前正在1学习','当前正在2学习','当前正在3学习','当前正在4学习','当前正在5学习','当前正在6学习','当前正在7学习','当前正在8学习'],
                stepProgree:4
              }
            ],
            visible:true
          }
      },
      components: {
        ARadioGroup,
        },
      mounted(){
        getTeachProgress({

        }).then(function (res) {
          console.log(res)
        }).catch(function (err) {
          console.log(err)
        })
      }
    }
</script>

<style scoped>

</style>