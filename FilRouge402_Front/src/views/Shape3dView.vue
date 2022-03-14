<template>
  <section v-if="loading" class="shape3d">
    <div class="shape3d__name">
      <h1>{{ myFormsById.forms2D.name }}</h1>
    </div>
    <div class="shape3d__scene">
      <Renderer
        ref="renderer"
        alpha="0"
        resize="true"
        antialias
        :orbit-ctrl="{
          autoRotate: true,
          enableDamping: true,
          dampingFactor: 0.05,
        }"
      >
        <Camera :position="{ x: 0, y: 0, z: 50 }" />
        <Scene>
          <Cone
            v-if="myFormsById.forms2D.type === 'Triangle'"
            ref="mesh"
            :size="1"
            :rotation="{ y: Math.PI / 4, z: Math.PI / 4 }"
            :radius="myFormsById.depths"
            :height="
              Math.sqrt(
                myFormsById.forms2D.longueur * myFormsById.forms2D.longueur -
                  (myFormsById.forms2D.base * myFormsById.forms2D.base) / 4
              )
            "
            :radialSegments="100"
            :heightSegments="100"
          >
            <MatcapMaterial name="9F886D_362F25_E8C9A4_5C4C3C" />
          </Cone>

          <Cylinder
            v-if="myFormsById.forms2D.type === 'Circle'"
            ref="mesh"
            :size="1"
            :rotation="{ y: Math.PI / 4, z: Math.PI / 4 }"
            :height="myFormsById.depths"
            :radiusTop="myFormsById.forms2D.rayon"
            :radiusBottom="myFormsById.forms2D.rayon"
            :radialSegments="100"
            :heightSegments="100"
          >
            <MatcapMaterial name="9F886D_362F25_E8C9A4_5C4C3C" />
          </Cylinder>

          <Box
            v-if="myFormsById.forms2D.type === 'Rectangle'"
            ref="mesh"
            :size="1"
            :rotation="{ y: Math.PI / 4, z: Math.PI / 4 }"
            :width="myFormsById.forms2D.largeur"
            :height="myFormsById.forms2D.longueur"
            :depth="myFormsById.depths"
            :radialSegments="100"
            :heightSegments="100"
          >
            <MatcapMaterial name="9F886D_362F25_E8C9A4_5C4C3C" />
          </Box>
        </Scene>
      </Renderer>
    </div>
    <div class="shape3d__info">
      <p v-if="myFormsById.forms2D.longueur">
        Longueur : {{ myFormsById.forms2D.longueur }} cm
      </p>
      <p v-if="myFormsById.forms2D.largeur">
        Largeur : {{ myFormsById.forms2D.largeur }} cm
      </p>
      <p v-if="myFormsById.forms2D.base">
        Base : {{ myFormsById.forms2D.base }} cm
      </p>
      <p v-if="myFormsById.forms2D.rayon">
        Rayon : {{ myFormsById.forms2D.rayon }} cm
      </p>
      <p v-if="myFormsById.forms2D.depths">
        Profondeur : {{ myFormsById.depths }} cm
      </p>
    </div>
  </section>
</template>

<script>
import { Box, Camera, MatcapMaterial, Renderer, Scene } from "troisjs";
import axios from "axios";
export default {
  name: "Shape3dView",
  components: { Box, Camera, MatcapMaterial, Renderer, Scene },
  data() {
    return {
      shapeId: this.$route.params.id,
      myFormsById: "",
      loading: false,
    };
  },
  methods: {
    getForms: function () {
      axios
        .get("http://localhost:9090/Forms3D/" + this.shapeId)
        .then((forms3d) => {
          this.myFormsById = forms3d.data;
          this.loading = true;
        });
    },
  },
  mounted() {
    this.getForms();
  },
};
</script>

<style lang="scss" scoped>
.shape3d {
  background-image: url("../assets/backgroundshape.jpg");
  background-size: cover;
  background-position: center center;
  backdrop-filter: blur(15px);
  height: 750px;
  &__name {
    text-align: center;
    font-size: 2rem;
    font-weight: bold;
    color: white;
  }
  &__scene {
    height: 675px;
  }
  &__info {
    font-size: 1.5rem;
    display: flex;
    justify-content: center;
    p {
      padding: 0 25px 0 25px;
    }
  }
}
</style>
