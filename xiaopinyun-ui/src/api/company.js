// src/api/company.js
import request from "@/utils/request";

export const companyApi = {
    getCompany(id) {
        return request({
            url: `/info-server/company/${id}`,
            method: "get",
        });
    },
    addCompany(companyView) {
        return request({
            url: `/info-server/company`,
            method: "post",
            data: companyView,
        });
    },
    updateCompany(companyView) {
        return request({
            url: `/info-server/company`,
            method: "put",
            data: companyView,
        });
    },
    deleteCompany(id) {
        return request({
            url: `/info-server/company/${id}`,
            method: "delete",
        });
    },
};
