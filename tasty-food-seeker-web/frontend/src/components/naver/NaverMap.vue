<template>
  <div id="mapWrapper">
    <naver-maps :height="height"
                :map-options="mapOptions"
                :init-layers="initLayers"
                @load="onLoad" />
  </div>
</template>

<script>
  export default {
    name: "NaverMap",
    data() {
      return {
        map: null,
        height: 460,
        mapOptions: {
          lat: 37.5758254,
          lng: 126.9778619,
          zoom: 15,
          zoomControl: true,
          zoomControlOptions: {position: 'TOP_RIGHT'},
          mapTypeControl: true
        },
        initLayers: ['BACKGROUND', 'BACKGROUND_DETAIL', 'POI_KOREAN', 'TRANSIT', 'ENGLISH', 'CHINESE', 'JAPANESE']
      }
    },
    methods: {
      onLoad(vue) {
        this.map = vue;
      }
    }
  }
</script>

<style scoped>
  #mapWrapper {
    margin: 0 auto;
    text-align: center;
    width: 100%;
  }
</style>