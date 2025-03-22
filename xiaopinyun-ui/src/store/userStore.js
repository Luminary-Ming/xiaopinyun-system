// src/store/userStore.js
import { defineStore } from "pinia";

export const useUserStore = defineStore("user", {
    state: () => ({
        token: localStorage.getItem("token") || "",
        pk_applicant: "", // 学生主键
        pk_hr: "", // hr主键
        pk_admin: "", // 管理员主键
        role: "", // 角色
        flag: "", // 注册标志
    }),
    actions: {
        setToken(token) {
            this.token = token;
            localStorage.setItem("token", token);
        },
        // 登出
        logout() {
            this.token = "";
            this.pk_applicant = "";
            this.flag = "";
            this.role = "";
            localStorage.removeItem("token");
        },
        setUserInfo(userInfo) {
            this.pk_applicant = userInfo.pk_applicant;
            this.pk_hr = userInfo.pk_hr;
            this.pk_admin = userInfo.pk_admin;
            this.role = userInfo.role;
            this.flag = userInfo.flag;
        },
    },
});
