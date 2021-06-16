import { createWebHistory, createRouter } from 'vue-router';

//import Standards from "@/components/Standards";
//import Main from "@/components/Main";

const routes = [
    {
        path: "",
        name: "Main",
        //component: Main
        component: () => import("@/components/Main")
    },
    {
        path: '/standards',
        name: 'Standards',
        //component: Standards
        component: () => import("@/components/Standards")
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router;
