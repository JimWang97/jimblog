<template>
  <div>
    <el-row style="margin-top: 10px">
      <el-col :span="10" :offset="7">
        <div class="main-content">
          <div v-for="blog in topBlogs" v-bind:key="blog.id">
            <router-link :to="{path:'/blog',query:{id: blog.id}}">
              <h1 class="title" style="margin-bottom: 5px">{{blog.title}} </h1>
            </router-link>
            <div class="type">
              <el-row>
                <el-col :span="16">
                  <span >{{blog.type}}</span>
                  <span v-if="blog.tags.length>0"> > </span>
                  <span v-for="tag in blog.tags" v-bind:key="tag"> {{tag}}</span>
                </el-col>
              </el-row>
            </div>
            <p class="content">{{blog.content}}</p>
            <br />
          </div>
        </div>
      </el-col>
    </el-row>
    </div>
</template>

<script>
import blogApi from '../api/blogApi'

export default {
  name: 'Main',
  data () {
    return {
      topBlogs: []
    }
  },
  created () {
    this.getTopBlogs()
  },
  methods: {
    getTopBlogs () {
      blogApi.getTopBlogs(5).then(res => {
        if (res.code === 20000) {
          this.topBlogs = res.data.blogs
          this.topBlogs.forEach(item => {
            item.content = item.content.replace(/[`&\|\\\*^%$#@\-]/g," ")
          })
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
    display: -webkit-box;
    /* -webkit-box-orient: vertical; */
    /*! autoprefixer: off */
    -webkit-box-orient: vertical;
    /* autoprefixer: on */
    -webkit-line-clamp: 4;
    overflow: hidden;
  }
  a:link {
    color: #000000;
    text-decoration: none;
  }
  a:visited {
    color: #000000;
    text-decoration: none;
  }
  a:hover {
    color: #999999;
    text-decoration: underline;
  }
  .title {
    text-align: left;
    display: -webkit-box;
    /* -webkit-box-orient: vertical; */
    /*! autoprefixer: off */
    -webkit-box-orient: vertical;
    /* autoprefixer: on */
    -webkit-line-clamp: 1;
    overflow: hidden;
  }
  .type {
    text-align: left;
    font-size: 10px;
    color: #B3C0D1;
    display: -webkit-box;
    /* -webkit-box-orient: vertical; */
    /*! autoprefixer: off */
    -webkit-box-orient: vertical;
    /* autoprefixer: on */
    -webkit-line-clamp: 1;
    overflow: hidden;
  }
</style>
