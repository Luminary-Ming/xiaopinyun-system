<!-- 个人中心公共出口 -->
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
                        <div class="name">{{ applicant.name }}</div>
                        <div class="info">{{ applicant.age }} | {{ applicant.identity }} | {{ applicant.qualification }}</div>
                        <div class="status">
                            <el-form :model="applicant" style="max-width: 150px">
                                <el-form-item>
                                    <el-select v-model="applicant.status" placeholder="选择求职状态">
                                        <el-option label="离校-随时到岗" value="0" />
                                        <el-option label="在校-月内到岗" value="1" />
                                        <el-option label="在校-看看机会" value="2" />
                                        <el-option label="在校-暂不考虑" value="3" />
                                    </el-select>
                                </el-form-item>
                            </el-form>
                        </div>
                    </div>
                    <div class="resume-content">
                        <div><span>期望：后端开发</span> <span class="salary">5-10K</span></div>
                        <div><span>河北经贸大学经济管理学院 · 计算机科学与技术</span><span class="time">2021-2025</span></div>
                        <div><span>用友网络科技股份有限公司 · Java</span><span class="time">2024.11-2025.03</span></div>
                    </div>
                    <div class="edit-resume">
                        <router-link to="/resume">
                            <el-button>
                                我的简历<el-icon class="el-icon--right"><ArrowRight /></el-icon>
                            </el-button>
                        </router-link>
                    </div>
                </div>
                <div class="job-menu">
                    <div :class="{ active: activeMenu === 'interest' }" @click="handleMenuClick('interest')">感兴趣&nbsp;<span>323</span></div>
                    <div :class="{ active: activeMenu === 'submit' }" @click="handleMenuClick('submit')">已投递&nbsp;<span>29</span></div>
                    <div :class="{ active: activeMenu === 'interview' }" @click="handleMenuClick('interview')">面试&nbsp;<span>3</span></div>
                </div>
                <div class="item-content"></div>
            </div>
            <div class="profile-file">
                <el-col :span="12">
                    <el-menu active-text-color="#000" background-color="#fff" class="el-menu-vertical-demo_resume-file" default-active="2" text-color="#000" @open="handleOpen" @close="handleClose">
                        <div class="mb-2">
                            <h3>
                                附件管理<el-icon><Paperclip /></el-icon>
                            </h3>
                            <el-icon style="font-size: 20px"><Plus /></el-icon>
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
let applicant = reactive({
    name: "张金龙",
    age: "22岁",
    identity: "25年应届生",
    qualification: "本科",
    status: "0",
});

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
.resume-content .salary {
    font-size: 16px;
    color: #fe574a;
    margin-left: 15px;
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

.profile-info .item-content {
    width: 100%;
    height: 150px;
    background-color: #fff;
    margin-top: 15px;
    border-radius: 15px;
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
