require('@/global/global.css')

import Vue from 'vue'
import VueMeta from 'vue-meta'
import NAVER_MAP from '@/util/naver-map'
import Store from './store'
import Router from './router'
import App from './App.vue'
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(VueMeta)
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.config.productionTip = false

NAVER_MAP.createNaverMapScript('sesxh1guj9');


new Vue({
  render: h => h(App),
  router: Router,
  store: Store
}).$mount('#app')
