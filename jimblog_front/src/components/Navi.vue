<template>
  <div style="margin-top: 20px">
    <el-row :gutter="10" type="flex" class="row-bg"  justify="end">
      <el-col :span="1">
        <a href="" class="home_link">首页</a>
      </el-col>
      <el-col :span="2">
        <a href="" class="home_link">Jim的博文</a>
      </el-col>
      <el-col :span="2">
        <a href="" class="home_link">Jim的项目</a>
      </el-col>
      <el-col :span="2">
        <a href="" class="home_link">Jim的足迹</a>
      </el-col>
      <el-col :span="2">
        <a href="" class="home_link">关于Jim</a>
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
.home_link {
  text-decoration:none;
  color: #eeeeee;
  font-weight: bolder;
  font-size: x-large;
  font-family: Hannotate SC;
}
</style>
