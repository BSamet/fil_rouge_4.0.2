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
    // 3D Shape
    getForms3dById: (state) => (id) => {
      return state.forms3d.filter((forms3d) => forms3d.id === id);
    },

    getForms3dBySceneId: (state) => (sceneId) => {
      return state.forms3d.filter((forms3d) => forms3d.sceneId === sceneId);
    },

    // 3D Scene
    getScene3dById: (state) => (id) => {
      return state.scene3d.filter((scene3d) => scene3d.id === id);
    },
  },

  mutations: {
    // Get 3d Shape
    SET_FORMS3D(state, forms3d) {
      state.forms3d = forms3d;
    },

    // Get 3d Scene
    SET_SCENE3D(state, scene3d) {
      state.scene3d = scene3d;
    },

    // Update 3d Shape
    SET_UPDATE_FORMS3D(state, fullData) {
      if (
        fullData.data.sceneId != fullData.sceneIdBeforeChange &&
        fullData.sceneIdBeforeChange != 0
      ) {
        const sceneToUpdate =
          state.scene3d[
            state.scene3d.findIndex(
              (scene3d) => scene3d.id == fullData.sceneIdBeforeChange
            )
          ];
        sceneToUpdate.myAll3dForms.splice(
          sceneToUpdate.myAll3dForms.findIndex(
            (forms3d) => forms3d.id === fullData.data.id
          ),
          1
        );
      }
      if (
        fullData.data.sceneId != fullData.sceneIdBeforeChange &&
        fullData.data.sceneId != 0
      ) {
        state.scene3d[
          state.scene3d.findIndex((scene) => scene.id == fullData.data.sceneId)
        ].myAll3dForms.push(fullData.data);
      }
      state.forms3d[
        state.forms3d.findIndex((shape) => shape.id == fullData.data.id)
      ] = JSON.parse(JSON.stringify(fullData.data));
      state.isModalUpdateVisible = false;
    },

    // Update 3d Scene
    SET_UPDATE_SCENE3D(state, updatedScene3d) {
      state.scene3d[
        state.scene3d.findIndex((scene) => scene.id == updatedScene3d.id)
      ].name = updatedScene3d.name;
      state.isModalUpdateVisible = false;
    },

    // Post 3d Shape
    SET_POST_FORMS3D(state, postForms3d) {
      state.forms3d.push(postForms3d);
      if (postForms3d.sceneId != 0) {
        state.scene3d[
          state.scene3d.findIndex((scene) => scene.id == postForms3d.sceneId)
        ].myAll3dForms.push(postForms3d);
      }
      state.isModalVisible = false;
    },

    // Post 3d Scene
    SET_POST_SCENE3D(state, postScene3d) {
      state.scene3d.push(postScene3d);
      state.isModalVisible = false;
      postScene3d.myAll3dForms.forEach((value) => {
        state.forms3d[
          state.forms3d.findIndex((forms3d) => forms3d.id === value.id)
        ].sceneId = postScene3d.id;
      });
    },

    // Post 3d Shape In Scene
    SET_POST_SHAPE3D_IN_SCENE(state, postShape3dInScene) {
      postShape3dInScene.shapeId.forEach((value) => {
        state.forms3d[
          state.forms3d.findIndex((forms3d) => forms3d.id === value)
        ].sceneId = postShape3dInScene.data.id;
      });
      state.scene3d[
        state.scene3d.findIndex(
          (scene) => scene.id == postShape3dInScene.data.id
        )
      ] = JSON.parse(JSON.stringify(postShape3dInScene.data));
      state.isModalVisible = false;
    },

    // Delete 3d Shape
    SET_DELETE_FORMS3D(state, shapeId) {
      const deletedShape =
        state.forms3d[
          state.forms3d.findIndex((forms3d) => forms3d.id == shapeId)
        ];
      const sceneId = deletedShape.sceneId;

      if (sceneId != 0) {
        const sceneToUpdate =
          state.scene3d[
            state.scene3d.findIndex((scene3d) => scene3d.id == sceneId)
          ];
        sceneToUpdate.myAll3dForms.splice(
          sceneToUpdate.myAll3dForms.findIndex(
            (forms3d) => forms3d.id === shapeId
          ),
          1
        );
      }

      state.forms3d.splice(
        state.forms3d.findIndex((forms3d) => forms3d.id === shapeId),
        1
      );
    },

    // Delete 3d Shape
    SET_DELETE_SCENE3D(state, sceneToDelete) {
      sceneToDelete.myAll3dForms.forEach((value) => {
        state.forms3d[
          state.forms3d.findIndex((forms3d) => forms3d.id === value.id)
        ].sceneId = 0;
      });
      state.scene3d.splice(
        state.scene3d.findIndex((scene) => scene.id === sceneToDelete.id),
        1
      );
    },

    // Delete 3d Shape in Scene
    SET_DELETE_FORMS3D_IN_SCENE(state, sceneRes) {
      sceneRes.forms3dId.forEach((value) => {
        state.forms3d[
          state.forms3d.findIndex((forms3d) => forms3d.id === value)
        ].sceneId = 0;
      });
      state.scene3d[
        state.scene3d.findIndex((scene) => scene.id == sceneRes.data.id)
      ] = JSON.parse(JSON.stringify(sceneRes.data));
    },
  },

  actions: {
    // Get 3d Shape
    getForms3d({ commit }) {
      axios.get("http://localhost:9090/Forms3D").then((forms3dData) => {
        commit("SET_FORMS3D", forms3dData.data);
      });
    },

    // Get 3d Scene
    getScene3d({ commit }) {
      axios
        .get("http://localhost:9090/Forms3DComposite")
        .then((scene3dData) => {
          commit("SET_SCENE3D", scene3dData.data);
        });
    },

    // Update 3d Shape
    setUpdateForms3d({ commit }, update3dShape) {
      axios
        .put("http://localhost:9090/Forms3D", update3dShape.data)
        .then((res) => {
          const fullData = {
            data: res.data,
            sceneIdBeforeChange: update3dShape.sceneIdBeforeChange,
          };
          commit("SET_UPDATE_FORMS3D", fullData);
        });
    },

    // Update 3d Scene
    setUpdateScene3d({ commit }, update3dScene) {
      const update3dSceneToSent = {
        name: update3dScene.name,
      };
      axios
        .put(
          "http://localhost:9090/Forms3DComposite/" + update3dScene.id,
          update3dSceneToSent
        )
        .then((res) => {
          commit("SET_UPDATE_SCENE3D", res.data);
        });
    },

    // Post 3d Shape
    setPostForms3d({ commit }, post3dShape) {
      axios.post("http://localhost:9090/Forms3D", post3dShape).then((res) => {
        commit("SET_POST_FORMS3D", res.data);
      });
    },

    // Post 3d Scene
    setPostScene3d({ commit }, post3dScene) {
      axios
        .post("http://localhost:9090/Forms3DComposite/0", post3dScene)
        .then((res) => {
          commit("SET_POST_SCENE3D", res.data);
        });
    },

    // Post 3d Shape in Scene
    setPostShape3dInScene({ commit }, post3dShapeInScene) {
      const finalPost = {
        forms3DId: post3dShapeInScene.forms3DId,
      };
      axios
        .post(
          "http://localhost:9090/Forms3DComposite/" +
            post3dShapeInScene.sceneId,
          finalPost
        )
        .then((res) => {
          const toUpdateInScene = {
            shapeId: post3dShapeInScene.forms3DId,
            data: res.data,
          };
          commit("SET_POST_SHAPE3D_IN_SCENE", toUpdateInScene);
        });
    },

    // Delete 3d Shape
    setDeleteForms3d({ commit }, shapeId) {
      axios.delete("http://localhost:9090/Forms3D/" + shapeId).then(() => {
        commit("SET_DELETE_FORMS3D", shapeId);
      });
    },

    // Delete 3d Scene
    setDeleteScene3d({ commit }, scene) {
      axios
        .delete("http://localhost:9090/Forms3DComposite/" + scene.id)
        .then(() => {
          commit("SET_DELETE_SCENE3D", scene);
        });
    },

    // Delete 3d Shape in Scene
    setDeleteForms3dInScene({ commit }, toDelete) {
      axios
        .delete(
          "http://localhost:9090/Forms3DComposite/" +
            toDelete.sceneId +
            "/delete",
          { data: toDelete.forms3DId }
        )
        .then((res) => {
          const toDeleteFinal = {
            forms3dId: toDelete.forms3DId,
            data: res.data,
          };
          commit("SET_DELETE_FORMS3D_IN_SCENE", toDeleteFinal);
        });
    },
  },
});

export default store;
