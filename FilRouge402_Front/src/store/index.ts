import { createStore } from "vuex";
import axios from "axios";

export interface State {
  loadingHome: boolean;
  isModalVisible: boolean;
  isModalUpdateVisible: boolean;
  updateComponent: boolean;
  forms3d: any;
  forms3dbyid: any;
  forms3dbyscene: any;
  scene3d: any;
  scene3dbyid: any;
}

const store = createStore<State>({
  state: {
    loadingHome: false,
    isModalVisible: false,
    isModalUpdateVisible: false,
    updateComponent: false,
    forms3d: [],
    forms3dbyid: [],
    forms3dbyscene: [],
    scene3d: [],
    scene3dbyid: [],
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
    SET_FORMS3D(state, forms3d) {
      state.forms3d = forms3d;
    },

    SET_FORMS3DBYID(state, forms3dbyId) {
      state.forms3dbyid = forms3dbyId;
    },

    SET_FORMS3DBYSCENE(state, forms3dbyscene) {
      state.forms3dbyscene = forms3dbyscene;
    },

    SET_SCENE3D(state, scene3d) {
      state.scene3d = scene3d;
    },

    SET_SCENE3DBYID(state, scene3dbyid) {
      state.scene3dbyid = scene3dbyid;
    },
  },

  actions: {
    getForms3d({ commit }) {
      axios.get("http://localhost:9090/Forms3D").then((forms3ddata) => {
        commit("SET_FORMS3D", forms3ddata.data);
      });
    },

    getForms3dById({ commit }, id) {
      axios.get("http://localhost:9090/Forms3D/" + id).then((forms3ddata) => {
        commit("SET_FORMS3DBYID", forms3ddata.data);
      });
    },

    getForms3dByScene({ commit }, sceneId) {
      axios
        .get("http://localhost:9090/Forms3D/scene/" + sceneId)
        .then((forms3dScenedata) => {
          commit("SET_FORMS3DBYSCENE", forms3dScenedata.data);
        });
    },

    getScene3d({ commit }) {
      axios
        .get("http://localhost:9090/Forms3DComposite")
        .then((scene3ddata) => {
          commit("SET_SCENE3D", scene3ddata.data);
        });
    },

    async getScene3dById({ commit }, id) {
      await axios
        .get("http://localhost:9090/Forms3DComposite/" + id)
        .then((scene3ddbyiddata) => {
          commit("SET_SCENE3DBYID", scene3ddbyiddata.data);
        });
    },
  },
});

export default store;
