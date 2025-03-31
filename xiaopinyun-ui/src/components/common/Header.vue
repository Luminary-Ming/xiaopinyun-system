<template>
    <header class="header">
        <div class="container">
            <div class="logo">
                <router-link to="/">校聘云</router-link>
            </div>
            <el-menu mode="horizontal" background-color=" #202329" text-color="#fff" active-text-color="#00b4b3" :default-active="activeIndex" @select="handleSelect" class="menu-container">
                <el-menu-item index="1">
                    <router-link to="/home">首页</router-link>
                </el-menu-item>
                <el-menu-item index="2" v-if="isStudent()">
                    <router-link to="/job">职位</router-link>
                </el-menu-item>
                <!-- <el-menu-item index="3">
                    <router-link to="/job">职位管理</router-link>
                </el-menu-item> -->
                <!-- <el-menu-item index="4" v-if="isStudent()">
                    <router-link to="/company">公司</router-link>
                </el-menu-item> -->
                <el-menu-item index="5" v-if="isStudent()">
                    <router-link to="/resume">我的简历</router-link>
                </el-menu-item>
                <el-menu-item index="6" v-if="!token">
                    <el-icon><User /></el-icon>
                    <router-link to="/login">登录/注册</router-link>
                </el-menu-item>
                <el-menu-item index="7" v-else>
                    <router-link to="/profile">
                        {{ name }}
                        <el-avatar shape="square" :size="35" :src="squareUrl" style="vertical-align: middle" />
                    </router-link>
                </el-menu-item>
                <el-sub-menu index="8" :popper-offset="20" v-if="token">
                    <template #title>退出</template>
                    <el-menu-item index="7-1" @click="loginout()">确定退出</el-menu-item>
                </el-sub-menu>
            </el-menu>
        </div>
    </header>
</template>

<script setup>
import { User } from "@element-plus/icons-vue";
import { ref } from "vue";
import { useUserStore } from "@/store/userStore";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
const router = useRouter();
const userStore = useUserStore();

const isStudent = () => {
    const role = userStore.role;
    if (role == 0) {
        return true;
    }
    return false;
};

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
.block_touxiang {
    vertical-align: middle;
}
.header {
    background-color: #202329;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    padding: 5px 0;
}
.container {
    max-width: 1200px;
    margin: 0 auto;
    display: flex;
    align-items: center;
    justify-content: space-between;
}
.logo a {
    font-size: 30px;
    font-weight: bold;
    color: #00b4b3;
    text-decoration: none;
}
.menu-container {
    flex: 1;
    display: flex;
    justify-content: flex-end;
    max-width: 600px; /* 限制导航栏的最大宽度 */
}
.el-menu {
    border-bottom: none; /* 去掉默认的下边框 */
}
.el-menu-item {
    padding: 0 15px; /* 调整菜单项的内边距 */
}
.el-menu-item a {
    text-decoration: none;
    color: inherit;
}
.login-register {
    margin-left: auto; /* 将登录/注册按钮推到最右侧 */
}
</style>
