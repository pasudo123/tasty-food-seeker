<template>
  <div id="exploreDetail">
    <div class="titleWrapper">
      <div class="categoryText">{{this.currentLocationItem.category}}</div>
      <h3 class="titleText">{{this.currentLocationItem.name}}</h3>
      <span>{{this.currentLocationItem.roadAddress}}</span>
    </div>
    <div id="current-map-container">
      <div :id="this.naverMapTagName" style="width:100%;height:550px;">
        <b-overlay
            :show="mapLoading" no-wrap></b-overlay>
      </div>
    </div>
  </div>
</template>

<script>

  import NAVER_MAP from "@/util/naver-map";
  import {createNamespacedHelpers} from 'vuex'

  const {
      mapActions: exploreMapActions,
      mapGetters: exploreMapGetters,
      mapMutations: exploreMapMutation
  } = createNamespacedHelpers('explore')

  export default {
    name: "ExploreDetail",
    data() {
      return {
        naverMapTagName: 'custom-naver-map',
        mapLoading: false,
        mapOptions: {
          zoom: 17,
          lat: 37,
          lng: 126
        },
      }
    },
    computed: {
      ...exploreMapGetters(['currentLocationItem'])
    },
    methods: {
      ...exploreMapActions(['fetchOneLocationById']),
      ...exploreMapMutation(['clearCurrentLocationItem']),
      naverMapInit() {
        this.clearCurrentLocationItem();

        this.fetchOneLocationById(this.$route.params).then(() => {
          this.mapOptions.lat = this.currentLocationItem.lat;
          this.mapOptions.lng = this.currentLocationItem.lng;
          NAVER_MAP.drawNaverMap(this.naverMapTagName, this.mapOptions);
        }).catch(() => {
          NAVER_MAP.drawNaverMap(this.naverMapTagName, this.mapOptions);
        })
      },
    },
    created() {
    },
    mounted() {
      this.mapLoading = true;
      this.$nextTick(() =>{
        this.naverMapInit();
        this.mapLoading = false;
      });
    },
    beforeDestroy() {
      this.map = null;
    }
  }
</script>

<style lang="scss" scoped>
  .titleWrapper {
    padding: 5px 0 10px 3px;
    text-align: left;

    .categoryText {
      padding: 0 0 5px 0;
    }

    .titleText {
      font-weight: 1000;
    }
  }

  #current-map-container {
    width: 100%;
  }
</style>