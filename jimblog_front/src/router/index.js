import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login'
import adminIndex from '../views/adminIndex'
import index from '../views/index'
import BlogData from '../components/admin/BlogData'
import BlogList from '../components/admin/BlogList'
import AddBlog from '../components/admin/AddBlog'
import IndexMain from '../components/IndexMain'
import BlogMain from '../components/BlogMain'
import Blog from '../components/Blog'
import Program from '../components/Program'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    component: index,
    children: [{
      path: '/',
      name: 'IndexMain',
      component: IndexMain
    }, {
      path: '/blogs',
      name: 'BLogList',
      component: BlogMain
    }, {
      path: '/blog',
      name: 'Blog',
      component: Blog
    }, {
      path: '/program',
      name: 'Program',
      component: Program
    }]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/admin/index',
    name: 'AdminIndex',
    component: adminIndex,
    meta: { type: 'login' },
    children: [{
      path: '/admin/index/blogdata',
      name: 'AdminBlogData',
      meta: { type: 'login' },
      component: BlogData
    }, {
      path: '/admin/index/bloglist',
      name: 'AdminBlogList',
      meta: { type: 'login' },
      component: BlogList
    }, {
      path: '/admin/addBlog',
      name: 'AdminAddBlog',
      meta: { type: 'login' },
      component: AddBlog
    }]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
