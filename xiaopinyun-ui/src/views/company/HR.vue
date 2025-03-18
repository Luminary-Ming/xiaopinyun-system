<!-- HR 个人中心 -->
<template>
    <div class="profile-box">
        <div class="profile-container">
            <div class="profile-info">
                <div class="userinfo-banner">
                    <div class="head-img-box">
                        <div class="head-img">
                            <el-avatar shape="square" :size="60" :src="squareUrl" style="vertical-align: middle; border-radius: 10px" />
                        </div>
                    </div>
                    <div class="profile-content">
                        <div class="name">{{ hr.name }}</div>
                        <div class="info">{{ hr.sex }} | {{ hr.identity }} | {{ hr.qualification }}</div>
                        <div class="status">
                            <el-form :model="hr" style="max-width: 150px">
                                <el-form-item>
                                    <el-select v-model="hr.status" placeholder="选择状态">
                                        <el-option label="在线" value="0" />
                                        <el-option label="隐身" value="1" />
                                    </el-select>
                                </el-form-item>
                            </el-form>
                        </div>
                    </div>
                    <div class="resume-content">
                        <div><span>所属公司：壹加科技</span></div>
                        <div><span>互联网</span></div>
                        <div><span class="benefit">五险一金、定期体检、年终奖、带薪年假、节日福利</span></div>
                    </div>
                    <div class="edit-resume">
                        <router-link to="/job">
                            <el-button>
                                发布职位<el-icon class="el-icon--right"><ArrowRight /></el-icon>
                            </el-button>
                        </router-link>
                    </div>
                </div>
                <div class="job-menu">
                    <div :class="{ active: activeMenu === 'interest' }" @click="handleMenuClick('interest')">
                        感兴趣&nbsp;<span>{{ contents.length }}</span>
                    </div>
                    <div :class="{ active: activeMenu === 'submit' }" @click="handleMenuClick('submit')">
                        已投递&nbsp;<span>{{ contents.length }}</span>
                    </div>
                    <div :class="{ active: activeMenu === 'interview' }" @click="handleMenuClick('interview')">
                        面试&nbsp;<span>{{ contents.length }}</span>
                    </div>
                </div>
                <div v-for="(content, index) in contents" :key="index" class="item-content">
                    <div class="content-top">
                        <div class="img-name-identity">
                            <div class="img-box">
                                <div class="img"><el-avatar shape="square" :size="30" :src="content.hr_img" style="vertical-align: middle; border-radius: 10px" /></div>
                            </div>
                            <div class="hr-name">{{ content.hr_name }}</div>
                            <div class="hr-identity">{{ content.hr_identity }}</div>
                        </div>
                        <div>
                            <el-button @click="sumbit()">立即沟通</el-button>
                            <el-button @click="cancel()">取消感兴趣</el-button>
                        </div>
                    </div>
                    <div class="content-body">
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
                    </div>
                </div>
            </div>
            <div class="profile-file">
                <el-col :span="12">
                    <el-menu active-text-color="#000" background-color="#fff" class="el-menu-vertical-demo_resume-file" default-active="2" text-color="#000" @open="handleOpen" @close="handleClose">
                        <div class="mb-2">
                            <h3>
                                附件管理<el-icon><Paperclip /></el-icon>
                            </h3>
                            <el-icon style="font-size: 20px; cursor: pointer"><Plus /></el-icon>
                        </div>

                        <div class="resume-wenjian">文件（3/3）</div>
                        <el-menu-item index="9">
                            <img class="pdf_img" src="/src/assets/images/icon-img/icon-pdf.png" />
                            <div class="resume-info">
                                <div class="resume-name">java工程师.pdf</div>
                                <div class="resume-font">115.2KB 更新于 2025-03-06 21:21</div>
                            </div>
                            <span class="iconfont icon-file-menu"></span>
                        </el-menu-item>

                        <el-menu-item index="10">
                            <img class="pdf_img" src="/src/assets/images/icon-img/icon-pdf.png" />
                            <div class="resume-info">
                                <div class="resume-name">实施工程师.pdf</div>
                                <div class="resume-font">115.2KB 更新于 2025-03-06 21:21</div>
                            </div>
                            <span class="iconfont icon-file-menu"></span>
                        </el-menu-item>
                        <el-menu-item index="11">
                            <img class="pdf_img" src="/src/assets/images/icon-img/icon-pdf.png" />
                            <div class="resume-info">
                                <div class="resume-name">运维工程师.pdf</div>
                                <div class="resume-font">115.2KB 更新于 2025-03-06 21:21</div>
                            </div>
                            <span class="iconfont icon-file-menu"></span>
                        </el-menu-item>
                    </el-menu>
                </el-col>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, reactive } from "vue";
import { Document, Menu as IconMenu, Location, Setting } from "@element-plus/icons-vue";
import { ElMessage } from "element-plus";

let squareUrl = ref("/src/assets/images/profile-img/default.png"); // 头像

// 个人信息
let hr = reactive({
    name: "徐晨",
    sex: "男",
    identity: "招聘专家",
    qualification: "2-3天内活跃",
    status: "0", // 0在线 1隐身
});

// 选项卡内容
let contents = reactive([
    {
        hr_img: "/src/assets/images/profile-img/default.png", // hr头像
        hr_name: "鲁米", // hr名字
        hr_identity: "求职者", // hr身份
        title: "Java工程师-2025届(J14894)", // 招聘标题
        address: "天津·滨海新区", // 工作地点
        salary: "6-8k", // 薪资
        requirement: "经验不限", // 工作要求
        education: "本科", // 学历要求
        company_img: "/src/assets/images/profile-img/default.png", // 公司头像
        companyName: "壹加科技", // 公司名
        industryType: "计算机服务", // 行业分类
        people: "22-99人", // 公司人数
    },
    {
        hr_img: "/src/assets/images/profile-img/default.png", // hr头像
        hr_name: "鲁米", // hr名字
        hr_identity: "求职者", // hr身份
        title: "Java工程师-2025届(J14894)", // 招聘标题
        address: "天津·滨海新区", // 工作地点
        salary: "6-8k", // 薪资
        requirement: "经验不限", // 工作要求
        education: "本科", // 学历要求
        company_img: "/src/assets/images/profile-img/default.png", // 公司头像
        companyName: "壹加科技", // 公司名
        industryType: "计算机服务", // 行业分类
        people: "22-99人", // 公司人数
    },
    {
        hr_img: "/src/assets/images/profile-img/default.png", // hr头像
        hr_name: "鲁米", // hr名字
        hr_identity: "求职者", // hr身份
        title: "Java工程师-2025届(J14894)", // 招聘标题
        address: "天津·滨海新区", // 工作地点
        salary: "6-8k", // 薪资
        requirement: "经验不限", // 工作要求
        education: "本科", // 学历要求
        company_img: "/src/assets/images/profile-img/default.png", // 公司头像
        companyName: "壹加科技", // 公司名
        industryType: "计算机服务", // 行业分类
        people: "22-99人", // 公司人数
    },
    {
        hr_img: "/src/assets/images/profile-img/default.png", // hr头像
        hr_name: "鲁米", // hr名字
        hr_identity: "求职者", // hr身份
        title: "Java工程师-2025届(J14894)", // 招聘标题
        address: "天津·滨海新区", // 工作地点
        salary: "6-8k", // 薪资
        requirement: "经验不限", // 工作要求
        education: "本科", // 学历要求
        company_img: "/src/assets/images/profile-img/default.png", // 公司头像
        companyName: "壹加科技", // 公司名
        industryType: "计算机服务", // 行业分类
        people: "22-99人", // 公司人数
    },
]);

// 菜单选中后样式
let activeMenu = ref("interest"); // 默认选中"感兴趣"选项
let handleMenuClick = function (menu) {
    activeMenu.value = menu;
};
</script>

<style scoped>
.profile-box {
    width: 100%;
    height: 100%;
    background: linear-gradient(to bottom, #e1f2f4, #f6f6f8);
    display: flex;
    justify-content: center;
}

.profile-box .profile-container {
    box-sizing: border-box;
    width: 1200px;
    height: 100%;
    display: flex;
    margin-top: 20px;
}
/* --------------------- 个人信息 ---------------------------------- */
.profile-container .profile-info {
    width: 885px;
    height: 100%;
    margin-right: 15px;
    margin-bottom: 200px;
}

.profile-info .userinfo-banner {
    width: 100%;
    height: 150px;
    background-color: #fff;
    border-radius: 15px;
    display: flex;
}
.userinfo-banner .head-img-box {
    width: 100px;
    height: 100%;
    position: relative;
}
.head-img-box .head-img {
    display: block;
    position: absolute;
    top: 25px;
    left: 25px;
    z-index: 1;
}

.userinfo-banner .profile-content {
    width: 200px;
    padding-left: 25px;
}
.profile-content .name {
    font-size: 20px;
    color: #222;
    margin-top: 25px;
}
.profile-content .info {
    font-size: 14px;
    color: #666;
    margin-top: 15px;
}
.profile-content .status {
    margin-top: 15px;
}

.userinfo-banner .resume-content {
    width: 485px;
    line-height: 40px;
    font-size: 14px;
    color: #333;
    padding-top: 15px;
    padding-left: 35px;
}
.resume-content .benefit {
    font-size: 14px;

    border-radius: 4px;
    font-weight: 400;
    color: #666;
    margin-right: 10px;
    padding: 8px;
}

.resume-content .time {
    color: #999;
    margin-left: 15px;
}

.userinfo-banner .edit-resume {
    width: 100px;
}
.edit-resume .el-button {
    width: 100px;
    height: 36px;
    background: #f8f8f8;
    margin-top: 30px;
    border-radius: 18px 0 0 18px;
    border: 1px solid #d4d5d6;
    border-right-color: transparent;
    cursor: pointer;
    transition: all 0.2s linear;
}

.edit-resume .el-button:hover {
    background: #e5f8f8;
    border: 1px solid #00bebd;
    border-right-color: transparent;
    color: #00a6a7;
}

/* ----------- 菜单 --------- */
.profile-info .job-menu {
    box-sizing: border-box;
    width: 100%;
    height: 45px;
    background-color: #fff;
    margin-top: 15px;
    border-radius: 15px;
    display: flex;
    align-items: center;
    gap: 36px;
    padding-left: 36px;
}

.job-menu div {
    font-size: 15px;
    color: #333;
    position: relative;
}

.job-menu div:hover {
    cursor: pointer;
    color: #00a6a7;
}

.job-menu div:hover span {
    cursor: pointer;
    color: #00a6a7;
}

.job-menu div.active {
    cursor: pointer;
    color: #00a6a7;
}

.job-menu div.active span {
    cursor: pointer;
    color: #00a6a7;
}

.job-menu div.active::after {
    content: "";
    position: absolute;
    bottom: -10px;
    left: 0;
    width: 100%;
    height: 3px;
    background-color: #00a6a7;
}

.job-menu div span {
    font-size: 16px;
    color: #b3b3b3;
}

/* ----------- 选项卡内容 --------- */
.profile-info .item-content {
    box-sizing: border-box;
    width: 100%;
    height: 150px;
    background-color: #fff;
    margin-top: 15px;
    border-radius: 15px;
}

.profile-info .item-content:hover {
    cursor: pointer;
    box-shadow: 0 16px 40px 0 hsla(0, 0%, 60%, 0.3);
}

.profile-info .item-content:hover .el-button {
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

.item-content .el-button {
    opacity: 0;
    color: #fff;
    background-color: #00bebd;
    border-radius: 8px;
}

.item-content .el-button:hover {
    background-color: #00a6a7;
}

.item-content .content-body {
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
/* --------------------- 附件管理 ---------------------------------- */
.profile-container .profile-file {
    width: 300px;
    height: 280px;
    background-color: #fff;
    border-radius: 15px;
}

.profile-file .pdf_img {
    width: 44px;
    height: 44px;
    margin: 0px 8px 0px 0px;
}

.profile-file .resume-name {
    margin-bottom: -35px;
    color: #414a60;
}
.profile-file .resume-font {
    font-size: 12px;
    color: #8d92a1;
}
.profile-file .resume-wenjian {
    font-size: 13px;
    color: #414a60;
    margin-left: 20px;
    margin-top: 10px;
    margin-bottom: 5px;
}

.profile-file .mb-2 {
    display: flex;
    justify-content: space-between;
    height: 35px;
    background-color: #f6f6f8;
    border-radius: 15px 15px 0 0;
    padding-top: 18px;
    padding-left: 25px;
    padding-right: 30px;
}

.profile-file .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 285px;
    min-height: 400px;
    border-radius: 15px;
}
.profile-file .el-menu-vertical-demo_resume-file:not(.el-menu--collapse) {
    width: 300px;
    min-height: 280px;
    border-radius: 15px;
}

.profile-file .section-title {
    font-size: 16px;
    color: var(--primary-color);
    margin: 20px 0;
    padding-bottom: 8px;
    border-bottom: 2px solid #eee;
}

.profile-file .action-buttons {
    margin-top: 30px;
    text-align: center;
}
</style>
