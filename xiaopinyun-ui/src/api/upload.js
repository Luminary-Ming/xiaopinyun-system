import request from "@/utils/request";

export const uploadApi = {
    uploadFile() {
        return request({
            url: `/upload-server/upload`,
            method: "post",
        });
    },
};
