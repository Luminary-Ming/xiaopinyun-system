<!-- HR 个人中心 -->
<template>
    <div class="profile-box">
        <div class="profile-container">
            <div class="profile-info">
                <div class="userinfo-banner">
                    <div class="head-img-box">
                        <div class="head-img">
                            <el-upload :show-file-list="false" :action="uploadUrl" :headers="headers" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                                <el-avatar shape="square" :size="60" :src="ImgUrl" style="vertical-align: middle; border-radius: 10px" />
                            </el-upload>
                        </div>
                    </div>
                    <div class="profile-content">
                        <div class="name">{{ hrView.name }}</div>
                        <div class="info">
                            {{ hrView.sex == 0 ? "男" : "女" }} | {{ hrView.identity }} | {{ coverStatus(hrView.status) }}
                            <div class="yuan"></div>
                        </div>
                        <div class="status">
                            <el-form :model="hrView" style="max-width: 150px">
                                <el-button plain @click="dialogFormVisible = true">修改个人信息</el-button>
                            </el-form>
                        </div>
                    </div>
                    <div class="resume-content">
                        <div>
                            <span>所属公司：{{ companyView.companyFullName }}</span>
                        </div>
                        <div>
                            <span>联系方式：{{ hrView.telephone }}</span>
                        </div>
                        <div>
                            <span>电子邮件：{{ hrView.email }}</span>
                        </div>
                    </div>
                    <div class="edit-resume" @click="addRecruit()">
                        <el-button>
                            发布职位<el-icon class="el-icon--right"><ArrowRight /></el-icon>
                        </el-button>
                    </div>
                </div>
                <div class="job-menu">
                    <div :class="{ active: activeMenu === 'job' }" @click="handleMenuClick('job')">
                        职位管理&nbsp;<span>{{ recruitViews?.length || 0 }}</span>
                    </div>
                    <div :class="{ active: activeMenu === 'submit' }" @click="handleMenuClick('submit')">
                        投递管理&nbsp;<span>{{ submitViews?.length || 0 }}</span>
                    </div>
                    <div :class="{ active: activeMenu === 'interest' }" @click="handleMenuClick('interest')">
                        感兴趣&nbsp;<span>{{ interestViews?.length || 0 }}</span>
                    </div>
                </div>
                <div v-for="(content, index) in contents" :key="index" class="item-content" v-if="true">
                    <div class="content-top">
                        <div class="img-name-identity" @click="queryBtn(index)">
                            <div class="img-box" v-if="activeMenu == 'job'">
                                <div class="img"><el-avatar shape="square" :size="30" :src="content.hr_img" style="vertical-align: middle; border-radius: 10px" /></div>
                            </div>
                            <div class="hr-name">{{ content.hr_name }}</div>
                            <div class="hr-identity">{{ content.hr_identity }}</div>
                        </div>
                        <div v-if="activeMenu == 'job'">
                            <el-button @click="updateBtn(index)" class="update-btn">编辑职位</el-button>
                            <el-button @click="delBtn(index)" type="danger">删除职位</el-button>
                        </div>
                        <div v-if="activeMenu == 'submit'">
                            <el-button @click="reply(index)" class="update-btn">回复</el-button>
                            <el-button @click="addInterest(index)" color="#626aef">感兴趣</el-button>
                        </div>
                        <div v-if="activeMenu == 'interest'">
                            <el-button @click="reply(index)" class="update-btn">回复</el-button>
                            <el-button @click="removeInterest(index)" type="danger">取消感兴趣</el-button>
                        </div>
                    </div>
                    <div class="content-body" @click="queryBtn(index)">
                        <div class="recruit">
                            <div class="title-address">
                                <div class="title">{{ content.title }}</div>
                                <div class="address">【{{ content.address }}】</div>
                            </div>
                            <div class="salary-requirement-education">
                                <div class="salary" v-if="activeMenu == 'job'">{{ content.salary }}</div>
                                <div class="requirement" v-if="activeMenu != 'job'">{{ content.work }}</div>
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
                            <el-upload :show-file-list="false" :action="uploadUrl" :headers="headers" :on-success="handleFileSuccess" :before-upload="beforeFileUpload">
                                <el-icon style="font-size: 20px"><Plus /></el-icon>
                            </el-upload>
                        </div>

                        <div class="resume-wenjian">文件（{{ attachmentView.length }}/3）</div>
                        <el-menu-item index="9" v-for="(attachment, index) in attachmentView" :key="index" v-if="attachmentView.length">
                            <img class="pdf_img" src="/src/assets/images/icon-img/icon-pdf.png" @click="previewFile(index)" />
                            <div class="resume-info" :title="attachment.name" @click="previewFile(index)">
                                <div class="resume-name">{{ attachment.name }}</div>
                                <div class="resume-font">{{ attachment.size }}kb 更新于 {{ attachment.ts }}</div>
                            </div>
                            <el-popconfirm class="box-item" :icon="InfoFilled" icon-color="#626AEF" title="对此附件的操作" placement="right">
                                <template #reference>
                                    <span class="iconfont icon-file-menu"> </span>
                                </template>
                                <template #actions="{ confirm, cancel }">
                                    <el-button @click="download(index)" style="width: 43px; height: 24px; border-radius: 4px">下载</el-button>
                                    <el-button @click="confirmRemove(index)" style="width: 43px; height: 24px; border-radius: 4px" type="danger">删除</el-button>
                                </template>
                            </el-popconfirm>
                        </el-menu-item>
                    </el-menu>
                </el-col>
            </div>
        </div>
    </div>
    <!-- hr个人信息 -->
    <el-dialog v-model="dialogFormVisible" title="个人信息" width="500">
        <el-form :model="hrView">
            <el-form-item label="姓名" :label-width="formLabelWidth">
                <el-input v-model="hrView.name" autocomplete="off" placeholder="输入姓名" />
            </el-form-item>
            <el-form-item label="性别" :label-width="formLabelWidth">
                <el-select v-model="hrView.sex" placeholder="选择性别">
                    <el-option label="男" value="0" />
                    <el-option label="女" value="1" />
                </el-select>
            </el-form-item>
            <el-form-item label="身份" :label-width="formLabelWidth">
                <el-input v-model="hrView.identity" autocomplete="off" placeholder="输入身份" />
            </el-form-item>
            <el-form-item label="手机号" :label-width="formLabelWidth">
                <el-input v-model="hrView.telephone" autocomplete="off" placeholder="输入手机号" />
            </el-form-item>
            <el-form-item label="电子邮件" :label-width="formLabelWidth">
                <el-input v-model="hrView.email" autocomplete="off" placeholder="输入电子邮件" />
            </el-form-item>
        </el-form>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="submitHR()">OK</el-button>
            </div>
        </template>
    </el-dialog>
    <!-- 公司表单 -->
    <el-dialog v-model="dialogCompanyFormVisible" title="公司信息" width="800">
        <el-form :model="companyView" class="company-from">
            <el-form-item label="公司名称">
                <el-input v-model="companyView.companyName" autocomplete="off" placeholder="输入公司名称" />
            </el-form-item>
            <el-form-item label="行业分类">
                <el-input v-model="companyView.industryType" autocomplete="off" placeholder="例互联网、电子商务" />
            </el-form-item>
            <el-form-item label="工作时间">
                <el-input v-model="companyView.workingHours" autocomplete="off" placeholder="例8:30 - 17:30" />
            </el-form-item>
            <el-form-item label="休假情况">
                <el-select v-model="companyView.holiday" placeholder="选择休假情况">
                    <el-option label="双休" value="0" />
                    <el-option label="单休" value="1" />
                    <el-option label="无休" value="2" />
                </el-select>
            </el-form-item>
            <el-form-item label="加班情况">
                <el-select v-model="companyView.overtime" placeholder="选择休假情况">
                    <el-option label="弹性工作" value="0" />
                    <el-option label="偶尔加班" value="1" />
                    <el-option label="经常加班" value="2" />
                </el-select>
            </el-form-item>
            <el-form-item label="公司地址">
                <el-input v-model="companyView.companyAddress" autocomplete="off" placeholder="输入公司地址" />
            </el-form-item>
            <el-form-item label="公司全称">
                <el-input v-model="companyView.companyFullName" autocomplete="off" placeholder="输入公司全称" />
            </el-form-item>
            <el-form-item label="法定代表">
                <el-input v-model="companyView.legalPerson" autocomplete="off" placeholder="输入法定代表人" />
            </el-form-item>
            <el-form-item label="注册资本">
                <el-input v-model="companyView.capital" autocomplete="off" placeholder="单位万人民币" />
            </el-form-item>
            <el-form-item label="成立日期">
                <el-input v-model="companyView.foundDate" autocomplete="off" placeholder="例1998-12-15" />
            </el-form-item>
            <el-form-item label="公司人数">
                <el-input v-model="companyView.people" autocomplete="off" placeholder="例0-20人、500-999人" />
            </el-form-item>
            <el-form-item label="员工福利">
                <el-input v-model="companyView.benefit" autocomplete="off" placeholder="例五险一金、带薪年假" />
            </el-form-item>
            <el-form-item label="公司LOGO" class="logo">
                <el-upload :show-file-list="false" :action="uploadUrl" :headers="headers" :on-success="handleAvatarSuccessCompany" :before-upload="beforeAvatarUpload">
                    <el-avatar shape="square" :size="50" :src="companyView.profileImgUrl" style="vertical-align: middle; border-radius: 10px" />
                </el-upload>
            </el-form-item>
            <el-form-item></el-form-item>
            <el-form-item label="公司介绍" class="textarea">
                <el-input
                    v-model="companyView.introduce"
                    autocomplete="off"
                    type="textarea"
                    maxlength="3000"
                    rows="5"
                    spellcheck="false"
                    style="width: 700px"
                    placeholder="输入公司介绍"
                    show-word-limit
                />
            </el-form-item>
        </el-form>
        <template #footer>
            <div class="submit-button">
                <el-button @click="dialogCompanyFormVisible = false">取消</el-button>
                <el-button type="primary" @click="submitCompany()" class="submitCompany">OK</el-button>
            </div>
        </template>
    </el-dialog>
    <!-- 招聘信息表单 -->
    <el-dialog v-model="dialogRecruitFormVisible" title="招聘详情" width="500">
        <el-form :model="recruitView">
            <el-form-item label="招聘标题" :label-width="formLabelWidth">
                <el-input v-model="recruitView.title" autocomplete="off" placeholder="输入招聘标题" />
            </el-form-item>
            <el-form-item label="岗位薪资" :label-width="formLabelWidth">
                <el-input v-model="recruitView.salary" autocomplete="off" placeholder="输入岗位薪资" />
            </el-form-item>
            <el-form-item label="工作要求" :label-width="formLabelWidth">
                <el-input v-model="recruitView.requirement" autocomplete="off" placeholder="输入工作要求" />
            </el-form-item>
            <el-form-item label="学历要求" :label-width="formLabelWidth">
                <el-select v-model="recruitView.education" placeholder="选择学历要求">
                    <el-option label="大专以上" value="0" />
                    <el-option label="本科" value="1" />
                    <el-option label="硕士" value="2" />
                </el-select>
            </el-form-item>
            <el-form-item label="员工福利" :label-width="formLabelWidth">
                <el-input v-model="recruitView.benefit" autocomplete="off" placeholder="输入员工福利" />
            </el-form-item>
            <el-form-item label="职位描述" class="textarea">
                <el-input
                    v-model="recruitView.jobInformation"
                    autocomplete="off"
                    type="textarea"
                    maxlength="3000"
                    rows="5"
                    spellcheck="false"
                    style="width: 700px"
                    placeholder="输入职位描述"
                    show-word-limit
                />
            </el-form-item>
            <el-form-item label="工作地点" :label-width="formLabelWidth">
                <el-input v-model="recruitView.address" autocomplete="off" placeholder="输入工作地点" />
            </el-form-item>
            <el-form-item label="招聘状态" :label-width="formLabelWidth">
                <el-select v-model="recruitView.publishStatus" placeholder="选择招聘状态">
                    <el-option label="停止招聘" value="0" />
                    <el-option label="招聘中" value="1" />
                </el-select>
            </el-form-item>
        </el-form>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="dialogRecruitFormVisible = false">取消</el-button>
                <el-button type="primary" @click="submitRecruit()">OK</el-button>
            </div>
        </template>
    </el-dialog>
    <!-- hr回复 -->
    <el-dialog v-model="dialogReplyFormVisible" title="回复" width="500">
        <el-form :model="replyForm">
            <el-form-item label="筛选状态" :label-width="formLabelWidth">
                <el-select v-model="replyForm.status" placeholder="请选择筛选状态">
                    <el-option label="默认" value="0" />
                    <el-option label="退回" value="1" />
                    <el-option label="通过" value="2" />
                    <el-option label="感兴趣" value="3" />
                </el-select>
            </el-form-item>
            <el-form-item label="回复" :label-width="formLabelWidth">
                <el-input v-model="replyForm.reply" autocomplete="off" placeholder="输入回复内容..." />
            </el-form-item>
        </el-form>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="dialogReplyFormVisible = false">取消</el-button>
                <el-button type="primary" @click="confirmReply()">确定</el-button>
            </div>
        </template>
    </el-dialog>
</template>

<script setup>
import { ref, reactive, onMounted, computed, watchEffect } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { InfoFilled } from "@element-plus/icons-vue";
import { uploadApi } from "@/api/upload";
import { hrApi } from "@/api/hr";
import { companyApi } from "@/api/company";
import { recruitApi } from "@/api/recruit";
import { resumeSubmitApi } from "@/api/resumeSubmit";
import { useRouter } from "vue-router";
import { useUserStore } from "@/store/userStore";
import { useRecruitStore } from "@/store/recruitStore";
const router = useRouter();
const userStore = useUserStore();
const recruitStore = useRecruitStore();

const submitViews = ref([]);
const interestViews = ref([]);
/* ------------------------------------- 查询API -------------------------------------- */
onMounted(async () => {
    // hr信息
    const resphr = await hrApi.getHR(userStore.pkHr);
    Object.assign(hrView, resphr.data);

    // 公司信息
    const respCompany = await companyApi.getCompany(hrView.pkCompany);
    Object.assign(companyView, respCompany.data);
    recruitView.pkCompany = companyView.id;
    recruitView.benefit = companyView.benefit;

    // 招聘信息
    const respRecruit = await recruitApi.queryByPkHr(hrView.id);
    Object.assign(recruitViews, respRecruit.data);

    // 附件
    const respAttachment = await uploadApi.getHRAttachment(userStore.pkHr);
    if (respAttachment.code == 200) {
        Object.assign(attachmentView, respAttachment.data);
    }

    // 查看投递情况
    const respSubmitInfo = await resumeSubmitApi.querySubmitInfo(userStore.pkHr);
    submitViews.value = respSubmitInfo.data;

    // 查看感兴趣的简历
    const respInterest = await resumeSubmitApi.queryInterest(userStore.pkHr);
    interestViews.value = respInterest.data;
});

/*------------------------------------- 上传附件 -------------------------------------*/
// 计算属性获取headers
const headers = computed(() => ({
    Authorization: userStore.token,
}));
const uploadUrl = "http://127.0.0.1:9999/upload-server/upload";
let attachmentView = reactive([]);

let addAttachmentView = reactive({
    id: "",
    pkHr: userStore.pkHr,
    resumePDF: "",
    ts: "",
    name: "",
    size: "",
});

const handleFileSuccess = async (response, uploadFile) => {
    addAttachmentView.resumePDF = response.data;
    addAttachmentView.name = uploadFile.name;
    addAttachmentView.size = (uploadFile.size / 1024).toFixed(1); // 保留一位小数
    const resp = await uploadApi.addAttachment(addAttachmentView);
    if (resp.code == 200) {
        addAttachmentView.ts = resp.data.ts;
        attachmentView.push({ ...addAttachmentView, id: resp.data.id });
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};

const beforeFileUpload = (rawFile) => {
    if (rawFile.type !== "application/pdf") {
        ElMessage.error("不是 PDF 格式！");
        return false;
    } else if (rawFile.size / 1024 / 1024 > 5) {
        ElMessage.error("大小超过 5MB!");
        return false;
    }
    return true;
};

// 删除附件
const confirmRemove = async (index) => {
    const resp = await uploadApi.deleteAttachmentById(attachmentView[index].id);
    if (resp.code == 200) {
        const pdfUrl = attachmentView[index].resumePDF;
        const filename = pdfUrl.split("/filebucket/")[1];
        await uploadApi.deleteFile(filename);
        attachmentView.splice(index, 1);
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};

// 下载附件
const download = async (index) => {
    // 获取文件名
    const pdfUrl = attachmentView[index].resumePDF;
    const filename = pdfUrl.split("/filebucket/")[1];
    const resp = await uploadApi.downloadFile(filename);

    // 创建Blob对象
    const blob = new Blob([resp], { type: "application/pdf" });

    // 创建下载链接
    const url = URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = attachmentView[index].name; // 设置下载文件名

    // 触发点击下载
    document.body.appendChild(a);
    a.click();

    // 清理
    setTimeout(() => {
        document.body.removeChild(a);
        URL.revokeObjectURL(url);
    }, 100);
};

// 预览附件
const previewFile = async (index) => {
    const pdfUrl = attachmentView[index].resumePDF;
    const filename = pdfUrl.split("/filebucket/")[1];
    const resp = await uploadApi.previewFile(filename);
    if (resp.code == 200) {
        window.open(resp.data);
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};
/*----------------------------------------- 个人信息 -------------------------------------------------- -*/
let hrView = reactive({
    id: "",
    profileImgUrl: userStore.profileImg,
    name: "姓名",
    sex: "性别",
    pkCompany: "公司名",
    identity: "招聘身份",
    telephone: "请填写手机号",
    email: "请填写电子邮件",
    status: "0",
});
let ImgUrl = ref(hrView.profileImgUrl);

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

/*----------------------------------------- 公司信息 -------------------------------------------------- -*/
let companyView = reactive({
    id: "",
    profileImgUrl: "",
    companyName: "",
    people: "", // 公司人数 互联网、电子商务、社交网络、金融科技、游戏娱乐、物流配送、内容创作与管理
    industryType: "", // 行业分类
    workingHours: "", // 工作时间 例8:30 - 17:30
    holiday: "", // 休假情况 0双休、1单休、2无休
    overtime: "", // 加班情况 0弹性工作、1偶尔加班、2经常加班
    benefit: "", // 员工福利  例五险一金、定期体检、年终奖、带薪年假、节日福利
    companyAddress: "", // 公司地址
    introduce: "", // 公司介绍
    companyFullName: "", // 公司全称
    legalPerson: "", // 法定代表人
    capital: "", // 注册资本
    foundDate: "", // 成立日期
});

/*----------------------------------------- 职位管理 ------------------------------------*/
let recruitViews = reactive([]);
let recruitView = reactive({
    id: "",
    pkCompany: "",
    pkHr: userStore.pkHr,
    title: "", // 招聘标题 例Java工程师-2025届(J14894)
    salary: "", // 薪资 例9k-15k
    benefit: "", // 员工福利 例五险一金、定期体检、年终奖、带薪年假、节日福利
    address: "", //  工作地点
    requirement: "", // 工作要求 经验不限、5天/周、在校/应届
    education: "", // 学历要求 0大专以上、1本科、2硕士
    jobInformation: "", // 岗位详情
    publishStatus: "1", // 是否发布 0不发布（职位关闭）、1发布（招聘中）
});

let currentIndex = ref(0);

// 添加职位
const addRecruit = () => {
    dialogRecruitFormVisible = true;
    Object.assign(recruitView, {
        id: "",
        pkCompany: "",
        pkHr: userStore.pkHr,
        title: "",
        salary: "",
        benefit: "",
        address: "",
        requirement: "",
        education: "",
        jobInformation: "",
        publishStatus: "1",
    });
};
/*----------------------------------------- 表单信息 -------------------------------------------------- -*/
// 个人信息表单
const dialogFormVisible = ref(false);
const formLabelWidth = "100px";
// 公司信息表单
const dialogCompanyFormVisible = ref(false);
// 职位信息表单
const dialogRecruitFormVisible = ref(false);
// 回复表单
const dialogReplyFormVisible = ref(false);

if (userStore.flag == "register" && companyView.id == null) {
    dialogFormVisible.value = true;
}

// HR信息提交
const submitHR = async () => {
    const resp = await hrApi.updateHR(hrView);
    if (resp.code == 200) {
        localStorage.setItem("name", hrView.name);
        ElMessage.success(resp.message);
        ElMessage.warning("请填写公司信息！");
    } else {
        ElMessage.error(resp.message);
    }
    dialogFormVisible.value = false;
    dialogCompanyFormVisible.value = true;
};

// 公司信息提交
const submitCompany = async () => {
    let resp = null;
    let flag = false;
    if (companyView.id == null) {
        resp = await companyApi.addCompany(companyView);
        flag = true;
    } else {
        resp = await companyApi.updateCompany(companyView);
    }
    if (resp.code == 200) {
        if (flag) {
            hrView.pkCompany = resp.data.id;
            await hrApi.updateHR(hrView);
            recruitView.benefit = companyView.benefit;
        }
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
    dialogCompanyFormVisible.value = false;
};

/*------------------------------------- 换头像 ------------------------------------- */
const handleAvatarSuccess = async (response, uploadFile) => {
    hrView.profileImgUrl = response.data;
    const resp = await hrApi.updateHR(hrView);
    if (resp.code == 200) {
        localStorage.setItem("profileImg", response.data);
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};

const handleAvatarSuccessCompany = async (response, uploadFile) => {
    companyView.profileImgUrl = response.data;
};

const beforeAvatarUpload = (rawFile) => {
    if (rawFile.type !== "image/jpeg" && rawFile.type !== "image/png") {
        ElMessage.error("不是图片格式！");
        return false;
    } else if (rawFile.size / 1024 / 1024 > 5) {
        ElMessage.error("大小超过 5MB!");
        return false;
    }
    return true;
};

/*----------------------------------------- 选项卡内容 ------------------------------------*/
const contents = computed(() => {
    if (activeMenu.value == "job") {
        return recruitViews.map((recruit) => ({
            hr_img: userStore.profileImg, // hr头像
            hr_name: userStore.name, // hr名字
            hr_identity: hrView.identity, // hr身份
            title: recruit.title, // 招聘标题
            address: recruit.address, // 工作地点
            salary: recruit.salary, // 薪资
            requirement: recruit.requirement, // 工作要求
            education: convertEducation(recruit.education), // 学历要求
            company_img: companyView.profileImgUrl, // 公司头像
            companyName: companyView.companyName, // 公司名
            industryType: companyView.industryType, // 行业分类
            people: companyView.people, // 公司人数
        }));
    }
    if (activeMenu.value == "submit") {
        return submitViews.value.map((item) => ({
            hr_name: "投递岗位：", // 投递岗位
            hr_identity: item.recruitVO.title, // 投递岗位
            title: item.advantageVO.majorSkill, // 个人优势
            address: item.applicantVO.address, // 居住地
            work: "应届生", // 有实习经历
            requirement: item.educationalVO.name, // 学校
            education: item.educationalVO.major, // 专业
            company_img: item.applicantVO.profileImgUrl, // 学生头像
            companyName: item.applicantVO.name, // 姓名
            industryType: item.educationalVO.qualification, // 学历
            people: convertStatus(item.applicantVO.status), // 求职状态
        }));
    }
    if (activeMenu.value == "interest") {
        return interestViews.value.map((item) => ({
            hr_name: "投递岗位：", // 投递岗位
            hr_identity: item.recruitVO.title, // 投递岗位
            title: item.advantageVO.majorSkill, // 个人优势
            address: item.applicantVO.address, // 居住地
            work: "应届生", // 有实习经历
            requirement: item.educationalVO.name, // 学校
            education: item.educationalVO.major, // 专业
            company_img: item.applicantVO.profileImgUrl, // 学生头像
            companyName: item.applicantVO.name, // 姓名
            industryType: item.educationalVO.qualification, // 学历
            people: convertStatus(item.applicantVO.status), // 求职状态
        }));
    }
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

// 转换求职状态
const convertStatus = function (status) {
    switch (status) {
        case "0":
            return "离校-随时到岗";
        case "1":
            return "在校-月内到岗";
        case "2":
            return "在校-看看机会";
        case "3":
            return "在校-暂不考虑";
    }
};

// 菜单选中后样式
let activeMenu = ref("job"); // 默认选中"感兴趣"选项
let handleMenuClick = function (menu) {
    activeMenu.value = menu;
};

// 查看信息
const queryBtn = (index) => {
    // 职位信息
    if (activeMenu.value == "job") {
        router.push("/job");
        recruitStore.setRecommendInfo(recommendViews[index]);
    } else {
        // 学生信息
        router.push("/resume");
        localStorage.setItem("pkApplicant", "1904134133366996993");
    }
};

// 编辑职位
const updateBtn = async (index) => {
    dialogRecruitFormVisible.value = true;
    currentIndex.value = index;
    Object.assign(recruitView, recruitViews[index]);
};

// 删除职位
const delBtn = async (index) => {
    const resp = await recruitApi.delete(recruitViews[index].id);
    if (resp.code == 200) {
        recruitViews.splice(index, 1);
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};

// 职位提交
const submitRecruit = async () => {
    let resp = null;
    if (recruitView.id == null) {
        resp = await recruitApi.insert(recruitView);
        recruitViews.push({ ...recruitView, id: resp.data.id });
    } else {
        recruitViews[currentIndex.value] = { ...recruitView };
        resp = await recruitApi.update(recruitView);
    }
    if (resp.code == 200) {
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
    dialogRecruitFormVisible.value = false;
};

// 回复表单
let replyForm = reactive({
    reply: "",
    status: "",
});

let currentReplyFormIndex = ref(null);

// 回复
const reply = async (index) => {
    dialogReplyFormVisible.value = true;
    currentReplyFormIndex.value = index;
    replyForm.reply = "";
    if (activeMenu.value == "submit") {
        replyForm.status = submitViews.value[currentReplyFormIndex.value].resumeSubmitVO.filterStatus;
    } else {
        replyForm.status = interestViews.value[currentReplyFormIndex.value].resumeSubmitVO.filterStatus;
    }
};

// 确认回复
const confirmReply = async () => {
    let resp = null;
    if (activeMenu.value == "submit") {
        submitViews.value[currentReplyFormIndex.value].resumeSubmitVO.filterStatus = replyForm.status;
        submitViews.value[currentReplyFormIndex.value].resumeSubmitVO.reply = replyForm.reply;
        resp = await resumeSubmitApi.reply(submitViews.value[currentReplyFormIndex.value].resumeSubmitVO);
    } else {
        interestViews.value[currentReplyFormIndex.value].resumeSubmitVO.filterStatus = replyForm.status;
        interestViews.value[currentReplyFormIndex.value].resumeSubmitVO.reply = replyForm.reply;
        resp = await resumeSubmitApi.reply(interestViews.value[currentReplyFormIndex.value].resumeSubmitVO);
    }

    if (resp.code == 200) {
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
    dialogReplyFormVisible.value = false;
};

// 感兴趣
const addInterest = async (index) => {
    const resp = await resumeSubmitApi.interest(submitViews.value[index]);
    if (resp.code == 200) {
        submitViews.value.splice(index, 1);
        interestViews.value = [...interestViews.value, resp.data];
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};

// 取消感兴趣
const removeInterest = async (index) => {
    const resp = await resumeSubmitApi.removeInterest(interestViews.value[index]);
    if (resp.code == 200) {
        interestViews.value.splice(index, 1);
        submitViews.value = [...submitViews.value, resp.data];
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};
</script>

<style scoped>
.company-from {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    padding: 0 32px;
}
.company-from .company-name {
}

.company-from .logo {
}

.company-from .el-form-item {
    width: 300px;
    height: 40px;
}

:deep().el-input__inner {
    height: 40px;
}

:deep().el-select__wrapper {
    height: 40px;
}

.company-from .textarea {
    width: 654px;
}

.submit-button {
    margin-top: 60px;
}

.submitCompany {
    margin-right: 55px;
}

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
    margin-bottom: 600px;
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

.profile-content .info .yuan {
    display: inline-block;
    width: 10px;
    height: 10px;
    border-radius: 100%;
    background-color: rgb(5, 202, 57);
}

.profile-content .status {
    margin-top: 15px;
}

.profile-content .el-button {
    background: #f8f8f8;
    border: 1px solid #d4d5d6;
}

.profile-content .el-button:hover {
    cursor: pointer;
    background: #e5f8f8;
    border: 1px solid #00bebd;
    color: #00a6a7;
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
    border-radius: 8px;
}

.item-content .update-btn {
    background-color: #00a6a7;
}

.item-content .update-btn:hover {
    background-color: #00bebd;
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
