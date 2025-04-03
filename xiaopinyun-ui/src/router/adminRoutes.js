// src/router/adminRoutes.js
export default [
    {
        path: "/admin/home",
        component: () => import("@/views/admin/UserAudit.vue"),
        meta: { title: "后台管理" },
    },
    {
        path: "/admin/stats",
        component: () => import("@/views/admin/Statistics.vue"),
        meta: { title: "后台管理" },
    },
];
