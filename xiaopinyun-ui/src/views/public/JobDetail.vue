<!-- src/views/public/JobDetail.vue -->
<template>
    <div class="job-detail">
        <el-row :gutter="20">
            <!-- 左侧主内容 -->
            <el-col :span="16">
                <el-card>
                    <h1 class="job-title">{{ job.title }}</h1>
                    <div class="company-info">
                        <el-avatar :size="50" :src="job.company.logo" />
                        <div class="company-text">
                            <h3>{{ job.company.name }}</h3>
                            <div class="tags">
                                <el-tag v-for="(tag, index) in job.tags" :key="index">{{ tag }}</el-tag>
                            </div>
                        </div>
                    </div>

                    <el-divider />

                    <div class="job-content">
                        <h3>职位描述</h3>
                        <div v-html="job.description"></div>

                        <h3>职位要求</h3>
                        <ul class="requirements">
                            <li v-for="(req, index) in job.requirements" :key="index">{{ req }}</li>
                        </ul>
                    </div>
                </el-card>
            </el-col>

            <!-- 右侧操作区 -->
            <el-col :span="8">
                <el-card class="action-card">
                    <div class="salary">{{ job.salary }}</div>
                    <el-button type="primary" size="large" class="apply-btn" @click="handleApply">
                        {{ hasApplied ? "已投递" : "立即申请" }}
                    </el-button>

                    <div class="meta-info">
                        <div class="info-item">
                            <span>{{ job.location }}</span>
                        </div>
                        <div class="info-item">
                            <span>{{ job.postTime }} 发布</span>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
const job = ref({
    id: route.params.id,
    title: "前端开发工程师",
    salary: "15-25K",
    company: {
        name: "XX科技有限公司",
        logo: "",
        tags: ["上市公司", "互联网"],
    },
    description: "<p>负责公司核心业务系统的前端开发工作</p>",
    requirements: ["3年以上前端开发经验", "精通Vue.js框架", "熟悉Webpack等构建工具"],
    location: "北京市海淀区",
    postTime: "2023-08-15",
});
</script>

<style scoped lang="scss">
.job-detail {
    .company-info {
        display: flex;
        align-items: center;
        margin: 20px 0;

        .company-text {
            margin-left: 20px;

            .tags {
                margin-top: 8px;
                .el-tag {
                    margin-right: 8px;
                }
            }
        }
    }

    .action-card {
        text-align: center;

        .salary {
            color: var(--primary-color);
            font-size: 24px;
            margin: 20px 0;
        }

        .apply-btn {
            width: 100%;
            margin-bottom: 20px;
        }

        .meta-info {
            text-align: left;
            .info-item {
                display: flex;
                align-items: center;
                margin: 12px 0;

                .el-icon {
                    margin-right: 8px;
                }
            }
        }
    }
}
</style>
