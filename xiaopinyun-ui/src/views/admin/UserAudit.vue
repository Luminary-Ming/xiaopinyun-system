<template>
    <div class="common-layout">
        <el-container>
            <div class="aside-box">
                <div class="tianchong"></div>
                <el-aside>
                    <div class="logo">
                        <router-link to="/admin/home">校聘云后台</router-link>
                    </div>
                    <div class="url">xiaopinyun.com</div>
                    <el-menu :default-openeds="['4']" active-text-color="#00a7a7" background-color="#202329" class="el-menu-vertical-demo" default-active="1" text-color="#fff">
                        <el-menu-item index="1" @click="headTitle = '学生管理'">
                            <el-icon><User /></el-icon>
                            <span>学生管理</span>
                        </el-menu-item>
                        <el-menu-item index="2" @click="headTitle = '企业管理'">
                            <el-icon><OfficeBuilding /></el-icon>
                            <span>企业管理</span>
                        </el-menu-item>
                        <el-menu-item index="3" @click="headTitle = '职位管理'">
                            <el-icon><Suitcase /></el-icon>
                            <span>职位管理</span>
                        </el-menu-item>
                        <el-sub-menu index="4" @click="headTitle = '统计分析'">
                            <template #title>
                                <el-icon><Histogram /></el-icon><span>统计分析</span>
                            </template>
                            <el-menu-item index="4-1">学生注册统计</el-menu-item>
                            <el-menu-item index="4-2">企业注册统计</el-menu-item>
                            <el-menu-item index="4-3">就业率统计</el-menu-item>
                        </el-sub-menu>
                    </el-menu>
                </el-aside>
            </div>
            <el-container>
                <el-header>
                    <div class="head-title">{{ headTitle }}</div>
                    <div class="head-box">
                        <el-menu mode="horizontal" background-color=" #fff" text-color="#000" active-text-color="#00b4b3" :default-active="activeIndex" @select="handleSelect" class="menu-container">
                            <el-menu-item index="1">
                                <router-link to="/admin/home">
                                    {{ name }}
                                    <el-avatar shape="square" :size="35" :src="squareUrl" style="vertical-align: middle" />
                                </router-link>
                            </el-menu-item>
                            <el-sub-menu index="2" :popper-offset="20">
                                <template #title>退出</template>
                                <el-menu-item index="2-1" @click="loginout()">确定退出</el-menu-item>
                            </el-sub-menu>
                        </el-menu>
                    </div>
                </el-header>
                <el-main>
                    <ApplicantManage v-if="headTitle == '学生管理'" />
                    <CompanyManage v-if="headTitle == '企业管理'" />
                    <JobManage v-if="headTitle == '职位管理'" />
                    <Statistics v-if="headTitle == '统计分析'" />
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>
import { User } from "@element-plus/icons-vue";
import { ref } from "vue";
import ApplicantManage from "@/views/admin/ApplicantManage.vue";
import CompanyManage from "@/views/admin/CompanyManage.vue";
import JobManage from "@/views/admin/JobManage.vue";
import Statistics from "@/views/admin/Statistics.vue";
import { useUserStore } from "@/store/userStore";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
const router = useRouter();
const userStore = useUserStore();

let headTitle = ref("学生管理");

let squareUrl = ref(userStore.profileImg); // 头像
let name = ref(userStore.name); // 名字
let token = ref(userStore.token); // tokne

const loginout = async () => {
    userStore.logout();
    await router.push("/home");
    window.location.reload();
    ElMessage.success("退出成功");
};
</script>

<style scoped>
.common-layout {
    width: 100%;
    height: 100%;
    background: linear-gradient(to bottom, #e1f2f4, #f6f6f8);
}

.el-container {
    width: 100%;
    height: 100%;
}

.aside-box {
    display: flex;
}

.aside-box .tianchong {
    position: absolute;
    background-color: #202329;
    width: 25px;
    height: 100%;
}

.el-aside {
    box-sizing: border-box;
    width: 219px;
    height: 100%;
    background-color: #202329;
    overflow-x: hidden;
    position: relative;
    z-index: 1;
    left: 20px;
}

.el-aside .logo {
    margin-top: 25px;
    margin-bottom: 5px;
    margin-left: 20px;
}
.el-aside .logo a {
    font-size: 25px;
    font-weight: bold;
    color: #f3faff;
    text-decoration: none;
}

.el-aside .url {
    font-size: 14px;
    font-weight: bold;
    color: #7b808a;
    text-decoration: none;
    margin-left: 20px;
}

.el-aside .el-menu-vertical-demo {
    margin-top: 20px;
    width: 200px;
}

.el-aside .el-menu-vertical-demo .el-icon {
    margin-left: 0px;
}

.el-aside .el-menu-vertical-demo span {
    font-size: 15px;
}

.el-header {
    width: 100%;
    height: 65px;
    background-color: #fff;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-radius: 25px 0 0 0;
    position: relative;
    z-index: 2;
}

.el-header .head-title {
    font-size: 18px;
}

.el-header .menu-container {
    width: 280px;
}

.el-main {
    width: 100%;
    height: 100%;
    border-radius: 0 0 0 25px;
    position: relative;
    z-index: 3;
    background: linear-gradient(to bottom, #e1f2f4, #f6f6f8);
}

/* .el-header .block_touxiang {
    vertical-align: middle;
}

.el-header .container {
    max-width: 1200px;
    margin: 0 auto;
    display: flex;
    align-items: center;
    justify-content: space-between;
}
.el-header .menu-container {
    flex: 1;
    display: flex;
    justify-content: flex-end;
    max-width: 600px; /* 限制导航栏的最大宽度 */
/* }
.el-header .el-menu {
    border-bottom: none; /* 去掉默认的下边框 */
/*
}
.el-header .el-menu-item {
    padding: 0 15px; /* 调整菜单项的内边距 */
/*
}

.el-header .el-menu-item a {
    text-decoration: none;
    color: inherit;
}
.el-header .login-register {
    margin-left: auto; /* 将登录/注册按钮推到最右侧 */
/*}*/
</style>
