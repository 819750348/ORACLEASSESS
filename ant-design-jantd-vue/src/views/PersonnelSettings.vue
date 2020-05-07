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
            <template slot="edit" href="javascript:;">
              <span style="cursor: pointer;color: #5d25ff" @click="editRole">{{"编辑"}}</span>
              <span style="cursor: pointer;color: #ff0000;margin-left: 30px">{{"删除"}}</span>
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
                    @change="">
                    <img src="@/assets/img/tianjia.png" style="cursor:pointer;width: 26px;height: 26px;position: relative;bottom: 3px"
                         @click=""/>
                  </a-upload>
                </a-col>
              </a-row>
            </span>
          </a-table>
        </a-col>
      </a-row>
    </a-card>
    <a-modal
      v-model="roleModal"
      width="800px"
    >
          <span slot="title">
            <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">
            <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "修改" }}</span>
          </span>
      <a-row type="flex" justify="center" style="margin-top: 30px">
        <a-col :span="4">
                <span style="font-size: 18px;color: #ffffff">
                  {{"人员角色:"}}
                </span>
        </a-col>
            <a-col :span="4">
              <a-select default-value="人员" style="width: 220px" @change="">
                <a-select-option value="教学管理人员">
                  {{"教学管理人员"}}
                </a-select-option>
                <a-select-option value="人员">
                  {{"人员"}}
                </a-select-option>
              </a-select>
            </a-col>
      </a-row>
      <a-row type="flex" justify="center" style="margin-top: 30px">
        <a-col :span="4">
                <span style="font-size: 18px;color: #ffffff">
                  {{"密码:"}}
                </span>
        </a-col>
        <a-col :span="4">
          <span>
            <a-input style="width: 220px"></a-input>
          </span>
        </a-col>
      </a-row>
      <span slot="footer">
            <a-row type="flex" justify="center">
              <a-col :span="6">
                <a-button>
                  {{"确定"}}
                </a-button>
                <a-button style="margin-left: 30px">
                  {{"取消"}}
                </a-button>
              </a-col>
            </a-row>
          </span>
    </a-modal>
  </div>
</template>

<script>
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
  ];


  const userData = [
    {
      No: '1',
      relName: '张三',
      role: '教学管理人员',
      password: '00000000'
    },
    {
      No: '2',
      relName: '张三',
      role: '学员',
      password: '00000000'
    },
    {
      No: '3',
      relName: '张三',
      role: '教学管理人员',
      password: '00000000'
    },
    {
      No: '4',
      relName: '张三',
      role: '人员',
      password: '00000000'
    },
    {
      No: '5',
      relName: '张三',
      role: '教学管理人员',
      password: '00000000'
    },
    {
      No: '6',
      relName: '张三',
      role: '人员',
      password: '00000000'
    },
    {
      No: '7',
      relName: '张三',
      role: '教学管理人员',
      password: '00000000'
    },
    {
      No: '8',
      relName: '张三',
      role: '人员',
      password: '00000000'
    },
    {
      No: '9',
      relName: '张三',
      role: '教学管理人员',
      password: '00000000'
    },
    {
      No: '10',
      relName: '张三',
      role: '教学管理人员',
      password: '00000000'
    }
  ];

  import './FacultyManagement.less'
  import './antTable.less'

  export default {
    data() {
      return {
        userData,
        columns,
        roleModal: false
      }
    },
    methods: {
      editRole() {
        this.roleModal = true
      }
    },
    mounted() {
    }
  }
</script>

<style scoped>
  .fontSize {
    font-size: 18px;
  }
</style>