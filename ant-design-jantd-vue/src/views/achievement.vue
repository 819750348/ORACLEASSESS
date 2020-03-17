<template>
  <div>
    <span>
      {{ "成绩概括" }}
    </span>
    <div>
      <a-row type="flex">
        <a-col :span="4">
          <div>
            <span>
              {{ "试卷得分" }}
            </span>
          </div>
        </a-col>
      </a-row>
      <a-row>
        <a-col>
          <span>
            {{ achievement }}
          </span>
        </a-col>
      </a-row>
    </div>
    <div>
      <a-row type="flex">
        <a-col :span="4">
          <div>
            <span>
              {{ "当前排名" }}
            </span>
          </div>
        </a-col>
      </a-row>
      <a-row type="flex">
        <a-col :span="4">
          {{ ranking }}
        </a-col>
      </a-row>
    </div>

    <a-row type="flex">
      <a-col>
        {{ "综合成绩分析" }}
      </a-col>
      <a-col>

      </a-col>
    </a-row>
    <a-row>
      <a-col>
        {{ "最新试题" }}
      </a-col>
    </a-row>
    <a-row>
      <acol>
        <a-table :colums="tableColums" :dataSource="tableData">

        </a-table>
      </acol>
    </a-row>

  </div>
</template>

<script>
  const tableColums = [{
    title: '序号',
    align: "center",
    dataIndex: 'No'
  },
    {
      title: '试卷名称',
      align: 'center',
      dataIndex: 'title'
    }, {
      title: '上传时间',
      align: 'center',
      dataIndex: 'update'
    },
    {
      title: '操作',
      align: 'center',
      scopedSlots: {customRender: 'operation'}
    }]
  const tableData = [
    {
      No: 1,
      title: '模型试卷',
      update: '2019-11-07 11:11:11',
    },{
      No: 2,
      title: '测试试卷',
      update: '2019-11-11 11:11:11'
    },
    {
      No: 3,
      title: '测试试卷',
      update: '2019-11-11 11:11:11'
    },
    {
      No: 4,
      title: '测试试卷',
      update: '2019-11-11 11:11:11'
    },{
      No: 5,
      title: '测试试卷',
      update: '2019-11-11 11:11:11'
    },{
      No: 6,
      title: '测试试卷',
      update: '2019-11-11 11:11:11'
    }
  ]

  export default {
    name: "Achievement",
    data() {
      return {
        achievement: 98,
        ranking: 1,
        tableColums,
        tableData
      }
    }
  }
</script>

<style scoped>

</style>