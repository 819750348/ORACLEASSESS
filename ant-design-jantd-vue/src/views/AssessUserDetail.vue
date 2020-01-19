<template>
  <div id="assessUserDetail">
    <a-card style="background-color: RGB(17,139,208)">
      <a-row type="flex">
        <a-col :span="2">
          <a-avatar class="avatar" :size="46" :src="getAvatar()"/>
        </a-col>
        <a-col :span="7" style="margin-left: 22px">
          <a-row>
            <a-col>
              <span style="color: white;font-weight: bold;">
                {{ userInfo.userCode }}
              </span>
            </a-col>
          </a-row>
          <a-row>
            <a-col style="color: white;">
              <span>
                {{ "真实姓名:" }}
              </span>
              <span style="font-weight: bold">
                {{ userInfo.realName }}
              </span>
            </a-col>
          </a-row>
        </a-col>
        <a-col :span="7">
          <a-row type="flex">
            <a-col style="color: white">
              <span>
                {{ "账户类型:" }}
              </span>
              <sapn style="font-weight: bold">
                {{ userInfo.accountType }}
              </sapn>
            </a-col>
            <a-col style="color: white">
              <span>
                {{ "培训分组:" }}
              </span>
              <span style="font-weight: bold">
                {{ userInfo.trainingGroup }}
              </span>
            </a-col>
          </a-row>
        </a-col>
        <a-col :span="7">
          <a-row tyle="flex">
            <a-col style="color: white">
              <span>已发试卷数
                <span style="font-weight: bold">
                  {{ userInfo.papersIssued }}
                </span>
                份</span>
            </a-col>
          </a-row>
          <a-row type="flex">
            <a-col :offset="3">
              <a-button size="small" type="primary">
                <span style="font-size: 12px">
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