import { fileURLToPath, URL } from "node:url";

import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import vueDevTools from "vite-plugin-vue-devtools";

// https://vite.dev/config/
export default defineConfig({
    plugins: [vue(), vueDevTools()],
    resolve: {
        alias: {
            "@": fileURLToPath(new URL("./src", import.meta.url)),
        },
    },
    server: {
        host: "xiaopinyun.com", // 自定义域名
        port: 80, // 设置端口号为 80
        open: true, // 自动打开浏览器
        proxy: {
            // 代理前缀  代理所有以"/resume-server"开始的前缀 例"/resume-server/applicant/${id}"
            "/resume-server": {
                target: "http://127.0.0.1:9999",
                changeOrigin: true,
            },
            "/upload-server": {
                target: "http://127.0.0.1:9999",
                changeOrigin: true,
            },
        },
    },
});
