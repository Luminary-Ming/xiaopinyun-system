<!-- 管理员审核 -->
<template>
    <div class="user-audit">
        <el-tabs v-model="activeTab">
            <el-tab-pane label="学生审核" name="students">
                <el-table :data="pendingStudents">
                    <el-table-column prop="name" label="姓名" />
                    <el-table-column prop="school" label="学校" />
                    <el-table-column label="操作" width="180">
                        <template #default="{ row }">
                            <el-button size="small" @click="auditStudent(row.id, true)">通过</el-button>
                            <el-button size="small" type="danger" @click="auditStudent(row.id, false)">拒绝</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>

            <el-tab-pane label="企业审核" name="companies">
                <el-table :data="pendingCompanies">
                    <el-table-column prop="name" label="企业名称" />
                    <el-table-column prop="license" label="营业执照" />
                    <el-table-column label="操作" width="180">
                        <template #default="{ row }">
                            <el-button size="small" @click="auditCompany(row.id, true)">通过</el-button>
                            <el-button size="small" type="danger" @click="auditCompany(row.id, false)">拒绝</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { adminApi } from "@/api/admin";

const activeTab = ref("students");
const pendingStudents = ref([]);
const pendingCompanies = ref([]);

const loadPendingData = async () => {
    const [studentsRes, companiesRes] = await Promise.all([adminApi.getPendingStudents(), adminApi.getPendingCompanies()]);
    pendingStudents.value = studentsRes.data;
    pendingCompanies.value = companiesRes.data;
};

loadPendingData();
</script>
