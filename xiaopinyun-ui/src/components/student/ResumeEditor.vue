<!-- src/components/student/ResumeEditor.vue -->
<template>
    <div class="resume-box">
        <div class="resume-list">
            <el-col :span="12">
                <el-menu active-text-color="#000" background-color="#fff" class="el-menu-vertical-demo" default-active="2" text-color="#000" @open="handleOpen" @close="handleClose">
                    <div class="mb-2">
                        <h3>简历目录</h3>
                    </div>
                    <el-menu-item index="1">
                        <el-icon><User /></el-icon>
                        <span>个人信息</span>
                    </el-menu-item>
                    <el-menu-item index="2">
                        <el-icon><Histogram /></el-icon>
                        <span>专业技能</span>
                    </el-menu-item>
                    <el-menu-item index="3">
                        <el-icon><document /></el-icon>
                        <span>求职期望</span>
                    </el-menu-item>
                    <el-menu-item index="4">
                        <el-icon><School /></el-icon>
                        <span>教育背景</span>
                    </el-menu-item>
                    <el-menu-item index="5">
                        <el-icon><Monitor /></el-icon>
                        <span>项目经历</span>
                    </el-menu-item>
                    <el-menu-item index="6">
                        <el-icon><OfficeBuilding /></el-icon>
                        <span>工作/实习经历</span>
                    </el-menu-item>
                    <el-menu-item index="7">
                        <el-icon><GoldMedal /></el-icon>
                        <span>获奖荣誉</span>
                    </el-menu-item>
                    <el-menu-item index="8">
                        <el-icon><EditPen /></el-icon>
                        <span>自我评价</span>
                    </el-menu-item>
                </el-menu>
            </el-col>
        </div>
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
                        <div>
                            <el-col :span="6">
                                <el-date-picker v-model="edu.duration" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" />
                            </el-col>
                        </div>
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
        <div class="resume-file">
            <el-col :span="12">
                <el-menu active-text-color="#000" background-color="#fff" class="el-menu-vertical-demo" default-active="2" text-color="#000" @open="handleOpen" @close="handleClose">
                    <div class="mb-2">
                        <h3>附件管理</h3>
                    </div>
                    <el-menu-item index="9">
                        <img class="pdf_img" src="https://static.zhipin.com/fe-zhipin-geek/web/chat/v5808/static/images/icon-pdf.bd26c623.png" />
                        <span>java工程师</span>
                    </el-menu-item>
                    <el-menu-item index="10">
                        <img class="pdf_img" src="https://static.zhipin.com/fe-zhipin-geek/web/chat/v5808/static/images/icon-pdf.bd26c623.png" />
                        <span>实施工程师</span>
                    </el-menu-item>
                </el-menu>
            </el-col>
        </div>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { Document, Menu as IconMenu, Location, Setting } from "@element-plus/icons-vue";
// import { studentApi } from "@/api/student";

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
    // await studentApi.updateResume(formData.value);
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
.resume-box {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: space-around;
    background: linear-gradient(to bottom, #e1f2f4, #f6f6f8);
    padding-top: 20px;
}

.resume-list {
    width: 200px;
    height: 400px;
    border: 1px #e6e7ec solid;
    border-radius: 10px;
}

.resume-editor {
}

.resume-file {
    width: 200px;
    height: 400px;
    border: 1px #e6e7ec solid;
    border-radius: 10px;
}

.pdf_img {
    width: 44px;
    height: 44px;
    margin: 0px 8px 0px 0px;
}

.mb-2 {
    height: 35px;
    background-color: #f6f6f8;
    border-radius: 10px 10px 0 0;
    padding-top: 5px;
    padding-left: 25px;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
    border-radius: 10px;
}

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
