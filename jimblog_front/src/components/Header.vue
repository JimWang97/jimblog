<template>
    <div class="tip-div">
      <el-row :gutter="20" style="">
        <el-col :span="6" :offset="8" style="margin-top: 150px; text-decoration:none; color: #eeeeee; font-weight: bolder; font-size: 72px; font-family: Hannotate SC">Jim Blog</el-col>
        <el-col :span="6" :offset="8" style="text-decoration:none; color: #eeeeee; font-weight: bolder; font-size: x-large; font-family: Hannotate SC">Personal blog powered by Jim Wang</el-col>
      </el-row>
    </div>
</template>

<script>
export default {
  name: 'Header'
}
</script>

<style scoped>
  .tip-div {
    width: 100%;
    height: 100%;
    color: red;
    background: rgba(0, 0, 0, 0) url('../assets/background.jpg') no-repeat scroll 50% 40%;
    background-size: auto;
    background-size: cover;
  }
</style>
