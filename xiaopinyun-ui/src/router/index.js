import { createRouter, createWebHashHistory } from "vue-router";

const router = createRouter({
    history: createWebHashHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "home",
            component: () => import("@/views/layout/home.vue"),
            children: [],
        },
        {
            path: "/login",
            name: "login",
            component: () => import()
        },
    ],
});

export default router;
