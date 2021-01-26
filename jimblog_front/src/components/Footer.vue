<template>
  <div style="background-color: #000000">
    <el-row style="width: 70%; margin: 50px auto 0px auto; color: #B3C0D1; font-size: small">
      <p><router-link to="/" >
        <a class="home_link">首页</a>
      </router-link> - <router-link to="/blogs">
        <a class="home_link">Jim的博文</a>
      </router-link> - <a href="">Jim的项目</a> - <a href="">Jim的足迹</a> - <a href="">关于Jim</a></p>
      <p style="color: #B3C0D1">©CopyRight 2021- Powered By Jim Wang</p>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Footer'
}
</script>

<style scoped>
  a:link {
    color: #B3C0D1;
    text-decoration: none;
  }
  a:visited {
    color: #B3C0D1;
    text-decoration: none;
  }
  a:hover {
    color: #999999;
    text-decoration: underline;
  }
</style>
