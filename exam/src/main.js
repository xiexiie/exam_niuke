// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import echarts from 'echarts'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueCookies from 'vue-cookies'
import mavonEditor from 'mavon-editor'  //引入mavon-editor 就是上面所安装的
import 'mavon-editor/dist/css/index.css'
import VueAwesomeSwiper from 'vue-awesome-swiper'
import 'swiper/swiper-bundle.css'


Vue.use(mavonEditor)  //让Vue使用mavonEditor
Vue.use(ElementUI)
Vue.use(VueCookies)
Vue.use(VueAwesomeSwiper, /* { default options with global component } */)

Vue.config.productionTip = false
Vue.prototype.bus = new Vue()
Vue.prototype.$echarts = echarts;
Vue.prototype.$axios = axios;

new Vue({
  el: '#app',
  router,
  render: h => h(App),
  components: { App },
  template: '<App/>'
})
