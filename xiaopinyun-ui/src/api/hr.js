import request from "@/utils/request";

export const hrApi = {
    getHR(id) {
        return request({
            url: `/info-server/hr/${id}`,
            method: "get",
        });
    },
    addHR(hrView) {
        return request({
            url: `/info-server/hr`,
            method: "post",
            data: hrView,
        });
    },
    updateHR(hrView) {
        return request({
            url: `/info-server/hr`,
            method: "put",
            data: hrView,
        });
    },
    deleteHR(id) {
        return request({
            url: `/info-server/hr/${id}`,
            method: "delete",
        });
    },
};
