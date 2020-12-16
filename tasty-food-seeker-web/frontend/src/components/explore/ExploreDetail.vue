<template>
  <div id="exploreDetail">
    디테일 페이지
    {{this.currentLocationItem}}
    <img src="https://naveropenapi.apigw.ntruss.com/map-static/v2/raster-cors?crs=NHN:128&w=800&h=800&center=309761,551897&public_transit=Y&level=16&X-NCP-APIGW-API-KEY-ID=sesxh1guj9">
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