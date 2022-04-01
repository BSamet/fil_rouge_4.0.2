import { createStore } from "vuex";
import axios from "axios";

export interface State {
  loadingHome: boolean;
  isModalVisible: boolean;
  isModalUpdateVisible: boolean;
  forms3d: any;
  scene3d: any;
}

const store = createStore<State>({
  state: {
    loadingHome: false,
    isModalVisible: false,
    isModalUpdateVisible: false,
    forms3d: [],
    scene3d: [],
  },

  getters: {
    getForms3dById: (state) => (id) => {
      return state.forms3d.filter((forms3d) => forms3d.id === id);
    },

    getForms3dBySceneId: (state) => (sceneId) => {
      return state.forms3d.filter((forms3d) => forms3d.sceneId === sceneId);
    },
  },

  mutations: {
    // Update 3d Shape
    SET_UPDATE_FORMS3D(state, updatedForms3d) {
      state.forms3d[
        state.forms3d.findIndex((shape) => shape.id == updatedForms3d.id)
      ] = JSON.parse(JSON.stringify(updatedForms3d));
      // state.forms3d[(updatedForms3d.id -1)] = updatedForms3d;
    },

    // Get 3d Shape
    SET_FORMS3D(state, forms3d) {
      state.forms3d = forms3d;
    },

    // Get 3d Scene
    SET_SCENE3D(state, scene3d) {
      state.scene3d = scene3d;
    },
  },

  actions: {
    // Update 3d Shape
    setUpdateForms3d({ commit }, update3dShape) {
      axios.put("http://localhost:9090/Forms3D", update3dShape).then((res) => {
        commit("SET_UPDATE_FORMS3D", res.data);
      });
    },

    // Get 3d Shape
    getForms3d({ commit }) {
      axios.get("http://localhost:9090/Forms3D").then((forms3ddata) => {
        commit("SET_FORMS3D", forms3ddata.data);
      });
    },

    // Get 3d Scene
    getScene3d({ commit }) {
      axios
        .get("http://localhost:9090/Forms3DComposite")
        .then((scene3ddata) => {
          commit("SET_SCENE3D", scene3ddata.data);
        });
    },
  },
});

export default store;
