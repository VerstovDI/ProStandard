<template>
  <div class="root-main">
    <div class="row pt-5 pb-5">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col text-center">
            <h4>Выберите необходимые параметры для формирования компетенций</h4>
          </div>
        </div>
      </div>
    </div>

    <div class="container">

      <div class="row justify-content-center">
        <div class="col-md-6">
          <form role="form">
            <div class="row gy-3">
              <div class="form-group">
                <label for="educationLevelChoice">
                  <b>Уровень образования</b>
                </label>
                <select class="form-select"
                        v-model="educationLevelChoice"
                        name="educationLevelChoice"
                        id="educationLevelChoice">
                  <option value="0" disabled selected hidden>Выберите уровень образования...</option>
                  <option value="1">Бакалавриат</option>
                  <option value="2">Специалитет</option>
                  <option value="3">Магистратура</option>
                  <option value="4">Аспирантура</option>
                </select>
              </div>
              <div class="form-group">
                <label for="specialty">
                  <b>Специальность</b>
                </label>
                <input type="text" v-model="specialty" name="specialty" class="form-control" id="specialty"
                       placeholder="Введите наименование специальности">
                <!--<p class="help-block"></p>-->
              </div>
              <div class="form-group">
                <label for="major">
                  <b>Направление обучения</b>
                </label>
                <input type="text" v-model="subj_major"  name="subj_major" class="form-control" id="major"
                       placeholder="Введите направление обучения">
              </div>
              <div class="form-group">
                <label for="resource">
                  <b>Уровень образования</b>
                </label>
                <select class="form-select" v-model="resource" name="resource" id="resource">
                  <option value="0" disabled selected hidden>Выберите источник стандартов...</option>
                  <option value="1">Росминтруд [https://profstandart.rosmintrud.ru/]</option>
                </select>
              </div>
            </div>
          </form>
        </div>


        <div class="col-md-4">
          <div class="row p-3 m-2">
            <button type="button" @click="resetInput" class="btn btn-secondary" id="reset">Сброс</button>
          </div>
          <div class="row p-3 m-2">
            <router-link to="/standards">
              <button @click="getStandards()" type="button" class="btn btn-success" id="getStandards">Подобрать</button>
            </router-link>
          </div>
        </div>

      </div>

    </div>
    <div class="fixed-bottom">
      <div class="row justify-content-center">ProStandard application, 2021, ver. 0.1</div>
    </div>
  </div>
</template>

<script>
import MainDataService from "@/services/MainDataService";

export default {
  name: "Main",
  data() {
    return {
      specialty: "",
      subj_major: ""
    }
  },
  methods: {
    resetInput() {
      this.specialty = "";
      this.subj_major = "";
    },
    getStandards() {
      let requestData = {
        educationLevelChoice: this.educationLevelChoice,
        specializationCode: this.specialty,
        majorId: this.subj_major,
        resourceToDownload: this.resource
      };

      console.log(requestData);

      MainDataService.getStandards(requestData).catch(e => {
        if(e.request) {console.log(e.request)} if (e.response) {console.log(e.response)}
      });
      //return data? JSON.parse(data) : data;
    }
  }
}
</script>