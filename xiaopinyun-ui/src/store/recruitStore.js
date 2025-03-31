import { defineStore } from "pinia";

export const useRecruitStore = defineStore("recruit", {
    state: () => ({
        recruitInfo: JSON.parse(localStorage.getItem("recruitInfo")) || {
            id: "",
            pkCompany: "",
            pkHr: "",
            title: "",
            salary: "",
            benefit: "",
            address: "",
            requirement: "",
            education: "",
            jobInformation: "",
            publishStatus: "1",
        },
        hrInfo: JSON.parse(localStorage.getItem("hrInfo")) || {
            profileImgUrl: "",
            name: "",
            identity: "",
            status: "",
            telephone: "",
            email: "",
        },
        companyInfo: JSON.parse(localStorage.getItem("companyInfo")) || {
            companyFullName: "",
            legalPerson: "",
            capital: "",
            foundDate: "",
        },
        recommendInfo: JSON.parse(localStorage.getItem("recommendInfo")) || {
            recruitVO: {
                id: "",
                pkCompany: "",
                pkHr: "",
                title: "",
                salary: "",
                benefit: "",
                address: "",
                requirement: "",
                education: "",
                jobInformation: "",
                publishStatus: "1",
            },
            hrvo: {
                profileImgUrl: "",
                name: "",
                identity: "",
                status: "",
                telephone: "",
                email: "",
            },
            companyVO: {
                companyFullName: "",
                legalPerson: "",
                capital: "",
                foundDate: "",
            },
        },
    }),

    actions: {
        setRecruitInfo(info) {
            this.recruitInfo = { ...info };
            localStorage.setItem("recruitInfo", JSON.stringify(this.recruitInfo));
        },

        setHrInfo(info) {
            this.hrInfo = { ...info };
            localStorage.setItem("hrInfo", JSON.stringify(this.hrInfo));
        },

        setCompanyInfo(info) {
            this.companyInfo = { ...info };
            localStorage.setItem("companyInfo", JSON.stringify(this.companyInfo));
        },
        setRecommendInfo(info) {
            this.recommendInfo = {
                recruitVO: { ...info.recruitVO },
                hrvo: { ...info.hrvo },
                companyVO: { ...info.companyVO },
            };
            localStorage.setItem("recommendInfo", JSON.stringify(this.recommendInfo));
        },
    },

    getters: {
        getRecruitInfo: (state) => state.recruitInfo,
        getHrInfo: (state) => state.hrInfo,
        getCompanyInfo: (state) => state.companyInfo,
        getRecommendInfo: (state) => state.recommendInfo,
    },
});
