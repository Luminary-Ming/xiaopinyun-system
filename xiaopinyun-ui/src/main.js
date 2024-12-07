import "./assets/main.css";

import { createApp } from "vue";
// 导入 ElementPlus 组件
import ElementPlus from "element-plus";
// 导入 ElementPlus 组件 css 样式
import "element-plus/dist/index.css";
// 配置 ElementPlus 组件使用中文
import zhCn from "element-plus/es/locale/lang/zh-cn";
import App from "./App.vue";
import router from "./router";

const app = createApp(App);

app.use(ElementPlus);
app.use(router);
app.use(ElementPlus, { locale: zhCn });

app.mount("#app");
