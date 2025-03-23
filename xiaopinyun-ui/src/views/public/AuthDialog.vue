<template>
    <!-- 登录页面 -->
    <div class="shell" v-if="isLoginPage">
        <form :model="login">
            <h2>LOGIN</h2>
            <div class="form-item">
                <label for="username">Username</label>
                <div class="input-wrapper">
                    <input v-model="login.username" type="text" id="username" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" data-lpignore="true" />
                </div>
            </div>
            <div class="form-item">
                <label for="password">Password</label>
                <div class="input-wrapper">
                    <input v-model="login.password" :type="passwordType" id="password" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" data-lpignore="true" />
                    <button type="button" id="eyeball" @click="togglePasswordVisibility">
                        <div class="eye"></div>
                    </button>
                    <div id="beam"></div>
                </div>
            </div>
            <div class="button-container">
                <el-dropdown placement="bottom-end" @command="handleSelect">
                    <el-button class="el-button_qxzsfdl"> {{ selectedRole }} </el-button>
                    <template #dropdown>
                        <el-dropdown-menu class="el-dropdown-item">
                            <el-dropdown-item :command="{ text: '学生', value: '0' }">学生</el-dropdown-item>
                            <el-dropdown-item :command="{ text: 'HR', value: '1' }">HR</el-dropdown-item>
                            <el-dropdown-item :command="{ text: '管理员', value: '2' }">管理员</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
                <el-button class="zcyg" @click="togglePage">
                    注册一个<el-icon><ArrowRightBold /></el-icon>
                </el-button>
            </div>
            <button id="submit" @click="signIn()">Sign in</button>
        </form>
    </div>

    <!-- 注册页面 -->
    <div class="shell" v-else>
        <form :model="login">
            <h2>REGISTER</h2>
            <div class="form-item">
                <label for="username">Username</label>
                <div class="input-wrapper">
                    <input v-model="login.username" type="text" id="username" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" data-lpignore="true" />
                </div>
            </div>
            <div class="form-item">
                <label for="password">Password</label>
                <div class="input-wrapper">
                    <input v-model="login.password" :type="passwordType" id="password" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" data-lpignore="true" />
                    <button type="button" id="eyeball" @click="togglePasswordVisibility">
                        <div class="eye"></div>
                    </button>
                    <div id="beam"></div>
                </div>
            </div>
            <div class="button-container">
                <el-dropdown placement="bottom-end" @command="handleSelect">
                    <el-button class="el-button_qxzsfdl"> {{ selectedRole }} </el-button>
                    <template #dropdown>
                        <el-dropdown-menu class="el-dropdown-item">
                            <el-dropdown-item :command="{ text: '学生', value: '0' }">学生</el-dropdown-item>
                            <el-dropdown-item :command="{ text: 'HR', value: '1' }">HR</el-dropdown-item>
                            <el-dropdown-item :command="{ text: '管理员', value: '2' }">管理员</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
                <el-button class="zcyg" @click="togglePage">
                    返回登录<el-icon><ArrowRightBold /></el-icon>
                </el-button>
            </div>
            <button id="submit" @click="register()">Register</button>
        </form>
    </div>
</template>

<script setup>
import { ref, reactive, onMounted, onBeforeMount, onBeforeUnmount } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import "@/assets/styles/variables.css";
import "@/assets/styles/auth.css";
import { userApi } from "@/api/user.js";
import { useRouter } from "vue-router";
import { useUserStore } from "@/store/userStore";
/*------------------------------------- API -------------------------------------*/
let login = reactive({
    username: "",
    password: "",
    role: "",
    name: "",
    profileImg: "",
    token: "",
});

const router = useRouter();
const userStore = useUserStore();
// 注册
const register = async () => {
    const resp = await userApi.register(login);
    debugger;
    if (resp.code == 200) {
        debugger;
        ElMessage.success("注册成功");

        userStore.setToken(resp.data.token); // 保存token

        // 传递给跳转页面数据，新注册的用户要补充信息
        userStore.setUserInfo({
            pkApplicant: resp.data.userVO.pkApplicant,
            pkHr: resp.data.userVO.pkHr,
            pkAdmin: resp.userVO.data.pkAdmin,
            role: resp.data.userVO.role,
            name: "新用户",
            profileImg: "/src/assets/images/profile-img/default.png",
            flag: "register",
        });

        // 根据角色跳转到不同页面
        switch (login.role) {
            case "0": // 学生
                router.push("/resume"); // 跳转简历
                ElMessageBox.alert("请填写简历完整信息", "提示", {
                    confirmButtonText: "OK",
                });
                break;
            case "1": // HR
                router.push("/home");
                break;
            case "2": // 管理员
                router.push("/home");
                break;
            default:
                router.push("/");
        }
    } else {
        ElMessage.error(resp.message);
    }
};

// 登录
const signIn = async () => {
    const resp = await userApi.login(login);
    debugger;
    if (resp.code == 200) {
        ElMessage.success("登录成功");

        userStore.setToken(resp.data.token); // 保存token

        // 保存用户信息
        userStore.setUserInfo({
            pkApplicant: resp.data.userVO.pkApplicant,
            pkHr: resp.data.userVO.pkHr,
            pkAdmin: resp.data.userVO.pkAdmin,
            role: resp.data.userVO.role,
            name: resp.data.name,
            profileImg: resp.data.profileImg,
            flag: "login",
        });

        switch (login.role) {
            case "0": // 学生
                router.push("/");
                break;
            case "1": // HR
                router.push("/home");
                break;
            case "2": // 管理员
                router.push("/home");
                break;
            default:
                router.push("/");
        }
    } else {
        ElMessage.error(resp.message);
    }
};
/*----------------------------------------------------------------------------*/
// 选择身份后更新按钮文本
const selectedRole = ref("请选择身份登录");
const handleSelect = (command) => {
    selectedRole.value = command.text; // 显示文本
    login.role = command.value; // 存储数值
};

// 组件挂载前添加 auth-page 类
onBeforeMount(() => {
    document.body.classList.add("auth-page");
});

// 组件卸载前移除 auth-page 类
onBeforeUnmount(() => {
    document.body.classList.remove("auth-page");
    document.body.classList.remove("show-password");
});

// 切换密码可见性
const passwordType = ref("password");
const togglePasswordVisibility = () => {
    document.body.classList.toggle("show-password");
    passwordType.value = passwordType.value === "password" ? "text" : "password";
    document.getElementById("password").focus();
};

// 切换页面
const isLoginPage = ref(true);
const togglePage = async () => {
    isLoginPage.value = !isLoginPage.value;
    // 使用 nextTick 等待 DOM 更新完成
    await nextTick();
    // 重新初始化光束位置
    initBeam();
};

const beamDegrees = ref(0); // 控制光束的旋转角度
// 初始化光束位置和事件监听
const initBeam = () => {
    const root = document.documentElement;
    const beam = document.getElementById("beam");
    if (!beam) return;

    // 重置光束角度
    beamDegrees.value = -350;
    root.style.setProperty("--beamDegrees", `-350deg`);

    // 移除之前的事件监听器
    root.removeEventListener("mousemove", handleMouseMove);
    // 添加新的事件监听器
    root.addEventListener("mousemove", handleMouseMove);
};

// 处理鼠标移动事件
const handleMouseMove = (e) => {
    const root = document.documentElement;
    const beam = document.getElementById("beam");
    if (!beam) return;
    const rect = beam.getBoundingClientRect();
    const mouseX = rect.right + rect.width / 2;
    const mouseY = rect.top + rect.height / 2;
    const rad = Math.atan2(mouseX - e.pageX, mouseY - e.pageY);
    const degrees = rad * (20 / Math.PI) * -1 - 350;
    beamDegrees.value = degrees;
    root.style.setProperty("--beamDegrees", `${degrees}deg`);
};

// 在组件挂载后初始化
onMounted(() => {
    initBeam();
});
</script>

<style scoped>
.button-container {
    display: flex;
    justify-content: space-between; /* 按钮两端对齐 */
    align-items: center; /* 垂直居中 */
    gap: 10px; /* 按钮之间的间距 */
}

.zcyg {
    display: flex;
    align-items: center; /* 垂直居中 */
    height: 45px;
    font-size: 20px;
    font-family: "优设标题黑";
    color: var(--bgColor);
    background-color: var(--inputColor);
}

.el-button_qxzsfdl {
    font-size: 20px;
    height: 45px;
    font-family: "优设标题黑";
    color: var(--bgColor);
    background-color: var(--inputColor);
}

.el-dropdown-item {
    width: 80px;
    font-size: 500px;
    font-family: "优设标题黑";
    color: var(--bgColor);
    background-color: var(--inputColor);
}

/* 设置全局样式 */
* {
    box-sizing: border-box;
    transition: 0.2s;
}

/* 设置外层容器样式 */
.shell {
    width: 100%;
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    background-size: cover;
}

/* 设置表单样式 */
form {
    transform: translate3d(0, 0, 0);
    padding: 50px;
    border: 20px solid var(--border);
    border-radius: 10px;
    box-shadow: 10px 10px 10px #00000065;
}

form > * + * {
    margin-top: 15px;
}

.form-item > * + * {
    margin-top: 0.5rem;
}

/* 设置label, input, button样式 */
h2,
label,
input,
button {
    font-size: 2rem;
    color: var(--inputColor);
    font-family: "优设标题黑";
}

h2 {
    font-size: 4rem;
    margin: 0;
}

label:focus,
input:focus,
button:focus {
    outline-offset: 2px;
}

label::-moz-focus-inner,
input::-moz-focus-inner,
button::-moz-focus-inner {
    border: none;
}

/* 设置密码相关样式 */
label[id="password"],
input[id="password"],
button[id="password"] {
    color: black;
}

/* 设置按钮样式 */
button {
    border: none;
}

[id="submit"] {
    width: 100%;
    cursor: pointer;
    margin: 20px 0 0 2px;
    padding: 0.75rem 1.25rem;
    color: var(--bgColor);
    background-color: var(--inputColor);
    box-shadow: 4px 4px 0 rgba(30, 144, 255, 0.2);
}

[id="submit"]:active {
    transform: translateY(1px);
}

/* 设置输入框包裹器样式 */
.input-wrapper {
    position: relative;
}

/* 设置输入框样式 */
input {
    padding: 0.75rem 4rem 0.75rem 0.75rem;
    width: 100%;
    border: 2px solid transparent;
    border-radius: 0;
    background-color: transparent;
    box-shadow: inset 0 0 0 2px black, inset 6px 6px 0 rgba(30, 144, 255, 0.2), 3px 3px 0 rgba(30, 144, 255, 0.2);
    /* -webkit-appearance: none; */
}

input:focus {
    outline-offset: 1px;
}

/* 设置显示密码时的输入框样式 */
.show-password input {
    box-shadow: inset 0 0 0 2px black;
    border: 2px dashed white;
}

.show-password input:focus {
    outline: none;
    border-color: rgb(255, 255, 145);
}

/* 设置眼睛按钮样式 */
[id="eyeball"] {
    --size: 1.25rem;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    outline: none;
    position: absolute;
    top: 50%;
    right: 0.75rem;
    border: none;
    background-color: transparent;
    transform: translateY(-50%);
}

[id="eyeball"]:active {
    transform: translateY(calc(-50% + 1px));
}

.eye {
    width: var(--size);
    height: var(--size);
    border: 2px solid var(--inputColor);
    border-radius: calc(var(--size) / 1.5) 0;
    transform: rotate(45deg);
}

.eye:before,
.eye:after {
    content: "";
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    margin: auto;
    border-radius: 100%;
}

.eye:before {
    width: 35%;
    height: 35%;
    background-color: var(--inputColor);
}

.eye:after {
    width: 65%;
    height: 65%;
    border: 2px solid var(--inputColor);
    border-radius: 100%;
}

/* 设置光束样式 */
[id="beam"] {
    position: absolute;
    top: 50%;
    right: 1.75rem;
    clip-path: polygon(100% 50%, 100% 50%, 0 0, 0 100%);
    width: 100vw;
    height: 25vw;
    z-index: 1;
    mix-blend-mode: multiply;
    transition: transform 200ms ease-out;
    transform-origin: 100% 50%;
    transform: translateY(-50%) rotate(var(--beamDegrees, 0));
    pointer-events: none;
}

.show-password [id="beam"] {
    background: rgb(255, 255, 145);
}
</style>
