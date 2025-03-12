<!-- src/views/student/Dashboard.vue -->
<template>
    <div class="dashboard">
        <el-row :gutter="20">
            <el-col :span="8">
                <el-card class="stats-card">
                    <h3>已投递岗位</h3>
                    <div class="count">{{ applicationsCount }}</div>
                </el-card>
            </el-col>
            <el-col :span="8">
                <el-card class="stats-card">
                    <h3>收到回复</h3>
                    <div class="count">{{ repliesCount }}</div>
                </el-card>
            </el-col>
            <el-col :span="8">
                <el-card class="stats-card">
                    <h3>收藏职位</h3>
                    <div class="count">{{ favoritesCount }}</div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { studentApi } from "@/api/student";

const applicationsCount = ref(0);
const repliesCount = ref(0);
const favoritesCount = ref(0);

const loadData = async () => {
    const res = await studentApi.getApplications();
    applicationsCount.value = res.data.total;
    repliesCount.value = res.data.replies;
    favoritesCount.value = res.data.favorites;
};

loadData();
</script>

<style scoped>
.stats-card {
    text-align: center;
    .count {
        font-size: 24px;
        color: var(--primary-color);
        margin: 10px 0;
    }
}
</style>
