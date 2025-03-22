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
        });
    },
    previewFile(filename) {
        return request({
            url: `/upload-server/upload/${filename}`,
            method: "get",
        });
    },
    deleteFile(id) {
        return request({
            url: `/upload-server/upload/${id}`,
            method: "delete",
        });
    },
};
