<template>
  <!-- 倒计时组件 -->
  <div>
    <!-- 这里是显示还未结束时的内容，这里只是我这得布局，你可以随意。 -->
    <div>
      <a-row type="flex" align="center" justify="center">
        <acol :span="24">
          <span style="font-size: 40px;color: RGB(132,210,250)">
            <span>{{ myHours }}</span> :
            <span>{{ myMinutes }}</span> :
            <span>{{ mySeconds }}</span>
          </span>
        </acol>
      </a-row>
      <a-row type="flex" align="center" justify="center" style="margin-bottom: 20px;margin-top: 20px">
        <acol :span="24">
          <span>
            <img src=".././assets/img/tijiaoshijuan.png" style="width: 120px;height: 38px;">
          </span>
        </acol>
      </a-row>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'DownTime',
    data () {
      return {
        isShow: false, // 控制显示结束或还未结束显示的内容
        clocker: '', // 结束后显示的内容
        timeObj: null, // 时间对象,下方会用到
        myDay: 0, // 我定义来接收计算出来的 天 的
        myHours: 0, // 我定义来接收计算出来的 小时 的
        myMinutes: 0, // 我定义来接收计算出来的 分钟 的
        mySeconds: 0// 我定义来接收计算出来的 秒钟 的
      }
    },

    mounted () {
      const that = this
      // 计算时间差
      let timeLag = 7200000 / 1000
      // 判断当前是否时分秒的值是否大于10
      let add = num => {
        return num < 10 ? '0' + num : num
      }
      // 时间倒计时运算的方法
      let timeFunction = () => {
        let time = timeLag--
        that.timeObj = { // 时间对象
          seconds: Math.floor(time % 60),
          minutes: Math.floor(time / 60) % 60,
          hours: Math.floor(time / 60 / 60) % 24,
          days: Math.floor(time / 60 / 60 / 24)
        }
        // 计算出时分秒
        that.myDay = `${add(that.timeObj.days)}`
        that.myHours = `${add(that.timeObj.hours)}`
        that.myMinutes = `${add(that.timeObj.minutes)}`
        that.mySeconds = `${add(that.timeObj.seconds)}`
        // 当时间差小于等于0时,停止倒计时
        if (time <= 0) {
          that.isShow = true
          that.clocker = that.endMsg || '该团已经结束'
          clearInterval(go)
        }
      }
      // 开始执行倒计时
      timeFunction()
      // 每一秒执行一次
      let go = setInterval(() => {
        timeFunction()
      }, 1000)
    }
  }
</script>

<style scoped>

</style>
