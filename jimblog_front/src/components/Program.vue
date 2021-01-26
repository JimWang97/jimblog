<template>
  <div>
    <el-row>
      <el-col :span="10" :offset="7">
        <h1 id="mainTitle" ref="mainTitle" name="mainTitle" style="text-align: left; font-size: 50px; margin-bottom: 10px">项目列表</h1>
        <hr>
        <div class="main-content">
          <div v-for="blog in blogs" v-bind:key="blog.id">
            <router-link target="_blank" :to="{path:'/blog',query:{id: blog.id}}">
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
        <el-pagination
          :current-page="page"
          :page-size="limit"
          :total="total"
          style="padding: 30px 0; text-align: center;"
          layout="total, prev, pager, next, jumper"
          @current-change="getBlogs"
        />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import blogApi from '../api/blogApi'

export default {
  name: 'Program',
  data () {
    return {
      isFixed: false,
      blogs: [],
      searchTitle: '',
      search: '',
      page: 1, // 当前页
      limit: 5, // 每页记录数
      total: 0// 总记录数
    }
  },
  created () {
    this.getBlogs(this.page)
  },
  mounted () {
    var PageId = document.querySelector('#mainTitle')
    // 打印出对应页面与窗口的距离
    // 使用平滑属性，滑动到上方获取的距离
    // 下方我只设置了top，当然 你也可以加上 left 让他横向滑动
    // widow 根据浏览器滚动条，如果你是要在某个盒子里面产生滑动，记得修改
    console.log(PageId.getBoundingClientRect().top)
    window.scrollTo({
      top: PageId.getBoundingClientRect().top,
      behavior: 'smooth'
    })

    // 设置bar浮动阈值为 #fixedBar 至页面顶部的距离
    this.offsetTop = this.$refs.navRef.offsetTop
    // 开启滚动监听
    window.addEventListener('scroll', this.handleScroll)
  },
  methods: {
    getBlogs (page) {
      this.page = page
      blogApi.getBlogs(this.page, this.limit).then(res => {
        if (res.code === 20000) {
          this.blogs = res.data.blogs
          this.total = res.data.total
          this.blogs.forEach(item => {
            item.content = item.content.replace(/[`&\|\\\*^%$#@\-]/g, '')
          })
        }
      })
    },
    searchHandler () {
      blogApi.searchBlog(this.searchTitle, this.page, this.limit).then(res => {
        if (res.code === 20000) {
          this.blogs = res.data.blogs
          this.total = res.data.total
          this.blogs.forEach(item => {
            item.content = item.content.replace(/[`&\|\\\*^%$#@\-]/g, '')
          })
        }
      })
    },
    // 滚动监听  滚动触发的效果写在这里
    handleScroll () {
      var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
      if (scrollTop >= this.offsetTop + 470) {
        this.isFixed = true
      } else {
        this.isFixed = false
      }
    }
  },
  destroyed () {
    // 离开页面 关闭监听 不然会报错
    window.removeEventListener('scroll', this.handleScroll)
  }
}
</script>

<style scoped>
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
  .side-content {
    margin-top: 50px;
    border: 0px solid #eee;
    width: 300px;
  }
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
  .nav_fixed {
    position: fixed;
    top: 30px;
    z-index: 2;
  }
  .side-bar {
    padding: 10px;
    width: 300px;
    border: 1px solid #eee;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  }
</style>
