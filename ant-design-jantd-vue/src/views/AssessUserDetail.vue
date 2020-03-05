<template>
  <div id="assessUserDetail">
    <a-card style="background-color: RGB(17,139,208)">
      <a-row type="flex">
        <a-col :span="2">
          <a-avatar class="avatar" :size="85" :src="getAvatar()"/>
        </a-col>
        <a-col :span="7" style="margin-left: 22px">
          <a-row>
            <a-col>
              <span style="color: white;font-weight: bold;font-size: 36px;">
                {{ userInfo.userCode }}
              </span>
            </a-col>
          </a-row>
          <a-row>
            <a-col style="color: white;font-size: 22px">
              <span>
                {{ "真实姓名:" }}
              </span>
              <span style="font-weight: bold;font-size: 24px">
                {{ userInfo.realName }}
              </span>
            </a-col>
          </a-row>
        </a-col>
        <a-col :span="7" style="margin-top: 10px">
          <a-row type="flex">
            <a-col style="color: white;font-size: 22px">
              <span>
                {{ "账户类型:" }}
              </span>
              <sapn style="font-weight: bold;font-size: 26px">
                {{ userInfo.accountType }}
              </sapn>
            </a-col>
            <a-col style="color: white;font-size: 22px;margin-top: 4px">
              <span>
                {{ "培训分组:" }}
              </span>
              <span style="font-weight: bold;font-size: 26px">
                {{ userInfo.trainingGroup }}
              </span>
            </a-col>
          </a-row>
        </a-col>
        <a-col :span="7" style="margin-top: 10px">
          <a-row tyle="flex">
            <a-col style="color: white;font-size: 22px">
              <span>已发试卷数
                <span style="font-weight: bold;font-size: 26px">
                  {{ userInfo.papersIssued }}
                </span>
                份</span>
            </a-col>
          </a-row>
          <a-row type="flex " style="margin-top: 4px">
            <a-col :offset="3">
              <a-button type="primary">
                <span style="font-size: 18px">
                  添加试卷
                </span>
              </a-button>
            </a-col>
          </a-row>
        </a-col>
      </a-row>
    </a-card>
  </div>
</template>

<script>
  import './AssessUserDetail.less'
  import {mapGetters} from 'vuex'
    export default {
        name: "AssessUserDetail",
      data(){
          return{
            userInfo:{
              userCode:'36598523',
              realName: '张飞',
              accountType:'教员',
              trainingGroup:'指挥指控组 ',
              papersIssued: '69'
            }
          }
      },
      methods:{
        ...mapGetters(["avatar"]),
        getAvatar(){
          console.log('url = '+ window._CONFIG['imgDomainURL']+"/"+this.avatar())
          return window._CONFIG['imgDomainURL']+"/"+this.avatar()
        }
      }
    }
</script>

<style scoped>

</style>