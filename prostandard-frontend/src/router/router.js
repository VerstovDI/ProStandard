import { createWebHistory, createRouter } from 'vue-router';


const routes = [
    // На главный компонент приложения
    {
        path: "",
        name: "Main",
        //component: Main
        component: () => import("@/components/Main/Main")
    },

    // На компонент с выдачей подобранных профстандартов
    {
        path: '/standards',
        name: 'Standards',
        props: true,
        //component: Standards
        component: () => import("@/components/Standard/Standards")
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router;
