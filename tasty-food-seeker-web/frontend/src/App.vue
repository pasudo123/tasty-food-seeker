<template>
  <div id="app">
    <b-navbar toggleable="lg" type="dark" variant="dark" fixed="top" :sticky="true">
      <b-navbar-brand class="brandTitle" @click="goto('Home')">맛있는 음식</b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item :active=navExplore @click="goto('Explore')" >살펴보기</b-nav-item>
          <b-nav-item :active=navMap @click="goto('Map')">지도</b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>

    <div class="activeContainer">
      <router-view />
    </div>
  </div>
</template>

<script>
  export default {
    name: 'App',
    data() {
      return {
        naverMapScriptId: 'naver-map-script',
        navExplore: false,
        navMap: false
      }
    },
    methods: {
      initNav() {
        this.navExplore = false;
        this.navMap = false;
      },

      goto(name) {
        this.initNav();

        if(name === 'Explore') {
          this.navExplore = true;
        }

        if(name === 'Map') {
          this.navMap = true;
        }

        this.$router.push({name:name}).catch(() => {});
      },
    },
  }
</script>

<style>
  #app {
    margin: 0 auto;
    width: 880px;
    text-align: center;
  }

  .brandTitle {
    cursor: pointer;
  }
</style>

<style scoped>
  .activeContainer {
    margin-top: 5px;
  }
</style>
