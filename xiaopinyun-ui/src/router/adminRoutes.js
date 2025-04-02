// src/router/adminRoutes.js
export default [
    {
        path: "/admin/users",
        component: () => import("@/views/admin/UserAudit.vue"),
        meta: { title: "我的简历" },
    },
    {
        path: "/admin/stats",
        component: () => import("@/views/admin/Statistics.vue"),
        meta: { title: "我的简历" },
    },
];
