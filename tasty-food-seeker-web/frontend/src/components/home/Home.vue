<template>
  <div id="home">
    <div class="avatarWrapper">
      <b-avatar
          class="myAvatar"
          size="100px">
      </b-avatar>
    </div>
    <div class="introWrapper">
      <ul>
        <li>서울시에서 업무추진비로 이용한 음식점들을 조회합니다.</li>
        <li>모든 데이터는 <a href="http://opengov.seoul.go.kr/" target="_blank">http://opengov.seoul.go.kr/</a> 사이트를 기반으로 작성되었습니다.</li>
      </ul>
    </div>
    <div class="footerWrapper">
      <b-navbar fixed="bottom" variant="faded" type="light">
        <b-nav-text class="footerText">pasudo123@naver.com</b-nav-text>
      </b-navbar>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Home",
    data() {
      return {
      }
    }
  }
</script>

<style lang="scss">
  #home {
    .avatarWrapper {
      margin: 40px 0 0 0;
      .myAvatar {
        border: 2px solid #2a2a2a;
      }
    }

    .introWrapper {
      margin: 30px 0 0 0;
      text-align: left;

      li {
        padding: 10px 0 10px 0;
      }
    }

    .footerWrapper {
      .navbar-expand {
        justify-content: center;
      }
    }
  }
</style>