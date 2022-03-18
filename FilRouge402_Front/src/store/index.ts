import { createStore } from "vuex";
import axios from "axios";

export interface State {
  isModalVisible: boolean;
  forms3d: any;
  forms3dbyid: any;
  updateComponent: number;
}


const store = createStore<State>({
  state: {
    isModalVisible: false,
    forms3d: [],
    forms3dbyid: [],
    updateComponent: 0,
  },

  mutations: {
    SET_FORMS3D(state, forms3d) {
      state.forms3d = forms3d
    },

    SET_FORMS3DBYID(state, forms3dbyId) {
      state.forms3dbyid = forms3dbyId
    }
  },

  actions: {
    getForms3d({ commit }) {
      axios
        .get("http://localhost:9090/Forms3D")
        .then(forms3ddata=> {
          commit('SET_FORMS3D', forms3ddata.data)
      })
    },

    getForms3dById({ commit }, id) {
      axios
        .get("http://localhost:9090/Forms3D/" + id)
        .then(forms3ddata => {
          commit('SET_FORMS3DBYID', forms3ddata.data)
      })
    },
  },
});


export default store;
