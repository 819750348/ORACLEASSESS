<template>
  <div id="examinationPaperSettings">
    <a-card style="background-color: RGB(10,19,49);height:1058px">
      <a-row style="padding: 30px 0 10px 0">
        <a-tabs @tabClick="tabsChang">
          <a-tab-pane tab="人员配置管理" key="1"/>
          <a-tab-pane tab="学习和训练项目管理" key="2"/>
          <a-tab-pane tab="试题管理" key="3"/>
        </a-tabs>
      </a-row>
      <div v-if="tabsVisible1 ===true " class="examination">
        <a-row type="flex">
          <a-col :span="6">
            <span style="color: #ffffff;font-size: 20px">
              {{"所属岗位:"}}
            </span>
            <template>
              <a-select style="width: 200px" defaultValue="all" @change="searchStaffing">
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>全部</span>
                  <a-select-option value="all">
                    全部
                  </a-select-option>
                </a-select-opt-group>
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>武器系统</span>
                  <a-select-option value="1">
                    总体
                  </a-select-option>
                </a-select-opt-group>
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>指控车</span>
                  <a-select-option value="2">
                    指挥
                  </a-select-option>
                  <a-select-option value="3">
                    操作
                  </a-select-option>
                  <a-select-option value="4">
                    维修
                  </a-select-option>
                </a-select-opt-group>
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>发射车</span>
                  <a-select-option value="5">
                    操作
                  </a-select-option>
                  <a-select-option value="6">
                    维修
                  </a-select-option>
                </a-select-opt-group>
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>雷达车</span>
                  <a-select-option value="7">
                    操作
                  </a-select-option>
                  <a-select-option value="8">
                    维修
                  </a-select-option>
                </a-select-opt-group>
              </a-select>
            </template>
          </a-col>
        </a-row>
        <a-row
          type="flex"
          style="margin-top: 20px;margin-bottom: 30px"
          justify="center"
          align="center">
          <a-col :span="24">
            <a-table :dataSource="initStaffingManagementData" :pagination="false" :columns="staffingManagementColumns">
              <template slot="equipPosition" slot-scope="text,record">
                <span>
                  <span v-if="record.id == personnelRowId & personnelRowShow ==true ">
                    <a-select style="width: 200px" @change="positionSetting" mode="multiple">
                        <a-select-opt-group>
                          <span slot="label"><a-icon type="rocket"/>武器系统</span>
                          <a-select-option value="1">
                            总体
                          </a-select-option>
                        </a-select-opt-group>
                        <a-select-opt-group>
                          <span slot="label"><a-icon type="rocket"/>指控车</span>
                          <a-select-option value="2">
                            总体
                          </a-select-option>
                          <a-select-option value="3">
                            指挥
                          </a-select-option>
                        </a-select-opt-group>
                        <a-select-opt-group>
                          <span slot="label"><a-icon type="rocket"/>发射车</span>
                          <a-select-option value="4">
                            操作
                          </a-select-option>
                        </a-select-opt-group>
                        <a-select-opt-group>
                          <span slot="label"><a-icon type="rocket"/>雷达车</span>
                          <a-select-option value="5">
                            维修
                          </a-select-option>
                        </a-select-opt-group>
              </a-select>
                  </span>
                  <sapn v-else>
                      {{text}}
                  </sapn>
                </span>
              </template>
              <template slot="operation" slot-scope="text,record">
                <span v-if="record.id == personnelRowId & personnelRowShow ==true ">
                  <span style="font-size: 18px;color: #0ca5ec;cursor: pointer" @click="OKPersonnelRow(record,'1')">
                      {{"确定"}}
                    </span>
                  <span style="font-size: 18px;color: #ff0000;cursor: pointer;margin-left: 20px"
                        @click="personnelSettings">
                      {{"取消"}}
                    </span>
                </span>
                <span v-else>
                  <span style="font-size: 18px;color: #0ca5ec;cursor: pointer"
                        @click="personnelSettings(record,'3')">
                    {{"设置"}}
                  </span>
                </span>
              </template>
              <span slot="footer">
                <a-row type="flex">
                  <a-col :span="2" style="margin-left: 25px"></a-col>
                  <a-col :span="20">
                                      <a-pagination
                                        size="small"
                                        @change="setPage"
                                        v-model="staffingManagementPage"
                                        style="text-align: center;"
                                        :total="staffingManagementTotal"
                                        :showSizeChanger="false"
                                        showQuickJumper
                                        :showTotal="total => `共 ${staffingManagementTotal} 条`"/>
                  </a-col>
                  <a-col :span="1"></a-col>
                </a-row>
              </span>
            </a-table>
          </a-col>
        </a-row>
      </div>


      <div v-if="tabsVisible2 ===true " class="lowerHair">
        <a-row
          type="flex"
          style="margin-top: 20px;margin-bottom: 30px"
          justify="center"
          align="center">
          <a-col :span="24">
            <a-table :dataSource="learningManagementData" :pagination="false" :columns="learningManagementColumns">
              <template slot="equipPosition" slot-scope="text,record">
                <span>
                   <span>
                  <span v-if="learningManagementSet">
                    <a-select style="width: 200px" @change="" mode="multiple">
                        <a-select-opt-group>
                          <span slot="label"><a-icon type="rocket"/>武器系统</span>
                          <a-select-option value="1">
                            总体
                          </a-select-option>
                        </a-select-opt-group>
                        <a-select-opt-group>
                          <span slot="label"><a-icon type="rocket"/>指控车</span>
                          <a-select-option value="2">
                            总体
                          </a-select-option>
                          <a-select-option value="3">
                            指挥
                          </a-select-option>
                        </a-select-opt-group>
                        <a-select-opt-group>
                          <span slot="label"><a-icon type="rocket"/>发射车</span>
                          <a-select-option value="4">
                            操作
                          </a-select-option>
                        </a-select-opt-group>
                        <a-select-opt-group>
                          <span slot="label"><a-icon type="rocket"/>雷达车</span>
                          <a-select-option value="5">
                            维修
                          </a-select-option>
                        </a-select-opt-group>
              </a-select>
                  </span>
                  <sapn v-else>
                      {{text}}
                  </sapn>
                </span>
                </span>
              </template>
              <template slot="operation" slot-scope="text,record">
                <sapn v-if="learningManagementSet">
                  <span style="font-size: 18px;color: #0ca5ec;cursor: pointer">
                      {{"确定"}}
                    </span>
                  <span style="font-size: 18px;color: #ff0000;cursor: pointer;margin-left: 20px"
                        @click="learningManagementSetUp">
                      {{"取消"}}
                    </span>
                </sapn>
                <span v-else>
                  <span style="font-size: 18px;color: #0ca5ec;cursor: pointer" @click="learningManagementSetUp">
                      {{"设置"}}
                    </span>
                </span>

              </template>
              <span slot="footer">
                <a-row type="flex">
                  <a-col :span="20" offset="2">
                    <a-pagination
                      size="small"
                      style="text-align: center;"
                      :total="3"
                      :showSizeChanger="false"
                      showQuickJumper
                      :showTotal="total => `共 ${total} 条`"/>
                  </a-col>

                  <a-col :span="1">
                    <span @click="showLearningAndTrainingProjectManagement">
                      <a href="javascript:;">
                        <img
                          src=".././assets/img/tianjia.png"
                          style="width: 16px;height: 16px;float: right;margin-right: 20px;position:relative;top: 4px">
                      </a>
                    </span>
                  </a-col>
                </a-row>
              </span>
            </a-table>
          </a-col>
        </a-row>


        <!--                添加-->
        <a-modal
          v-model="showLearningAndTrainingProjectManagementModal"
          width="800px"
        >
              <span slot="title">
                <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">
                <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "设置" }}</span>
              </span>
          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"学习与培训项目:"}}
                  </span>
            </a-col>
            <a-col :span="4">
               <span>
                 <a-textarea placeholder="" allow-clear @change="" style="width: 220px"/>
              </span>
            </a-col>
          </a-row>
          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"学习类型:"}}
            </span>
            </a-col>
            <a-col :span="4">
              <span>
                <a-input style="width: 220px"></a-input>
              </span>
            </a-col>
          </a-row>


          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"适用岗位:"}}
            </span>
            </a-col>
            <a-col :span="4">
              <span>
                <a-select style="width: 220px" @change="">
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>武器系统</span>
                  <a-select-option value="1">
                    总体
                  </a-select-option>
                </a-select-opt-group>
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>指控车</span>
                  <a-select-option value="2">
                    总体
                  </a-select-option>
                  <a-select-option value="3">
                    指挥
                  </a-select-option>
                </a-select-opt-group>
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>发射车</span>
                  <a-select-option value="4">
                    操作
                  </a-select-option>
                </a-select-opt-group>
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>雷达车</span>
                  <a-select-option value="5">
                    维修
                  </a-select-option>
                </a-select-opt-group>
              </a-select>
              </span>
            </a-col>
          </a-row>


          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"学时:"}}
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


      <div v-if="tabsVisible3 ===true " class="examination">
        <a-row type="flex">
          <a-col :span="6">
            <span style="color: #ffffff;font-size: 20px">
              {{"所属岗位:"}}
            </span>
            <template>
              <a-select style="width: 200px" @change="">
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>武器系统</span>
                  <a-select-option value="1">
                    总体
                  </a-select-option>
                </a-select-opt-group>
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>指控车</span>
                  <a-select-option value="2">
                    总体
                  </a-select-option>
                  <a-select-option value="3">
                    指挥
                  </a-select-option>
                </a-select-opt-group>
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>发射车</span>
                  <a-select-option value="4">
                    操作
                  </a-select-option>
                </a-select-opt-group>
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>雷达车</span>
                  <a-select-option value="5">
                    维修
                  </a-select-option>
                </a-select-opt-group>
              </a-select>
            </template>
          </a-col>
          <a-col :span="5">
            <span>
              <a-input style="width: 220px" placeholder="请输入描述内容"/>
              <a-button style="margin-left: 10px">
                {{"搜索"}}
              </a-button>
            </span>
          </a-col>


          <a-col :span="3">
            <span>
                          <a-upload
                            action="http://192.168.5.253:8080/jantd-boot/teacher/problem/importExcel"
                            accept=".xlsx"
                            @change="handleImportExcel">
                                <a-button @click="batchImport">
                                  {{'批量导入'}}
                                </a-button>
                          </a-upload>
            </span>
          </a-col>
          <a-col :span="2">
            <span>
                <a-button>
                  {{"删除"}}
                </a-button>
            </span>
          </a-col>
        </a-row>
        <a-row
          type="flex"
          style="margin-top: 20px;margin-bottom: 30px"
          justify="center"
          align="center">
          <a-col :span="24">
            <a-table :dataSource="testManagementData" :pagination="false" :columns="testManagementColumns"
                     :rowSelection="rowSelection">
              <template slot="equipPosition" slot-scope="text,record">
                <span>
                  <span v-if="testManagementSet === true">
                    <a-select style="width: 200px" @change="" mode="multiple">
                        <a-select-opt-group>
                          <span slot="label"><a-icon type="rocket"/>武器系统</span>
                          <a-select-option value="1">
                            总体
                          </a-select-option>
                        </a-select-opt-group>
                        <a-select-opt-group>
                          <span slot="label"><a-icon type="rocket"/>指控车</span>
                          <a-select-option value="2">
                            总体
                          </a-select-option>
                          <a-select-option value="3">
                            指挥
                          </a-select-option>
                        </a-select-opt-group>
                        <a-select-opt-group>
                          <span slot="label"><a-icon type="rocket"/>发射车</span>
                          <a-select-option value="4">
                            操作
                          </a-select-option>
                        </a-select-opt-group>
                        <a-select-opt-group>
                          <span slot="label"><a-icon type="rocket"/>雷达车</span>
                          <a-select-option value="5">
                            维修
                          </a-select-option>
                        </a-select-opt-group>
              </a-select>
                  </span>
                  <sapn v-else>
                      {{text}}
                  </sapn>
                </span>

              </template>
              <template slot="operation" slot-scope="text,record">

                <sapn v-if="testManagementSet">
                  <span style="font-size: 18px;color: #0ca5ec;cursor: pointer">
                      {{"确定"}}
                    </span>
                  <span style="font-size: 18px;color: #ff0000;cursor: pointer;margin-left: 20px"
                        @click="testManagementSettings">
                      {{"取消"}}
                    </span>
                </sapn>
                <span v-else>
                  <span style="font-size: 18px;color: #03ecd2;cursor: pointer"
                        @click="showTestManagementDetails(record)">
                      {{"详情"}}
                    </span>
                  <span style="font-size: 18px;color: #0ca5ec;cursor: pointer;margin-left: 20px"
                        @click="testManagementSettings">
                      {{"设置"}}
                    </span>
                      <span style="font-size: 18px;color: #ff0000;cursor: pointer;margin-left: 20px">
                      {{"删除"}}
                    </span>
                </span>
              </template>
              <span slot="footer">
                <a-row type="flex">
                  <a-col :span="2" style="margin-left: 25px"></a-col>
                  <a-col :span="20">
                    <a-pagination
                      size="small"
                      style="text-align: center;"
                      :total="5"
                      :showSizeChanger="false"
                      showQuickJumper
                      :showTotal="total => `共 ${total} 条`"/>
                  </a-col>
                  <a-col :span="1">
                    <span @click="addTestQuestions">
                      <a href="javascript:;">
                        <img
                          src=".././assets/img/tianjia.png"
                          style="width: 16px;height: 16px;float: right;margin-right: 20px;position:relative;top: 4px">
                      </a>
                    </span>
                  </a-col>
                </a-row>
              </span>
            </a-table>
          </a-col>
        </a-row>
        <!--                添加-->
        <a-modal
          v-model="testQuestionsModal"
          width="800px"
        >
              <span slot="title">
                <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">
                <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "设置" }}</span>
              </span>
          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"试题描述:"}}
                  </span>
            </a-col>
            <a-col :span="4">
               <span>
                 <a-textarea placeholder="请输入描述" allow-clear @change="" style="width: 220px"/>
              </span>
            </a-col>
          </a-row>
          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"试题类型:"}}
            </span>
            </a-col>
            <a-col :span="4">
              <span>
                <a-input style="width: 220px"></a-input>
              </span>
            </a-col>
          </a-row>


          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"适用岗位:"}}
            </span>
            </a-col>
            <a-col :span="4">
              <span>
                <a-select style="width: 220px" @change="">
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>武器系统</span>
                  <a-select-option value="1">
                    总体
                  </a-select-option>
                </a-select-opt-group>
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>指控车</span>
                  <a-select-option value="2">
                    总体
                  </a-select-option>
                  <a-select-option value="3">
                    指挥
                  </a-select-option>
                </a-select-opt-group>
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>发射车</span>
                  <a-select-option value="4">
                    操作
                  </a-select-option>
                </a-select-opt-group>
                <a-select-opt-group>
                  <span slot="label"><a-icon type="rocket"/>雷达车</span>
                  <a-select-option value="5">
                    维修
                  </a-select-option>
                </a-select-opt-group>
              </a-select>
              </span>
            </a-col>
          </a-row>


          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"试题描述:"}}
                  </span>
            </a-col>
            <a-col :span="4">
               <span>
                     <a-select default-value="单选" style="width: 220px" @change="">
                        <a-select-option value="单选">
                          单选
                        </a-select-option>
                        <a-select-option value="多选">
                          多选
                        </a-select-option>
                        <a-select-option value="判断">
                          判断
                        </a-select-option>
                      </a-select>
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


        <!--详情页面-->
        <a-modal
          v-model="testManagementDetailsModal"
          width="800px"
        >
              <span slot="title">
                <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">
                <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "详情" }}</span>
              </span>
          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"试题描述:"}}
                  </span>
            </a-col>
            <a-col :span="4">
               <span style="font-size: 18px;color: #ffffff">
                 {{testManagementDetails.name}}
              </span>
            </a-col>
          </a-row>
          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"试题类型:"}}
            </span>
            </a-col>
            <a-col :span="4">
              <span style="font-size: 18px;color: #ffffff">
                {{testManagementDetails.examType}}
              </span>
            </a-col>
          </a-row>

          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
            <span style="color: #ffffff;font-size: 18px">
              {{"适用岗位:"}}
            </span>
            </a-col>
            <a-col :span="4">
              <span style="font-size: 18px;color: #ffffff">
                {{testManagementDetails.equipPosition}}
              </span>
            </a-col>
          </a-row>


          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"题型:"}}
                  </span>
            </a-col>
            <a-col :span="4">
               <span style="font-size: 18px;color: #ffffff">
                 {{testManagementDetails.topicType}}
               </span>
            </a-col>
          </a-row>


          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"答案:"}}
                  </span>
            </a-col>
            <a-col :span="4">
               <span style="font-size: 18px;color: #ffffff">
                 {{testManagementDetails.answer}}
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
    </a-card>

  </div>
</template>

<script>
  import './ExaminationPaperSettings.less'
  import {initStaffingManagement,editStaffingManagement} from '@/api/staffingManagement.js'

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
  const examinationC = [{
    title: '题库分类',
    dataIndex: 'tikufenlei',
    scopedSlots: {customRender: 'tikufenlei'},
    align: 'center'
  }, {
    title: '题目',
    dataIndex: 'timu',
    scopedSlots: {customRender: 'timu'},
    align: 'center'
  },
    {
      title: '题型',
      dataIndex: 'tixing',
      scopedSlots: {customRender: 'tixing'},
      align: 'center'
    },
    {
      title: '查看',
      scopedSlots: {customRender: 'chakan'},
      align: 'center'
    }
  ]
  const examinationD = [
    {
      tikufenlei: '指控控制车',
      timu: '多目标操作流程',
      tixing: '选择题',
    },
    {
      tikufenlei: '指挥车作战使命',
      timu: '多目标操作流程',
      tixing: '选择题',
    },
    {
      tikufenlei: '空勤处理操作手',
      timu: '多目标操作流程',
      tixing: '选择题',
    }, {
      tikufenlei: '指挥车系统概述',
      timu: '多目标操作流程',
      tixing: '选择题',
    }, {
      tikufenlei: '指挥车战机指标',
      timu: '多目标操作流程',
      tixing: '选择题',
    }
  ]
  const testManagementColumns = [
    {
      title: "序号",
      dataIndex: 'NO',
      align: 'center'
    },
    {
      title: "试题描述",
      dataIndex: 'name',
      align: 'center'
    },
    {
      title: '试题类型',
      dataIndex: 'examType',
      align: 'center'
    },
    // {
    //   title: '所属系统',
    //   dataIndex: 'system',
    //   align: 'center'
    // },
    {
      title: '适用岗位',
      dataIndex: 'equipPosition',
      scopedSlots: {customRender: 'equipPosition'},
      align: 'center'
    },
    {
      title: '题型',
      dataIndex: 'topicType',
      align: 'center'
    },
    // {
    //   title: '答案',
    //   dataIndex: 'answer',
    //   align: 'center'
    // },
    {
      title: '操作',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ];

  // const rowSelection = {
  //   onChange: (selectedRowKeys, selectedRows) => {
  //     console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
  //   },
  //   onSelect: (record, selected, selectedRows) => {
  //     console.log(record, selected, selectedRows);
  //   },
  //   onSelectAll: (selected, selectedRows, changeRows) => {
  //     console.log(selected, selectedRows, changeRows);
  //   },
  // };


  const testManagementData = [
    {
      NO: 1,
      name: 'xxx',
      examType: '武器系统学习 ',
      equipPosition: '武器系统_指挥',
      topicType: '单选',
      answer: 'B'
    },
    {
      NO: 2,
      name: 'xxx',
      examType: '武器系统学习 ',
      equipPosition: '武器系统_指挥',
      topicType: '单选',
      answer: 'A'
    },
    {
      NO: 3,
      name: 'xxx',
      examType: '武器系统学习 ',
      equipPosition: '武器系统_指挥',
      topicType: '单选',
      answer: 'C'
    },
    {
      NO: 4,
      name: 'xxx',
      examType: '武器系统学习 ',
      equipPosition: '武器系统_指挥',
      topicType: '单选',
      answer: 'A'
    },
    {
      NO: 5,
      name: 'xxx',
      examType: '武器系统学习 ',
      equipPosition: '武器系统_指挥',
      topicType: '多选',
      answer: 'A、B'
    }
  ]
  const learningManagementColumns = [
    {
      title: "序号",
      dataIndex: 'NO',
      align: 'center'
    },
    {
      title: '学习与培训项目',
      dataIndex: 'learningAndTrainingPrograms',
      align: 'center'
    },
    // {
    //   title: '所属装备',
    //   dataIndex: 'system',
    //   align: 'center'
    //
    // },
    {
      title: '学习类型',
      dataIndex: 'typesOfLearning',
      align: 'center'

    },
    {
      title: '适用岗位',
      dataIndex: 'equipPosition',
      scopedSlots: {customRender: 'equipPosition'},
      align: 'center'
    },
    {
      title: '学时',
      dataIndex: 'classHour',
      align: 'center'
    },
    {
      title: '操作',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ];

  const learningManagementData = [
    {
      NO: '1',
      learningAndTrainingPrograms: 'xxx',
      typesOfLearning: '操作训练学习',
      equipPosition: ' 指挥车_指挥、指挥车_操作',
      classHour: '2'
    },
    {
      NO: '2',
      learningAndTrainingPrograms: 'xxx',
      typesOfLearning: '理论学习',
      equipPosition: ' 指挥车_指挥、指挥车_操作',
      classHour: '2'
    },
    {
      NO: '3',
      learningAndTrainingPrograms: 'xxx',
      typesOfLearning: '操作训练学习',
      equipPosition: ' 指挥车_指挥、指挥车_操作',
      classHour: '2'
    }
  ];

  //人员配置管理
  const staffingManagementData = [
    {
      No: '1',
      name: '王杰',
      system: ' 雷达车',
      station: '总体'
    },
    {
      No: '2',
      name: '李兰',
      system: ' 雷达车',
      station: '维修'
    }, {
      No: '3',
      name: '谢风',
      system: '指挥车',
      station: '维修'
    }
    , {
      No: '4',
      name: '王瑞',
      system: '武器系统',
      station: '指挥'
    }, {
      No: '5',
      name: '刘解',
      system: '指挥车',
      station: '操作'
    }, {
      No: '6',
      name: '赵玲',
      system: '武器系统',
      station: '操作'
    }, {
      No: '7',
      name: '张站',
      system: '雷达车',
      station: '总体'
    }, {
      No: '8',
      name: '谢玲玲',
      system: '武器系统',
      station: '维修'
    }, {
      No: '9',
      name: '李杰',
      system: '发射车',
      station: '总体'
    }, {
      No: '10',
      name: '李禅茶',
      system: '武器系统',
      station: '总体'
    }
  ]

  const staffingManagementColumns = [
    {
      title: '姓名',
      dataIndex: 'name',
      align: 'center'
    },
    {
      title: '适用岗位',
      dataIndex: 'equipPosition',
      scopedSlots: {customRender: 'equipPosition'},
      align: 'center'
    },
    // {
    //   title: '岗位',
    //   dataIndex: 'station',
    //   align: 'center'
    // },
    {
      title: '操作',
      scopedSlots: {customRender: 'operation'},
      align: 'center'
    }
  ]


  export default {
    name: "ExaminationPaperSettings",
    data() {
      return {
        /**
         * @Author:     风中的那朵云
         * @Description:  ${description}
         * @Date:    2020/5/6
         * @Version:    1.0
         */
        learningManagementData,
        learningManagementColumns,

        staffingManagementData,
        staffingManagementColumns,

        testManagementColumns,
        testManagementData,

        LMM: false,


        testManagementSet: true,

        personnelModal: false,
        learningManagementSet: true,
        tabsVisible3: false,
        tabsVisible1: true,
        tabsVisible2: false,

        batchImportModal: false,

        examinationC,
        examinationD,
        rowSelection,


        tabsEquipment: [],
        optionalModal: false,
        equipment: [
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
          }
        ],
        stations: [
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
          }
        ],
        tabsStation: [],
        CV: false,
        equipments: ['xxx系统', 'xxx车', 'xxxx车'],
        /**
         * @Author:     风中的那朵云
         * @Description:  人员设置
         * @Date:    2020/5/6
         * @Version:    1.0
         */
        personnelSet: false,

        /**
         * @Author:     风中的那朵云
         * @Description:  人员配置
         * @Date:    2020/5/6
         * @Version:    1.0
         */
        equipPosition: 'all',
        staffingManagementPage: 1,
        staffingManagementTotal: 0,
        initStaffingManagementData: [],


        testQuestionsModal: false,
        testManagementDetailsModal: false,
        testManagementDetails: {
          name: '',
          examType: '',
          equipPosition: '',
          topicType: '',
          answer: ''
        },

        equipPositionArray: [],


        showLearningAndTrainingProjectManagementModal: false,

        /**
         * @Author:     风中的那朵云
         * @Description:  人员设置参数
         * @Date:    2020/5/6
         * @Version:    1.0
         */
        personnelPosition: [],

        personnelRowShow: false,
        personnelRowId: '',
        personnelEquipPosition: []

      }
    },
    methods: {
      closeTags(value) {
        this.tabsEquipment[value].visible = false
        console.log(value)
      },
      changeSelect(i) {
        if (this.equipment[i].visible === false) {
          this.equipment[i].visible = true
        } else if (this.equipment[i].visible === true) {
          this.equipment[i].visible = false
        }
        this.tabsEquipment = this.equipment
      },
      closeTagsStation(value) {
        this.tabsStation[value].visible = false
        console.log(value)
      },
      changeStation(i) {
        if (this.stations[i].visible === false) {
          this.stations[i].visible = true
        } else if (this.stations[i].visible === true) {
          this.stations[i].visible = false
        }
        this.tabsStation = this.stations
      },
      /**
       * @Author:     风中的那朵云
       * @Description:  ${description}
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      tabsChang(key) {
        if (key === '1') {
          this.tabsVisible1 = true
          this.tabsVisible2 = false
          this.tabsVisible3 = false
        } else if (key === '2') {
          this.tabsVisible2 = true
          this.tabsVisible1 = false
          this.tabsVisible3 = false
        } else if (key === '3') {
          this.tabsVisible2 = false
          this.tabsVisible1 = false
          this.tabsVisible3 = true
        }
      },
      /**
       * @Author:     风中的那朵云
       * @Description:  ${description}
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      batchImport() {
        this.batchImportModal = true
      },
      random() {
        this.CV = true
      },
      custom() {
        this.CV = false
        this.optionalModal = true
      },
      //人员配置管理设置
      personnelSettings(record, v) {

        this.personnelRowId = record.id
        // this.personnelPosition = record.equipPosition
        if (v == "3") {
          this.personnelRowShow = true
        } else {
          this.personnelRowShow = false
        }

      },
      //试题管理
      testManagementSettings() {
        if (this.testManagementSet === false) {
          this.testManagementSet = true
        } else {
          this.testManagementSet = false
        }
      },
      positionSetting(v, p) {
        this.personnelEquipPosition = v
        console.log(v, p)
      },

      /**
       * @Author:     风中的那朵云
       * @Description:  ${description}
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      learningManagementSetUp() {
        if (this.learningManagementSet === false) {
          this.learningManagementSet = true
        } else {
          this.learningManagementSet = false
        }

      },
      addLearningManagement() {
        this.LMM = true
      },

      /**
       * @Author:     风中的那朵云
       * @Description:  人员配置页面初始化
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      initStaffingManagement() {

        let that = this
        initStaffingManagement({
          pageNo: that.staffingManagementPage,
          equipPosition: that.equipPosition
        }).then(function (res) {
          console.log(res)
          that.initStaffingManagementData = res.personnelList
          that.staffingManagementTotal = res.total
          let i = 1
          that.initStaffingManagementData.map(item => {
            item.No = i
            i++
            if (item.equipPosition !== "" && item.equipPosition !== null && item.equipPosition.length > 0) {
              let a = item.equipPosition.replace("1", "武器装备_总体");
              let b = a.replace("2", "指控车_指挥");
              let c = b.replace("3", "指控车_操作");
              let d = c.replace("4", "武器装备_维修");
              let e = d.replace("5", "发射车_操作");
              let f = e.replace("6", "发射车_维修");
              let g = f.replace("7", "雷达车_操作");
              let h = g.replace("8", "雷达车_维修");
              item.equipPosition = h;
            }
          })
        }).catch(function (err) {
          console.log(err)
        })

      },
      //  人员配置搜索管理
      searchStaffing(v, p) {
        console.log(v, p);
        this.equipPosition = v
        this.initStaffingManagement()
      },


      setPage(page) {
        console.log(page)
        this.staffingManagementPage = page
        this.initStaffingManagement()
      },
      /**
       * @Author:     风中的那朵云
       * @Description:  人员配置修改
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      OKPersonnelRow(record, v) {
        let that = this
        editStaffingManagement({
          personnelId: record.id,
          personnelEquipPosition: that.personnelEquipPosition.toString()
        }).then(function (res) {
          console.log(res)
          that.initStaffingManagement();
          if (res === 1) {

            that.$message.success("修改成功");
          } else {
            that.$message.warning("修改失败");
          }

        }).catch(function (err) {
          console.log(err)
        })
        this.personnelRowShow = false

      },
      //试题管理
      /**
       * @Author:     风中的那朵云
       * @Description:  试题添加
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      addTestQuestions() {
        this.testQuestionsModal = true
      },
      showTestManagementDetails(record) {
        this.testManagementDetails = record
        this.testManagementDetailsModal = true
      },


      //  学习和训练项目管理
      showLearningAndTrainingProjectManagement() {
        this.showLearningAndTrainingProjectManagementModal = true
      },


    },


    mounted() {
      this.initStaffingManagement()
    }
  }
</script>

<style scoped>
  .selectFont {
    color: #ffffff;
  }

</style>