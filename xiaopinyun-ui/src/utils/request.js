// src/utils/request.js
import axios from "axios";
import { useUserStore } from "@/store/userStore";

const service = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
    timeout: 10000,
});

service.interceptors.request.use((config) => {
    const userStore = useUserStore();
    if (userStore.token) {
        config.headers.Authorization = `Bearer ${userStore.token}`;
    }
    return config;
});

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
