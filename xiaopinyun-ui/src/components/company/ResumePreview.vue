<!-- src/components/company/ResumePreview.vue -->
<template>
    <el-card class="resume-preview">
        <div class="header">
            <h2>{{ resume.name }}的简历</h2>
            <el-rate v-model="resume.score" :colors="['#99A9BF', '#F7BA2A', '#FF9900']" disabled show-score />
        </div>

        <div class="section">
            <h3>基本信息</h3>
            <el-descriptions :column="2" border>
                <el-descriptions-item label="性别">{{ resume.gender }}</el-descriptions-item>
                <el-descriptions-item label="学历">{{ resume.education }}</el-descriptions-item>
                <el-descriptions-item label="毕业院校">{{ resume.school }}</el-descriptions-item>
                <el-descriptions-item label="联系电话">{{ resume.phone }}</el-descriptions-item>
            </el-descriptions>
        </div>

        <div class="section">
            <h3>求职意向</h3>
            <div class="intention">
                <el-tag type="info">{{ resume.targetCity }}</el-tag>
                <el-tag type="info">{{ resume.targetPosition }}</el-tag>
                <el-tag type="info">{{ resume.targetSalary }}</el-tag>
            </div>
        </div>

        <div class="section">
            <h3>项目经历</h3>
            <el-timeline>
                <el-timeline-item v-for="(project, index) in resume.projects" :key="index" :timestamp="project.duration">
                    <h4>{{ project.name }}</h4>
                    <p>{{ project.description }}</p>
                </el-timeline-item>
            </el-timeline>
        </div>
    </el-card>
</template>

<script setup>
defineProps({
    resume: {
        type: Object,
        default: () => ({
            name: "",
            score: 0,
            gender: "",
            education: "",
            school: "",
            phone: "",
            targetCity: "",
            targetPosition: "",
            targetSalary: "",
            projects: [],
        }),
    },
});
</script>

<style scoped lang="scss">
.resume-preview {
    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 20px;
    }

    .section {
        margin: 20px 0;

        h3 {
            color: var(--primary-color);
            margin-bottom: 15px;
        }

        .intention {
            .el-tag {
                margin-right: 10px;
            }
        }
    }
}
</style>
