<template>
    <div class="box">
        <div v-for="(content, index) in contents" :key="index" class="item-content" v-if="true">
            <div class="content-top">
                <div class="img-name-identity" @click="queryBtn(index)">
                    <div class="img-box">
                        <div class="img"><el-avatar shape="square" :size="30" :src="content.hr_img" style="vertical-align: middle; border-radius: 10px" /></div>
                    </div>
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
                        <div class="salary">{{ content.salary }}</div>
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
import { ElMessage, ElMessageBox } from "element-plus";
import { recruitApi } from "@/api/recruit";
import { useRouter } from "vue-router";
import { useUserStore } from "@/store/userStore";
import { useRecruitStore } from "@/store/recruitStore";
const router = useRouter();
const userStore = useUserStore();
const recruitStore = useRecruitStore();

/* ------------------------------------- 查询API -------------------------------------- */
onMounted(async () => {
    // 招聘信息
    const respRecruit = await recruitApi.queryAll();
    Object.assign(recruitViews, respRecruit.data);
});

/*----------------------------------------- 职位管理 ------------------------------------*/
let recruitViews = reactive([]);

const contents = computed(() => {
    return recruitViews.map((item) => ({
        hr_img: item.hrvo.profileImgUrl, // hr头像
        hr_name: item.hrvo.name, // hr名字
        hr_identity: item.hrvo.identity, // hr身份
        title: item.recruitVO.title, // 招聘标题
        address: item.recruitVO.address, // 工作地点
        salary: item.recruitVO.salary, // 薪资
        requirement: item.recruitVO.requirement, // 工作要求
        education: convertEducation(item.recruitVO.education), // 学历要求
        company_img: item.companyVO.profileImgUrl, // 公司头像
        companyName: item.companyVO.companyName, // 公司名
        industryType: item.companyVO.industryType, // 行业分类
        people: item.companyVO.people, // 公司人数
    }));
});

// 转换学历要求
const convertEducation = (education) => {
    if (education == "0") {
        return "大专以上";
    }
    if (education == "1") {
        return "本科";
    }
    if (education == "2") {
        return "硕士";
    }
};

// 查看信息
const queryBtn = (index) => {
    // 职位信息
    recruitStore.setRecommendInfo(recruitViews[index]);
    router.push("/job");
};

// 审核通过
const pass = async (index) => {
    recruitViews[index].recruitVO.checkStatus = "1";
    const resp = await recruitApi.update(recruitViews[index].recruitVO);
    if (resp.code == 200) {
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};

// 驳回
const reject = async (index) => {
    recruitViews[index].recruitVO.checkStatus = "0";
    const resp = await recruitApi.update(recruitViews[index].recruitVO);
    if (resp.code == 200) {
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};

// 审核状态
const checkStatus = (index) => {
    return recruitViews[index].recruitVO.checkStatus;
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
