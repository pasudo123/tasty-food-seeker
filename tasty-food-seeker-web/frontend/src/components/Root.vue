<template>
  <div id="root">
    <home />
    <router-view />
  </div>
</template>

<script>

  import Home from '@/components/home/Home'

  export default {
    name: "Root",
    components: { Home },
    data() {
      return {

      }
    },
    methods: {

    },
    created() {
    },
    metaInfo() {
      return {
        title: '맛있는 음식을 찾아라',
        meta: [
          {
            name: 'viewport',
            content: 'width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no'
          },
        ]
      }
    }
  }
</script>

<style scoped>
  #root {
    margin-top: 10px;
  }
</style>