<template>
  <div id="FacultyManagement">
    <a-card style="background-color: RGB(10,19,49);height: 960px;">
      <span style="font-size: 30px;color: #40a9ff ">
            {{"用户设置"}}
      </span>
      <a-row type="flex" style="padding: 30px 0 10px 0">
        <a-col :span="30">
          <a-select
            style="width: 220px;margin-left: 10px;position: relative;bottom:4px"
            placeholder="全部"
            @change="findRole"
            class="fontSize"
            :defaultActiveFirstOption="false"
          >
            <a-select-option value="全部">
              全部
            </a-select-option>
            <a-select-option value="教员管理员">
              教员管理员
            </a-select-option>
            <a-select-option value="学员">
              学员
            </a-select-option>
          </a-select>
          <span style="margin-left: 20px;position: relative;bottom:4px">
            <a-input class="fontSize" placeholder="请输入姓名" v-model="userName" style="width: 210px;margin-left: 10px"/>
            <a-button style="margin-left: 10px;position: relative;bottom:3px" @click="findName">
              <a-icon type="search"/>
            </a-button>
          </span>
          <a-button style="margin-left: 10px;position: relative;bottom:7px">
            {{"删除"}}
          </a-button>
          <span style="margin-left: 10px;position: relative;bottom:7px">
              <a-button @click="addData">
                {{"添加"}}
              </a-button>
          </span>
        </a-col>
      </a-row>
      <a-row type="flex" style="margin-top: 20px;margin-bottom: 30px" :justify="center" :align="center">
        <a-col :span="24">
          <a-table :dataSource="initPersonnelData" :pagination="false" :columns="columns" :rowSelection="rowSelection">
            <template slot="role" slot-scope="text,record">
              <span>
                <span v-if="editRow">
                  <a-select style="width: 200px;margin-left: 10px;position: relative;bottom:4px"
                            class="fontSize"
                            v-model="text"
                            :defaultActiveFirstOption="false">
                      <a-select-option value="教员管理员">
                        教员管理员
                      </a-select-option>
                      <a-select-option value="学员">
                        学员
                      </a-select-option>
                  </a-select>
                </span>
                <span v-else>
                  {{text}}
                </span>
              </span>
            </template>
            <template slot="password" slot-scope="text,record">
              <span>
                <sapn v-if="editRow">
                   <a-input v-model="text" style="width: 200px;font-size: 18px"/>
                </sapn>
                <span v-else>
                    {{text}}
                </span>
              </span>
            </template>
            <template slot="edit">
              <span>
                <span v-if="editRow">
                  <span style="cursor: pointer;color: #5d25ff" @click="editRole">{{"确定"}}</span>
                  <span style="cursor: pointer;color: #9ad9ff;margin-left: 20px" @click="editRole">{{"取消"}}</span>
                </span>
                <span v-else>
                  <span style="cursor: pointer;color: #0ca5ec" @click="editRole">{{"设置"}}</span>
                </span>
              </span>
            </template>
            <span slot="footer">
              <a-row type="flex">
                <a-col :span="20" offset="2">
                  <a-pagination
                    size="small"
                    @change="setPage"
                    v-model="page"
                    style="text-align: center;"
                    :total="total"
                    :showSizeChanger="false"
                    showQuickJumper
                    :showTotal="total => `共 ${total} 条`"/>
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
            <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "添加" }}</span>
          </span>

      <a-row type="flex" justify="center" style="margin-top: 30px">
        <a-col :span="4">
          <span style="font-size: 18px;color: #ffffff">
           {{"姓名:"}}
          </span>
        </a-col>
        <a-col :span="4">
          <a-input style="width: 220px" v-model="addForm.name"></a-input>
        </a-col>
      </a-row>
      <a-row type="flex" justify="center" style="margin-top: 30px">
        <a-col :span="4">
             <span style="font-size: 18px;color: #ffffff">
               {{"人员角色:"}}
             </span>
        </a-col>
        <a-col :span="4">
          <a-select :defaultActiveFirstOption="false" default-value="人员" v-model="addForm.staffGroup"
                    style="width: 220px" @change="">
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
                    <a-input style="width: 220px" v-model="addForm.password"></a-input>
                  </span>
        </a-col>
      </a-row>
      <span slot="footer">
                    <a-row type="flex" justify="center">
                      <a-col :span="6">
                        <a-button @click="addPersonnel">
                          {{"确定"}}
                        </a-button>
                        <a-button style="margin-left: 30px" @click="addData">
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
      dataIndex: 'name',
      width: 180,
      scopedSlots: {customRender: 'relName'},
      align: 'center'
    },
    {
      title: '人员角色',
      width: 200,
      dataIndex: 'staffGroup',
      scopedSlots: {customRender: 'role'},
      align: 'center'
    },
    {
      title: '密码',
      width: 200,
      scopedSlots: {customRender: 'password'},
      dataIndex: 'password',
      align: 'center'
    },
    {
      title: '操作',
      dataIndex: 'edit',
      width: 100,
      scopedSlots: {customRender: 'edit'},
      align: 'center'
    }
  ];


  const userData = [
    {
      No: '1',
      relName: '张玲玲',
      role: '教学管理人员',
      password: '00000000'
    },
    {
      No: '2',
      relName: '王杰',
      role: '学员',
      password: '00000000'
    },
    {
      No: '3',
      relName: '李小蓝',
      role: '教学管理人员',
      password: '00000000'
    },
    {
      No: '4',
      relName: '谢玲',
      role: '学员',
      password: '00000000'
    },
    {
      No: '5',
      relName: '张写成',
      role: '学员',
      password: '00000000'
    },
    {
      No: '6',
      relName: '吴峰',
      role: '教学管理人员',
      password: '00000000'
    },
    {
      No: '7',
      relName: '余潇潇',
      role: '学员',
      password: '00000000'
    },
    {
      No: '8',
      relName: '于于',
      role: '学员',
      password: '00000000'
    },
    {
      No: '9',
      relName: '李燕',
      role: '教学管理人员',
      password: '00000000'
    },
    {
      No: '10',
      relName: '王涛',
      role: '教学管理人员',
      password: '00000000'
    }
  ];
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
  import './FacultyManagement.less'
  import './antTable.less'
  import './selectDropdown.less'
  import {addPersonnel, getInitData} from '@/api/personnelSettings.js'

  export default {
    data() {
      return {
        userData,
        columns,
        rowSelection,
        editRow: false,
        roleModal: false,
        page: 1,
        total: 0,
        initPersonnelData: [],
        addForm: {
          name: '',
          staffGroup: '',
          password: ''
        },
        userName: ''
      }
    },
    methods: {
      editRole() {
        if (this.editRow === false) {
          this.editRow = true
        } else {
          this.editRow = false
        }
      },
      /**
       * @Author:     风中的那朵云
       * @Description:  导入按钮添加数据
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      addData() {
        if (this.roleModal === false) {
          this.roleModal = true
        } else {
          this.roleModal = false
        }
      },
      /**
       * @Author:     风中的那朵云
       * @Description:  ${description}
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      initData() {
        let that = this
        getInitData({
          pageNo: that.page
        }).then(function (res) {
          console.log(res)
          that.initPersonnelData = res.personnelList
          that.total = res.total
          let i = 1
          that.initPersonnelData.map(item => {
            item.No = i
            i++
          })
        }).catch(function (err) {
          console.log(err)
        })
      },
      setPage(page) {
        console.log(page)
        this.page = page
        this.initData()
      },
      /**
       * @Author:     风中的那朵云
       * @Description:  添加用户
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      addPersonnel() {
        console.log(this.addForm)
        let that = this
        let name = that.addForm.name
        let staffGroup = that.addForm.staffGroup
        let password = that.addForm.password

        if (name === null || name === "") {
          that.$message.warning("名称不能为空");
          return;
        } else if (name.length > 6) {
          that.$message.warning("名称太长了");
          return;
        }
        if (staffGroup === null || staffGroup === "") {
          that.$message.warning("角色不能为空");
          return;
        }
        if (password === null || password === "") {
          that.$message.warning("密码不能为空");
          return;
        } else if (password.length > 10) {
          that.$message.warning("密码太长了");
          return;
        }
        that.roleModal = false;
        addPersonnel({
          name: name,
          staffGroup: staffGroup,
          password: password
        }).then(function (res) {
          console.log(res)
          if (res === 1) {
            that.initData();
            that.$message.success("添加成功");
          } else {
            that.$message.warning("添加失败");
          }

        }).catch(function (err) {
          console.log(err)
        })

      },
      /**
       * @Author:     风中的那朵云
       * @Description:  角色筛选
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      findRole(value, p) {
        console.log(value, p)
        this.filterValues(value)
      },
      findName() {
        this.filterValues()
      },
      filterValues(value) {

      }

    },
    mounted() {
      this.initData();
    }
  }
</script>

<style scoped>
  .fontSize {
    font-size: 18px;
  }
</style>