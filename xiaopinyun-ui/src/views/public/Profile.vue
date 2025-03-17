<!-- 个人中心公共出口 -->
<!-- https://wallspic.com/cn/image/179036-san-fen_hong_se-pin_hong_se-cheng_shi-wu_ye -->
<template>
    <div v-if="isStudent">
        <Application />
    </div>
    <div v-else>
        <HR />
    </div>
</template>

<script setup>
import { ref } from "vue";
import Application from "@/views/student/Application.vue";
import HR from "@/views/company/HR.vue";

// 定义一个响应式变量来控制显示哪个组件
const isStudent = ref(false); // true 显示 Application 组件，false 显示 HR 组件

//
const toggleView = () => {
    isStudent.value = !isStudent.value;
};
</script>

<style scoped>
div {
    width: 100%;
    height: 100%;
}
</style>
