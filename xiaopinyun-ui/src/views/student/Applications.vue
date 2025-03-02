<!-- src/views/student/Applications.vue -->
<template>
    <div class="applications-page">
        <el-tabs v-model="activeTab">
            <el-tab-pane label="全部投递" name="all">
                <ApplicationList :data="allApplications" />
            </el-tab-pane>
            <el-tab-pane label="待处理" name="pending">
                <ApplicationList :data="pendingApplications" />
            </el-tab-pane>
            <el-tab-pane label="已回复" name="replied">
                <ApplicationList :data="repliedApplications" />
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script setup>
import { ref, computed } from "vue";
import ApplicationList from "@/components/student/ApplicationList.vue";
import { studentApi } from "@/api/student";

const activeTab = ref("all");
const applications = ref([]);

const loadData = async () => {
    const res = await studentApi.getApplications();
    applications.value = res.data;
};

const allApplications = computed(() => applications.value);
const pendingApplications = computed(() => applications.value.filter((app) => app.status === "pending"));
const repliedApplications = computed(() => applications.value.filter((app) => app.status !== "pending"));

loadData();
</script>
