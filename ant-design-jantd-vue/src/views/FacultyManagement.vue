<template>
  <div id="FacultyManagement">
    <a-card style="background-color: RGB(10,19,49);height: 960px;">
      <a-row type="flex" style="padding: 30px 0 10px 0">
        <a-col :span="24">
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
        <a-col :span="24">
          <a-table :dataSource="userData" :pagination="false" :columns="columns">
            <template slot="role" slot-scope="text,record">
              <span v-if="text==='1'">
                <span v-if="roleData===true">
                  <a-select defaultValue="1" style="width: 180px" @change="handleChange">
                    <a-select-option value="1">教学管理人员</a-select-option>
                    <a-select-option value="2">人员</a-select-option>
                  </a-select>
                </span>
                <span v-if="roleData===false">
                  {{"教学管理人员"}}
                </span>
              </span>
              <span v-else>
                <span v-if="roleData===true">
                  <a-select defaultValue="2" style="width: 180px" @change="handleChange">
                    <a-select-option value="1">教学管理人员</a-select-option>
                    <a-select-option value="2">人员</a-select-option>
                  </a-select>
                </span>
                <span v-if="roleData===false">
                  {{"人员"}}
                </span>
              </span>
            </template>

            <template slot="edit" href="javascript:;">
              <span style="cursor: pointer;color: #ffffff" @click="editRole">{{"编辑"}}</span>
              <img src="@/assets/img/shanchu.png"
                   style="cursor: pointer; width: 12px;height: 16px;margin-left: 20px;margin-bottom: 5px">
            </template>
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
                <a-col :span="1">
                  <a-upload
                    action="http://192.168.5.253:8080/jantd-boot/teacher/problem/importExcel"
                    accept=".xlsx"
                    @change="handleImportExcel">
                    <img src="@/assets/img/tianjia.png" style="width: 26px;height: 26px;position: relative;bottom: 3px"
                         @click="visibleModal"/>
                  </a-upload>
                </a-col>
              </a-row>
            </span>
          </a-table>
        </a-col>
      </a-row>
    </a-card>
    <!--    <a-modal-->
    <!--      v-model="visible"-->
    <!--      width="800px"-->
    <!--    >-->
    <!--      <span slot="title">-->
    <!--        <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">-->
    <!--        <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "添加" }}</span>-->
    <!--      </span>-->
    <!--        <a-row type="flex" justify="center" style="margin-top: 30px">-->
    <!--          <a-col :span="14">-->
    <!--            <a-input style="width: 280px"></a-input>-->
    <!--            <a-upload-->
    <!--              action="http://192.168.5.253:8080/jantd-boot/teacher/problem/importExcel"-->
    <!--              accept=".xlsx"-->
    <!--              @change="handleImportExcel">-->
    <!--              <a-button style="margin-left: 20px">-->
    <!--                <a-icon type="upload"/>-->
    <!--                选择-->
    <!--              </a-button>-->
    <!--            </a-upload>-->
    <!--          </a-col>-->
    <!--        </a-row>-->
    <!--        <span slot="footer">-->
    <!--        <a-row type="flex" justify="center">-->
    <!--          <a-col :span="4">-->
    <!--            <a-button>-->
    <!--              <a-icon type="upload"/>-->
    <!--              确定导入-->
    <!--            </a-button>-->
    <!--          </a-col>-->
    <!--        </a-row>-->
    <!--      </span>-->
    <!--    </a-modal>-->
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
  const columns = [
    {
      title: '序号',
      dataIndex: 'No',
      width: 20,
      scopedSlots: {customRender: 'No'},
      align: 'center'
    },
    {
      title: '姓名',
      dataIndex: 'relName',
      width: 120,
      scopedSlots: {customRender: 'relName'},
      align: 'center'
    },
    {
      title: '人员角色',
      dataIndex: 'role',
      width: 180,
      scopedSlots: {customRender: 'role'},
      align: 'center'
    },
    {
      title: '密码',
      width: 180,
      dataIndex: 'password',
      align: 'center'
    },
    {
      title: '操作',
      dataIndex: 'edit',
      width: 120,
      scopedSlots: {customRender: 'edit'},
      align: 'center'
    }
  ]


  const userData = [
    {
      No: '1',
      relName: '张三',
      role: '2',
      password: '00000000'
    },
    {
      No: '2',
      relName: '张三',
      role: '1',
      password: '00000000'
    },
    {
      No: '3',
      relName: '张三',
      role: '1',
      password: '00000000'
    },
    {
      No: '4',
      relName: '张三',
      role: '2',
      password: '00000000'
    },
    {
      No: '5',
      relName: '张三',
      role: '1',
      initPassword: '00000000'
    },
    {
      No: '6',
      relName: '张三',
      role: '2',
      password: '00000000'
    },
    {
      No: '7',
      relName: '张三',
      role: '1',
      password: '00000000'
    },
    {
      No: '8',
      relName: '张三',
      role: '2',
      password: '00000000'
    },
    {
      No: '9',
      relName: '张三',
      role: '1',
      password: '00000000'
    },
    {
      No: '10',
      relName: '张三',
      role: '2',
      password: '00000000'
    }
  ]

  const plainOptions = [
    '武器系统和导弹概述', '武器系统工作状态', '指挥车作战使命',
    '武器系统基本组成', '武器系统工作原理、作战方式', '指挥车战机指标',
    '武器系统作战使命', '指挥车系统概述', '指挥车工作过程和工作原理',
    '指挥车控制操作手', '作战拦截操作手', '空勤处理操作手',
    '通信专业', '电子雷达专业', '机电专业'
  ]

  import './FacultyManagement.less'
  import './antTable.less'

  export default {
    name: "FacultyManagement",
    data() {
      return {
        userData,
        columns,
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
        roleData: false
      }
    },
    methods: {
      visibleModal() {
        this.visible = true
      },
      editRole() {
        if (this.roleData === false) {
          this.roleData = true
        } else if (this.roleData === true) {
          this.roleData = false
        }

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