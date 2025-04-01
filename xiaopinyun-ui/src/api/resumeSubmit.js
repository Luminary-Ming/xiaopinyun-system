import request from "@/utils/request";

export const resumeSubmitApi = {
    queryByPkApplicant(pkApplicant) {
        return request({
            url: `/resume-submit-server/resumeSubmit/queryByPkApplicant/${pkApplicant}`,
            method: "get",
        });
    },
    queryByPkRecruit(pkRecruit) {
        return request({
            url: `/resume-submit-server/resumeSubmit/queryByPkRecruit/${pkRecruit}`,
            method: "get",
        });
    },
    queryReply(pkApplicant) {
        return request({
            url: `/resume-submit-server/resumeSubmit/queryReply/${pkApplicant}`,
            method: "get",
        });
    },
    submit(resumeSubmitVO) {
        return request({
            url: `/resume-submit-server/resumeSubmit`,
            method: "post",
            data: resumeSubmitVO,
        });
    },
};
