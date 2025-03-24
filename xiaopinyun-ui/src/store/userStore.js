// src/store/userStore.js
import { defineStore } from "pinia";

export const useUserStore = defineStore("user", {
    state: () => ({
        pkApplicant: localStorage.getItem("pkApplicant") || "", // 学生主键
        pkHr: localStorage.getItem("pkHr") || "", // hr主键
        pkAdmin: localStorage.getItem("pkAdmin") || "", // 管理员主键
        role: localStorage.getItem("role") || "", // 角色
        name: localStorage.getItem("name") || "新用户", // 姓名
        profileImg: localStorage.getItem("profileImg") || "/src/assets/images/profile-img/default.png", // 头像
        token: localStorage.getItem("token") || "",
        flag: localStorage.getItem("flag") || "", // 注册标志
    }),
    actions: {
        setToken(token) {
            this.token = token;
            localStorage.setItem("token", token);
        },
        // 登出
        logout() {
            this.pkApplicant = "";
            this.pkHr = "";
            this.pkAdmin = "";
            this.role = "";
            this.name = "";
            this.profileImg = "";
            this.token = "";
            this.flag = "";
            localStorage.removeItem("pkApplicant");
            localStorage.removeItem("pkHr");
            localStorage.removeItem("pkAdmin");
            localStorage.removeItem("role");
            localStorage.removeItem("name");
            localStorage.removeItem("profileImg");
            localStorage.removeItem("token");
            localStorage.removeItem("flag");
        },
        setUserInfo(userInfo) {
            this.pkApplicant = userInfo.pkApplicant;
            this.pkHr = userInfo.pkHr;
            this.pkAdmin = userInfo.pkAdmin;
            this.role = userInfo.role;
            this.name = userInfo.name;
            this.profileImg = userInfo.profileImg;
            this.flag = userInfo.flag;
            localStorage.setItem("pkApplicant", userInfo.pkApplicant);
            localStorage.setItem("pkHr", userInfo.pkHr);
            localStorage.setItem("pkAdmin", userInfo.pkAdmin);
            localStorage.setItem("role", userInfo.role);
            localStorage.setItem("name", userInfo.name);
            localStorage.setItem("profileImg", userInfo.profileImg);
            localStorage.setItem("flag", userInfo.flag);
        },
    },
});
