import { createApp } from 'vue'
import App from './App.vue'

import axios from "axios";
import VueAxios from "vue-axios";
import router from "./router/router";

axios.defaults.baseURL = "http://localhost:8080/main"

//createApp(App).use(VueAxios, axios, router);

createApp(App).use(VueAxios, axios, router).mount('#app')
