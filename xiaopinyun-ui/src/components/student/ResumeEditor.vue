<!-- src/components/student/ResumeEditor.vue -->
<template>
    <div class="resume-editor">
        <el-form :model="formData" label-width="100px">
            <!-- 专业技能 -->
            <el-form-item label="专业技能" prop="skills">
                <el-input v-model="formData.skills" type="textarea" :rows="3" placeholder="请输入专业技能（用逗号分隔）" />
            </el-form-item>

            <!-- 教育背景 -->
            <div class="section-title">教育背景</div>
            <el-form-item v-for="(edu, index) in formData.education" :key="index" :label="`经历${index + 1}`">
                <el-row :gutter="20">
                    <el-col :span="8">
                        <el-input v-model="edu.school" placeholder="学校名称" />
                    </el-col>
                    <el-col :span="8">
                        <el-input v-model="edu.major" placeholder="专业" />
                    </el-col>
                    <el-col :span="6">
                        <el-date-picker v-model="edu.duration" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" />
                    </el-col>
                    <el-col :span="2">
                        <el-button type="danger" circle :icon="Delete" @click="removeEducation(index)" />
                    </el-col>
                </el-row>
            </el-form-item>
            <el-button type="primary" @click="addEducation">添加教育经历</el-button>

            <!-- 操作按钮 -->
            <div class="action-buttons">
                <el-button type="primary" @click="submitForm">保存简历</el-button>
                <el-button @click="resetForm">重置</el-button>
            </div>
        </el-form>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { Delete } from "@element-plus/icons-vue";
import { studentApi } from "@/api/student";

const formData = ref({
    skills: "",
    education: [
        {
            school: "",
            major: "",
            duration: [],
        },
    ],
});

const addEducation = () => {
    formData.value.education.push({
        school: "",
        major: "",
        duration: [],
    });
};

const removeEducation = (index) => {
    formData.value.education.splice(index, 1);
};

const submitForm = async () => {
    await studentApi.updateResume(formData.value);
    ElMessage.success("简历保存成功");
};

const resetForm = () => {
    formData.value = {
        skills: "",
        education: [
            {
                school: "",
                major: "",
                duration: [],
            },
        ],
    };
};
</script>

<style scoped>
.section-title {
    font-size: 16px;
    color: var(--primary-color);
    margin: 20px 0;
    padding-bottom: 8px;
    border-bottom: 2px solid #eee;
}

.action-buttons {
    margin-top: 30px;
    text-align: center;
}
</style>
