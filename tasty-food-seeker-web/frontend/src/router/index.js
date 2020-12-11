import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  base: '/',
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: () => import('../components/Root')
    },
    {
      path: '/explore',
      name: 'Explore',
      component: () => import('../components/explore/Explore')
    },
    {
      path: '/map',
      name: 'Map',
      component: () => import('../components/explore/Explore')
    }
  ]
})