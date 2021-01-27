<template>
  <div style="margin-top: 20px; z-index: 999">
    <el-row :gutter="0" type="flex" class="row-bg"  justify="end">
      <el-col :span="1">
        <router-link to="/" >
          <div class="home_link">首页</div>
        </router-link>
      </el-col>
      <el-col :span="2">
        <router-link to="/blogs">
          <div class="home_link">Jim的博文</div>
        </router-link>
      </el-col>
      <el-col :span="2">
        <router-link to="/program">
          <div class="home_link">Jim的项目</div>
        </router-link>
      </el-col>
      <el-col :span="2">
        <router-link to="/track">
          <div class="home_link">Jim的足迹</div>
        </router-link>
      </el-col>
      <el-col :span="2">
        <a href="#" class="home_link">关于Jim</a>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Navi'
}
</script>

<style scoped>
a {
  text-decoration: none;
}

.router-link-active {
  text-decoration: none;
}
.home_link {
  color: #eeeeee;
  font-weight: bolder;
  font-size: x-large;
  font-family: Hannotate SC;
}
</style>
