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
                  </span>
                  <sapn v-else>
                    <span :title="text" v-if="text !==null && text !=='' && text.length > 18 ">
                      {{text.substring(0,18) + '...'}}
                    </span>
                    <span v-else>
                      {{text}}
                    </span>
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
            <a-table :dataSource="initLearningAndTrainingProjectManagementData" :pagination="false"
                     :columns="learningManagementColumns">
              <template slot="equipPosition" slot-scope="text,record">
                <span>
                   <span>
                  <span v-if="staffingManagementRowShow == true && staffingManagementRowId == record.id">
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
                  </span>
                  <sapn v-else>
                    <span :title="text" v-if="text !==null && text !=='' && text.length > 18 ">
                      {{text.substring(0,18) + '...'}}
                    </span>
                    <span v-else>
                      {{text}}
                    </span>
                  </sapn>
                </span>
                </span>
              </template>
              <template slot="operation" slot-scope="text,record">
                <sapn v-if="staffingManagementRowShow == true && staffingManagementRowId == record.id">
                  <span style="font-size: 18px;color: #0ca5ec;cursor: pointer"
                        @click="OKLearningManagementRow(record,'1')">
                      {{"确定"}}
                    </span>
                  <span style="font-size: 18px;color: #ff0000;cursor: pointer;margin-left: 20px"
                        @click="staffingManagementSettings">
                      {{"取消"}}
                    </span>
                </sapn>
                <span v-else>
                  <span style="font-size: 18px;color: #0ca5ec;cursor: pointer"
                        @click="staffingManagementSettings(record, 3)">
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
                      @change="setLearningAndTrainingProjectManagementPage"
                      v-model="LearningAndTrainingProjectManagementPage"
                      :total="learningAndTrainingProjectManagementTotal"
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
                 <a-textarea v-model="LearningAndTrainingProjectManagementForm.name" placeholder="" allow-clear
                             @change="" style="width: 220px"/>
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
                    <a-select default-value="1" style="width: 220px"
                              v-model="LearningAndTrainingProjectManagementForm.studyType">
                        <a-select-option value="1">
                          {{"理论学习"}}
                        </a-select-option>
                        <a-select-option value="2">
                          {{"操作训练学习"}}
                        </a-select-option>
                    </a-select>
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
                     <a-select style="width: 220px" v-model="LearningAndTrainingProjectManagementForm.equipPosition"
                               mode="multiple">
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
                 <a-input style="width: 220px" v-model="LearningAndTrainingProjectManagementForm.studyTime"></a-input>
               </span>
            </a-col>
          </a-row>

          <span slot="footer">
                <a-row type="flex" justify="center">
                  <a-col :span="4">
                    <a-button @click="addLearningAndTrainingProjectManagementForm">
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
              <a-select style="width: 200px" defaultValue="all" @change="searchTestManagement">
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
          <a-col :span="5">
            <span>
              <a-input v-model="testManagementDescribe" style="width: 220px" placeholder="请输入描述内容"/>
              <a-button style="margin-left: 10px" @click="searchTestManagementDescribe">
                {{"搜索"}}
              </a-button>
            </span>
          </a-col>


          <a-col :span="3">
            <!--:action="http://localhost:8080/jantd-boot/api/testManagement/importExcel"-->
            <span>
                          <a-upload
                            name="file"
                            :multiple="true"
                            :headers="headers"
                            :action="url.fileUpload"
                            accept=".xlsx"
                            @change="handleImportExcel">
                                <a-button>
                                  {{'批量导入'}}
                                </a-button>
                          </a-upload>
            </span>
          </a-col>
          <a-col :span="2">
            <span>
            <a-popconfirm title="确定删除吗?" @confirm="removeTestManagement">
              <a-button>
                  {{"删除"}}
              </a-button>
          </a-popconfirm>
            </span>
          </a-col>
        </a-row>
        <a-row
          type="flex"
          style="margin-top: 20px;margin-bottom: 30px"
          justify="center"
          align="center">
          <a-col :span="24">
            <a-table :dataSource="initTestManagementData" :pagination="false" :columns="testManagementColumns"
                     :rowSelection="{ onChange: onSelectChange }">
              <template slot="problemTitle" slot-scope="text,record">
                <span>
                  <span v-if="text.length > 8" :title="text">
                      {{text.substring(0,8) + "..."}}
                  </span>
                  <sapn v-else>
                      {{text}}
                  </sapn>
                </span>
              </template>
              <template slot="equipPosition" slot-scope="text,record">
                <span>
                  <span v-if="testManagementSet=== true && testManagementRowId === record.id">
                      <a-select style="width: 200px" @change="testManagementPositionSetting" mode="multiple">
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
                  </span>
                  <sapn v-else>
                      {{text}}
                  </sapn>
                </span>

              </template>
              <template slot="operation" slot-scope="text,record">
                <sapn v-if="testManagementSet=== true && testManagementRowId === record.id">
                  <span style="font-size: 18px;color: #0ca5ec;cursor: pointer"
                        @click="OKTestManagementRow(record, '1')">
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
                        @click="testManagementSettings(record,'3')">
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
                      style="text-align: center;"
                      :total="testManagementDataTotal"

                      @change="setTestManagementPage"
                      v-model="testManagementPage"
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
                 <a-textarea placeholder="" allow-clear v-model="addTestForm.problemTitle" style="width: 220px"/>
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
                    <a-select default-value="1" style="width: 220px"
                              v-model="addTestForm.problemClassify">
                        <a-select-option value="1">
                          {{"理论学习"}}
                        </a-select-option>
                        <a-select-option value="2">
                          {{"操作训练学习"}}
                        </a-select-option>
                    </a-select>
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
                <a-select style="width: 220px" @change="" mode="multiple" v-model="addTestForm.equipPosition">
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
               <span>
                     <a-select default-value="1" style="width: 220px" v-model="addTestForm.problemType"
                               @change="selectQuestionType">
                        <a-select-option value="1">
                          单选
                        </a-select-option>
                        <a-select-option value="2">
                          多选
                        </a-select-option>
                        <a-select-option value="3">
                          判断
                        </a-select-option>
                      </a-select>
              </span>
            </a-col>
          </a-row>

          <template>
            <a-row type="flex" style="margin-top: 30px" justify="center">
              <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"选项A:"}}
                  </span>
              </a-col>
              <a-col :span="4">
               <span>
                 <a-textarea placeholder="" v-model="addTestForm.answerStr[0]" allow-clear @change=""
                             style="width: 220px"/>
               </span>
              </a-col>
            </a-row>
            <a-row type="flex" style="margin-top: 30px" justify="center">
              <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"选项B:"}}
                  </span>
              </a-col>
              <a-col :span="4">
               <span>
                 <a-textarea placeholder="" allow-clear @change="" v-model="addTestForm.answerStr[1]"
                             style="width: 220px"/>
               </span>
              </a-col>
            </a-row>
            <a-row type="flex" style="margin-top: 30px" justify="center" v-if="questionType">
              <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"选项C:"}}
                  </span>
              </a-col>
              <a-col :span="4">
               <span>
                 <a-textarea placeholder="" v-model="addTestForm.answerStr[2]" allow-clear @change=""
                             style="width: 220px"/>
               </span>
              </a-col>
            </a-row>
            <a-row type="flex" style="margin-top: 30px" justify="center" v-if="questionType">
              <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"选项D:"}}
                  </span>
              </a-col>
              <a-col :span="4">
               <span>
                 <a-textarea placeholder="" v-model="addTestForm.answerStr[3]" allow-clear @change=""
                             style="width: 220px"/>
               </span>
              </a-col>
            </a-row>
          </template>

          <a-row type="flex" style="margin-top: 30px" justify="center">
            <a-col :span="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"正确答案:"}}
                  </span>
            </a-col>
            <a-col :span="4">
               <span>
                     <a-select style="width: 220px" mode="multiple" v-model="addTestForm.successAnswer">
                        <a-select-option value="A">
                          A
                        </a-select-option>
                        <a-select-option value="B">
                          B
                        </a-select-option>
                        <a-select-option value="C" v-if="questionType">
                          C
                        </a-select-option>
                       <a-select-option value="D" v-if="questionType">
                          D
                        </a-select-option>
                      </a-select>
              </span>
            </a-col>
          </a-row>
          <span slot="footer">
                <a-row type="flex" justify="center">
                  <a-col :span="4">
                    <a-button @click="addTest">
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
          :footer="null"
        >
              <span slot="title">
                <img src="@/assets/img/tianjiaB.png" style="width: 24px;height: 24px;">
                <span style="font-size: 24px;position: relative;left: 12px;top:5px;">{{ "详情" }}</span>
              </span>
          <a-row type="flex" style="margin-top: 30px">
            <a-col :span="3" offset="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"试题描述:"}}
                  </span>
            </a-col>
            <a-col :span="18">
               <span style="font-size: 18px;color: #ffffff">
                 {{testManagementDetails.problemTitle}}
              </span>
            </a-col>
          </a-row>
          <a-row type="flex" style="margin-top: 30px" justify="center" v-for="item in testManagementDetails.answerArray" :key="item">
            <a-col :span="3" offset="3">
              <span style="color: #ffffff;font-size: 18px">
                {{item.No + ':'}}
              </span>
            </a-col>
            <a-col :span="18">
              <span style="font-size: 18px;color: #ffffff">
                {{item.answerStr}}
              </span>
            </a-col>
          </a-row>
          <a-row type="flex" style="margin-top: 30px">
            <a-col :span="3" :offset="3">
                  <span style="font-size: 18px;color: #ffffff">
                    {{"正确答案:"}}
                  </span>
            </a-col>
            <a-col :span="18">
               <span style="font-size: 18px;color: #ffffff">
                 {{testManagementDetails.successAnswer}}
               </span>


            </a-col>
          </a-row>

          <!--          <span slot="footer">-->
          <!--                <a-row type="flex" justify="center">-->
          <!--                  <a-col :span="4">-->
          <!--                    <a-button>-->
          <!--                      <a-icon type="upload"/>-->
          <!--                      确定-->
          <!--                    </a-button>-->
          <!--                  </a-col>-->
          <!--                </a-row>-->
          <!--              </span>-->
        </a-modal>
      </div>
    </a-card>

  </div>
</template>

<script>
  import './ExaminationPaperSettings.less'
  import {initStaffingManagement, editStaffingManagement} from '@/api/staffingManagement.js'
  import {
    initLearningAndTrainingProjectManagement,
    addLearningAndTrainingProjectManagement,
    editAndTrainingProjectManagement
  } from '@/api/learningAndTrainingProjectManagement.js'

  import {initTestManagement, editTestManagement, deleteTestManagement, addTest} from "@/api/testManagement.js"

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
      dataIndex: 'No',
      align: 'center'
    },
    {
      title: "试题描述",
      dataIndex: 'problemTitle',
      scopedSlots: {customRender: 'problemTitle'},
      align: 'center'
    },
    {
      title: '试题类型',
      dataIndex: 'problemClassify',
      align: 'center'
    },
    {
      title: '适用岗位',
      dataIndex: 'equipPosition',
      scopedSlots: {customRender: 'equipPosition'},
      align: 'center'
    },
    {
      title: '题型',
      dataIndex: 'problemType',
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
      dataIndex: 'No',
      align: 'center'
    },
    {
      title: '学习与培训项目',
      dataIndex: 'name',
      align: 'center'
    },
    {
      title: '学习类型',
      dataIndex: 'studyType',
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
      dataIndex: 'studyTime',
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


        testManagementSet: false,

        personnelModal: false,
        learningManagementSet: false,
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
        searchtestManagementequipPosition: 'all',

        staffingManagementPage: 1,
        LearningAndTrainingProjectManagementPage: 1,
        staffingManagementTotal: 0,
        initStaffingManagementData: [],


        initLearningAndTrainingProjectManagementData: [],
        learningAndTrainingProjectManagementTotal: 0,


        testQuestionsModal: false,
        testManagementDetailsModal: false,
        testManagementDetails: {
          name: '',
          examType: '',
          equipPosition: '',
          topicType: '',
          answer: '',
          answerArray: []
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
        personnelEquipPosition: [],
        LearningAndTrainingProjectManagementForm: {
          name: '',
          studyType: '',
          // equipPosition: "1",
          studyTime: ''
        },
        staffingManagementRowId: '',
        staffingManagementRowShow: false,

        testManagementPage: 1,
        testManagementDescribe: "",
        initTestManagementData: [],
        testManagementDataTotal: 0,


        testManagementEquipPosition: [],
        testManagementRowId: '',

        removeTestManagementId: '',

        questionType: true,

        addTestForm: {
          problemTitle: '',
          problemType: '',
          equipPosition: [],
          problemClassify: '',
          answerStr: [],
          // successAnswer: null
        },
        headers: {
          authorization: 'authorization-text',
        },
        url: {
          fileUpload: window._CONFIG['domianURL']+"/api/testManagement/importExcel"
        }
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
          this.initStaffingManagement()
        } else if (key === '2') {
          this.tabsVisible2 = true
          this.tabsVisible1 = false
          this.tabsVisible3 = false
          this.initLearningAndTrainingProjectManagement()
        } else if (key === '3') {
          this.tabsVisible2 = false
          this.tabsVisible1 = false
          this.tabsVisible3 = true
          this.initTestManagement()
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
        this.personnelEquipPosition = []
        this.personnelRowId = record.id
        // this.personnelPosition = record.equipPosition
        if (v == "3") {
          this.personnelRowShow = true
        } else {
          this.personnelRowShow = false
        }

      },
      //试题管理
      testManagementSettings(record, v) {
        this.testManagementRowId = record.id
        if (v == "3") {
          this.testManagementSet = true
        } else {
          this.testManagementSet = false
        }
      },
      positionSetting(v, p) {
        this.personnelEquipPosition = v
        console.log(v, p)
      },

      testManagementPositionSetting(v, p) {
        this.testManagementEquipPosition = v
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
              let d = c.replace("4", "指控车_维修");
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

      staffingManagementSettings(record, v) {
        this.personnelEquipPosition = []
        this.staffingManagementRowId = record.id
        if (v == "3") {
          this.staffingManagementRowShow = true
        } else {
          this.staffingManagementRowShow = false
        }

      },
      //  人员配置搜索管理
      searchStaffing(v, p) {
        console.log(v, p);
        this.equipPosition = v
        this.initStaffingManagement()
      },

      searchTestManagement(v, p) {
        console.log(v, p);
        this.searchtestManagementequipPosition = v
        this.initTestManagement()
      },

      searchTestManagementDescribe() {
        this.initTestManagement()
      },


      setPage(page) {
        console.log(page)
        this.staffingManagementPage = page
        this.initStaffingManagement()
      },
      setLearningAndTrainingProjectManagementPage(page) {
        console.log(page)
        this.LearningAndTrainingProjectManagementPage = page
        this.initLearningAndTrainingProjectManagement()
      },

      setTestManagementPage(page) {
        console.log(page)
        this.testManagementPage = page
        this.initTestManagement()
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


      OKLearningManagementRow(record, v) {
        let that = this
        editAndTrainingProjectManagement({
          learningManagementId: record.id,
          equipPosition: that.personnelEquipPosition.toString()
        }).then(function (res) {
          console.log(res)
          that.initLearningAndTrainingProjectManagement();
          if (res === 1) {
            that.$message.success("修改成功");
          } else {
            that.$message.warning("修改失败");
          }

        }).catch(function (err) {
          console.log(err)
        })
        this.staffingManagementRowShow = false

      },

      OKTestManagementRow(record, v) {
        let that = this
        editTestManagement({
          testManagementId: record.id,
          testManagementEquipPosition: that.testManagementEquipPosition.toString()
        }).then(function (res) {
          console.log(res)
          that.initTestManagement();
          if (res === 1) {
            that.$message.success("修改成功");
          } else {
            that.$message.warning("修改失败");
          }

        }).catch(function (err) {
          console.log(err)
        })
        this.testManagementSet = false

      },


      //试题管理
      /**
       * @Author:     风中的那朵云
       * @Description:  试题添加
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      addTestQuestions() {
        this.addTestForm ={
          problemTitle: '',
          problemType: '',
          equipPosition: [],
          problemClassify: '',
          answerStr: [],
          // successAnswer: null
        }
        this.testQuestionsModal = true
      },
      showTestManagementDetails(record) {
        this.testManagementDetails = record
        this.testManagementDetails.answerArray = JSON.parse(record.answerStr)
        this.testManagementDetailsModal = true
      },


      /**
       * @Author:     风中的那朵云
       * @Description:  学习与训练项目管理
       * @Date:    2020/5/28
       * @Version:    1.0
       */
      initLearningAndTrainingProjectManagement() {
        let that = this
        initLearningAndTrainingProjectManagement({
          pageNo: that.LearningAndTrainingProjectManagementPage
        }).then(function (res) {
          console.log(res)
          that.initLearningAndTrainingProjectManagementData = res.personnelList
          that.learningAndTrainingProjectManagementTotal = res.total
          let i = 1
          that.initLearningAndTrainingProjectManagementData.map(item => {
            item.No = i
            i++

            if (item.studyType === '1') {
              item.studyType = "理论学习"
            } else if (item.studyType === '2') {
              item.studyType = "操作训练学习"
            }
            if (item.equipPosition !== "" && item.equipPosition !== null && item.equipPosition.length > 0) {
              let a = item.equipPosition.replace("1", "武器装备_总体");
              let b = a.replace("2", "指控车_指挥");
              let c = b.replace("3", "指控车_操作");
              let d = c.replace("4", "指控车_维修");
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


      addLearningAndTrainingProjectManagementForm() {
        let that = this
        let name = that.LearningAndTrainingProjectManagementForm.name
        let studyType = that.LearningAndTrainingProjectManagementForm.studyType
        let equipPosition = that.LearningAndTrainingProjectManagementForm.equipPosition.toString()
        let studyTime = that.LearningAndTrainingProjectManagementForm.studyTime

        if (name === null || name === "") {
          that.$message.warning("描述不能为空");
          return;
        } else if (name.length > 6) {
          that.$message.warning("名称太长了");
          return;
        }
        if (studyType === null || studyType === "") {
          that.$message.warning("学习类型不能为空");
          return;
        }
        if (equipPosition === null || equipPosition === "") {
          that.$message.warning("使用岗位不能为空");
          return;
        } else if (studyTime === null || studyTime === "") {
          that.$message.warning("学时不能为空");
          return;
        }
        that.showLearningAndTrainingProjectManagementModal = false;
        addLearningAndTrainingProjectManagement({
          name: name,
          studyType: studyType,
          equipPosition: equipPosition,
          studyTime: studyTime
        }).then(function (res) {
          console.log(res)
          if (res === 1) {
            that.initLearningAndTrainingProjectManagement();
            that.$message.success("添加成功");
          } else {
            that.$message.warning("添加失败");
          }

        }).catch(function (err) {
          console.log(err)
        })
      },
      //  学习和训练项目管理
      showLearningAndTrainingProjectManagement() {
        this.LearningAndTrainingProjectManagementForm= {}
        this.showLearningAndTrainingProjectManagementModal = true
      },


      /**
       * @Author:     风中的那朵云
       * @Description:  试卷管理
       * @Date:    2020/5/6
       * @Version:    1.0
       */
      initTestManagement() {
        let that = this
        initTestManagement({
          pageNo: that.testManagementPage,
          equipPosition: that.searchtestManagementequipPosition.toString(),
          testDescribe: that.testManagementDescribe
        }).then(function (res) {
          console.log(res)
          that.initTestManagementData = res.personnelList
          that.testManagementDataTotal = res.total
          let i = 1
          that.initTestManagementData.map(item => {
            item.No = i
            i++

            if (item.problemType === '1') {
              item.problemType = "单选"
            } else if (item.problemType === '2') {
              item.problemType = "多选"
            } else if (item.problemType === '3') {
              item.problemType = "判断"
            }

            if (item.problemClassify === "1") {
              item.problemClassify = "理论学习"
            } else if (item.problemClassify === "2") {
              item.problemClassify = "操作训练学习"
            }


            if (item.equipPosition !== "" && item.equipPosition !== null && item.equipPosition.length > 0) {
              let a = item.equipPosition.replace("1", "武器装备_总体");
              let b = a.replace("2", "指控车_指挥");
              let c = b.replace("3", "指控车_操作");
              let d = c.replace("4", "指控车_维修");
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
      removeTestManagement() {
        let that = this
        deleteTestManagement({
          testManagementId: that.removeTestManagementId
        }).then(function (res) {
          console.log(res)
          if (res === 1) {
            that.initTestManagement();
            that.$message.success("删除成功");
          } else {
            that.$message.warning("删除失败");
          }
        }).catch(function (err) {
          console.log(err)
        })
      },
      onSelectChange(selectedRowKeys, selectedRows) {
        console.log(selectedRowKeys, selectedRows);
        if (selectedRows.length > 0) {
          let x;
          let keys = [];
          for (let i = 0; i < selectedRows.length; i++) {
            keys.push(selectedRows[i].id);
          }
          this.removeTestManagementId = keys.toString();
        }
      },
      selectQuestionType(v) {
        if (v === "1") {
          this.questionType = true
        } else if (v === "2") {
          this.questionType = true
        } else if (v === "3") {
          this.questionType = false
        }
      },


      addTest() {
        let that = this
        addTest({
          problemTitle: that.addTestForm.problemTitle,
          problemClassify: that.addTestForm.problemClassify,
          problemType: that.addTestForm.problemType,
          equipPosition: that.addTestForm.equipPosition.toString(),
          answerStr: that.addTestForm.answerStr.toString(),
          successAnswer: that.addTestForm.successAnswer.toString()
        }).then(function (res) {
          that.testQuestionsModal = false
          console.log(res)
          if (res === 1) {
            that.initTestManagement();
            that.$message.success("添加成功");
          } else {
            that.$message.warning("添加失败");
          }

        }).catch(function (err) {
          console.log(err)
        })

      },
      handleImportExcel(info) {
        if (info.file.status !== 'uploading') {
          console.log(info.file, info.fileList);
        }
        if (info.file.status === 'done') {
          this.initTestManagement();
          this.$message.success(`${info.file.name} 上传成功`);
        } else if (info.file.status === 'error') {
          this.$message.error(`${info.file.name} 上传失败.`);
        }
      }
    },


    mounted() {
      //this.initTestManagement()
      this.initStaffingManagement()
      //this.initLearningAndTrainingProjectManagement()

    }
  }
</script>

<style scoped>
  .selectFont {
    color: #ffffff;
  }

</style>