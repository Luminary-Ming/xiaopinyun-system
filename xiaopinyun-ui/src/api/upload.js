import request from "@/utils/request";

export const uploadApi = {
    uploadFile() {
        return request({
            url: `/upload-server/upload`,
            method: "post",
        });
    },
    downloadFile(filename) {
        return request({
            url: `/upload-server/upload/download/${filename}`,
            method: "get",
            responseType: "blob", // 指定响应类型为 blob
        });
    },
    previewFile(filename) {
        return request({
            url: `/upload-server/upload/${filename}`,
            method: "get",
        });
    },
    deleteFile(filename) {
        return request({
            url: `/upload-server/upload/${filename}`,
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
    getHRAttachment(pkHr) {
        return request({
            url: `/resume-server/attachment/queryHR/${pkHr}`,
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
