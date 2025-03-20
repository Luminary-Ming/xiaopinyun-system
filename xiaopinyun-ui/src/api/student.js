// src/api/student.js
import request from "@/utils/request";

export const studentApi = {
    getApplicant(id) {
        return request({
            url: `/resume-server/applicant/${id}`,
            method: "get",
        });
    },
};
