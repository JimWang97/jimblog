import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login'
import adminIndex from '../views/adminIndex'
import BlogData from '../components/admin/BlogData'
import BlogList from '../components/admin/BlogList'
import AddBlog from '../components/admin/AddBlog'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/admin/index',
    name: 'AdminIndex',
    component: adminIndex,
    children: [{
      path: '/admin/index/blogdata',
      name: 'AdminBlogData',
      component: BlogData
    }, {
      path: '/admin/index/bloglist',
      name: 'AdminBlogList',
      component: BlogList
    }, {
      path: '/admin/addBlog',
      name: 'AdminAddBlog',
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
