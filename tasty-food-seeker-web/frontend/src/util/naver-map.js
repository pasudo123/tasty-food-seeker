

const NAVER_MAP = {

    naverMapScriptId: 'naver-map-script',
    map: null,
    marker: null,

    /**
     * document.head 에 naver script 삽입
     * @param clientId  클라이언트 아이디
     */
    createNaverMapScript(clientId) {
        if(this.isExistNaverMapScript()) {
            return;
        }

        let script = document.createElement('script');
        script.setAttribute('id', this.naverMapScriptId);
        script.setAttribute('type', 'text/javascript');
        script.setAttribute('src', `https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=${clientId}`);
        document.head.appendChild(script);
    },

    isExistNaverMapScript() {
        return document.getElementById(this.naverMapScriptId);
    },

    /**
     * 특정 태그 내에, 네이버 맵 그리기
     */
    drawNaverMap(tag, mapOptions) {
        if(window.naver) {
            this.loadNaverMapInfos(tag, mapOptions);
            this.loadNaverMakerInfors(mapOptions);
            return;
        }

        document.getElementById(this.naverMapScriptId).onload = () => {
            this.loadNaverMapInfos(tag, mapOptions);
            this.loadNaverMakerInfors(mapOptions);
        }
    },

    /**
     * 네이버 맵에 대한 정보 그리기
     */
    loadNaverMapInfos(tag, {zoom, lat, lng}) {
        let mapOptions = {
            center: new window.naver.maps.LatLng(lat, lng),
            zoom: zoom
        };

        this.map = new window.naver.maps.Map(tag, mapOptions);
    },

    /**
     * 네이버 맵에 마커 표시
     */
    loadNaverMakerInfors({lat, lng}) {
        this.marker = new window.naver.maps.Marker({
            position: new window.naver.maps.LatLng(lat, lng),
            map: this.map,
        });
    }
}

export default NAVER_MAP;