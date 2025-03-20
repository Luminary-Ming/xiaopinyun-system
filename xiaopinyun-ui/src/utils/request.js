// src/utils/request.js
import axios from "axios";
import { useUserStore } from "@/store/userStore";

console.log("API Base URL:", import.meta.env.VITE_API_BASE_URL);

const service = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
    timeout: 10000,
});

// 请求拦截器
// service.interceptors.request.use((config) => {
//     const userStore = useUserStore();
//     if (userStore.token) {
//         config.headers.Authorization = `Bearer ${userStore.token}`;
//     }
//     return config;
// });

// 响应拦截器
service.interceptors.response.use(
    (response) => response.data,
    (error) => {
        if (error.response?.status === 401) {
            useUserStore().logout();
        }
        return Promise.reject(error);
    }
);

export default service;
