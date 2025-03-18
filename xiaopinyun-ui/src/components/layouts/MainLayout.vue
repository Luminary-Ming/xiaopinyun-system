<!-- src/layouts/MainLayout.vue -->
<template>
    <div class="main-layout" id="sectionId0">
        <Header />
        <div class="main-content">
            <router-view />
        </div>
        <Footer />
    </div>
    <div class="back-top" @click="scrollToSection('sectionId0')">
        <el-icon><CaretTop /></el-icon>
    </div>
</template>

<script setup>
import Header from "@/components/common/Header.vue";
import Footer from "@/components/common/Footer.vue";

// 返回顶部
const scrollToSection = (sectionId) => {
    const element = document.getElementById(sectionId);
    if (element) {
        element.scrollIntoView({
            behavior: "smooth",
            block: "start",
        });
    }
};
</script>

<style scoped>
.main-layout {
    width: 100%;
    height: 100%;
}

.main-content {
    width: 100%;
    height: auto;
    background: linear-gradient(to bottom, #e1f2f4, #f6f6f8);
}

/* 返回顶部 */
.back-top {
    width: 44px;
    height: 44px;
    padding: 10px 0;
    border-radius: 22px;
    box-shadow: 0 4px 20px 0 rgba(0, 0, 0, 0.06);
    box-sizing: border-box;
    position: fixed;
    background-color: #fff;
    z-index: 3;
    bottom: 100px;
    right: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.back-top:hover {
    cursor: pointer;
}

.back-top:hover .el-icon {
    color: #00bebd;
}

.back-top .el-icon {
    font-size: 32px;
    color: #666;
}
</style>
