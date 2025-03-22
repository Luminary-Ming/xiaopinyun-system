// src/api/student.js
import request from "@/utils/request";

export const studentApi = {
    getApplicant(id) {
        return request({
            url: `/resume-server/applicant/${id}`,
            method: "get",
        });
    },
    addApplicant(applicantView) {
        return request({
            url: `/resume-server/applicant`,
            method: "post",
            data: applicantView,
        });
    },
    updateApplicant(applicantView) {
        return request({
            url: `/resume-server/applicant`,
            method: "put",
            data: applicantView,
        });
    },
    deleteApplicantById(id) {
        return request({
            url: `/resume-server/applicant/${id}`,
            method: "delete",
        });
    },
};
