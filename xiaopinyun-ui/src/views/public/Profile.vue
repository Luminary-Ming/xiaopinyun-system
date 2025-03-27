<!-- 个人中心公共出口 -->
<!-- https://wallspic.com/cn/image/179036-san-fen_hong_se-pin_hong_se-cheng_shi-wu_ye -->
<template>
    <div v-if="isStudent == 0" class="applicant">
        <Application />
    </div>
    <div v-if="isStudent == 1" class="hr">
        <HR />
    </div>
</template>

<script setup>
import { ref } from "vue";
import Application from "@/views/student/Application.vue";
import HR from "@/views/company/HR.vue";
import { useUserStore } from "@/store/userStore";
const userStore = useUserStore();
const isStudent = ref(userStore.role);
</script>

<style scoped>
.applicant {
    width: 100%;
    height: 100%;
}
.hr {
    width: 100%;
    height: 100%;
}
</style>
