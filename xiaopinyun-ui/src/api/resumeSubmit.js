import request from "@/utils/request";

export const resumeSubmitApi = {
    queryByPkApplicant(pkApplicant) {
        return request({
            url: `/resume-submit-server/resumeSubmit/queryByPkApplicant/${pkApplicant}`,
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
    querySubmitInfo(pkHr) {
        return request({
            url: `/resume-submit-server/resumeSubmit/querySubmitInfo/${pkHr}`,
            method: "get",
        });
    },
    queryInterest(pkHr) {
        return request({
            url: `/resume-submit-server/resumeSubmit/queryInterest/${pkHr}`,
            method: "get",
        });
    },
    reply(resumeSubmitVO) {
        return request({
            url: `/resume-submit-server/resumeSubmit/reply`,
            method: "post",
            data: resumeSubmitVO,
        });
    },
    interest(resumeSubmitVO) {
        return request({
            url: `/resume-submit-server/resumeSubmit/interest`,
            method: "post",
            data: resumeSubmitVO,
        });
    },
    removeInterest(resumeSubmitVO) {
        return request({
            url: `/resume-submit-server/resumeSubmit/removeInterest`,
            method: "post",
            data: resumeSubmitVO,
        });
    },
};
