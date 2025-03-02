<template>
    <div class="home">
        <div class="banner">
            <el-carousel height="400px">
                <el-carousel-item v-for="item in banners" :key="item.id">
                    <div :style="{ background: item.color }" class="banner-item">
                        <h2>{{ item.title }}</h2>
                    </div>
                </el-carousel-item>
            </el-carousel>
        </div>
        <div class="job-list">
            <h2>热门职位</h2>
            <JobList :jobs="jobs" />
        </div>
    </div>
</template>

<script>
export default {
    name: "Home",
    data() {
        return {
            banners: [
                { id: 1, title: "招聘季，高薪职位等你来", color: "#409EFF" },
                { id: 2, title: "互联网大厂直招", color: "#67C23A" },
                { id: 3, title: "应届生专场", color: "#E6A23C" },
            ],
            jobs: [
                { id: 1, title: "前端开发工程师", location: "北京", salary: "15-25K" },
                { id: 2, title: "后端开发工程师", location: "上海", salary: "20-30K" },
                { id: 3, title: "UI设计师", location: "深圳", salary: "12-18K" },
            ],
        };
    },
};
</script>

<style scoped>
.home {
    padding: 20px;
}
.banner {
    margin-bottom: 20px;
}
.banner-item {
    height: 400px;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #fff;
}
.job-list {
    max-width: 1200px;
    margin: 0 auto;
}
</style>
