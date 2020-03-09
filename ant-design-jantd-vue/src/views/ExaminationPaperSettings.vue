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
          <span style="margin-left: 10px">
            <a-input style="width: 210px" placeholder="" />
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
            <a-input style="width: 210px" placeholder="" />
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
              <span style="color: white">
                {{ "自选考题" }}
              </span>
              <a-divider type="vertical" />
              <span style="color: white">
                {{ "随机考题" }}
              </span>
            </template>
          </a-table>
        </a-col>
      </a-row>
    </a-card>
  </div>
</template>

<script>
  import './ExaminationPaperSettings.less'
  const columns = [
    {
      title: 'Name',
      dataIndex: 'name',
      scopedSlots: { customRender: 'name' },
    },
    {
      title: 'Cash Assets',
      className: 'column-money',
      dataIndex: 'money',
    },
    {
      title: 'Address',
      dataIndex: 'address',
    },
  ];

  const data = [
    {
      key: '1',
      name: 'John Brown',
      money: '￥300,000.00',
      address: 'New York No. 1 Lake Park',
    },
    {
      key: '2',
      name: 'Jim Green',
      money: '￥1,256,000.00',
      address: 'London No. 1 Lake Park',
    },
    {
      key: '3',
      name: 'Joe Black',
      money: '￥120,000.00',
      address: 'Sidney No. 1 Lake Park',
    },
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