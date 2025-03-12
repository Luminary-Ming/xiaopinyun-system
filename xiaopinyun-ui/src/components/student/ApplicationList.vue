<!-- src/components/student/ApplicationList.vue -->
<template>
    <div class="application-list">
        <el-table :data="data" style="width: 100%">
            <el-table-column prop="jobTitle" label="职位名称" />
            <el-table-column prop="company" label="企业名称" />
            <el-table-column prop="status" label="状态">
                <template #default="{ row }">
                    <el-tag :type="statusColor[row.status]">
                        {{ statusText[row.status] }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="applyTime" label="投递时间" />
            <el-table-column label="操作">
                <template #default="{ row }">
                    <el-button type="text" @click="viewDetail(row.id)"> 查看详情 </el-button>
                    <el-button type="text" :disabled="row.status !== 'pending'" @click="cancelApply(row.id)"> 取消申请 </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script setup>
defineProps({
    data: {
        type: Array,
        default: () => [],
    },
});

const statusColor = {
    pending: "warning",
    accepted: "success",
    rejected: "danger",
};

const statusText = {
    pending: "待处理",
    accepted: "已通过",
    rejected: "已拒绝",
};

const emit = defineEmits(["view", "cancel"]);

const viewDetail = (id) => {
    emit("view", id);
};

const cancelApply = (id) => {
    emit("cancel", id);
};
</script>

<style scoped>
.application-list {
    margin-top: 20px;
}
</style>
