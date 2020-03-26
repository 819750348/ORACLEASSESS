<template>
  <div id="teachingManagement">
    <a-card style="background-color: RGB(10,19,49);height: 957px;">
      <a-row type="flex" style="padding: 30px 0 10px 0">
        <a-col :span="24">
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
            placeholder="全部"
            class="fontSize"
          >
            <a-select-option v-for="item in selectData" :key="item.itemText" class="fontSize">{{ item.itemText }}
            </a-select-option>
          </a-select>
          <span style="margin-left: 20px;position: relative;bottom:4px">
            <a-input class="fontSize" placeholder="请输入关键字" style="width: 210px;margin-left: 10px"/>
            <a-button style="margin-left: 10px;position: relative;bottom:4px">
              <a-icon type="search"/>
            </a-button>
          </span>
        </a-col>
      </a-row>
      <a-row type="flex" style="margin-top: 20px;margin-bottom: 30px" justify="center" align="center">
        <a-col :span="12">
          <a-table :dataSource="userData" :pagination="false" :columns="userDataColumns" :rowSelection="rowSelection"
                   :customRow="rowClick">
            <span slot="footer">
              <a-pagination
                size="small"
                style="text-align: center;"
                :total="10"
                :showSizeChanger="false"
                showQuickJumper
                :showTotal="total => `共 ${total} 条`"/>
            </span>
          </a-table>
        </a-col>
        <a-col :span="2">
          <img src="@/assets/img/jiaotou3.png" style="width: 28px;height: 28px;margin-top: 300px;margin-left: 50px">
        </a-col>
        <a-col :span="10">
          <a-table :dataSource="courseData" :pagination="false" :columns="courseColumns">
            <span slot="status" slot-scope="text,record">
              <span v-if="text ==='1'">
                <img src="@/assets/img/yiwancheng.png" style="position:relative;bottom:2px;width: 14px;height: 14px;">
                <span style="margin-left: 6px">{{ "已完成" }}</span>
              </span>
              <span v-else>
                <img src="@/assets/img/weiwancheng.png" style="position:relative;bottom:2px;width: 14px;height: 14px;">
                <sapn style="margin-left: 6px">{{ "未完成" }}</sapn>
              </span>
            </span>
            <a slot="edit" href="javascript:;">
              <img src="@/assets/img/shangyi.png" style="width: 12px;height: 16px;">
              <img src="@/assets/img/shanchu.png" style="width: 12px;height: 16px;margin-left: 10px">
            </a>
            <span slot="footer">
              <a-row type="flex">
                <a-col :span="20">
                  <a-pagination
                    size="small"
                    style="text-align: center;"
                    :total="10"
                    :showSizeChanger="false"
                    showQuickJumper
                    :showTotal="total => `共 ${total} 条`"/>
                </a-col>
                <a-col :span="4">
                  <a-button type="primary " @click="visibleModal">{{ "课程管理" }}</a-button>
                </a-col>
              </a-row>
            </span>
          </a-table>
        </a-col>
      </a-row>
    </a-card>
    <a-modal
      v-model="visible"
      width="1200px"
    >
      <span slot="title">
        <img src="@/assets/img/shezhi.png" style="width: 26px;height: 26px;position: relative;bottom: 3px">
        <span style="font-size: 22px;margin-left: 12px;">{{ "课程设置" }}</span>
      </span>
      <div>
        <a-row type="flex">
          <a-col :span="24">
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
              placeholder="全部"
              class="fontSize"
            >
              <a-select-option v-for="item in selectData" :key="item.itemText" class="fontSize">{{ item.itemText }}
              </a-select-option>
            </a-select>
            <span style="margin-left: 20px;position: relative;bottom:4px">
              <a-input class="fontSize" placeholder="" style="width: 210px;margin-left: 10px"/>
              <a-button style="margin-left: 10px;position: relative;bottom:4px">
                <a-icon type="search"/>
              </a-button>
            </span>
          </a-col>
        </a-row>
      </div>
      <div>
        <a-checkbox-group :options="plainOptions" v-model="checkedList" @change="onChange"/>
      </div>
      <span slot="footer">
        <a-row>
          <a-col :span="24">
            <a-pagination
              style="text-align: center;"
              size="small"
              :total="10"
              :showSizeChanger="false"
              showQuickJumper
              :showTotal="total => `共 ${total} 条`"/>
          </a-col>
        </a-row>
      </span>
    </a-modal>
  </div>
</template>

<script>
  const rowSelection = {
    onChange: (selectedRowKeys, selectedRows) => {
      console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
    },
    onSelect: (record, selected, selectedRows) => {
      console.log(record, selected, selectedRows);
    },
    onSelectAll: (selected, selectedRows, changeRows) => {
      console.log(selected, selectedRows, changeRows);
    },
  };
  const userDataColumns = [
    {
      title: "姓名",
      dataIndex: 'name',
      scopedSlots: {customRender: 'name'},
      align: 'center'
    },
    {
      title: "用户编号",
      dataIndex: 'userCode',
      scopedSlots: {customRender: 'userCode'},
      align: "center"
    },
    {
      title: "所属装备",
      dataIndex: 'equid',
      scopedSlots: {customRender: 'equid'},
      align: "center",
    }, {
      title: "岗位",
      dataIndex: 'station',
      scopedSlots: {customRender: 'station'},
      align: 'center'
    },
    {
      title: "人员分组",
      dataIndex: 'personnelGrouping',
      scopedSlots: {customRender: 'personnelGrouping'},
      align: 'center'
    }
  ]


  const userData = [
    {
      name: '李珊珊',
      userCode: '32564723',
      equid: '指控指挥车',
      station: ' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name: '赵雪',
      userCode: '56743452',
      equid: '指控指挥车',
      station: ' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name: '李玲玲',
      userCode: '36234671',
      equid: '指控指挥车',
      station: ' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name: '王苗苗',
      userCode: '67452341',
      equid: '指控指挥车',
      station: ' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name: '鹏宇',
      userCode: '23123545',
      equid: '指控指挥车',
      station: ' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name: '解灵灵',
      userCode: '45623451',
      equid: '指控指挥车',
      station: ' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name: '张谢成',
      userCode: '23411467',
      equid: '指控指挥车',
      station: ' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name: '王佳佳',
      userCode: '89765146',
      equid: '指控指挥车',
      station: ' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name: '张伟',
      userCode: '23126732',
      equid: '指控指挥车',
      station: ' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name: '赵燕',
      userCode: '78873213',
      equid: '指控指挥车',
      station: ' 指挥官',
      personnelGrouping: '指挥发射组'
    }
  ]
  const courseColumns = [
    {
      title: '序号',
      dataIndex: 'No',
      scopedSlots: {customRender: 'No'},
      align: 'center'
    },
    {
      title: '课程',
      dataIndex: 'course',
      scopedSlots: {customRender: 'course'},
      align: 'center'
    },
    {
      title: '状态',
      dataIndex: 'status',
      scopedSlots: {customRender: 'status'},
      align: 'center'
    },
    {
      title: '编辑',
      dataIndex: 'edit',
      scopedSlots: {customRender: 'edit'},
      align: 'center'
    }
  ]


  const courseData = [
    {
      No: '1',
      course: '武器系统和导弹概述',
      status: '1'
    },
    {
      No: '2',
      course: '武器系统工作状态',
      status: '1'
    },
    {
      No: '3',
      course: '指挥车作战使命',
      status: '1'
    },
    {
      No: '4',
      course: '武器系统基本组成',
      status: '1'
    },
    {
      No: '5',
      course: '武器系统工作原理、作战方式',
      status: '0'
    },
    {
      No: '6',
      course: '指挥车战机指标',
      status: '0'
    },
    {
      No: '7',
      course: '武器系统作战使命',
      status: '0'
    },
    {
      No: '8',
      course: '指挥车系统概述',
      status: '0'
    },
    {
      No: '9',
      course: '指挥车工作过程和工作原理',
      status: '0'
    },
    {
      No: '10',
      course: '空勤处理操作手',
      status: '已完成'
    }
  ]

  const plainOptions = [
    '武器系统和导弹概述', '武器系统工作状态', '指挥车作战使命',
    '武器系统基本组成', '武器系统工作原理、作战方式', '指挥车战机指标',
    '武器系统作战使命', '指挥车系统概述', '指挥车工作过程和工作原理',
    '指挥车控制操作手', '作战拦截操作手', '空勤处理操作手',
    '通信专业', '电子雷达专业', '机电专业'
  ]

  import './TeachingManagement.less'
  import './antTable.less'

  export default {
    name: "TeachingManagement",
    data() {
      return {
        userData,
        courseData,
        userDataColumns,
        courseColumns,
        rowSelection,
        rowClick: record => ({
          on: {
            click: () => {
              console.log(record, 'record')
              this.courseData = [
                {
                  No: '1',
                  course: '指挥车系统概述',
                  status: '1'
                },
                {
                  No: '2',
                  course: '指挥车工作过程和工作原理',
                  status: '1'
                },
                {
                  No: '3',
                  course: '设计原理',
                  status: '1'
                },
                {
                  No: '4',
                  course: '指挥车战机指标',
                  status: '1'
                },
                {
                  No: '5',
                  course: '故障排查',
                  status: '0'
                },
                {
                  No: '6',
                  course: '武器系统基本组成',
                  status: '0'
                },
                {
                  No: '7',
                  course: '武器系统工作状态',
                  status: '0'
                },
                {
                  No: '8',
                  course: '空勤处理操作手',
                  status: '0'
                },
                {
                  No: '9',
                  course: '电子雷达专业',
                  status: '0'
                },
                {
                  No: '10',
                  course: '武器系统工作原理、作战方式',
                  status: '已完成'
                }
              ]
            }
          }
        }),
        /**
         * 课程管理模态框
         *
         * @Author 尘埃Friend
         * @date 2019-12-03
         */
        visible: false,
        plainOptions,
        /**
         * modal选中类容
         *
         * @Author 尘埃Friend
         * @date 2019-12-03
         */
        checkedList: '',
        dictCode: 'equip'
      }
    },
    methods: {
      visibleModal() {
        this.visible = true
      },
      initPersonnel(){

      }
    },
    mounted() {
      this.initPersonnel()
    }
  }
</script>

<style scoped>
  .fontSize {
    font-size: 18px;
  }
</style>