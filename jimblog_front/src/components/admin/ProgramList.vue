<template>
  <div>
    <div style="margin: 10px auto 0px 20px; text-align: left">
      <router-link to="/admin/addProgram">
        <el-button>新增</el-button>
      </router-link>
    </div>

    <el-main>
      <el-table
        :data="tableData.filter(data => !search || data.title.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%">
        <el-table-column prop="title" label="标题" :show-overflow-tooltip='true' width="250">
        </el-table-column>
        <el-table-column prop="url" label="url" width="300">
        </el-table-column>
        <el-table-column prop="content" label="内容">
        </el-table-column>
        <el-table-column prop="lastEdit" label="最近修改时间" width="150">
        </el-table-column>
        <el-table-column
          align="right" width="200">
          <template slot="header">
            <el-input
              v-model="search"
              size="mini"
              placeholder="输入关键字搜索"/>
          </template>
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        :current-page="page"
        :page-size="limit"
        :total="total"
        style="padding: 30px 0; text-align: center;"
        layout="total, prev, pager, next, jumper"
        @current-change="getPrograms"
      />
    </el-main>
  </div>
</template>

<script>
import programApi from '../../api/programApi'
export default {
  name: 'ProgramList',
  methods: {
    getPrograms (page = 1) {
      this.page = page
      programApi.adminGetPrograms(this.page, this.limit).then(res => {
        if (res.code === 20000) {
          this.tableData = res.data.programs
          this.total = res.data.total
        }
      })
    },
    handleEdit (index, row) {
      this.$router.push({
        path: '/admin/addProgram',
        query: {
          id: row.id
        }
      })
    },
    handleDelete (index, row) {
      programApi.adminDeleteProgram(row.id).then(res => {
        if (res.code === 20000) {
          this.$message({
            message: res.message,
            type: 'success'
          })
          this.getBlogs(this.page)
        } else {
          this.$message.error(res.message)
        }
      })
    }
  },
  data () {
    return {
      tableData: {},
      search: '',
      page: 1, // 当前页
      limit: 8, // 每页记录数
      total: 0// 总记录数
    }
  },
  created () {
    this.getPrograms(this.page)
  }
}
</script>

<style scoped>

</style>
