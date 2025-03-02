// src/store/permissionStore.js
import { defineStore } from "pinia";

export const usePermissionStore = defineStore("permission", {
    state: () => ({
        routes: [],
        permissions: [],
    }),
    actions: {
        async loadRoutes() {
            // 根据角色加载动态路由
        },
    },
});
