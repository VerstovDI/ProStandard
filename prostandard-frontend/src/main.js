import { createApp } from 'vue'
import App from './App.vue'

import router from "./router/router";
//import VueAxios from "vue-axios";
//import axios from "axios";

createApp(App).use(router).mount('#app')
    //.use(VueAxios, axios)