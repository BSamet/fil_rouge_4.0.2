import { createStore } from "vuex";
import axios from "axios";

export interface State {
  isModalVisible: boolean;
  isModalUpdateVisible: boolean
  forms3d: any;
  forms3dbyid: any;
  scene3d: any;
  updateComponent: number;
}


const store = createStore<State>({
  state: {
    isModalVisible: false,
    isModalUpdateVisible: false,
    forms3d: [],
    forms3dbyid: [],
    scene3d: [],
    updateComponent: 0,
  },

  mutations: {
    SET_FORMS3D(state, forms3d) {
      state.forms3d = forms3d
    },

    SET_FORMS3DBYID(state, forms3dbyId) {
      state.forms3dbyid = forms3dbyId
    },

    SET_SCENE3D(state, scene3d) {
      state.scene3d = scene3d
    },
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

    getScene3d({ commit }) {
      axios
          .get("http://localhost:9090/Forms3DComposite")
          .then(scene3ddata=> {
            commit('SET_SCENE3D', scene3ddata.data)
          })
    },
  },
});


export default store;
