<template>
  <div id="drawPlan">
    <a-card style="background-color: RGB(10,19,49);height:1058px">
      <a-row style="padding: 30px 0 10px 0">
        <a-tabs @tabClick="tabsChang">
          <a-tab-pane tab="课表配置" key="1"/>
          <a-tab-pane tab="学习和训练进度" key="2"/>
        </a-tabs>
      </a-row>
      <div v-if="tabsVisible1 ===true " class="examination">
        <a-row
          type="flex"
          style="margin-top: 20px;margin-bottom: 30px"
          justify="center"
          align="center">
          <a-col :span="24">
            <a-table :dataSource="courseConfigurationData" :pagination="false" :columns="courseConfigurationColumns" :rowSelection="rowSelection">
              <template slot="operation">
                <span style="font-size: 18px;color: #0ca5ec;cursor: pointer"
                      @click="updateTimetableModal">{{"修改"}}</span>
                <span style="font-size: 18px;color: #ff0000;cursor: pointer;margin-left: 30px" @click="">{{"删除"}}</span>
              </template>
              <span slot="footer">
                <a-row type="flex">
                  <a-col :span="2" style="margin-left: 25px">
                    <a-button>
                      {{"课时推送"}}
                    </a-button>
                  </a-col>
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
                    <span>
                      <img src="@/assets/img/tianjia.png" style="width: 26px;height: 26px;position: relative;bottom: 3px;cursor: pointer" @click="addTimetable"/>
                    </span>
                  </a-col>
                </a-row>
              </span>
            </a-table>
          </a-col>
        </a-row>

        <a-modal
          v-model="timetableModal"
          width="800px"
        >
              <span slot="title">
                <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">
                <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "设置" }}</span>
              </span>
          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"系统或装备:"}}
                  </span>
            </a-col>
            <a-col :span="4" style="margin-left: 30px">
               <span>
                <a-select style="width: 220px" placeholder="全部" @change="">
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
                      </a-select-option>
                </a-select>
              </span>
            </a-col>
          </a-row>
          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"学习和操作训练项目:"}}
            </span>
            </a-col>
            <a-col :span="4" style="margin-left: 30px">
                          <span>
                <a-select style="width: 220px" placeholder="全部" @change="">
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
                      </a-select-option>
                </a-select>
            </span>
            </a-col>
          </a-row>

          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"课时:"}}
            </span>
            </a-col>
            <a-col :span="4" style="margin-left: 30px">
                <span>
                  <a-input style="width: 220px"></a-input>
                </span>
            </a-col>
          </a-row>
          <span slot="footer">
                <a-row type="flex" justify="center">
                  <a-col :span="4">
                    <a-button>
                      <a-icon type="upload"/>
                      确定
                    </a-button>
                  </a-col>
                </a-row>
              </span>
        </a-modal>


        <a-modal
          v-model="addTimetableModal"
          width="800px"
        >
              <span slot="title">
                <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">
                <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "设置" }}</span>
              </span>
          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"系统或装备:"}}
                  </span>
            </a-col>
            <a-col :span="4" style="margin-left: 30px">
               <span>
                <a-select style="width: 220px" placeholder="全部" @change="">
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
                      </a-select-option>
                </a-select>
              </span>
            </a-col>
          </a-row>
          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"学习和操作训练项目:"}}
            </span>
            </a-col>
            <a-col :span="4" style="margin-left: 30px">
                          <span>
                <a-select style="width: 220px" placeholder="全部" @change="">
                      <a-select-option v-for="item in equipments" :key="item" :value="item">
                          {{ item }}
                      </a-select-option>
                </a-select>
            </span>
            </a-col>
          </a-row>

          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"课时:"}}
            </span>
            </a-col>
            <a-col :span="4" style="margin-left: 30px">
                <span>
                  <a-input style="width: 220px"></a-input>
                </span>
            </a-col>
          </a-row>

          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"学员:"}}
            </span>
            </a-col>
            <a-col :span="4" style="margin-left: 30px">
                <span>
                  <a-input style="width: 220px"></a-input>
                </span>
            </a-col>
          </a-row>
          <span slot="footer">
                <a-row type="flex" justify="center">
                  <a-col :span="4">
                    <a-button>
                      <a-icon type="upload"/>
                      确定
                    </a-button>
                  </a-col>
                </a-row>
              </span>
        </a-modal>

      </div>
      <div v-if="tabsVisible2 ===true " class="examination">
        <a-row
          type="flex"
          style="margin-top: 20px;margin-bottom: 30px"
          justify="center"
          align="center">
          <a-col :span="24">
            <a-table :dataSource="LATPData" :pagination="false" :columns="LATPColumns">
              <template slot="operation">
                <span style="font-size: 18px;color: #0ca5ec;cursor: pointer" @click="">{{"删除"}}</span>
              </template>
              <span slot="footer">
                <a-row type="flex">
                  <a-col :span="2" style="margin-left: 25px"></a-col>
                  <a-col :span="20">
                    <a-pagination
                      size="small"
                      style="text-align: center;"
                      :total="10"
                      :showSizeChanger="false"
                      showQuickJumper
                      :showTotal="total => `共 ${total} 条`"/>
                  </a-col>
                  <a-col :span="1"></a-col>
                </a-row>
              </span>
            </a-table>
          </a-col>
        </a-row>
      </div>
    </a-card>
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


  const courseConfigurationColumns = [
    {
      title: '系统或装备',
      dataIndex: 'SystemOrEquipment',
      align: 'center'
    },
    {
      title: '学习和操作训练项目',
      dataIndex: 'LAOTP',
      align: 'center'
    },
    {
      title: '课时',
      dataIndex: 'classHour',
      align: 'center'
    },
    {
      title: '学员',
      dataIndex: 'student',
      align: 'center'
    },
    {
      title: '操作',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ];
  const courseConfigurationData = [
    {
      SystemOrEquipment: '指挥系统',
      LAOTP: '武器装备理论学习',
      classHour: '2',
      student: '总体，操作，维护',
    },
    {
      SystemOrEquipment: '指挥系统',
      LAOTP: '测试理论学习',
      classHour: '2',
      student: '总体，操作，维护',
    },
    {
      SystemOrEquipment: '指挥',
      LAOTP: '作战理论学习',
      classHour: '2',
      student: '总体，操作，维护',
    },
    {
      SystemOrEquipment: 'xxx系统',
      LAOTP: 'xxx理论学习',
      classHour: '2',
      student: '总体，操作，维护',
    },
    {
      SystemOrEquipment: 'xxx系统',
      LAOTP: 'xxx理论学习',
      classHour: '2',
      student: '总体，操作，维护',
    },
    {
      SystemOrEquipment: 'xxx系统',
      LAOTP: 'xxx理论学习',
      classHour: '2',
      student: '总体，操作，维护',
    },
    {
      SystemOrEquipment: 'xxx系统',
      LAOTP: 'xxx理论学习',
      classHour: '2',
      student: '总体，操作，维护',
    },
    {
      SystemOrEquipment: 'xxx系统',
      LAOTP: 'xxx理论学习',
      classHour: '2',
      student: '总体，操作，维护',
    },
    {
      SystemOrEquipment: 'xxx系统',
      LAOTP: 'xxx理论学习',
      classHour: '2',
      student: '总体，操作，维护',
    },
    {
      SystemOrEquipment: 'xxx系统',
      LAOTP: 'xxx理论学习',
      classHour: '2',
      student: '总体，操作，维护',
    }
  ]
  const LATPColumns = [
    {
      title: '系统或装备',
      dataIndex: 'systemOrEquipment',
      align: 'center'
    },
    {
      title: '学习与训练项目',
      dataIndex: 'LATP',
      align: 'center'
    },
    {
      title: '完成率',
      dataIndex: 'completionRate',
      align: 'center'
    }
  ];
  const LATPData = [
    {
      systemOrEquipment: 'xxx系统',
      LATP: 'xxx理论学习',
      completionRate: '100%'
    },
    {
      systemOrEquipment: 'xxx系统',
      LATP: 'xxx理论学习',
      completionRate: '100%'
    },
    {
      systemOrEquipment: 'xxx系统',
      LATP: 'xxx理论学习',
      completionRate: '100%'
    },
    {
      systemOrEquipment: 'xxx系统',
      LATP: 'xxx理论学习',
      completionRate: '100%'
    },
    {
      systemOrEquipment: 'xxx系统',
      LATP: 'xxx理论学习',
      completionRate: '100%'
    },
    {
      systemOrEquipment: 'xxx系统',
      LATP: 'xxx理论学习',
      completionRate: '100%'
    },
    {
      systemOrEquipment: 'xxx系统',
      LATP: 'xxx理论学习',
      completionRate: '100%'
    },
    {
      systemOrEquipment: 'xxx系统',
      LATP: 'xxx理论学习',
      completionRate: '100%'
    },
    {
      systemOrEquipment: 'xxx系统',
      LATP: 'xxx理论学习',
      completionRate: '100%'
    },
    {
      systemOrEquipment: 'xxx系统',
      LATP: 'xxx理论学习',
      completionRate: '100%'
    }
  ];

  import './DrawPlam.less'

  export default {
    name: "DrawPlan",
    data() {
      return {
        tabsVisible1: true,
        tabsVisible2: false,
        courseConfigurationColumns,
        courseConfigurationData,

        rowSelection,

        equipments: ['xxx系统', 'xxx车', 'xxxx车'],

        /**
         * @Author:     风中的那朵云
         * @Description:  ${description}
         * @Date:    2020/5/6
         * @Version:    1.0
         */
        timetableModal: false,
        addTimetableModal: false,

        LATPColumns,
        LATPData,
      }
    },
    methods: {
      tabsChang(key) {
        if (key === '1') {
          this.tabsVisible1 = true
          this.tabsVisible2 = false
        } else if (key === '2') {
          this.tabsVisible2 = true
          this.tabsVisible1 = false
        }
      },
      updateTimetableModal() {
        this.timetableModal = true
      },
      addTimetable(){
        this.addTimetableModal = true
      }
    }
  }
</script>

<style scoped>

</style>