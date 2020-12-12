import request from '../../util/request'

const state = {
    tableFields: [
        { key: 'index', label: '#', thClass: 'indexTh'},
        { key: 'name', label: '가게명', thClass: 'nameTh'},
        { key: 'category', label: '분류', thClass: 'categoryTh'},
        { key: 'roadAddress', label: '주소'},
        { key: 'detail', label: '상세', thClass: 'moreTh', tdClass: 'moreTd' }
    ],
    currentLocationItems: {
        items:[],
        totalElements: 0,
        totalPages: 0
    },
    currentLocationItem: {}
}

const getters = {
    tableFields: (state) => state.tableFields,
    currentLocationItems: (state) => state.currentLocationItems,
    currentLocationItem: (state) => state.currentLocationItem
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

    fetchOneLocationById({commit}, params) {
        const uri = `/api/restaurants/${params.id}`;

        return new Promise((resolve, reject) => {
            request.get(uri).then((response) => {
                commit('setCurrentLocationItem', response);
                resolve();
            }).catch(() => reject())
        })
    }
}

const mutations = {
    setCurrentLocationItems(state, data) {
        state.currentLocationItems.items = [];
        state.currentLocationItems.items = data.restaurantResponseDtos;
        state.currentLocationItems.totalElements = data.totalElements;
        state.currentLocationItems.totalPages = data.totalPages;
    },
    setCurrentLocationItem(state, data) {
        state.currentLocationItem = data;
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}
