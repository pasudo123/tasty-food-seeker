<template>
  <div id="root">
    <b-navbar toggleable="lg" type="dark" variant="dark" fixed="top" :sticky="true">
      <b-navbar-brand href="#">맛있는 음식</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">기본</b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>

    <div class="mapContainer">
      <h2>네이버 지도</h2>
      <naver-map />
    </div>
  </div>
</template>

<script>

  import NaverMap from './naver/NaverMap'

  export default {
    name: "Root",
    components: { NaverMap },
    data() {
      return {
        map: {
          width: 1000,
          height: 500
        },
        mapOptions: {
          zoom: 15,
          lat: 37.3595704,
          lng: 127.105399
        }
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
  }

  .mapContainer {
    margin-top: 24px;
  }
</style>