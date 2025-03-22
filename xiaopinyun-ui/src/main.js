import "./assets/main.css";
import "./assets/styles/variables.css";

import { createApp } from "vue";
// 导入 ElementPlus 组件
import ElementPlus from "element-plus";
// 导入 ElementPlus 组件 css 样式
import "element-plus/dist/index.css";
// 配置 ElementPlus 组件使用中文
import zhCn from "element-plus/es/locale/lang/zh-cn";
// Icon 图标
import * as ElementPlusIconsVue from "@element-plus/icons-vue";
import { pinia } from "@/store/index.js";
import App from "./App.vue";
import router from "./router";

const app = createApp(App);

app.use(pinia);
app.use(ElementPlus);
app.use(ElementPlus, { locale: zhCn });
app.use(router);

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component);
}
app.mount("#app");
