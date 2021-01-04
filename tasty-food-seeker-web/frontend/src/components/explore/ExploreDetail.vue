<template>
  <div id="exploreDetail">
    <div class="headerWrapper">
      <div class="titleWrapper">
        <div class="categoryText">{{ this.currentLocationItem.category }}</div>
        <h3 class="titleText">{{ this.currentLocationItem.name }}</h3>
        <span>{{ this.currentLocationItem.roadAddress }}</span>
      </div>
      <div class="backBtnWrapper">
        <b-button class="backBtn"
                  variant="outline-secondary"
                  @click="backToExplore">
          목록보기
        </b-button>
      </div>
    </div>
    <div id="current-map-container">
      <div :id="this.naverMapTagName" style="width:100%;height:550px;">
        <b-overlay
            :show="mapLoading" no-wrap></b-overlay>
      </div>
    </div>
    <div class="blogItemsWrapper">
      <b-table hover
               primary-key="index"
               sort-by.sync="title"
               selectable
               @row-selected="onRowSelected"
               :select-mode="'single'"
               :head-variant="'dark'"
               :fields="this.blogFields"
               :items="this.currentBlogItems">
        <template #cell(index)="data">
          {{ (data.index + 1) }}
        </template>

        <template #cell(title)="data">
          <div class="titleDiv">
            {{ data.item.title }}
          </div>
        </template>

        <template #cell(desc)="data">
          <div class="descDiv">
            {{ data.item.desc }}
          </div>
        </template>
      </b-table>
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
        blogFields: []
      }
    },
    computed: {
      ...exploreMapGetters([
          'currentLocationItem',
        'currentBlogItems',
        'blogTableFields',
        'blogTableFieldsOnMobile'])
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
      onRowSelected(items) {
        let currentSelectedItem = items[0];
        window.open(`${currentSelectedItem.link}`, '_blank');
      },
      backToExplore(event) {
        history.back();
      },
      handleWindowSize() {
        this.blogFields = (window.innerWidth >= 860)
            ? this.blogTableFields
            : this.blogTableFieldsOnMobile;
      }
    },
    mounted() {
      this.handleWindowSize();
      window.addEventListener('resize', this.handleWindowSize, {passive: true});
      this.mapLoading = true;
      this.$nextTick(() => {
        this.naverMapInit();
        this.mapLoading = false;
      });
    },
    beforeDestroy() {
      window.removeEventListener('resize', this.handleWindowSize, false);
      this.map = null;
    }
  }
</script>

<style lang="scss">
  /* PC 화면 기준 */
  @media screen and (min-width: 881px) {
    #exploreDetail {
      .headerWrapper {
        display: flex;
        align-items: center;
        justify-content: space-between;

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

        .backBtnWrapper {
          .backBtn {
            width: 100px;
            height: 40px;
          }
        }
      }

      #current-map-container {
        width: 100%;
        border: 2px solid darkslategray;
      }

      .blogItemsWrapper {
        margin: 10px 0 0 0;
      }

      div.titleDiv {
        width: 390px;
        text-overflow: ellipsis;
        overflow: hidden;
        white-space: nowrap;
      }

      div.descDiv {
        width: 390px;
        text-overflow: ellipsis;
        overflow: hidden;
        white-space: nowrap;
      }
    }
  }

  /* Mobile 화면 기준 */
  @media screen and (max-width: 880px) {
    #exploreDetail {
      .headerWrapper {
        display: flex;
        align-items: center;
        justify-content: space-between;

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

        .backBtnWrapper {
          .backBtn {
            font-size: 13px;
            width: 80px;
            height: 40px;
          }
        }
      }

      #current-map-container {
        border: 1px solid darkslategray;
        #custom-naver-map {
          width: 100%;
          height: 300px !important;
        }
      }

      div.titleDiv {
        text-align: center;
      }
    }
  }
</style>