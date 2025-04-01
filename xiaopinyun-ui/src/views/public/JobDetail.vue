<template>
    <div class="box" v-if="currentRecruit">
        <div class="job-banner">
            <div class="inner">
                <div class="job-primary">
                    <div class="job-status">{{ convertPublishStatus(currentRecruit.recruitVO.publishStatus) }}</div>
                    <div class="name">{{ currentRecruit.recruitVO.title }}</div>
                    <div class="salary">{{ currentRecruit.recruitVO.salary }}</div>
                    <div class="icon">
                        <div class="address">{{ convertAddress(currentRecruit.recruitVO.address) }}</div>
                        <div class="requirement">{{ currentRecruit.recruitVO.requirement }}</div>
                        <div class="education">{{ convertEducation(currentRecruit.recruitVO.education) }}</div>
                    </div>
                    <div class="job-op">
                        <div class="btn btn-interest" @click="collect()">感兴趣</div>
                        <div class="btn btn-submit" @click="submitResume()">立即投递</div>
                        <div class="btn btn-submit up" @click="up()">上一个</div>
                        <div class="btn btn-submit" @click="down()">下一个</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="job-box">
            <div>
                <div class="job-detail">
                    <div class="info">职位描述</div>
                    <ul class="benefit">
                        <li>Java</li>
                        <li>SpringBoot</li>
                        <li>StringCloud</li>
                        <li>Mysql</li>
                        <li>五险一金</li>
                        <li>定期体检</li>
                        <li>年终奖</li>
                        <li>带薪年假</li>
                        <li>节日福利</li>
                    </ul>
                    <div class="content">{{ currentRecruit.recruitVO.jobInformation }}</div>
                    <div class="hr">
                        <div class="img">
                            <el-avatar shape="square" :size="60" :src="currentRecruit.hrvo.profileImgUrl" style="vertical-align: middle; border-radius: 100%" />
                        </div>
                        <div class="name-contact">
                            <div>
                                <div class="name-status">
                                    <div class="name">{{ currentRecruit.hrvo.name }}</div>
                                    <div class="status">{{ coverStatus(currentRecruit.hrvo.status) }}</div>
                                    <div class="yuan"></div>
                                </div>
                                <div class="identity">{{ currentRecruit.hrvo.identity }}</div>
                            </div>
                            <div class="contact">
                                <div>联系方式：</div>
                                <div class="tel">{{ currentRecruit.hrvo.telephone }}</div>
                                <div>{{ currentRecruit.hrvo.email }}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="job-detail-company">
                    <div class="title address-title">工作地址</div>
                    <div class="address">{{ currentRecruit.recruitVO.address }}</div>
                    <div class="company-info-title">工商信息</div>
                    <div class="company-info">
                        <div class="item">
                            <div class="title fullname-title">公司全称：</div>
                            <div class="fullname">{{ currentRecruit.companyVO.companyFullName }}</div>
                        </div>
                        <div class="item">
                            <div class="title legal-title">法定代表人：</div>
                            <div class="legal">{{ currentRecruit.companyVO.legalPerson }}</div>
                        </div>
                        <div class="item">
                            <div class="title capital-title">注册资本：</div>
                            <div class="capital">{{ currentRecruit.companyVO.capital }}万人民币</div>
                        </div class="item">
                        <div>
                            <div class="title found-title">成立日期：</div>
                            <div class="found-date">{{ currentRecruit.companyVO.foundDate }}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div v-else class="loading">
        <el-empty description="加载中..." />
    </div>
</template>

<script setup>
import { ref, reactive, onMounted, computed } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { recruitApi } from "@/api/recruit";
import { jobCollectApi } from "@/api/jobCollect";
import { resumeSubmitApi } from "@/api/resumeSubmit";
import { useUserStore } from '@/store/userStore';
import { useRecruitStore } from '@/store/recruitStore';
const userStore = useUserStore();
const recruitStore = useRecruitStore();

const recruitViews = ref([]);
let currentIndex = ref(0);

// 获取推荐岗位数据
const recommendInfo = computed(() => recruitStore.getRecommendInfo);

// 使用计算属性获取当前显示的招聘信息
const currentRecruit = computed(() => {
    // 如果有推荐信息，优先显示推荐信息
    if (recommendInfo.value) {
        return recommendInfo.value;
    }
    // 否则显示列表中的职位
    return recruitViews.value[currentIndex.value];
});

// 上一个
const up = () => {
    // 如果是第一个，则跳到最后一个
    if (currentIndex.value <= 0) {
        currentIndex.value = recruitViews.value.length - 1;
    } else {
        currentIndex.value--;
    }
    // 更新当前显示的职位
    recruitStore.setRecommendInfo({
        recruitVO: recruitViews.value[currentIndex.value].recruitVO,
        hrvo: recruitViews.value[currentIndex.value].hrvo,
        companyVO: recruitViews.value[currentIndex.value].companyVO
    });
};

// 下一个
const down = () => {
    // 如果是最后一个，则跳到第一个
    if (currentIndex.value >= recruitViews.value.length - 1) {
        currentIndex.value = 0;
    } else {
        currentIndex.value++;
    }
    // 更新当前显示的职位
    recruitStore.setRecommendInfo({
        recruitVO: recruitViews.value[currentIndex.value].recruitVO,
        hrvo: recruitViews.value[currentIndex.value].hrvo,
        companyVO: recruitViews.value[currentIndex.value].companyVO
    });
};

// 收藏
const collect = async () => {
    const recommendInfo = recruitStore.getRecommendInfo;
    let jobCollectVO = {
        pkApplicant: userStore.pkApplicant,
        pkRecruit: recommendInfo.recruitVO.id,
    }
    const resp = await jobCollectApi.insert(jobCollectVO);
    if (resp.code == 200) {
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    } 
}

// 投递
const submitResume = async () => { 
    const recommendInfo = recruitStore.getRecommendInfo;
    const resp = await resumeSubmitApi.submit({
        pkRecruit: recommendInfo.recruitVO.id,
        pkApplicant: userStore.pkApplicant,
    })
    if (resp.code == 200) {
        ElMessage.success("投递成功！");
    } else {
        ElMessage.error(resp.message);
    } 
}



/* ------------------------------------- 查询API -------------------------------------- */
onMounted(async () => {
    // 职位
    const respRecruit = await recruitApi.queryAll();
    recruitViews.value = respRecruit.data
});

// 截取城市
const convertAddress = (address) => {
    if (!address) return '';
    return address.substring(0, 2);  // 截取前两个字符
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

// 转换发布状态
const convertPublishStatus = (publishStatus) => {
    if (publishStatus == "0") {
        return "停止招聘";
    }
    if (publishStatus == "1") {
        return "招聘中";
    }
}

// 转换状态
const coverStatus = (status) => {
    switch (status) {
        case "0":
            return "在线";
        case "1":
            return "昨日活跃";
        case "2":
            return "3天内活跃";
        case "3":
            return "7天内活跃";
        case "4":
            return "1月内活跃";
    }
};

</script>

<style scoped>
.box {
    width: 100%;
    height: 100%;
    background: linear-gradient(to bottom, #e1f2f4, #f6f6f8);
}

.job-banner {
    background: linear-gradient(90deg, #3b526a 0, #345a6d 100%);
    padding: 18px 0 30px 0;
}

.job-banner .inner {
    width: 1184px;
    margin: 0 auto;
}

.job-banner .inner .job-primary {
    border: none;
    height: auto;
    padding: 0;
    margin: 0;
}

.job-primary .job-status {
    top: 0;
    left: 0;
    z-index: 1;
    color: #fff;
    height: 22px;
    line-height: 22px;
}

.job-primary .name {
    display: inline-block;
    padding: 0;
    line-height: 41px;
    height: 41px;
    font-size: 30px;
    color: #fff;
    font-weight: 400;
    margin-top: 15px;
}

.job-primary .salary {
    display: inline-block;
    font-size: 34px;
    color: #f26d49;
    line-height: 41px;
    height: auto;
    font-weight: 400;
    position: relative;
    margin-left: 30px;
}

.job-primary .icon {
    display: flex;
    font-size: 14px;
    color: #fff;
    line-height: 20px;
    height: 20px;
    gap: 20px;
    margin-top: 20px;
}

.job-primary .icon div::before {
    content: " ";
    display: inline-block;
    width: 16px;
    height: 16px;
    margin-right: 6px;
    vertical-align: middle;
    position: relative;
    top: -1px;
    background: url(/src/assets/images/icon-img/elf2.png) 0 0 / 16px auto no-repeat;
}

.job-primary .icon .address::before {}

.job-primary .icon .requirement::before {
    background-position: 0 -32px;
}

.job-primary .icon .education::before {
    background-position: 0 -48px;
}

.job-primary .job-op {
    display: flex;
    gap: 20px;
    margin-top: 20px;
}

.job-primary .job-op .btn {
    width: 150px;
    border-radius: 12px;
    font-size: 18px;
    line-height: 43px;
    height: 45px;
    transition: all 0.2s linear;
    box-sizing: border-box;
    text-align: center;
    border: 1px solid #00bebd;
    cursor: pointer;
}

.job-primary .job-op .btn-interest {
    color: #00d1d0;
    background-color: transparent;
}

.job-primary .job-op .btn-interest::before {
    content: " ";
    position: relative;
    top: -2px;
    display: inline-block;
    margin-right: 6px;
    width: 24px;
    height: 24px;
    vertical-align: middle;
    background: url(/src/assets/images/icon-img/elf3.png);
    background-size: 100%;
}

.job-primary .job-op .btn-interest:hover::before {
    background-position: 0 -24px;
}

.job-primary .job-op .btn-interest:hover {
    color: #00e6e4;
    background: rgba(0, 190, 189, 0.1);
    border: 1px solid #00e6e4;
}

.job-primary .job-op .btn-submit {
    background: #00bebd;
    color: #fff;
}

.job-primary .job-op .up {
    margin-left: 550px;
}


.job-primary .job-op .btn-submit:hover {
    color: #00ffff;
    border: 1px solid #00ffff;
    color: #fff;
}

/* ------------------------ 职位描述 -------------------------- */
.job-box {
    margin-top: 16px;
    display: flex;
    justify-content: center;
}

.job-box .job-detail {
    box-sizing: border-box;
    width: 1200px;
    background-color: #fff;
    padding: 20px 30px;
    border-radius: 20px;
}

.job-detail .info {
    font-size: 26px;
    font-weight: 600;
    color: #222;
    line-height: 25px;
}

.job-detail .benefit {
    overflow: hidden;
    margin-top: -8px;
    margin-top: 15px;
    margin-bottom: 15px;
}

.job-detail .benefit li {
    margin-top: 8px;
    margin-right: 20px;
    font-size: 16px;
    color: #666;
    line-height: 20px;
    float: left;
    padding: 4px 12px;
    white-space: nowrap;
    background: #f8f8f8;
    border-radius: 4px;
}

.job-detail .content {
    display: inline;
    white-space: pre-wrap;
    word-break: break-all;
    margin-top: 20px;
    margin-bottom: 20px;
    line-height: 32px;
    color: #333;
    font-size: 16px;
    text-align: justify;
    letter-spacing: 0;
}

.job-detail .hr {
    display: flex;
    border-top: 1px solid #ededed;
    padding-top: 20px;
    margin-top: 20px;
    gap: 20px;
}

.hr .name-status {
    display: flex;
    gap: 10px;
}

.hr .name {
    color: #222;
    font-size: 20px;
}

.hr .status {
    color: #222;
    font-size: 13px;
    padding-top: 3px;
    color: #666;
}

.hr .yuan {
    position: relative;
    display: inline-block;
    width: 10px;
    height: 10px;
    border-radius: 100%;
    background-color: rgb(5, 202, 57);
    left: -5px;
    top: 5px;

}

.hr .identity {
    margin-top: 15px;
    color: #666;
    font-size: 15px;
}

.hr .name-contact {
    display: flex;
    width: 100%;
    justify-content: space-between;
}

.hr .contact {
    color: #666;
    font-size: 15px;
}

.contact .tel {
    margin-top: 5px;
    margin-bottom: 5px;
}

.job-box .job-detail-company {
    box-sizing: border-box;
    width: 1200px;
    background-color: #fff;
    padding: 20px 30px;
    border-radius: 20px;
    margin-top: 20px;
    margin-bottom: 150px;
}

.job-detail-company .address-title {
    font-size: 26px;
    font-weight: 600;
    color: #222;
    line-height: 25px;
}

.job-detail-company .address {
    font-size: 16px;
    color: #333;
    line-height: 22px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    padding: 12px 20px 12px 46px;
    background: url(/src/assets/images/icon-img/address-icon.png) 20px 14px / 18px auto no-repeat;
    margin-top: 20px;
    margin-bottom: 20px;
}

.job-detail-company .company-info-title {
    font-size: 26px;
    font-weight: 600;
    color: #222;
    line-height: 25px;
}

.job-detail-company .company-info {
    margin-top: 20px;
    background: #f8f8f8;
    border-radius: 12px;
    padding: 20px 40px 20px 20px;
    display: flex;
    flex-wrap: wrap;
}

.company-info .item {
    width: 50%;
}

.company-info .title {
    color: #999;
    font-size: 14px;
}

.company-info .fullname {
    color: #414a60;
    font-size: 14px;
    margin-top: 10px;
    margin-bottom: 10px;
}

.company-info .legal {
    color: #414a60;
    font-size: 14px;
    margin-top: 10px;
    margin-bottom: 10px;
}

.company-info .capital {
    color: #414a60;
    font-size: 14px;
    margin-top: 10px;
}

.company-info .found-date {
    color: #414a60;
    font-size: 14px;
    margin-top: 10px;
}
</style>
