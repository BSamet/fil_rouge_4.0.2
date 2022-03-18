import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import { TroisJSVuePlugin } from "troisjs";
import axios from "axios";
import store from "./store";

const app = createApp(App);

app.config.globalProperties.axios=axios
app.use(router);
app.use(TroisJSVuePlugin);
app.use(store);
app.mount("#app");
