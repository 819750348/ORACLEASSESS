<template>
  <div id="assessUserDetail">
    <a-card style="background-image: linear-gradient(to right, RGB(14,108,194) , RGB(19,151,211));">
      <a-row type="flex">
        <a-col :span="2">
          <a-avatar class="avatar" :size="85" :src="getAvatar()"/>
        </a-col>
        <a-col :span="7" style="margin-left: 22px">
          <a-row type="flex">
            <a-col >
              <span style="color: white;font-weight: bold;font-size: 36px;">
                {{ userInfo.userName }}
              </span>
            </a-col>
          </a-row>
          <a-row>
            <a-col style="color: white;font-size: 22px">
              <span>
                {{ "真实姓名:" }}
              </span>
              <span style="font-weight: bold;font-size: 24px">
                {{ userInfo.name }}
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
                {{ userInfo.staffTypeStr }}
              </sapn>
            </a-col>
            <a-col style="color: white;font-size: 22px;margin-top: 4px">
              <span>
                {{ "培训分组:" }}
              </span>
              <span style="font-weight: bold;font-size: 26px">
                {{ userInfo.staffGroupStr }}
              </span>
            </a-col>
          </a-row>
        </a-col>
        <a-col :span="7" style="margin-top: 10px">
          <a-row tyle="flex">
            <a-col style="color: white;font-size: 22px">
              <span>已发试卷数
                <span style="font-weight: bold;font-size: 26px">
                  {{ userInfo.examCount }}
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
  import {getAssessUserDetail} from '@/api/assessment.js'

  export default {
    name: "AssessUserDetail",
    data() {
      return {
        userInfo: {
          name: "null",
          staffTypeStr: "null",
          staffGroupStr: "null",
          examCount: 0,
          userName: '0'
        }
      }
    },
    methods: {
      ...mapGetters(["avatar"]),
      getAvatar() {
        console.log('url = ' + window._CONFIG['imgDomainURL'] + "/" + this.avatar())
        return window._CONFIG['imgDomainURL'] + "/" + this.avatar()
      }
    },
    //获取人员初始化数据
    mounted() {
      let that = this
      console.log("获取用户")
      getAssessUserDetail({
      }).then(function (res) {
        console.log(res)
        Object.assign(that.userInfo,res.result)
      }).catch(function (err) {
        console.log(err)
      })
    }
  }
</script>

<style scoped>

</style>