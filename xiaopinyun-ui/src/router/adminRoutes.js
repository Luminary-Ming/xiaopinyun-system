// src/router/adminRoutes.js
export default [
    {
        path: "/admin/users",
        component: () => import("@/views/admin/UserAudit.vue"),
        meta: { requiresAuth: true, roles: ["admin"] },
    },
    {
        path: "/admin/stats",
        component: () => import("@/views/admin/Statistics.vue"),
        meta: { requiresAuth: true, roles: ["admin"] },
    },
];
