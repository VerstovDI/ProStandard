import http from "../http-common";

class MainDataService {
    getStandards(data) {
        return http.post("/standards", data);
        // ДОБАВИТЬ РЕСУРС ДЛЯ ПАРСИНГА
        // GET ИЛИ POST? То же - в Controllere
    }
}

export default new MainDataService();