<template>
  <div id="exploreDetail">
    디테일 페이지
    {{this.currentLocationItem}}
  </div>
</template>

<script>

  import {createNamespacedHelpers} from 'vuex'

  const {
    mapActions: exploreMapActions,
    mapGetters: exploreMapGetters
  } = createNamespacedHelpers('explore')

  export default {
    name: "ExploreDetail",
    computed: {
      ...exploreMapGetters(['currentLocationItem'])
    },
    methods: {
      ...exploreMapActions(['fetchOneLocationById'])
    },
    created() {
      this.fetchOneLocationById(this.$route.params).then(() => {

      }).catch(() => {})
    }
  }
</script>

<style scoped>

</style>