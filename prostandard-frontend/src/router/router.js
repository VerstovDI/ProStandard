import { createWebHistory, createRouter } from 'vue-router';

import Standards from "@/components/Standards";
import Main from "@/components/Main";

const routes = [
    {
        path: "/main",
        name: "MainPage",
        component: Main
    },
    {
        path: '/main/standards',
        name: 'Standards',
        component: Standards
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router;


/*const apiClient = {
    async sendRequest() {
        const response = await axios.post("/main/send-request");
        return response.data;
    }
};

export default apiClient;*/
