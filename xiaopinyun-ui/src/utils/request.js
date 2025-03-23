// src/utils/request.js
import axios from "axios";
import { useUserStore } from "@/store/userStore";

console.log("API Base URL:", import.meta.env.VITE_API_BASE_URL);

const service = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
    timeout: 10000,
});

// 请求拦截器
service.interceptors.request.use(
    (config) => {
        // 请求成功时的拦截逻辑
        const userStore = useUserStore();
        if (userStore.token) {
            // 如果存在token，则将其添加到请求头的 Authorization 字段中
            config.headers["Authorization"] = `${userStore.token}`;
        }
        return config;
    },
    (error) => {
        // 请求失败时的拦截逻辑
        return Promise.reject(error); // 抛出错误
    }
);

// 响应拦截器
service.interceptors.response.use(
    (response) => response.data,
    (error) => {
        // 遇到 401 错误时自动登出并跳转登录页
        if (error.response?.status === 401) {
            const userStore = useUserStore();
            userStore.logout();
            router.push("/login");
        }
        return Promise.reject(error);
    }
);

export default service;
