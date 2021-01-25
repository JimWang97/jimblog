<template>
  <div>
    <Navi style="width: 90%; position: absolute; z-index: 100"></Navi>
    <Header class="header"></Header>
    <router-view class="main"></router-view>

  </div>
</template>

<script>
import Navi from '../components/Navi'
import Header from '../components/Header'
export default {
  name: 'index.vue',
  components: {
    Navi,
    Header
  }
}
</script>

<style scoped>
.header {
  top:0px;
  left:0px;
  right:0px;
  bottom:0px;
  width: 100%;
  height: 500px;
  position: absolute;
  z-index: 0;
}
.main {
  margin-top: 0px;
  padding-top: 500px;
}
</style>
