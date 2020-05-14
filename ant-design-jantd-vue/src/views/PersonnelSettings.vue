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
            <a-button style="margin-left: 10px;position: relative;bottom:1px">
              <a-icon type="search"/>
            </a-button>
          </span>
          <a-button style="margin-left: 10px;position: relative;bottom:4px">
            {{"删除"}}
          </a-button>
          <span style="margin-left: 10px;position: relative;bottom:4px">
            <a-upload
              action="http://192.168.5.253:8080/jantd-boot/teacher/problem/importExcel"
              accept=".xlsx"
              @change="">
                 <a-button>
                    {{"导入按钮"}}
                 </a-button>
            </a-upload>
          </span>
        </a-col>
      </a-row>
      <a-row type="flex" style="margin-top: 20px;margin-bottom: 30px" justify="center" align="center">
        <a-col :span="24">
          <a-table :dataSource="userData" :pagination="false" :columns="columns" :rowSelection="rowSelection">
            <template slot="edit" href="javascript:;">
              <span style="cursor: pointer;color: #5d25ff" @click="editRole">{{"编辑"}}</span>
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

  export default {
    data() {
      return {
        userData,
        columns,
        rowSelection,

      }
    },
    methods: {

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