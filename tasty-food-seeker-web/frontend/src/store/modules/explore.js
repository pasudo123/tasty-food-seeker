import request from '../../util/request'

const state = {
    tableFields: [
        { key: 'index', label: '#' },
        { key: 'name', label: '가게명' },
        { key: 'category', label: '분류'},
        { key: 'roadAddress', label: '주소' }
    ],
    currentLocationItems:{
        items:[],
        totalElements: 0,
        totalPages: 0
    }
}

const getters = {
    tableFields: (state) => state.tableFields,
    currentLocationItems: (state) => state.currentLocationItems
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
    }
}

const mutations = {
    setCurrentLocationItems(state, data) {
        state.currentLocationItems.items = [];
        state.currentLocationItems.items = data.restaurantResponseDtos;
        state.currentLocationItems.totalElements = data.totalElements;
        state.currentLocationItems.totalPages = data.totalPages;
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}
