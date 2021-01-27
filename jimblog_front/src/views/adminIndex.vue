<template>
    <div>
      <div style="margin: 10px auto 0px 20px; text-align: right">
        <el-button @click="logout()">注销</el-button>
      </div>
      <el-container style="height: 730px; border: 1px solid #eee; margin-top: 50px">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
          <el-menu :default-openeds="['1', '3']">
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-message"></i>博文管理</template>
              <el-menu-item-group>
                <template slot="title">博文</template>
                <router-link to="/admin/index/blogdata">
                  <el-menu-item index="1-1">
                    博文数据
                  </el-menu-item>
                </router-link>
                <router-link to="/admin/index/bloglist">
                  <el-menu-item index="1-2">
                    博文列表
                  </el-menu-item>
                </router-link>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-menu"></i>项目展示</template>
              <el-menu-item-group>
                <template slot="title">项目</template>
                <router-link to="/admin/index/programlist">
                  <el-menu-item index="2-1">
                    项目列表
                  </el-menu-item>
                </router-link>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title"><i class="el-icon-setting"></i>足迹管理</template>
              <el-menu-item-group>
                <template slot="title">足迹</template>
                <el-menu-item index="3-1">足迹图</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>

        <el-container>
          <router-view style="width: 100%; height: 100%"></router-view>
        </el-container>
      </el-container>
    </div>
</template>

<script>
import login from '../api/login'
import { setToken } from '../../utils/auth'
export default {
  name: 'adminIndex',
  data () {
    const item = {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    }
    return {
      tableData: Array(10).fill(item)
    }
  },
  methods: {
    logout () {
      login.logout().then(res => {
        if (res.code === 20000) {
          this.$router.push('/login')
          setToken('')
        }
      })
    }
  }
}
</script>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>
