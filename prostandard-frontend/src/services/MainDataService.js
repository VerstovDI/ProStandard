import http from "../http-common";

class MainDataService {
    getStandards(data) {
        return http.post("/standards", data);
    }
}

export default new MainDataService();