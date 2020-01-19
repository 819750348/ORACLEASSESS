<template>
  <div id="teachProgree">
    <a-card style="background-color: rgba( 0,0,0,0.2);margin-top: 30px">
      <span slot="title">
        <img src=".././assets/img/jiaoxuejindu.png" style="width: 18px;height: 18px;margin-top: -2px">
        <apn style="color: rgb(12,152,220);margin-left: 5px">
          {{ "教学进度" }}
        </apn>
      </span>
      <template>
        <a-row type="flex">
          <a-col>
            <span>
              <span style="color: white">{{ "所属装备:" }}</span>>
              <a-select defaultValue="全部" style="width: 138px" @change="handleChange">
                <a-select-option value="全部">全部</a-select-option>
                <a-select-option value="1">武器系统基本组成</a-select-option>
                <a-select-option value="3">通讯专业</a-select-option>
              </a-select>
            </span>
            <span style="margin-left: 20px">
              <span style="color: white">{{ "人员分组:" }}</span>>
              <a-select defaultValue="全部" style="width: 138px" @change="handleChange">
                <a-select-option value="全部">全部</a-select-option>
                <a-select-option value="1">武器系统基本组成</a-select-option>
                <a-select-option value="3">通讯专业</a-select-option>
              </a-select>
            </span>
            <span style="margin-left: 20px;">
              <span style="color: white">{{ "岗位:" }}</span>>
              <a-select defaultValue="全部" style="width: 138px" @change="handleChange">
                <a-select-option value="全部">全部</a-select-option>
                <a-select-option value="1">武器系统基本组成</a-select-option>
                <a-select-option value="3">通讯专业</a-select-option>
              </a-select>
            </span>
          </a-col>
        </a-row>
        <a-row type="flex" style="margin-top: 30px" v-for="item in studentInfos" :key="item" >
          <a-col :span="2">
            <span style="color: white">
              {{ item.studentName + ":" }}
            </span>
          </a-col>
          <a-col :span="18" style="margin-left: 10px">
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
        }
    }
</script>

<style scoped>

</style>