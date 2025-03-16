// src/router/studentRoutes.js
export default [
    {
        path: "/",
        component: () => import("@/components/layouts/MainLayout.vue"),
        redirect: "/home", // 重定向到首页
        children: [
            {
                path: "/resume",
                name: "Resume",
                component: () => import("@/views/student/ResumeEditor.vue"),
                meta: { title: "我的简历" },
            },
        ],
    },
];
