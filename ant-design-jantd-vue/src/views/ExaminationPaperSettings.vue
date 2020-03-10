<template>
  <div id="examinationPaperSettings">
    <a-card style="background-color: RGB(10,19,49)">
      <a-row style="padding: 30px 0 10px 0">
        <a-tabs>
          <a-tab-pane tab="教员管理" key="1"/>
          <a-tab-pane tab="学员管理" key="2"/>
        </a-tabs>
      </a-row>
      <a-row type="flex" style="margin-top: 20px;margin-bottom: 30px" justify="center" align="center">
        <a-col :span="24">
          <span style="color: RGB(2,144,215);font-size: 24px">
            {{ "指定所属装备" }}
          </span>
          <span style="color: RGB(2,144,215);margin: 0 10px 0 10px;font-size: 24px">
            {{ ":" }}
          </span>
          <span style="margin-left: 10px">
            <a-select
              mode="multiple"
              style="width: 210px"
              placeholder="请选择"
              maxTagCount="0"
              notFoundContent="空"
              maxTagPlaceholder="请选择"
            >
              <a-select-option
                v-for="(item, i) in equipmentOption"
                :key="item"
              >
                <a-checkbox @change="changeSelect(i)" :checked="item.visible">
                  {{ item.name }}
                </a-checkbox>
              </a-select-option>
            </a-select>
            <!--<a-select defaultValue="指挥指控车" style="width: 220px" @change="handleChange">-->
            <!--<div slot="dropdownRender" slot-scope="menu">-->
            <!--<v-nodes :vnodes="menu" />-->
            <!--&lt;!&ndash;<a-divider style="margin: 4px 0;" />&ndash;&gt;-->
            <!--<div style="padding: 8px; cursor: pointer;">-->
            <!--<a-checkbox-group :options="plainOptions" v-model="checkedList" @change="onChange" />-->
            <!--</div>-->
            <!--</div>-->
            <!--&lt;!&ndash;<a-select-option value="指挥指控车">指挥指控车</a-select-option>&ndash;&gt;-->
            <!--&lt;!&ndash;<a-select-option value="作战拦截操作手">作战拦截操作手</a-select-option>&ndash;&gt;-->
            <!--</a-select>-->
          </span>
          <span style="margin-left: 10px">
            <span v-for="(item , i) in subordinateEquipment" :key="item">
              <a-tag closable v-model="item.visible" @close="closeTags(i)">
                {{ item.name }}
              </a-tag>
            </span>
          </span>
        </a-col>
      </a-row>
      <a-row type="flex" style="margin-top: 20px;margin-bottom: 30px" justify="center" align="center">
        <a-col :span="24">
          <span style="color: RGB(2,144,215);font-size: 24px;margin-left: 48px">
            {{ "指定岗位" }}
          </span>
          <span style="color: RGB(2,144,215);margin: 0 10px 0 10px;font-size: 24px">
            {{ ":" }}
          </span>
          <span style="margin-left: 10px">
            <a-select
              mode="multiple"
              style="width: 210px"
              placeholder="请选择"
              maxTagCount="0"
              notFoundContent="空"
              maxTagPlaceholder="请选择"
            >
              <a-select-option
                v-for="(item, i) in equipmentOption"
                :key="item"
              >
                <a-checkbox @change="changeSelect(i)" :checked="item.visible">
                  {{ item.name }}
                </a-checkbox>
              </a-select-option>
            </a-select>
            <!--<a-select defaultValue="指挥指控车" style="width: 220px" @change="handleChange">-->
            <!--<div slot="dropdownRender" slot-scope="menu">-->
            <!--<v-nodes :vnodes="menu" />-->
            <!--&lt;!&ndash;<a-divider style="margin: 4px 0;" />&ndash;&gt;-->
            <!--<div style="padding: 8px; cursor: pointer;">-->
            <!--<a-checkbox-group :options="plainOptions" v-model="checkedList" @change="onChange" />-->
            <!--</div>-->
            <!--</div>-->
            <!--&lt;!&ndash;<a-select-option value="指挥指控车">指挥指控车</a-select-option>&ndash;&gt;-->
            <!--&lt;!&ndash;<a-select-option value="作战拦截操作手">作战拦截操作手</a-select-option>&ndash;&gt;-->
            <!--</a-select>-->
          </span>
          <span style="margin-left: 10px">
            <span v-for="(item , i) in subordinateEquipment" :key="item">
              <a-tag closable v-model="item.visible" @close="closeTags(i)">
                {{ item.name }}
              </a-tag>
            </span>
          </span>
        </a-col>
      </a-row>
      <a-row type="flex" style="margin-top: 20px;margin-bottom: 30px" justify="center" align="center">
        <a-col :span="24">
          <span style="color: RGB(2,144,215);font-size: 24px;margin-left: 48px">
            {{ "考试时间" }}
          </span>
          <span style="color: RGB(2,144,215);margin: 0 10px 0 10px;font-size: 24px">
            {{ ":" }}
          </span>
          <span style="margin-left: 10px" class="write">
            <a-input style="width: 210px" placeholder=""/>
            <span style="font-size: 14px;color: white">
              {{ "分钟" }}
            </span>
          </span>
          <span style="color: RGB(2,144,215);font-size: 24px;margin-left: 48px">
            {{ "本卷总分" }}
          </span>
          <span style="color: RGB(2,144,215);margin: 0 10px 0 10px;font-size: 24px">
            {{ ":" }}
          </span>
          <span style="margin-left: 10px">
            <a-input style="width: 210px" class="read" disabled placeholder=""/>
            <span style="font-size: 14px;color: white">
              {{ "分" }}
            </span>
          </span>
        </a-col>
      </a-row>
      <a-row type="flex" style="margin-top: 20px;margin-bottom: 30px">
        <a-col :span="3">
          <span style="color: RGB(2,144,215);font-size: 24px;margin-left: 48px">
            {{ "题型占比" }}
          </span>
          <span style="color: RGB(2,144,215);margin: 0 10px 0 10px;font-size: 24px">
            {{ ":" }}
          </span>
        </a-col>
        <a-col :span="19">
          <a-table :columns="columns" :dataSource="data" bordered :pagination="false">
            <template slot="title">
              <a style="color: white;margin-left: 58px">
                {{ "自选考题" }}
              </a>
              <!--<a-divider type="vertical"/>-->
              <a style="color: white;position: relative;margin-left:150px;">
                {{ "随机考题" }}
              </a>
              <div
                style="position: relative;margin-left:160px;width: 88px;border-bottom:1px solid RGB(19,61,110);-moz-transform: rotate(90deg);-webkit-transform: rotate(90deg);"></div>
            </template>
            <!--style="transform:rotate(25deg);"-->
            <span slot="customTitle">
              <span style="position: relative;left: 80px;bottom: 16px;">
                {{ "题目" }}
              </span>
              <span style="position: relative;top: 16px;right: 80px">
                {{ "题型" }}
              </span>
              <!--<span style="border:1px solid red; "></span>-->
              <div
                style="position: relative;bottom:13px;margin-left:-20px;margin-right:-20px;border-bottom:1px solid RGB(19,61,110);-moz-transform: rotate(17deg);-webkit-transform: rotate(17deg);"></div>
            </span>
            <span slot="zhkzc">
              <a-select
                style="width: 120px"
                defaultValue="10"
              >
                <a-select-option value="10">10%</a-select-option>
                <a-select-option value="20">20%</a-select-option>
                <a-select-option value="30">30%</a-select-option>
                <a-select-option value="40">40%</a-select-option>
                <a-select-option value="50">50%</a-select-option>
                <a-select-option value="60">60%</a-select-option>
                <a-select-option value="70">70%</a-select-option>
                <a-select-option value="80">80%</a-select-option>
                <a-select-option value="90">90%</a-select-option>
                <a-select-option value="100">100%</a-select-option>
              </a-select>
            </span>
            <span slot="xkxt">
              <a-select
                style="width: 120px"
                defaultValue="10"
              >
                <a-select-option value="10">10%</a-select-option>
                <a-select-option value="20">20%</a-select-option>
                <a-select-option value="30">30%</a-select-option>
                <a-select-option value="40">40%</a-select-option>
                <a-select-option value="50">50%</a-select-option>
                <a-select-option value="60">60%</a-select-option>
                <a-select-option value="70">70%</a-select-option>
                <a-select-option value="80">80%</a-select-option>
                <a-select-option value="90">90%</a-select-option>
                <a-select-option value="100">100%</a-select-option>
              </a-select>
            </span>
            <span slot="txxt">
              <a-select
                style="width: 120px"
                defaultValue="10"
              >
                <a-select-option value="10">10%</a-select-option>
                <a-select-option value="20">20%</a-select-option>
                <a-select-option value="30">30%</a-select-option>
                <a-select-option value="40">40%</a-select-option>
                <a-select-option value="50">50%</a-select-option>
                <a-select-option value="60">60%</a-select-option>
                <a-select-option value="70">70%</a-select-option>
                <a-select-option value="80">80%</a-select-option>
                <a-select-option value="90">90%</a-select-option>
                <a-select-option value="100">100%</a-select-option>
              </a-select>
            </span>
            <span slot="xtjg">
              <a-select
                style="width: 120px"
                defaultValue="10"
              >
                <a-select-option value="10">10%</a-select-option>
                <a-select-option value="20">20%</a-select-option>
                <a-select-option value="30">30%</a-select-option>
                <a-select-option value="40">40%</a-select-option>
                <a-select-option value="50">50%</a-select-option>
                <a-select-option value="60">60%</a-select-option>
                <a-select-option value="70">70%</a-select-option>
                <a-select-option value="80">80%</a-select-option>
                <a-select-option value="90">90%</a-select-option>
                <a-select-option value="100">100%</a-select-option>
              </a-select>
            </span>
          </a-table>5
        </a-col>
      </a-row>
      <a-row type="flex" justify="center" style="margin-top: 80px;margin-bottom: 50px">
        <a-col>
          <img
            src="@/assets/img/shengchengshijuan.png"
            style="width: 140px;height: 50px;margin-top: -2px"
            @click="lowerHair">
        </a-col>
      </a-row>
    </a-card>

  </div>
</template>

<script>
  import './ExaminationPaperSettings.less'

  const columns = [
    {
      dataIndex: 'type',
      slots: {title: 'customTitle'},
      scopedSlots: {customRender: 'type'},
      align: 'center'

    },
    {
      title: '指挥控制车',
      dataIndex: 'zhkzc',
      scopedSlots: {customRender: 'zhkzc'},
      align: 'center'
    },
    {
      title: '显控系统',
      dataIndex: 'xkxt',
      scopedSlots: {customRender: 'xkxt'},
      align: 'center'
    },
    {
      title: '通讯系统',
      dataIndex: 'txxt',
      scopedSlots: {customRender: 'txxt'},
      align: 'center'
    },
    {
      title: '系统接口',
      dataIndex: 'xtjg',
      scopedSlots: {customRender: 'xtjg'},
      align: 'center'
    }
  ];

  const data = [
    {
      key: '1',
      type: '单选'
    },
    {
      key: '2',
      type: '多选'
    },
    {
      key: '3',
      type: '判断'
    }
  ];
  export default {
    name: "ExaminationPaperSettings",
    data() {
      return {
        data,
        columns,
        subordinateEquipment: [],
        equipmentOption: [
          {
            name: '指挥控制操作手',
            visible: false
          },
          {
            name: '作战拦截操作手',
            visible: false
          },
          {
            name: '空情处理操作手',
            visible: false
          },
          {
            name: '指挥控制操作手',
            visible: false
          },
          {
            name: '作战拦截操作手',
            visible: false
          },
          {
            name: '空情处理操作手',
            visible: false
          }, {
            name: '指挥控制操作手',
            visible: false
          },
          {
            name: '作战拦截操作手',
            visible: false
          },
          {
            name: '空情处理操作手',
            visible: false
          }, {
            name: '指挥控制操作手',
            visible: false
          },
          {
            name: '作战拦截操作手',
            visible: false
          },
          {
            name: '空情处理操作手',
            visible: false
          }, {
            name: '指挥控制操作手',
            visible: false
          },
          {
            name: '作战拦截操作手',
            visible: false
          },
          {
            name: '空情处理操作手',
            visible: false
          }, {
            name: '指挥控制操作手',
            visible: false
          },
          {
            name: '作战拦截操作手',
            visible: false
          },
          {
            name: '空情处理操作手',
            visible: false
          }, {
            name: '指挥控制操作手',
            visible: false
          },
          {
            name: '作战拦截操作手',
            visible: false
          },
          {
            name: '空情处理操作手',
            visible: false
          },
          {
            name: '指挥控制操作手',
            visible: false
          },
          {
            name: '作战拦截操作手',
            visible: false
          },
          {
            name: '空情处理操作手',
            visible: false
          }, {
            name: '指挥控制操作手',
            visible: false
          },
          {
            name: '作战拦截操作手',
            visible: false
          },
          {
            name: '空情处理操作手',
            visible: false
          },
          {
            name: '指挥控制操作手',
            visible: false
          },
          {
            name: '作战拦截操作手',
            visible: false
          },
          {
            name: '空情处理操作手',
            visible: false
          }, {
            name: '指挥控制操作手',
            visible: false
          },
          {
            name: '作战拦截操作手',
            visible: false
          },
          {
            name: '空情处理操作手',
            visible: false
          }, {
            name: '指挥控制操作手',
            visible: false
          },
          {
            name: '作战拦截操作手',
            visible: false
          },
          {
            name: '空情处理操作手',
            visible: false
          }
        ]
      }
    },
    methods: {
      // changeEquipment(value, value1) {
      //   console.log(value, value1)
      //   this.subordinateEquipment = value
      // },
      closeTags(value, value2, value3) {
        // this.subordinateEquipment.splice(value,1)
        this.equipmentOption = this.subordinateEquipment
        console.log(value, value2, value3)
      },
      changeSelect(i) {
        if (this.equipmentOption[i].visible === false) {
          this.equipmentOption[i].visible = true
        } else if (this.equipmentOption[i].visible === true) {
          this.equipmentOption[i].visible = false
        }
        this.subordinateEquipment = this.equipmentOption
      }
    }
  }
</script>

<style scoped>

</style>