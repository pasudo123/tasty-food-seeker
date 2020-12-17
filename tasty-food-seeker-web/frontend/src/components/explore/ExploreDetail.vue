<template>
  <div id="exploreDetail">
    {{ this.currentLocationItem }}

    <div id="current-map-container">
      <naver-maps
          :key="this.mapRender"
          :width="this.mapWidth" :height="580"
          :initLayers="this.initLayers"
          :mapOptions="this.mapOptions"></naver-maps>
    </div>

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
    data() {
      return {
        mapRender: true,
        mapWidth: 1080,
        initLayers: ['BACKGROUND', 'BACKGROUND_DETAIL', 'POI_KOREAN', 'TRANSIT'],
        mapOptions: {
          zoom: 15,
          lat: 37,
          lng: 126
        }
      }
    },
    computed: {
      ...exploreMapGetters(['currentLocationItem'])
    },
    methods: {
      ...exploreMapActions(['fetchOneLocationById']),
      initMap() {
        this.fetchOneLocationById(this.$route.params).then(() => {
          this.mapOptions.lat = this.currentLocationItem.lat;
          this.mapOptions.lng = this.currentLocationItem.lng;
          this.mapRender = !this.mapRender;
        }).catch(() => {
          this.mapRender = !this.mapRender;
        })
      },
      initMapWidth() {
        // width 를 현재 화면에 맞춤
        let mapContainer = document.querySelector('#current-map-container');
        this.mapWidth = mapContainer.offsetWidth - 1;
      }
    },
    created() {

    },
    mounted() {
      this.$nextTick(() =>{
        this.initMap();
        this.initMapWidth();
      });
    }
  }
</script>

<style scoped>
  #current-map-container {
    /*border: 1px solid red;*/
    width: 100%;
  }
</style>