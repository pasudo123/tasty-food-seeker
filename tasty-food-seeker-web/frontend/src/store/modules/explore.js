import request from '../../util/request'

const state = {}

const getters = {}

const actions = {

    fetchLocationByGu({commit}, params) {
        const uri = `/api/restaurants`
        const queryParam = {}
        queryParam.gu = params.selected;

        return new Promise((resolve, reject) => {
            request.get(uri, {params: queryParam}).then((response) => {
                console.debug(response);
                resolve();
            })
        })
    }
}

const mutations = {}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}
