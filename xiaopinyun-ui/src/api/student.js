// src/api/student.js
import request from "@/utils/request";

export const studentApi = {
    // 个人信息
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

    // 个人优势（专业技能、获奖荣誉、自我评价）
    getAdvantage(pkApplicant) {
        return request({
            url: `/resume-server/advantage/${pkApplicant}`,
            method: "get",
        });
    },
    updateAdvantage(advantageVO) {
        return request({
            url: `/resume-server/advantage`,
            method: "put",
            data: advantageVO,
        });
    },

    // 求职期望
    getJobExpectation(pkApplicant) {
        return request({
            url: `/resume-server/jobExpectation/${pkApplicant}`,
            method: "get",
        });
    },
    addJobExpectation(jobExpectationView) {
        return request({
            url: `/resume-server/jobExpectation`,
            method: "post",
            data: jobExpectationView,
        });
    },
    updateJobExpectation(jobExpectationView) {
        return request({
            url: `/resume-server/jobExpectation`,
            method: "put",
            data: jobExpectationView,
        });
    },
    deleteJobExpectation(id) {
        return request({
            url: `/resume-server/jobExpectation/${id}`,
            method: "delete",
        });
    },

    // 教育背景
    getBackground(pkApplicant) {
        return request({
            url: `/resume-server/background/${pkApplicant}`,
            method: "get",
        });
    },
    updateBackground(backgroundView) {
        return request({
            url: `/resume-server/background`,
            method: "put",
            data: backgroundView,
        });
    },

    // 项目经历
    getProjectExperience(pkApplicant) {
        return request({
            url: `/resume-server/projectExperience/${pkApplicant}`,
            method: "get",
        });
    },
    addProjectExperience(projectExperienceView) {
        return request({
            url: `/resume-server/projectExperience`,
            method: "post",
            data: projectExperienceView,
        });
    },
    updateProjectExperience(projectExperienceView) {
        return request({
            url: `/resume-server/projectExperience`,
            method: "put",
            data: projectExperienceView,
        });
    },
    deleteProjectExperienceById(id) {
        return request({
            url: `/resume-server/projectExperience/${id}`,
            method: "delete",
        });
    },

    // 工作/实习经历
    getWorkExperience(pkApplicant) {
        return request({
            url: `/resume-server/workExperience/${pkApplicant}`,
            method: "get",
        });
    },
    addWorkExperience(workExperienceView) {
        return request({
            url: `/resume-server/workExperience`,
            method: "post",
            data: workExperienceView,
        });
    },
    updateWorkExperience(workExperienceView) {
        return request({
            url: `/resume-server/workExperience`,
            method: "put",
            data: workExperienceView,
        });
    },
    deleteWorkExperienceById(id) {
        return request({
            url: `/resume-server/workExperience/${id}`,
            method: "delete",
        });
    },

    // 附件
    getAttachment(pkApplicant) {
        return request({
            url: `/resume-server/attachment/${pkApplicant}`,
            method: "get",
        });
    },
    addAttachment(attachmentView) {
        return request({
            url: `/resume-server/attachment`,
            method: "post",
            data: attachmentView,
        });
    },
    deleteAttachmentById(id) {
        return request({
            url: `/resume-server/attachment/${id}`,
            method: "delete",
        });
    },
};
