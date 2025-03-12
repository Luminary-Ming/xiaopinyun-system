<!-- src/layouts/MainLayout.vue -->
<template>
    <div class="main-layout">
        <Header />
        <div class="main-content">
            <router-view />
        </div>
        <Footer />
    </div>
</template>

<script setup>
import Header from "@/components/common/Header.vue";
import Footer from "@/components/common/Footer.vue";
</script>

<style scoped>
.main-layout {
    width: 100%;
    height: 100%;
}
.main-content {
    width: 100%;
    height: 100%;
    min-height: calc(100vh - 120px);
}
</style>
