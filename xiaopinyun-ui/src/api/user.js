import request from "@/utils/request";

export const userApi = {
    login(login) {
        return request({
            url: `/user-server/login/login`,
            method: "post",
            data: login,
        });
    },
    register(login) {
        return request({
            url: `/user-server/login/register`,
            method: "post",
            data: login,
        });
    },
};
