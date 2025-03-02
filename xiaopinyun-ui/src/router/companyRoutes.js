// src/router/companyRoutes.js
export default [
    {
        path: "/company/dashboard",
        component: () => import("@/views/company/Dashboard.vue"),
        meta: { requiresAuth: true, roles: ["company"] },
    },
    {
        path: "/company/jobs",
        component: () => import("@/views/company/JobManagement.vue"),
        meta: { requiresAuth: true, roles: ["company"] },
    },
];
