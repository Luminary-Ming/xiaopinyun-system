<!-- src/components/student/JobCard.vue -->
<template>
    <el-card class="job-card" @click="$emit('click')">
        <div class="job-header">
            <h3 class="title">{{ job.title }}</h3>
            <span class="salary">{{ job.salary }}</span>
        </div>

        <div class="company-info">
            <el-avatar :size="40" :src="job.company.logo" />
            <div class="company-detail">
                <div class="name">{{ job.company.name }}</div>
                <div class="tags">
                    <el-tag v-for="(tag, index) in job.company.tags" :key="index" size="small">
                        {{ tag }}
                    </el-tag>
                </div>
            </div>
        </div>

        <div class="job-footer">
            <div class="location">
                <el-icon><Location /></el-icon>
                {{ job.location }}
            </div>
            <el-button type="text" :icon="job.isCollected ? StarFilled : Star" @click.stop="toggleCollect" />
        </div>
    </el-card>
</template>

<script setup>
import { Location, Star, StarFilled } from "@element-plus/icons-vue";

defineProps({
    job: {
        type: Object,
        required: true,
    },
});

const emit = defineEmits(["collect"]);

const toggleCollect = () => {
    emit("collect", !job.isCollected);
};
</script>

<style scoped lang="scss">
.job-card {
    margin-bottom: 15px;
    cursor: pointer;
    transition: box-shadow 0.3s;

    &:hover {
        box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
    }

    .job-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 15px;

        .title {
            margin: 0;
            font-size: 16px;
        }

        .salary {
            color: var(--primary-color);
            font-weight: bold;
        }
    }

    .company-info {
        display: flex;
        align-items: center;
        margin-bottom: 15px;

        .company-detail {
            margin-left: 10px;

            .name {
                font-size: 14px;
            }

            .tags {
                margin-top: 5px;
            }
        }
    }

    .job-footer {
        display: flex;
        justify-content: space-between;
        align-items: center;
        color: var(--text-secondary);

        .location {
            display: flex;
            align-items: center;

            .el-icon {
                margin-right: 5px;
            }
        }
    }
}
</style>
