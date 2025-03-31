<template>
    <div class="school-box" id="sectionId0">
        <div class="container">
            <div class="search-layout">
                <div class="search-box">
                    <div class="search-box-head">
                        <div :class="{ active: activeMenu == 'xiaozhao' }" @click="handleMenuClick('xiaozhao')">校招</div>
                        <div :class="{ active: activeMenu == 'shixi' }" @click="handleMenuClick('shixi')">实习</div>
                    </div>
                    <div class="search-box-body">
                        <div><input class="search-input" type="text" placeholder="搜索职位、公司" /></div>
                        <div class="search-btn">搜索</div>
                    </div>
                </div>
            </div>
            <div class="lunbo-box">
                <el-carousel height="380px" :interval="3000" arrow="always">
                    <el-carousel-item v-for="(item, index) in carouselImages" :key="index">
                        <img :src="item.url" :alt="item.title" class="carousel-image" />
                    </el-carousel-item>
                </el-carousel>
            </div>
            <div class="job-box">
                <div class="title">{{ title }}</div>
                <div class="job-list">
                    <div class="job-card" v-for="(item, index) in jobCards" :key="index" @click="queryBtn(index)">
                        <div class="job-info-box">
                            <div class="title-salary">
                                <div class="job-title">{{ item.jobTitle }}</div>
                                <div class="salary">{{ item.salary }}</div>
                            </div>
                            <div class="address-requirement-education">
                                <div class="address">{{ item.address }}</div>
                                <div class="requirement">{{ item.requirement }}</div>
                                <div class="education">{{ item.education }}</div>
                            </div>
                        </div>
                        <div class="company-info">
                            <div class="img-name">
                                <div class="company-img"><el-avatar shape="square" :size="24" :src="item.companyImg" style="vertical-align: middle" /></div>
                                <div class="company-name">{{ item.companyName }}</div>
                            </div>
                            <div class="industry-type">{{ item.industryType }}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, reactive, onMounted, computed } from "vue";
import { Document, Menu as IconMenu, Location, Setting } from "@element-plus/icons-vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { recruitApi } from "@/api/recruit";
import { useRouter } from "vue-router";
import { useRecruitStore } from "@/store/recruitStore";
const router = useRouter();
const recruitStore = useRecruitStore();
/* ------------------------------------- 查询API -------------------------------------- */
onMounted(async () => {
    // 职位
    const respRecruit = await recruitApi.queryRecommend();
    const recruits = respRecruit.data;
    Object.assign(recommendViews, recruits);
});

/* ------------------------------------- 推荐职位 -------------------------------------- */
let recommendViews = reactive([]);

// 职位卡片
let jobCards = computed(() => {
    return recommendViews.map((recommendJob) => ({
        jobTitle: recommendJob.recruitVO.title, // 标题
        salary: recommendJob.recruitVO.salary, // 薪资
        address: convertAddress(recommendJob.recruitVO.address), // 地点
        requirement: recommendJob.recruitVO.requirement, // 工作要求
        education: convertEducation(recommendJob.recruitVO.education), // 学历
        companyImg: recommendJob.companyVO.profileImgUrl, // 公司头像
        companyName: recommendJob.companyVO.companyName, // 公司名称
        industryType: recommendJob.companyVO.industryType, // 行业分类
    }));
});

// 截取城市
const convertAddress = (address) => {
    if (!address) return "";
    return address.substring(0, 2); // 截取前两个字符
};

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

// 查看职位
const queryBtn = (index) => {
    router.push("/job");
    recruitStore.setRecommendInfo(recommendViews[index]);
};

// 选择校招、实习按钮
let activeMenu = ref("xiaozhao");
let title = ref("热门校招");
let handleMenuClick = function (menu) {
    activeMenu.value = menu;
    if (menu == "shixi") {
        title.value = "热门实习";
    } else {
        title.value = "热门校招";
    }
};

// 轮播图数据
const carouselImages = ref([
    {
        url: "src/assets/images/lunbo-img/lunbo2.jpg",
        title: "图片2",
    },
    {
        url: "src/assets/images/lunbo-img/lunbo3.jpg",
        title: "图片3",
    },
]);
</script>

<style scoped>
/* 盒子 */
.school-box {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
}

.school-box .container {
    height: 100%;
}

/* ------------------------------------- 搜索框 ------------------------------------- */
.container .search-layout {
    width: 100%;
    height: 180px;
    box-sizing: border-box;
    padding: 36px 0 38px;
    display: flex;
    justify-content: center;
}

.search-layout .search-box {
    width: 900px;
    height: 100px;
    box-sizing: border-box;
}

.search-box .search-box-head {
    display: flex;
    background-color: #fff;
    width: 200px;
    height: auto;
    border-radius: 15px 15px 0 0;
}

.search-box .search-box-head div {
    width: 100px;
    height: 48px;
    box-sizing: border-box;
    color: #666;
    background-color: #fff;
    padding: 8px 30px 18px;
    font-size: 16px;
    font-weight: 700;
    border-radius: 12px 12px 0 0;
    text-align: center;
}

.search-box .search-box-head div:hover {
    cursor: pointer;
    color: #00a6a7;
}

.search-box .search-box-head div.active {
    color: #fff;
    background-color: #00a6a7;
    border-radius: 12px 12px 0 0;
}

.search-box .search-box-body {
    position: relative;
    top: -10px;
    width: 900px;
    display: flex;
    height: 55px;
    background: #00bebd;
    border-radius: 12px;
    border: 2px solid #00bebd;
    box-shadow: 0 10px 30px 0 hsla(0, 0%, 60%, 0.2);
}

.search-box .search-box-body .search-input {
    width: 780px;
    box-sizing: border-box;
    color: #222;
    height: 54px;
    border: none;
    box-shadow: none;
    outline: none;
    font-size: 16px;
    font-weight: 400;
    line-height: 22px;
    padding: 14px 18px;
    border-radius: 12px;
}

.search-box .search-box-body .search-btn {
    width: 100%;
    display: block;
    font-weight: 500;
    color: #fff;
    line-height: 28px;
    width: 118px;
    font-size: 20px;
    text-align: center;
    padding: 11px 0;
}

/* ------------------------------------- 轮播图 ------------------------------------- */
.container .lunbo-box {
    width: 1200px;
    height: 380px;
    background-color: #666;
    border-radius: 15px;
}

.carousel-image {
    border-radius: 15px;
}

.el-carousel {
    border-radius: 15px;
}
/* ------------------------------------- 推荐职位 ------------------------------------- */
.container .job-box {
    width: 1200px;
    height: 100%;
    margin-top: 40px;
    margin-bottom: 200px;
}

.job-box .title {
    font-size: 28px;
    font-weight: 600;
    color: #222;
    line-height: 40px;
}

.job-box .job-list {
    width: 1200px;
    height: 100%;
    margin-top: 20px;
    display: flex;
    flex-wrap: wrap;
    gap: 24px;
}

.job-list .job-card {
    width: 384px;
    height: 136px;
    box-sizing: border-box;
    background: #fff;
    border-radius: 12px;
    transition: all 0.2s linear;
}

.job-list .job-card:hover {
    position: relative;
    box-shadow: 0 16px 40px 0 rgba(138, 149, 158, 0.2);
    z-index: 1;
    cursor: pointer;
}

.job-card .job-info-box {
    box-sizing: border-box;
    padding: 16px 20px;
}

.job-info-box .title-salary {
    display: flex;
    justify-content: space-between;
}

.job-info-box .title-salary .job-title {
    font-size: 16px;
    font-weight: 500;
    line-height: 22px;
    color: #222;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.job-info-box .title-salary .job-title:hover {
    color: #00a6a7;
}

.job-info-box .title-salary .salary {
    font-size: 20px;
    font-weight: 500;
    color: #fe574a;
    line-height: 21px;
}

.job-info-box .address-requirement-education {
    display: flex;
    margin: 12px -4px 0px;
}

.job-info-box .address {
    float: left;
    margin-left: 4px;
    margin-right: 4px;
    background: #f8f8f8;
    border-radius: 4px;
    font-size: 13px;
    color: #666;
    line-height: 18px;
    padding: 2px 8px;
}

.job-info-box .requirement {
    float: left;
    margin-left: 4px;
    margin-right: 4px;
    background: #f8f8f8;
    border-radius: 4px;
    font-size: 13px;
    color: #666;
    line-height: 18px;
    padding: 2px 8px;
}

.job-info-box .education {
    float: left;
    margin-left: 4px;
    margin-right: 4px;
    background: #f8f8f8;
    border-radius: 4px;
    font-size: 13px;
    color: #666;
    line-height: 18px;
    padding: 2px 8px;
}

.job-card .company-info {
    box-sizing: border-box;
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 48px;
    padding: 0 20px;
    background: linear-gradient(90deg, #f5fcfc, #fcfbfa);
    border-radius: 0 0 12px 12px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
.company-info .img-name {
    display: flex;
    color: #666;
    font-size: 13px;
    justify-content: space-between;
    align-items: center;
}

.company-info .img-name .company-name {
    margin-left: 10px;
}

.company-info .industry-type {
    color: #666;
    font-size: 13px;
}
</style>
