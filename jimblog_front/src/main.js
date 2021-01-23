import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Router from 'vue-router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import qs from 'qs'
import { getToken } from '../utils/auth'
Vue.use(ElementUI)

Vue.config.productionTip = false
Vue.prototype.$qs = qs
// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = Router.prototype.push
Router.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}
router.beforeEach((to, from, next) => {
  const type = to.meta.type// type是根据meta中设置的变量
  if (type === 'login') {
    if (getToken()) { // 使用了js-cookie中间件处理cookie值
      next()
    } else {
      // todo 这里不要跳转到登陆入口，跳转到博客首页去
      next({
        path: '/login'
        // query: {
        //   redirect: to.fullPath
        // }
      })// 保存要跳转的页面路由，作为参数，传到登陆页面
    }
  } else {
    next()
  }
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
