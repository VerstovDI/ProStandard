import http from "../http-common";

class MainDataService {

    searchStandards(data) {
        return http.get("/standards", {
            params: {
                educationLevel: data.educationLevel,
                specializationCode: data.specializationCode,
                subjMajor: data.subjMajor,
                resourceToDownload: data.resourceToDownload,
                keywords: data.keywords.join(',')
            }
        });
    }

    getInfo() {
        return http.get("/info");
    }

}

export default new MainDataService();
