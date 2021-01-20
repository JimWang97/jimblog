<template>
  <div>
    <div style="margin: 10px auto 0px 20px; text-align: left">
      <router-link to="/admin/addBlog">
      <el-button>新增</el-button>
      </router-link>
    </div>

    <el-main>
      <el-table
        :data="tableData.filter(data => !search || data.title.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%">
        <el-table-column prop="title" label="标题" :show-overflow-tooltip='true' width="150">
        </el-table-column>
        <el-table-column prop="type" label="类型" width="50">
        </el-table-column>
        <el-table-column prop="content" :show-overflow-tooltip='true' label="描述">
        </el-table-column>
        <el-table-column prop="tags" label="标签" width="300" :show-overflow-tooltip='true'>
          <template slot-scope="scope">
            <el-button size="mini" type="warning" round v-for="tag in scope.row.tags" :key="tag">{{tag}}</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="views" label="观看数" width="70">
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
        @current-change="getBlogs"
      />
    </el-main>
  </div>
</template>

<script>
import blogApi from '../../api/blogApi'
export default {
  name: 'BlogList',
  methods: {
    getBlogs (page = 1) {
      this.page = page
      blogApi.adminGetBlogs(this.page, this.limit).then(res => {
        if (res.code === 20000) {
          this.tableData = res.data.blogs
          this.total = res.data.total
        }
      })
    },
    handleEdit (index, row) {
      console.log(index, row)
    },
    handleDelete (index, row) {
      console.log(index, row)
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
    this.getBlogs(this.page)
  }
}
</script>

<style scoped>

</style>
