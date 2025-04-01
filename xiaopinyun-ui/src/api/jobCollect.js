import request from "@/utils/request";

export const jobCollectApi = {
    query(pkApplicant) {
        return request({
            url: `/job-collect-server/jobCollect/${pkApplicant}`,
            method: "get",
        });
    },
    insert(jobCollectVO) {
        return request({
            url: `/job-collect-server/jobCollect`,
            method: "post",
            data: jobCollectVO,
        });
    },
    delete(id) {
        return request({
            url: `/job-collect-server/jobCollect/${id}`,
            method: "delete",
        });
    },
};
