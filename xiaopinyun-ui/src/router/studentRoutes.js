// src/router/studentRoutes.js
export default [
    {
        path: "/student/dashboard",
        component: () => import("@/views/student/Dashboard.vue"),
        meta: { requiresAuth: true, roles: ["student"] },
    },
    {
        path: "/student/profile",
        component: () => import("@/views/student/Profile.vue"),
        meta: { requiresAuth: true, roles: ["student"] },
    },
    {
        path: "/student/applications",
        component: () => import("@/views/student/Applications.vue"),
        meta: { requiresAuth: true, roles: ["student"] },
    },
];
