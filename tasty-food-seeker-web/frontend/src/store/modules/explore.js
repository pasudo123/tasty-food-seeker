import request from '../../util/request'

const state = {
    historyInfos: {},
    tableFieldsOnPc: [
        { key: 'index', label: '#', thClass: 'indexTh'},
        { key: 'name', label: '가게명', thClass: 'nameTh'},
        { key: 'category', label: '분류', thClass: 'categoryTh'},
        { key: 'roadAddress', label: '주소'},
        { key: 'detail', label: '상세', thClass: 'moreTh', tdClass: 'moreTd' }
    ],
    tableFieldsOnMobile: [
        { key: 'name', label: '가게명', thClass: 'nameTh'},
        { key: 'category', label: '분류', thClass: 'categoryTh'},
        { key: 'detail', label: '상세', thClass: 'moreTh', tdClass: 'moreTd' }
    ],
    currentLocationItems: {
        items:[],
        totalElements: 0,
        totalPages: 0
    },
    currentLocationItem: {},
    currentBlogItems: [],
    blogTableFields: [
        { key: 'index', label: '#', thClass: 'indexTh'},
        { key: 'title', label: '제목', thClass: 'titleTh', sortable: true},
        { key: 'desc', label: '설명', thClass: 'descTh'},
    ],
    blogTableFieldsOnMobile: [
        { key: 'title', label: '제목', thClass: 'titleTh', sortable: true},
    ]
}

const getters = {
    historyInfos: (state) => state.historyInfos,
    tableFieldsOnPc: (state) => state.tableFieldsOnPc,
    tableFieldsOnMobile: (state) => state.tableFieldsOnMobile,
    currentLocationItems: (state) => state.currentLocationItems,
    currentLocationItem: (state) => state.currentLocationItem,
    currentBlogItems: (state) => state.currentBlogItems,
    blogTableFields: (state) => state.blogTableFields,
    blogTableFieldsOnMobile: (state) => state.blogTableFieldsOnMobile
}

const actions = {

    fetchLocationByGu({commit}, params) {
        const uri = `/api/restaurants`
        const queryParam = {}
        queryParam.gu = params.selected;
        queryParam.page = params.currentPage;
        queryParam.size = params.size;

        return new Promise((resolve, reject) => {
            request.get(uri, {params: queryParam}).then((response) => {
                commit('setCurrentLocationItems', response);
                resolve();
            }).catch(() => reject())
        })
    },

    fetchOneLocationById({dispatch, commit}, params) {
        const uri = `/api/restaurants/${params.id}`;

        return new Promise((resolve, reject) => {
            request.get(uri).then((response) => {
                commit('setCurrentLocationItem', response);
                resolve();
                dispatch('fetchBlogInfosByName');
            }).catch(() => reject())
        })
    },

    fetchBlogInfosByName({state, commit}) {
        let roadAddresses = state.currentLocationItem.roadAddress.split(" ");
        let category = state.currentLocationItem.category;
        let name = state.currentLocationItem.name;
        const uri = `/api/restaurants/blog`;
        const queryParam = {};

        // road 검색은 서울특별시 {자치구} {신주소} + {음식 카테고리} + {상호명} 으로 한다.
        queryParam.name = `${roadAddresses[1]} ${roadAddresses[2]} ${category} ${name}`;

        return new Promise((resolve, reject) => {
           request.get(uri, {params: queryParam}).then((response) => {
               commit('setCurrentBlogItems', response);
               resolve();
           }).catch(() => reject());
        });
    }
}

const mutations = {
    setHistoryInfos(state, params) {
        state.historyInfos.selected = params.selected;
        state.historyInfos.currentPage = params.currentPage;
    },
    clearHistoryInfos(state) {
        state.historyInfos = {};
    },
    setCurrentLocationItems(state, data) {
        state.currentLocationItems.items = [];
        state.currentLocationItems.items = data.restaurantResponseDtos;
        state.currentLocationItems.totalElements = data.totalElements;
        state.currentLocationItems.totalPages = data.totalPages;
    },
    setCurrentLocationItem(state, data) {
        state.currentLocationItem = data;
    },
    clearCurrentLocationItem(state) {
        state.currentLocationItem.name = '';
        state.currentLocationItem.roadAddress = '';
        state.currentLocationItem.category = '';
    },
    setCurrentBlogItems(state, data) {
        state.currentBlogItems = data.restaurantBlogResponseDtos;
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}
