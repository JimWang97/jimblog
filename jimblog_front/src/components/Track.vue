<template>
  <div>
    <el-row>
      <el-col :span="10" :offset="7">
        <h1 id="mainTitle" ref="mainTitle" name="mainTitle" style="text-align: left; font-size: 50px; margin-bottom: 10px">我的足迹</h1>
        <hr>
        <div class="main-content">
        </div>
<!--        <el-pagination-->
<!--          :current-page="page"-->
<!--          :page-size="limit"-->
<!--          :total="total"-->
<!--          style="padding: 30px 0; text-align: center;"-->
<!--          layout="total, prev, pager, next, jumper"-->
<!--          @current-change="getPrograms"-->
<!--        />-->
      </el-col>
    </el-row>
  </div>
</template>

<script>

export default {
  name: 'Track'
}
</script>

<style scoped>
  .main-content {
    margin: auto;
    font-family: Yuanti SC;
  }
</style>
