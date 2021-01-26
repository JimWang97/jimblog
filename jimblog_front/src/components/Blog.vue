<template>
  <el-row>
    <el-col :span="10" :offset="7">
      <div class="main-content">
        <h1 class="title" style="margin-bottom: 5px">{{blog.title}} </h1>
        <div class="type">
          <el-row>
            <el-col :span="16">
              <span >{{blog.type}}</span>
              <span v-if="blog.tags.length>0"> > </span>
              <span v-for="tag in blog.tags" v-bind:key="tag"> {{tag}}</span>
            </el-col>
            <el-col :span="3" style="text-align: right">
              <span style="text-align: right"><i class="el-icon-view"></i>{{blog.views}}  </span>
            </el-col>
            <el-col :span="5" style="text-align: right">
              <span>{{blog.lastEdit}}</span>
            </el-col>
          </el-row>
        </div>
        <MarkdownPreview :initialValue="blog.content" style="text-align: left"></MarkdownPreview>
        <br />
      </div>
    </el-col>
  </el-row>
</template>

<script>
import blogApi from '../api/blogApi'
import { MarkdownPreview } from 'vue-meditor'
export default {
  name: 'Blog',
  components: {
    MarkdownPreview
  },
  data () {
    return {
      blogid: '',
      blog: {}
    }
  },
  created () {
    if (this.$route.query.id) {
      this.blogid = this.$route.query.id
      this.getBlog()
    } else {
      this.$message.error('该博文不存在')
      this.$router.push('/')
    }
  },
  methods: {
    getBlog () {
      blogApi.getBlog(this.blogid).then(res => {
        if (res.code === 20000) {
          console.log(res.data.blog)
          this.blog = res.data.blog
        }
      })
    }
  }
}
</script>

<style scoped>
  .main-content {
    margin: auto;
    font-family: Yuanti SC;
  }
  .content {
    text-align: left;
  }
  .title {
    text-align: left;
  }
  .type {
    text-align: left;
    font-size: 10px;
    color: #B3C0D1;
  }
</style>
