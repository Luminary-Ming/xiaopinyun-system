// src/store/userStore.js
import { defineStore } from "pinia";

export const useUserStore = defineStore("user", {
    state: () => ({
        userInfo: null,
        token: localStorage.getItem("token") || "",
    }),
    actions: {
        async login(credentials) {
            // 实际调用登录接口
            this.token = "mock-token";
            this.userInfo = { id: 1, role: "student", name: "张三" };
            localStorage.setItem("token", this.token);
        },
        logout() {
            this.$reset();
            localStorage.removeItem("token");
        },
    },
});
