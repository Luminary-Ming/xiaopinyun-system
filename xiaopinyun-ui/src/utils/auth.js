// src/utils/auth.js
export const checkPermission = (to) => {
    const userStore = useUserStore();
    if (to.meta.requiresAuth && !userStore.token) return "/login";
    if (to.meta.roles && !to.meta.roles.includes(userStore.userInfo?.role)) return "/403";
    return true;
};
