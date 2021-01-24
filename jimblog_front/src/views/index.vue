<template>
  <div>
    <Navi style="width: 80%; position: fixed"></Navi>
    <Header class="header"></Header>
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
  position: page;
  top:0px;
  left:0px;
  right:0px;
  bottom:0px;
}
</style>
