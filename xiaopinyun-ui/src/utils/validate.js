// src/utils/validate.js
export const validatePassword = (rule, value, callback) => {
    if (!/(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}/.test(value)) {
        callback(new Error("至少8位，包含大小写字母和数字"));
    } else {
        callback();
    }
};

export const validatePhone = (rule, value, callback) => {
    if (!/^1[3-9]\d{9}$/.test(value)) {
        callback(new Error("请输入有效的手机号码"));
    } else {
        callback();
    }
};
