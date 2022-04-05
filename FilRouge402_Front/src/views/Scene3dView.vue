<template>
  <transition name="fade" mode="out-in">
    <section v-if="loading">
      <div v-for="scene3d in getScene3dById" :key="scene3d.id" class="scene3d">
        <div class="scene3d__name">
          <h1>{{ scene3d.name }}</h1>
        </div>
        <div class="scene3d__scene">
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
            <Camera :position="{ x: 0, y: 0, z: 100 }" />
            <Scene>
              <div
                v-for="(shape3d, index) in scene3d.myAll3dForms"
                :key="shape3d.id"
              >
                <Cone
                  v-if="shape3d.forms2D.type === 'Triangle'"
                  ref="mesh"
                  :size="1"
                  :rotation="{ y: Math.PI / 4, z: Math.PI / 4 }"
                  :radius="shape3d.depths"
                  :height="shape3d.forms2D.hauteur"
                  :radialSegments="100"
                  :heightSegments="100"
                  :position="{ y: index * 50, x: index * 10 }"
                >
                  <MatcapMaterial name="9F886D_362F25_E8C9A4_5C4C3C" />
                </Cone>

                <Cylinder
                  v-if="shape3d.forms2D.type === 'Circle'"
                  ref="mesh"
                  :size="1"
                  :rotation="{ y: Math.PI / 4, z: Math.PI / 4 }"
                  :height="shape3d.depths"
                  :radiusTop="shape3d.forms2D.rayon"
                  :radiusBottom="shape3d.forms2D.rayon"
                  :radialSegments="100"
                  :heightSegments="100"
                  :position="{ y: index * 50, x: index * 10 }"
                >
                  <MatcapMaterial name="9F886D_362F25_E8C9A4_5C4C3C" />
                </Cylinder>

                <Box
                  v-if="shape3d.forms2D.type === 'Rectangle'"
                  ref="mesh"
                  :size="1"
                  :rotation="{ y: Math.PI / 4, z: Math.PI / 4 }"
                  :width="shape3d.forms2D.largeur"
                  :height="shape3d.forms2D.longueur"
                  :depth="shape3d.depths"
                  :radialSegments="100"
                  :heightSegments="100"
                  :position="{ y: index * 50, x: index * 10 }"
                >
                  <MatcapMaterial name="9F886D_362F25_E8C9A4_5C4C3C" />
                </Box>
              </div>
            </Scene>
          </Renderer>
        </div>
      </div>
      <div>
        <card3d-by-scene-id></card3d-by-scene-id>
      </div>
    </section>
    <div v-else class="scene3d--loading">
      <img src="../assets/loader.svg" />
    </div>
  </transition>
</template>

<script>
import { Box, Camera, MatcapMaterial, Renderer, Scene } from "troisjs";
import Card3dBySceneId from "@/components/scene/Card3dBySceneId.vue";

export default {
  name: "Scene3dView",
  components: {Card3dBySceneId, Box, Camera, MatcapMaterial, Renderer, Scene },
  data() {
    return {
      sceneId: Number(this.$route.params.id),
      loading: false,
    };
  },
  computed: {
    getScene3dById() {
      return this.$store.getters.getScene3dById(this.sceneId);
    },
  },
  mounted() {
    setTimeout(() => {
      this.loading = true;
    }, 250);
  },
};
</script>

<style lang="scss" scoped>
section {
  margin-bottom: 50px;
}
.scene3d {
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
  &--loading {
    width: 100%;
    text-align: center;
  }
}

//Forms transition
.slide-fade-enter-active,
.slide-fade-leave-active {
  transition: all 0.3s ease-out;
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  transform: translateX(20px);
  opacity: 0;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
