import { createWebHistory, createRouter } from 'vue-router';


const routes = [
    // На главный компонент приложения
    {
        path: "",
        name: "Main",
        //component: Main
        component: () => import("@/components/Main")
    },

    // На компонент с выдачей подобранных профстандартов
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
