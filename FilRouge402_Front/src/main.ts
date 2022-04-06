import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import { TroisJSVuePlugin } from "troisjs";
import axios from "axios";
import store from "./store";

const app = createApp(App);

app.config.globalProperties.axios = axios;
app.use(router);
app.use(TroisJSVuePlugin);
app.use(store);
app.mount(await store.dispatch("getForms3d"))
app.mount(await store.dispatch("getScene3d"))
app.mount("#app");

// Fetch data every 10 mins
setInterval(async function(){
    app.mount(await store.dispatch("getForms3d"))
    app.mount(await store.dispatch("getScene3d"))
}, 600000)
