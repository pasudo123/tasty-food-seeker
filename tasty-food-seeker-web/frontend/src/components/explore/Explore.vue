<template>
  <div id="explore">
    <div class="exploreTextWrapper">
       검색할 장소를 선택해서 음식점을 조회해보세요 !
    </div>
    <div class="selectorWrapper">
      <b-input-group>
        <template #prepend>
          <b-input-group-text class="locationLabel">장소</b-input-group-text>
        </template>
        <b-form-select v-model="selected" :options="locationOptions"></b-form-select>
        <template #append>
          <b-overlay class="exploreOverlay" :show="fetchBtnLoading" opacity="0.6" spinner-small>
            <b-button variant="outline-dark" class="exploreBtn" @click="exploreTastyLocation()">조회</b-button>
          </b-overlay>
        </template>
      </b-input-group>
    </div>
    <div class="tableWrapper">
      <b-overlay :show="fetchTableLoading" spinner-type="grow">
        <b-table fixed
                 id="location-table"
                 primary-key="id"
                 :head-variant="'dark'"
                 :fields="this.tableFields"
                 :items="this.items"
                 :per-page="perPage">
          <template #cell(index)="data">
            {{ (data.index + 1) + (currentPage - 1) * 10 }}
          </template>

          <template #cell(roadAddress)="data">
            <div class="addressDiv">
              {{ data.item.roadAddress }}
            </div>
          </template>

          <template #cell(detail)="data">
            <b-icon icon="arrow-up-right-circle"
                    class="arrow-btn"
                    style="width: 18px; height: 18px; vertical-align:middle; cursor: pointer;"
                    @click="gotoDetailRestaurant(data.item)"/>
          </template>
        </b-table>
      </b-overlay>
      <b-pagination v-model="currentPage"
                    aria-control="location-table"
                    align="center"
                    @change="changeCurrentPage"
                    :total-rows="rows"
                    :per-page="perPage"></b-pagination>
    </div>
  </div>
</template>

<script>

  import {createNamespacedHelpers} from 'vuex'

  const {
    mapActions: exploreMapActions,
    mapGetters: exploreMapGetters,
    mapMutations: exploreMapMutations
  } = createNamespacedHelpers('explore')

  export default {
    name: "Explore",
    data() {
      return {
        items: [],
        currentPage: 1,
        perPage: 10,
        rows: 0,
        fetchBtnLoading: false,
        fetchTableLoading: false,
        selected: 'ALL',
        locationOptions: [
          {text: '전체', value: 'ALL'},
          {text: '종로구', value: 'JONGNO_GU'},
          {text: '용산구', value: 'YONGSAN_GU'},
          {text: '광진구', value: 'GWANGJIN_GU'},
          {text: '중랑구', value: 'JUNGNANG_GU'},
          {text: '강북구', value: 'GANGBUK_GU'},
          {text: '노원구', value: 'NOWON_GU'},
          {text: '서대문구', value: 'SEODAEMUN_GU'},
          {text: '양천구', value: 'YANGCHEON_GU'},
          {text: '구로구', value: 'GURO_GU'},
          {text: '영등포구', value: 'YEONGDEUNGPO_GU'},
          {text: '관악구', value: 'GWANAK_GU'},
          {text: '강남구', value: 'GANGNAM_GU'},
          {text: '강동구', value: 'GANGDONG_GU'},
          {text: '송파구', value: 'SONGPA_GU'},
          {text: '서초구', value: 'SEOCHO_GU'},
          {text: '동작구', value: 'DONGJAK_GU'},
          {text: '금천구', value: 'GEUMCHEON_GU'},
          {text: '강서구', value: 'GANGSEO_GU'},
          {text: '마포구', value: 'MAPO_GU'},
          {text: '은평구', value: 'EUNPYEONG_GU'},
          {text: '도봉구', value: 'DOBONG_GU'},
          {text: '성북구', value: 'SEONGBUK_GU'},
          {text: '동대문구', value: 'DONGDAEMUN_GU'},
          {text: '성동구', value: 'SEONGDONG_GU'},
          {text: '중구', value: 'JUNG_GU'}
        ]
      }
    },
    computed: {
      ...exploreMapGetters(['historyInfos', 'currentLocationItems', 'tableFields'])
    },
    methods: {
      ...exploreMapActions(['fetchLocationByGu']),
      ...exploreMapMutations(['setHistoryInfos', 'clearHistoryInfos']),

      exploreTastyLocation() {
        this.currentPage = 1;

        const params = {}
        params.selected = this.selected;
        params.currentPage = this.currentPage;
        params.size = this.perPage;

        this.fetchBtnLoading = true;
        this.fetchLocationByGu(params).then(() => {
          this.fetchBtnLoading = false;
          this.items = this.currentLocationItems.items;
          this.rows = this.currentLocationItems.totalElements;
        }).catch(() => {
          this.items = [];
          this.fetchBtnLoading = false;
        })
      },

      changeCurrentPage(currentPage) {
        const params = {}

        this.currentPage = currentPage;

        this.fetchTableLoading = true;
        params.selected = this.selected;
        params.currentPage = this.currentPage;
        params.size = this.perPage;
        this.fetchLocationByGu(params).then(() => {
          this.items = this.currentLocationItems.items;
          this.rows = this.currentLocationItems.totalElements;
          this.fetchTableLoading = false;
        }).catch(() => {
          this.fetchTableLoading = false;
        })
      },

      gotoDetailRestaurant(item) {
        this.$router.push({name: 'ExploreDetail', params: {id: item.id}}, () => {
          // selected & currentPage 만 보관
          const params = {};
          params.selected = this.selected;
          params.currentPage = this.currentPage;
          this.setHistoryInfos(params);
        })
      }
    },
    created() {

      // 이전 페이지 정보 여부 확인
      if (Object.keys(this.historyInfos).length !== 0) {
        this.selected = this.historyInfos.selected;
        this.changeCurrentPage(this.historyInfos.currentPage);
        this.clearHistoryInfos();
        return;
      }

      this.exploreTastyLocation();
    }
  }
</script>

<style lang="scss">
  #explore {
    .exploreTextWrapper {
      text-align: left;
      padding: 0 0 10px 0;
    }

    .locationLabel {
      padding: 0 25px;
    }

    .exploreOverlay {
      .exploreBtn {
        padding: 0 25px;
        height: 100%;
      }
    }

    .tableWrapper {
      margin: 5px 0 0 0;
    }

    .locationLabel {
      padding: 0 30px;
    }

    .exploreBtn {
      padding: 0 30px;
      height: 100%;
    }

    th.indexTh {
      width: 50px;
    }

    th.categoryTh {
      width: 200px;
    }

    div.addressDiv {
      width: 260px;
      text-overflow: ellipsis;
      overflow: hidden;
      white-space: nowrap;
    }

    th.moreTh {
      width: 70px;
    }

    .arrow-btn:hover {
      color: mediumblue;
      width: 25px !important;
      height: 25px !important;
    }
  }
</style>