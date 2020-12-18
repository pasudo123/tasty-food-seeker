<template>
  <div id="root">
    <router-view />
  </div>
</template>

<script>

  export default {
    name: "Root",
    // components: { NaverMap },
    data() {
      return {

      }
    },
    methods: {
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