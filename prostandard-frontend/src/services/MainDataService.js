import http from "../http-common";

class MainDataService {

    searchStandards(data) {
        console.log(JSON.stringify(data.tags));
        return http.get("/standards", {
            params: {
                educationLevel: data.educationLevel,
                specializationCode: data.specializationCode,
                subjMajor: data.subjMajor,
                resourceToDownload: data.resourceToDownload,
                keywords: data.keywords
            }
        });
    }

    getInfo() {
        return http.get("/info");
    }

}

export default new MainDataService();
