<!-- src/components/common/AuthDialog.vue -->
<template>
    <el-dialog v-model="visible" title=" " width="380px" :show-close="false" class="boss-dialog" @closed="resetForm">
        <!-- 顶部导航 -->
        <div class="auth-header">
            <div v-for="tab in tabs" :key="tab.value" :class="['tab-item', { active: activeTab === tab.value }]" @click="activeTab = tab.value">
                {{ tab.label }}
            </div>
        </div>

        <!-- 主体表单 -->
        <el-form ref="formRef" :model="form">
            <!-- 国家区号选择 -->
            <div class="phone-prefix">
                <el-select v-model="form.prefix" class="prefix-select">
                    <el-option label="+86" value="+86" />
                </el-select>
                <el-input v-model="form.phone" placeholder="手机号" class="phone-input" />
            </div>

            <!-- 验证码 -->
            <div class="sms-code">
                <el-input v-model="form.code" placeholder="短信验证码" />
                <el-button class="send-btn" :disabled="countdown > 0" @click="sendSmsCode">
                    {{ countdown ? `${countdown}s后重发` : "发送验证码" }}
                </el-button>
            </div>

            <!-- 登录按钮 -->
            <el-button type="primary" class="submit-btn" @click="handleSubmit"> 登录/注册 </el-button>

            <!-- 第三方登录 -->
            <div class="third-login">
                <div class="divider">
                    <span class="line"></span>
                    <span class="text">其他方式登录</span>
                    <span class="line"></span>
                </div>
                <el-button class="wechat-btn" @click="wechatLogin">
                    <el-icon class="wechat-icon"><svg-icon icon="wechat" /></el-icon>
                    微信
                </el-button>
            </div>

            <!-- 用户协议 -->
            <div class="agreement">
                已阅读并同意
                <el-link type="primary">《用户协议》</el-link>
                <el-link type="primary">《隐私政策》</el-link>
            </div>
        </el-form>

        <!-- 底部信息 -->
        <template #footer>
            <div class="footer">
                <p>客服电话: 400-065-5790</p>
                <p>服务时间：8:00-22:00</p>
                <div class="certificates">
                    <span>人力资源服务许可证</span>
                    <span>建设执照</span>
                    <span>核准人</span>
                    <span>注册资格证书</span>
                </div>
            </div>
        </template>
    </el-dialog>
</template>

<script setup>
import { ref } from "vue";
import { ElMessage } from "element-plus";

const visible = ref(false);
const activeTab = ref("jobSeeker"); // 当前选项卡
const countdown = ref(0); // 验证码倒计时

// 表单数据
const form = ref({
    prefix: "+86",
    phone: "",
    code: "",
});

// 选项卡配置
const tabs = [
    { label: "我要找工作", value: "jobSeeker" },
    { label: "我要招聘", value: "recruiter" },
];

// 发送验证码
const sendSmsCode = () => {
    if (!/^1[3-9]\d{9}$/.test(form.value.phone)) {
        ElMessage.error("请输入正确的手机号");
        return;
    }

    countdown.value = 60;
    const timer = setInterval(() => {
        countdown.value--;
        if (countdown.value <= 0) clearInterval(timer);
    }, 1000);
};

// 微信登录
const wechatLogin = () => {
    // 微信登录逻辑
};

// 提交表单
const handleSubmit = () => {
    // 验证逻辑
};

// 重置表单
const resetForm = () => {
    form.value = { prefix: "+86", phone: "", code: "" };
    countdown.value = 0;
};
</script>

<style lang="scss" scoped>
.boss-dialog {
    :deep(.el-dialog__header) {
        display: none;
    }

    :deep(.el-dialog__body) {
        padding: 0;
    }
}

.auth-header {
    display: flex;
    height: 56px;
    border-bottom: 1px solid #eee;

    .tab-item {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 16px;
        color: #666;
        cursor: pointer;

        &.active {
            color: #00b38a;
            border-bottom: 2px solid #00b38a;
        }
    }
}

.phone-prefix {
    display: flex;
    margin: 24px 20px 0;
    gap: 8px;

    .prefix-select {
        width: 100px;

        :deep(.el-input__inner) {
            padding-right: 0;
        }
    }

    .phone-input {
        flex: 1;
    }
}

.sms-code {
    display: flex;
    margin: 16px 20px;
    gap: 8px;

    .send-btn {
        width: 120px;
        color: #00b38a;
        border-color: #00b38a;

        &:disabled {
            color: #999;
            border-color: #ddd;
        }
    }
}

.submit-btn {
    width: calc(100% - 40px);
    height: 40px;
    margin: 16px 20px;
    background: #00b38a;
    border: none;
}

.third-login {
    margin: 24px 20px;

    .divider {
        display: flex;
        align-items: center;
        color: #999;
        font-size: 12px;

        .line {
            flex: 1;
            height: 1px;
            background: #eee;
            margin: 0 8px;
        }
    }

    .wechat-btn {
        width: 100%;
        margin-top: 16px;
        color: #09bb07;
        border-color: #09bb07;

        &:hover {
            background: #f6fffc;
        }
    }

    .wechat-icon {
        margin-right: 8px;
        font-size: 18px;
    }
}

.agreement {
    margin: 16px 20px;
    color: #999;
    font-size: 12px;
    line-height: 1.5;

    .el-link {
        vertical-align: baseline;
    }
}

.footer {
    padding: 16px 0;
    text-align: center;
    color: #999;
    font-size: 12px;

    p {
        margin: 4px 0;
    }

    .certificates {
        margin-top: 8px;

        span {
            position: relative;
            padding: 0 8px;

            &::after {
                content: "|";
                position: absolute;
                right: -2px;
                color: #ddd;
            }

            &:last-child::after {
                display: none;
            }
        }
    }
}
</style>
