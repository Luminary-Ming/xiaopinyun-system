<template>
    <div class="box">
        <div v-for="(content, index) in contents" :key="index" class="item-content">
            <div class="content-top">
                <div class="img-name-identity" @click="queryBtn(index)">
                    <div class="hr-name">{{ content.hr_name }}</div>
                    <div class="hr-identity">{{ content.hr_identity }}</div>
                </div>
                <div>
                    <el-button @click="pass(index)" class="update-btn">审核通过</el-button>
                    <el-button @click="reject(index)" type="danger">审核驳回</el-button>
                </div>
            </div>
            <div class="content-body" @click="queryBtn(index)">
                <div class="recruit">
                    <div class="title-address">
                        <div class="title">{{ content.title }}</div>
                        <div class="address">【{{ content.address }}】</div>
                    </div>
                    <div class="salary-requirement-education">
                        <div class="requirement" v-if="activeMenu != 'job'">{{ content.work }}</div>
                        <div class="requirement">{{ content.requirement }}</div>
                        <div class="education">{{ content.education }}</div>
                    </div>
                </div>
                <div class="company">
                    <div class="img-box">
                        <div class="img"><el-avatar shape="square" :size="60" :src="content.company_img" style="vertical-align: middle; border-radius: 10px" /></div>
                    </div>
                    <div class="name-industry-people">
                        <div class="company-name">{{ content.companyName }}</div>
                        <div class="industry-people">
                            <div class="industry-type">{{ content.industryType }}</div>
                            <div class="people">{{ content.people }}</div>
                        </div>
                    </div>
                </div>
                <div class="filter-status">
                    <el-button color="#626aef" v-if="checkStatus(index) == '2'" class="zi">待审核</el-button>
                    <el-button type="success" v-if="checkStatus(index) == '1'">审核通过</el-button>
                    <el-button type="danger" v-if="checkStatus(index) == '0'">审核驳回</el-button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, reactive, onMounted, computed } from "vue";
import { ElMessage } from "element-plus";
import { studentApi } from "@/api/student";
import { useRouter } from "vue-router";
import { useUserStore } from "@/store/userStore";
const router = useRouter();
const userStore = useUserStore();

const applicantManageDTO = ref([]);
/* ------------------------------------- 查询API -------------------------------------- */
onMounted(async () => {
    const respResumeDTO = await studentApi.queryAll();
    applicantManageDTO.value = respResumeDTO.data;
});

/*----------------------------------------- 选项卡内容 ------------------------------------*/
const contents = computed(() => {
    return applicantManageDTO.value.map((item) => ({
        hr_name: "最近登录时间：", // 投递岗位
        hr_identity: item.ts, // 最近登录时间
        title: item.resumeDTO.majorSkill, // 个人优势
        address: item.resumeDTO.applicantVO.address, // 居住地
        work: "应届生", // 有实习经历
        requirement: item.resumeDTO.educationalVO[0].name, // 学校
        education: item.resumeDTO.educationalVO[0].major, // 专业
        company_img: item.resumeDTO.applicantVO.profileImgUrl, // 学生头像
        companyName: item.resumeDTO.applicantVO.name, // 姓名
        industryType: item.resumeDTO.educationalVO[0].qualification, // 学历
        people: convertStatus(item.resumeDTO.applicantVO.status), // 求职状态
    }));
});

// 转换求职状态
const convertStatus = function (status) {
    switch (status) {
        case "0":
            return "离校-随时到岗";
        case "1":
            return "在校-月内到岗";
        case "2":
            return "在校-看看机会";
        case "3":
            return "在校-暂不考虑";
    }
};

// 查看信息
const queryBtn = (index) => {
    localStorage.setItem("pkApplicant", applicantManageDTO.value[index].resumeDTO.applicantVO.id);
    router.push("/resume");
};

// 审核通过
const pass = async (index) => {
    applicantManageDTO.value[index].resumeDTO.applicantVO.checkStatus = "1";
    const resp = await studentApi.updateApplicant(applicantManageDTO.value[index].resumeDTO.applicantVO);
    if (resp.code == 200) {
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};

// 驳回
const reject = async (index) => {
    applicantManageDTO.value[index].resumeDTO.applicantVO.checkStatus = "0";
    const resp = await studentApi.updateApplicant(applicantManageDTO.value[index].resumeDTO.applicantVO);
    if (resp.code == 200) {
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};

// 审核状态
const checkStatus = (index) => {
    return applicantManageDTO.value[index].resumeDTO.applicantVO.checkStatus;
};
</script>

<style scoped>
.box {
    display: flex;
    justify-content: center;
    gap: 20px;
    flex-wrap: wrap;
}

.filter-status {
    position: absolute;
    right: 25px;
    top: 30px;
}

/* 针对不同类型按钮分别处理 */
.filter-status .el-button--danger:hover {
    background-color: var(--el-color-danger); /* 保持危险按钮的红色 */
    border-color: var(--el-color-danger);
}

.filter-status .el-button--success:hover {
    background-color: var(--el-color-success); /* 保持成功按钮的绿色 */
    border-color: var(--el-color-success);
}

.filter-status .zi:hover {
    background-color: #626aef; /* 保持紫色颜色 */
    border-color: #626aef;
}

/* ----------- 选项卡内容 --------- */
.item-content {
    box-sizing: border-box;
    width: 885px;
    height: 150px;
    background-color: #fff;
    margin-top: 15px;
    border-radius: 15px;
}

.item-content:hover {
    cursor: pointer;
    box-shadow: 0 16px 40px 0 hsla(0, 0%, 60%, 0.3);
}

.item-content:hover .content-top .el-button {
    opacity: 1;
}

.item-content .content-top {
    display: flex;
    box-sizing: border-box;
    width: 100%;
    height: 48px;
    background-color: #f5fcfc;
    padding: 12px 24px;
    border-radius: 15px 15px 0 0;
    align-items: center;
    justify-content: space-between;
}

.item-content .img-name-identity {
    display: flex;
    align-items: center;
}

.content-top .hr-name {
    color: #222222;
    font-size: 13px;
    margin-left: 12px;
}

.content-top .hr-identity {
    color: #666;
    font-size: 13px;
    margin-left: 12px;
}

.item-content .content-top .el-button {
    opacity: 0;
    color: #fff;
    border-radius: 8px;
}

.item-content .update-btn {
    background-color: #00a6a7;
}

.item-content .update-btn:hover {
    background-color: #00bebd;
}

.item-content .content-body {
    position: relative;
    box-sizing: border-box;
    width: 100%;
    padding: 16px 24px;
    display: flex;
}

.content-body .recruit {
    box-sizing: border-box;
    width: 500px;
    line-height: 30px;
}

.content-body .title-address {
    display: flex;
}

.content-body .recruit .title {
    font-size: 16px;
    color: #222;
    white-space: nowrap; /* 文本不换行 */
    overflow: hidden; /* 溢出隐藏 */
    text-overflow: ellipsis; /* 显示省略号 */
    max-width: 300px; /* 设置最大宽度 */
}

.content-body .recruit .address {
    font-size: 16px;
    color: #222;
    margin-left: 8px;
}

.content-body .salary-requirement-education {
    display: flex;
    margin-top: 10px;
}

.content-body .recruit .salary {
    font-size: 16px;
    color: #fe574a;
    margin-right: 10px;
}

.content-body .recruit .requirement {
    font-size: 13px;
    background: #f8f8f8;
    border-radius: 4px;
    font-weight: 400;
    color: #666;
    margin-right: 10px;
    padding: 0 8px;
}

.content-body .recruit .education {
    font-size: 13px;
    background: #f8f8f8;
    border-radius: 4px;
    font-weight: 400;
    color: #666;
    padding: 0 8px;
}

.content-body .company {
    display: flex;
    align-items: center;
}

.content-body .img-box {
    margin-right: 10px;
}

.content-body .name-industry-people {
    width: 300px;
    margin-right: 20px;
}

.content-body .company .company-name {
    font-size: 16px;
    color: #222;
}

.content-body .industry-people {
    display: flex;
    height: 30px;
    align-items: center;
    margin-top: 15px;
}

.content-body .company .industry-type {
    font-size: 13px;
    background: #f8f8f8;
    border-radius: 4px;
    font-weight: 400;
    color: #666;
    margin-right: 10px;
    padding: 8px;
}

.content-body .company .people {
    font-size: 13px;
    background: #f8f8f8;
    border-radius: 4px;
    font-weight: 400;
    color: #666;
    padding: 8px;
}
</style>
