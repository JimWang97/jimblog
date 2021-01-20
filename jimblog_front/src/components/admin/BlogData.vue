<template>
  <div>
    <h1>文章总数：{{count}}</h1>
    <h1>阅读总数：{{views}}</h1>
  </div>
</template>

<script>
import blogApi from '../../api/blogApi'
export default {
  name: 'BlogData',
  data () {
    return {
      count: 0,
      views: 0
    }
  },
  methods: {
    getInfo () {
      blogApi.getStat().then(res => {
        if (res.code === 20000) {
          this.count = res.data.count
          this.views = res.data.views
        }
      })
    }
  },
  created() {
    this.getInfo()
  }
}
</script>

<style scoped>

</style>
