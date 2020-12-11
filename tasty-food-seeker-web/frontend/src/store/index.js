import Vue from 'vue'
import Vuex from 'vuex'
import explore from './modules/explore'

Vue.use(Vuex)

const store = new Vuex.Store({
    modules: {
        explore
    }
});

export default store;