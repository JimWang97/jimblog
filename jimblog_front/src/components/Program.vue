<template>
  <div>
    <el-row>
      <el-col :span="10" :offset="7">
        <h1 id="mainTitle" ref="mainTitle" name="mainTitle" style="text-align: left; font-size: 50px; margin-bottom: 10px">项目列表</h1>
        <hr>
        <div class="main-content">
          <div v-for="program in programs" v-bind:key="program.id">
            <a target="_blank" :href="program.url">
              <h1 class="title" style="margin-bottom: 5px">{{program.title}} </h1>
            </a>
            <p class="content">{{program.content}}</p>
            <br />
          </div>
        </div>
        <el-pagination
          :current-page="page"
          :page-size="limit"
          :total="total"
          style="padding: 30px 0; text-align: center;"
          layout="total, prev, pager, next, jumper"
          @current-change="getPrograms"
        />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import programApi from '../api/programApi'

export default {
  name: 'Program',
  data () {
    return {
      programs: [],
      page: 1, // 当前页
      limit: 5, // 每页记录数
      total: 0// 总记录数
    }
  },
  created () {
    this.getPrograms(this.page)
  },
  mounted () {
    var PageId = document.querySelector('#mainTitle')
    // 打印出对应页面与窗口的距离
    // 使用平滑属性，滑动到上方获取的距离
    // 下方我只设置了top，当然 你也可以加上 left 让他横向滑动
    // widow 根据浏览器滚动条，如果你是要在某个盒子里面产生滑动，记得修改
    window.scrollTo({
      top: PageId.getBoundingClientRect().top,
      behavior: 'smooth'
    })
  },
  methods: {
    getPrograms (page) {
      this.page = page
      programApi.getPrograms(this.page, this.limit).then(res => {
        if (res.code === 20000) {
          this.programs = res.data.programs
          this.total = res.data.total
        }
      })
    }
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
