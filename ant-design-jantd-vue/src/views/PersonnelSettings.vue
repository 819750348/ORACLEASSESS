<template>
  <div id="FacultyManagement">
    <a-card style="background-color: RGB(10,19,49);height: 960px;">
      <a-row type="flex" style="padding: 30px 0 10px 0">
        <a-col :span="30">
          <span style="font-size: 24px;color: #40a9ff ">
            {{"用户设置"}}
          </span>

          <a-select
            style="width: 220px;margin-left: 10px;position: relative;bottom:4px"
            placeholder="全部"
            class="fontSize"
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
            <a-input class="fontSize" placeholder="请输入关键字" style="width: 210px;margin-left: 10px"/>
            <a-button style="margin-left: 10px;position: relative;bottom:3px">
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
      <a-row type="flex" style="margin-top: 20px;margin-bottom: 30px" justify="center" align="center">
        <a-col :span="24">
          <a-table :dataSource="userData"  :pagination="false" :columns="columns" :rowSelection="rowSelection">
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
                    style="text-align: center;"
                    :total="10"
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
            <a-input  style="width: 220px"></a-input>
          </a-col>
        </a-row>


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
      dataIndex: 'relName',
      width: 180,
      scopedSlots: {customRender: 'relName'},
      align: 'center'
    },
    {
      title: '人员角色',
      width: 200,
      dataIndex: 'role',
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

  export default {
    data() {
      return {
        userData,
        columns,
        rowSelection,
        editRow: false,

        roleModal: false

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