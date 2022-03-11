import axios from "axios";
import Vuex from "vuex";
import Vue from "vue";
import { createStore, Store } from 'vuex'

export interface State {
    Forms3d: []
}

export const store = createStore<State>({
    state: {
        Forms3d: [],
    }
})

const getters = {};

const actions = {
  getForms3d({ commit }: { commit: any }) {
    axios.get("http://localhost:9090/Forms3D").then((response) => {
      commit("SET_FORMS3D", response.data);
    });
  },
};

const mutations = {
    SET_FORMS3D(state: any, forms3d: any) {
        state.forms3d = forms3d
    }
};

export default {
  state,
  getters,
  actions,
  mutations,
};
