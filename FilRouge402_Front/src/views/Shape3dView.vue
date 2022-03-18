<template>
  <section v-if="loading" class="shape3d">
    <div class="shape3d__name">
      <h1>{{ forms3dbyid.forms2D.name }}</h1>
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
            v-if="forms3dbyid.forms2D.type === 'Triangle'"
            ref="mesh"
            :size="1"
            :rotation="{ y: Math.PI / 4, z: Math.PI / 4 }"
            :radius="forms3dbyid.depths"
            :height="
              Math.sqrt(
                forms3dbyid.forms2D.longueur * forms3dbyid.forms2D.longueur -
                  (forms3dbyid.forms2D.base * forms3dbyid.forms2D.base) / 4
              )
            "
            :radialSegments="100"
            :heightSegments="100"
          >
            <MatcapMaterial name="9F886D_362F25_E8C9A4_5C4C3C" />
          </Cone>

          <Cylinder
            v-if="forms3dbyid.forms2D.type === 'Circle'"
            ref="mesh"
            :size="1"
            :rotation="{ y: Math.PI / 4, z: Math.PI / 4 }"
            :height="forms3dbyid.depths"
            :radiusTop="forms3dbyid.forms2D.rayon"
            :radiusBottom="forms3dbyid.forms2D.rayon"
            :radialSegments="100"
            :heightSegments="100"
          >
            <MatcapMaterial name="9F886D_362F25_E8C9A4_5C4C3C" />
          </Cylinder>

          <Box
            v-if="forms3dbyid.forms2D.type === 'Rectangle'"
            ref="mesh"
            :size="1"
            :rotation="{ y: Math.PI / 4, z: Math.PI / 4 }"
            :width="forms3dbyid.forms2D.largeur"
            :height="forms3dbyid.forms2D.longueur"
            :depth="forms3dbyid.depths"
            :radialSegments="100"
            :heightSegments="100"
          >
            <MatcapMaterial name="9F886D_362F25_E8C9A4_5C4C3C" />
          </Box>
        </Scene>
      </Renderer>
    </div>
    <div class="shape3d__info">
      <p v-if="forms3dbyid.forms2D.longueur">
        Longueur : {{ forms3dbyid.forms2D.longueur }} cm
      </p>
      <p v-if="forms3dbyid.forms2D.largeur">
        Largeur : {{ forms3dbyid.forms2D.largeur }} cm
      </p>
      <p v-if="forms3dbyid.forms2D.base">
        Base : {{ forms3dbyid.forms2D.base }} cm
      </p>
      <p v-if="forms3dbyid.forms2D.rayon">
        Rayon : {{ forms3dbyid.forms2D.rayon }} cm
      </p>
      <p v-if="forms3dbyid.depths">
        Profondeur : {{ forms3dbyid.depths }} cm
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
      loading: false
    };
  },
  computed: {
    forms3dbyid() {
      return this.$store.state.forms3dbyid;
    }
  },
  async mounted() {
    await this.$store.dispatch("getForms3dById", this.shapeId)
      .then(() => (this.loading = true));
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
    font-size: 1.5rem;
    font-weight: bold;
    color: white;
  }
  &__scene {
    height: 690px;
  }
  &__info {
    font-size: 1.2rem;
    display: flex;
    justify-content: center;
    p {
      padding: 0 25px 0 25px;
    }
  }
}
</style>
