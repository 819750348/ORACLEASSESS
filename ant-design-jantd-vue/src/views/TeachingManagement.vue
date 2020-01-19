<template>
  <div id="teachingManagement">
    <a-card style="background-color: RGB(10,19,49)">
      <a-row>
        <a-col>
          <span>
            <span style="color: white">{{ "所属装备:" }}</span>
            <a-select defaultValue="全部" style="width: 180px;margin-left: 10px" @change="handleChange">
              <a-select-option value="全部">全部</a-select-option>
              <a-select-option value="1">武器系统基本组成</a-select-option>
              <a-select-option value="3">通信专业</a-select-option>
            </a-select>
          </span>
          <span style="margin-left: 20px">
            <span style="color: white">{{ "人员分组:" }}</span>
            <a-select defaultValue="全部" style="width: 180px;margin-left: 10px" @change="handleChange">
              <a-select-option value="全部">全部</a-select-option>
              <a-select-option value="1">武器系统基本组成</a-select-option>
              <a-select-option value="3">通信专业</a-select-option>
            </a-select>
          </span>
          <span style="margin-left: 20px;">
            <span style="color: white">{{ "岗位:" }}</span>
            <a-select defaultValue="全部" style="width: 180px;margin-left: 10px" @change="handleChange">
              <a-select-option value="全部">全部</a-select-option>
              <a-select-option value="1">武器系统基本组成</a-select-option>
              <a-select-option value="3">通信专业</a-select-option>
            </a-select>
          </span>
          <span style="margin-left: 20px">
            <a-input placeholder="请输入试卷名称" style="width: 180px;margin-left: 10px"/>
            <a-button style="margin-left: 10px">
              <a-icon type="search" />
            </a-button>
          </span>
        </a-col>
      </a-row>
      <a-row type="flex" style="margin-top: 20px" justify="center" align="center">
        <a-col :span="12">
          <a-table :dataSource="userData" :pagination="false" :columns="userDataColumns">
            <!--<a-table-column title="姓名" dataIndex="name"/>-->
            <!--<a-table-column title="用户编号" dataIndex="userNum" />-->
            <!--<a-table-column title="所属装备" dataIndex="equid" />-->
            <!--<a-table-column title="岗位" dataIndex="station" />-->
            <!--<a-table-column title="人员分组" dataIndex="personnelGrouping" />-->
            <span slot="footer">
              <a-pagination
                size="small"
                style="text-align: center;"
                :total="50"
                :showSizeChanger="false"
                showQuickJumper
                :showTotal="total => `共 ${total} 条`"/>
            </span>
          </a-table>
        </a-col>
        <a-col :span="2">
          <img src=".././assets/img/jiaotou3.png" style="width: 28px;height: 28px;margin-top: 220px;margin-left: 30px">
        </a-col>
        <a-col :span="10">
          <a-table :dataSource="courseData" :pagination="false" :columns="courseColumns">
            <span slot="status" slot-scope="text,record">
              <span v-if="text ==='1'">
                <img src=".././assets/img/yiwancheng.png" style="position:relative;bottom:2px;width: 14px;height: 14px;">
                <span style="margin-left: 6px">{{ "已完成" }}</span>
              </span>
              <span v-else>
                <img src=".././assets/img/weiwancheng.png" style="position:relative;bottom:2px;width: 14px;height: 14px;">
                <sapn style="margin-left: 6px">{{ "未完成" }}</sapn>
              </span>
            </span>
            <a slot="edit" href="javascript:;">
              <img src=".././assets/img/shangyi.png" style="width: 12px;height: 16px;">
              <img src=".././assets/img/shanchu.png" style="width: 12px;height: 16px;margin-left: 10px">
            </a>
            <span slot="footer">
              <a-row type="flex">
                <a-col :span="20">
                  <a-pagination
                    size="small"
                    style="text-align: center;"
                    :total="50"
                    :showSizeChanger="false"
                    showQuickJumper
                    :showTotal="total => `共 ${total} 条`"/>
                </a-col>
                <a-col :span="4">
                  <a-button size="small" type="primary " @click="visibleModal">{{ "课程管理" }}</a-button>
                </a-col>
              </a-row>
            </span>
          </a-table>
        </a-col>
      </a-row>
    </a-card>
    <a-modal
      v-model="visible"
      width="900px"
    >
      <span slot="title">
        <img src=".././assets/img/shezhi.png" style="width: 14px;height: 14px;margin-top: -2px">
        {{ "课程设置" }}
      </span>
      <div>
        <span>
          <span style="color: white">{{ "所属装备:" }}</span>
          <a-select defaultValue="全部" style="width: 140px" @change="handleChange">
            <a-select-option value="全部">全部</a-select-option>
            <a-select-option value="1">武器系统基本组成</a-select-option>
            <a-select-option value="3">通信专业</a-select-option>
          </a-select>
        </span>
        <span style="margin-left: 20px">
          <span style="color: white">{{ "人员分组:" }}</span>
          <a-select defaultValue="全部" style="width: 140px" @change="handleChange">
            <a-select-option value="全部">全部</a-select-option>
            <a-select-option value="1">武器系统基本组成</a-select-option>
            <a-select-option value="3">通信专业</a-select-option>
          </a-select>
        </span>
        <span style="margin-left: 20px;">
          <span style="color: white">{{ "岗位:" }}</span>
          <a-select defaultValue="全部" style="width: 140px" @change="handleChange">
            <a-select-option value="全部">全部</a-select-option>
            <a-select-option value="1">武器系统基本组成</a-select-option>
            <a-select-option value="3">通信专业</a-select-option>
          </a-select>
        </span>
        <span style="margin-left: 20px">
          <a-input placeholder="请输入试卷名称" style="width: 140px;margin-left: 10px" />
          <a-button style="margin-left: 10px">
            <a-icon type="search" />
          </a-button>
        </span>
      </div>
      <div>
        <a-checkbox-group :options="plainOptions" v-model="checkedList" @change="onChange" />
      </div>
      <span slot="footer">
        <a-row>
          <a-col :span="24">
            <a-pagination
              style="text-align: center;"
              size="small"
              :total="50"
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
  const userDataColumns=[
    {
      title:"姓名",
      dataIndex: 'name',
      scopedSlots:{customRender: 'name'},
      align: 'center'
    },
    {
      title:"用户编号",
      dataIndex: 'userCode',
      scopedSlots: { customRender: 'userCode'},
      align:"center"
    },
    {
      title:"所属装备",
      dataIndex: 'equid',
      scopedSlots: { customRender : 'equid'},
      align: "center",
    },{
      title:"岗位",
      dataIndex: 'station',
      scopedSlots:{customRender : 'station'},
      align: 'center'
    },
    {
      title:"人员分组",
      dataIndex:'personnelGrouping',
      scopedSlots: {customRender :'personnelGrouping'},
      align: 'center'
    }
  ]



  const userData = [
    {
      name:'张三',
      userCode: '1234567',
      equid: '指控指挥车',
      station:' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name:'张三',
      userCode: '1234567',
      equid: '指控指挥车',
      station:' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name:'张三',
      userCode: '1234567',
      equid: '指控指挥车',
      station:' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name:'张三',
      userCode: '1234567',
      equid: '指控指挥车',
      station:' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name:'张三',
      userCode: '1234567',
      equid: '指控指挥车',
      station:' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name:'张三',
      userCode: '1234567',
      equid: '指控指挥车',
      station:' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name:'张三',
      userCode: '1234567',
      equid: '指控指挥车',
      station:' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name:'张三',
      userCode: '1234567',
      equid: '指控指挥车',
      station:' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name:'张三',
      userCode: '1234567',
      equid: '指控指挥车',
      station:' 指挥官',
      personnelGrouping: '指挥发射组'
    },
    {
      name:'张三',
      userCode: '1234567',
      equid: '指控指挥车',
      station:' 指挥官',
      personnelGrouping: '指挥发射组'
    }
  ]
  const courseColumns =[
    {
      title:'序号',
      dataIndex: 'No',
      scopedSlots:{customRender: 'No' },
      align: 'center'
    },
    {
      title:'课程',
      dataIndex:'course',
      scopedSlots: {customRender: 'course'},
      align: 'center'
    },
    {
      title:'状态',
      dataIndex: 'status',
      scopedSlots: {customRender: 'status'},
      align: 'center'
    },
    {
      title:'编辑',
      dataIndex: 'edit',
      scopedSlots: {customRender: 'edit'},
      align: 'center'
    }
  ]



  const courseData =[
    {
      No:'1',
      course:'基本组成',
      status: '1'
    },
    {
      No:'2',
      course:'基本组成',
      status: '1'
    },
    {
      No:'3',
      course:'基本组成',
      status: '1'
    },
    {
      No:'4',
      course:'基本组成',
      status: '1'
    },
    {
      No:'5',
      course:'基本组成',
      status: '0'
    },
    {
      No:'6',
      course:'基本组成',
      status: '0'
    },
    {
      No:'7',
      course:'基本组成',
      status: '0'
    },
    {
      No:'8',
      course:'基本组成',
      status: '0'
    },
    {
      No:'9',
      course:'基本组成',
      status: '0'
    },
    {
      No:'10',
      course:'基本组成',
      status: '已完成'
    }
  ]

  const plainOptions =[
    '武器系统和导弹概述','武器系统工作状态','指挥车作战使命',
    '武器系统基本组成','武器系统工作原理、作战方式','指挥车战机指标',
    '武器系统作战使命','指挥车系统概述','指挥车工作过程和工作原理',
    '指挥车控制操作手','作战拦截操作手','空勤处理操作手',
    '通信专业','电子雷达专业','机电专业'
  ]

import './TeachingManagement.less'
    export default {
        name: "TeachingManagement",
      data(){
          return{
            userData,
            courseData,
            userDataColumns,
            courseColumns,
            /**
             * 课程管理模态框
             *
             * @Author 尘埃Friend
             * @date 2019-12-03
             */
            visible:false,
            plainOptions,
            /**
             * modal选中类容
             *
             * @Author 尘埃Friend
             * @date 2019-12-03
             */
            checkedList:''
          }
      },
      methods:{
        visibleModal(){
          this.visible=true
        }
      }
    }
</script>

<style scoped>
</style>