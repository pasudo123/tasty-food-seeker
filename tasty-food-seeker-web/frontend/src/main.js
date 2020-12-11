require('@/global/global.css')

import Vue from 'vue'
import VueMeta from 'vue-meta'
import naver from 'vue-naver-maps'
import Store from './store'
import Router from './router'
import App from './App.vue'
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(VueMeta)
Vue.use(naver, {clientID: 'sesxh1guj9', useGovAPI: false, subModules: ''})
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.config.productionTip = false


new Vue({
  render: h => h(App),
  router: Router,
  store: Store
}).$mount('#app')
