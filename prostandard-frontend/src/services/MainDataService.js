import http from "../http-common";

class MainDataService {

    getStandards(data) {
        return http.post("/standards", data);
    }

    getInfo() {
        return http.get("/info");
    }

}

export default new MainDataService();
