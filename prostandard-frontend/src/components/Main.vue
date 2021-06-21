<template>
  <!-- root страницы !-->
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

    <!-- Контейнер с формами ввода !-->
    <div class="container">

      <div class="row justify-content-center">
        <div class="col-md-6">
          <form role="form">
            <div class="row gy-3">

              <!-- Форма "Уровень образования" !-->
              <div class="form-group">
                <label for="educationLevel">
                  <b>Уровень образования</b>
                </label>
                <select v-model="educationLevel"
                        class="form-select"
                        name="educationLevel"
                        id="educationLevel">
                  <option disabled hidden value=''>Выберите уровень образования...</option>
                  <option v-bind:key="level.id" v-for="level in educationLevels">
                    {{ level.name }}
                  </option>
                </select>
              </div>

              <!-- Форма "Специальность" !-->
              <div class="form-group">
                <span class="has-float-label">
                <label for="specializationCode">
                  <b>Специальность</b>
                </label>
                </span>
                <input type="text" v-model="specializationCode"
                       name="specializationCode" class="form-control" id="specializationCode"
                       placeholder="Введите наименование специальности">
              </div>

              <!-- Форма "Направление обучения" !-->
              <div class="form-group">
                <label for="subjMajor">
                  <b>Направление обучения</b>
                </label>
                <input type="text" v-model="subjMajor"  name="subjMajor" class="form-control" id="subjMajor"
                       placeholder="Введите направление обучения">
              </div>

              <!-- Форма "Источник данных" !-->
              <div class="form-group">
                <label for="resourceToDownload">
                  <b>Источник данных</b>
                </label>
                <select v-model="resourceToDownload"
                        class="form-select"
                        name="resourceToDownload"
                        id="resourceToDownload">
                  <option disabled hidden value=''>Выберите источник профстандартов...</option>
                  <option v-bind:key="option.name" v-for="option in resourceOptions">
                    {{ option.name }}
                  </option>
                </select>
                <!-- Подсказки для выпадающих источников ресурсов !-->
                <p class="help-block" v-if="resourceToDownload === 'Росминтруд'">
                  <i>([https://profstandart.rosmintrud.ru/])</i>
                </p>
              </div>
            </div>
          </form>
        </div>

        <div class="col-md-4">
          <!-- Кнопка "Сброс" !-->
          <div class="row p-3 m-2">
            <button type="button" @click="resetInput" class="btn btn-secondary" id="reset">Сброс</button>
          </div>

          <!-- Кнопка "Справка" !-->
          <div class="row p-3 m-2">
            <button type="button" class="btn btn-info" @click="showModal = true;">
<!--              data-bs-toggle="modal" data-bs-target="#helpModal"-->
              Справка
            </button>
            <ModalInfo :infoData="infoData" v-if="showModal" @close="showModal = false">
<!--              <template v-slot:body>
                Hello, modal!
              </template>-->
            </ModalInfo>
          </div>

          <!-- Кнопка "Подобрать" !-->
          <div class="row p-3 m-2">
            <router-link to="/standards">
              <button @click="getStandards()" type="button" class="btn btn-success" id="getStandards">Подобрать</button>
            </router-link>
          </div>

        </div>

      </div>
    </div>

    <!-- Footer страницы !-->
    <div class="fixed-bottom">
      <div class="row justify-content-center">ProStandard application, 2021, ver. 0.1</div>
    </div>
  </div>
</template>

<script>
import MainDataService from "@/services/MainDataService";
import ModalInfo from "@/components/ModalInfo";

export default {
  name: "Main",
  components: {ModalInfo},
  data() {
    return {
      // Накопленные ошибки работы приложения
      errors: [],
      // Переменные форм
      resourceOptions: [ {id: 0, name: 'Росминтруд'} ],
      educationLevels: [ {id: 0, name: 'Бакалавриат'},
                         {id: 1, name: 'Специалитет'},
                         {id: 2, name: 'Магистратура'}
                       ],
      educationLevel: '',
      specializationCode: '',
      subjMajor: '',
      resourceToDownload: '',
      // Переменные блока "Справка"
      infoData: {
        aboutApp: '',
        appVersion: '',
        developerInfo: '',
        lastUpdateInfo: ''
      },
      // Переменная открытия модального окна
      showModal: false
    }
  },
  methods: {

    resetInput() {
      this.educationLevel = '';
      this.specializationCode = '';
      this.subjMajor = '';
      this.resourceToDownload = '';
    },

    getStandards() {
      let requestData = {
        educationLevel: this.educationLevel,
        specializationCode: this.specializationCode,
        subjMajor: this.subjMajor,
        resourceToDownload: this.resourceToDownload
      };

      console.log(requestData);

      MainDataService.getStandards(requestData).catch(e => {
        if(e.request) {console.log(e.request)} if (e.response) {console.log(e.response)}
      });
    },

    getInfo() {
      return MainDataService.getInfo();
    },

  },
  mounted: function () {
    this.getInfo().then((response) => {
      this.infoData = response.data;
    });
  },

}
</script>

<style>

</style>