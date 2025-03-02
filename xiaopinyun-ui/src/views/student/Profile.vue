<!-- src/views/student/Profile.vue -->
<template>
    <div class="profile-page">
        <el-card class="profile-card">
            <template #header>
                <div class="card-header">
                    <span>个人信息管理</span>
                    <el-button type="primary" @click="saveProfile">保存修改</el-button>
                </div>
            </template>

            <el-form :model="form" label-width="100px">
                <el-form-item label="姓名">
                    <el-input v-model="form.name" />
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio-group v-model="form.gender">
                        <el-radio label="male">男</el-radio>
                        <el-radio label="female">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="联系方式">
                    <el-input v-model="form.phone" />
                </el-form-item>
                <el-form-item label="求职期望">
                    <el-cascader v-model="form.expectation" :options="positionOptions" :props="{ multiple: true }" />
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { studentApi } from "@/api/student";

const form = ref({
    name: "",
    gender: "",
    phone: "",
    expectation: [],
});

const positionOptions = ref([
    {
        value: "technology",
        label: "技术",
        children: [
            { value: "frontend", label: "前端开发" },
            { value: "backend", label: "后端开发" },
        ],
    },
]);

const saveProfile = async () => {
    await studentApi.updateProfile(form.value);
    ElMessage.success("个人信息更新成功");
};
</script>

<style scoped>
.profile-card {
    max-width: 800px;
    margin: 20px auto;
}
.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
</style>
