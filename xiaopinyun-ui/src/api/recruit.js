import request from "@/utils/request";

export const recruitApi = {
    query(id) {
        return request({
            url: `/recruit-server/recruit/${id}`,
            method: "get",
        });
    },
    queryByPkHr(id) {
        return request({
            url: `/recruit-server/recruit/queryByPkHr/${id}`,
            method: "get",
        });
    },
    queryRecommend() {
        return request({
            url: `/recruit-server/recruit/queryRecommend`,
            method: "get",
        });
    },
    queryAll() {
        return request({
            url: `/recruit-server/recruit/queryAll`,
            method: "get",
        });
    },
    insert(recruitView) {
        return request({
            url: `/recruit-server/recruit`,
            method: "post",
            data: recruitView,
        });
    },
    update(recruitView) {
        return request({
            url: `/recruit-server/recruit`,
            method: "put",
            data: recruitView,
        });
    },
    delete(id) {
        return request({
            url: `/recruit-server/recruit/${id}`,
            method: "delete",
        });
    },
};
