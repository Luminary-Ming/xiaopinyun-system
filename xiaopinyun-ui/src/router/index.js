// src/router/index.js
import { createRouter, createWebHashHistory } from "vue-router";
import { useUserStore } from "@/store/userStore";

// 导入各角色路由配置
import studentRoutes from "./studentRoutes";
import companyRoutes from "./companyRoutes";
import adminRoutes from "./adminRoutes";

// 公共路由
const publicRoutes = [
    {
        path: "/",
        component: () => import("@/layouts/MainLayout.vue"),
        redirect: "/home", // 重定向到首页
        children: [
            {
                path: "/home",
                name: "Home",
                component: () => import("@/views/public/Home.vue"),
                meta: { title: "首页" },
            },
            {
                path: "/job",
                name: "JobDetail",
                component: () => import("@/views/public/JobDetail.vue"),
                meta: { title: "职位详情" },
                props: true,
            },
            {
                path: "/company",
                name: "Company",
                component: () => import("@/views/company/Dashboard.vue"),
                meta: { title: "公司详情" },
            },
            {
                path: "/resume",
                name: "Resume",
                component: () => import("@/components/student/ResumeEditor.vue"),
                meta: { title: "我的简历" },
            },
        ],
    },
    // 登录路由
    {
        path: "/login",
        name: "Login",
        component: () => import("@/components/common/AuthDialog.vue"),
        meta: { title: "用户登录" },
    },
    // 404处理
    {
        path: "/:pathMatch(.*)*",
        redirect: { name: "Home" },
    },
];

// 合并所有路由
const routes = [...publicRoutes, ...studentRoutes, ...companyRoutes, ...adminRoutes];

// 创建路由实例
const router = createRouter({
    history: createWebHashHistory(import.meta.env.BASE_URL),
    routes,
    scrollBehavior(to, from, savedPosition) {
        return savedPosition || { top: 0 };
    },
});

// 路由守卫（根据之前提供的权限控制）
router.beforeEach(async (to, from, next) => {
    const userStore = useUserStore();

    // 设置页面标题
    document.title = to.meta.title ? `${to.meta.title} - 校聘云` : "校聘云";

    // 登录状态检查
    // if (to.meta.requiresAuth && !userStore.token) {
    //     return next({ name: "Login", query: { redirect: to.fullPath } });
    // }

    // 角色权限检查
    // if (to.meta.roles && !to.meta.roles.includes(userStore.userInfo?.role)) {
    //     return next("/403"); // 需要自行创建403页面
    // }

    next();
});

export default router;
