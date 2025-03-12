<!-- src/views/company/JobManagement.vue -->
<template>
    <div class="job-management">
        <el-button type="primary" @click="showDialog = true">发布新职位</el-button>

        <el-table :data="jobs" style="width: 100%">
            <el-table-column prop="title" label="职位名称" />
            <el-table-column prop="status" label="状态">
                <template #default="{ row }">
                    <el-tag :type="row.status === 'published' ? 'success' : 'info'">
                        {{ row.status === "published" ? "已发布" : "已关闭" }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template #default="{ row }">
                    <el-button size="small" @click="editJob(row)">编辑</el-button>
                    <el-button size="small" type="danger" @click="deleteJob(row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog v-model="showDialog" title="职位发布">
            <PostJobForm @submit="handleSubmit" />
        </el-dialog>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { companyApi } from "@/api/company";
import PostJobForm from "@/components/company/PostJobForm.vue";

const jobs = ref([]);
const showDialog = ref(false);

const loadJobs = async () => {
    const res = await companyApi.getJobs();
    jobs.value = res.data;
};

const handleSubmit = async (formData) => {
    await companyApi.postJob(formData);
    showDialog.value = false;
    loadJobs();
};

loadJobs();
</script>
