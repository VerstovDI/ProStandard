<template>
  <!-- root страницы !-->

  <div class="root-main">
    <div class="wrapper container">
      <!-- Шапка главной страницы !-->
      <MainHeader></MainHeader>

      <!-- Контейнер с формами ввода !-->
      <div class="container">

        <div class="row">
          <div class="col-md-6">
            <form role="form" > <!-- @submit="checkForm" !-->
              <div class="row gy-3">

                <!-- Форма "Уровень образования" !-->
                <div class="form-group">
                  <label for="educationLevel" style="color: lavender">
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
                <label for="specializationCode" style="color: lavender">
                  <b>Специальность</b>
                </label>
                </span>
                  <input type="text" v-model="specializationCode"
                         name="specializationCode" class="form-control" id="specializationCode"
                         placeholder="Введите наименование специальности...">
                </div>

                <!-- Форма "Направление обучения" !-->
                <div class="form-group">
                  <label for="subjMajor" style="color: lavender">
                    <b>Направление обучения</b>
                  </label>
                  <input type="text" v-model="subjMajor"
                         name="subjMajor" class="form-control" id="subjMajor"
                         placeholder="Введите направление обучения...">
                </div>

                <!-- Форма "Источник данных" !-->
                <div class="form-group">
                  <label for="resourceToDownload" style="color: lavender">
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
                    <i style="color: lightgray"><b>([https://profstandart.rosmintrud.ru/])</b></i>
                  </p>
                </div>

                <!-- Форма "Ключевые слова" !-->
                <label for="subjMajor" style="color: lavender">
                  <b>Ключевые слова</b>
                </label>
                <div class="tagsInputClass">
                  <vue-tags-input
                      v-model="tag"
                      :tags="tags"
                      :autocomplete-items="filteredItems"
                      placeholder="Введите ключевое слово..."
                      @tags-changed="newTags => tags = newTags"
                  />
                </div>
              </div>
            </form>
          </div>

          <div class="col-md-4">
            <!-- Кнопка "Сброс" !-->
            <div class="row p-3 m-2">
              <button type="button" class="btn btn-secondary" @click="resetInput" id="reset">
                Сброс
              </button>
            </div>

            <!-- Кнопка "Справка" !-->
            <div class="row p-3 m-2">
              <button type="button" class="btn btn-info" @click="showModal = true;" id="info">
                <!--              data-bs-toggle="modal" data-bs-target="#helpModal"-->
                Справка
              </button>
              <ModalInfo :helpData="helpData" v-if="showModal" @close="showModal = false">
                <!--              <template v-slot:body>
                                Hello, modal!
                              </template>-->
              </ModalInfo>
            </div>

            <!-- Кнопка "Дополнить базу" !-->
            <div class="row p-3 m-2">
              <button type="button" class="btn btn-info" id="enterInfo">
                Дополнить базу
              </button>
            </div>

            <!-- Кнопка "Подобрать" !-->
            <div class="row p-3 m-2">
              <button type="button" class="btn btn-success" id="searchStandards">
                <router-link :to="{ name: 'Standards', params: { educationLevel,
                                                              specializationCode,
                                                              subjMajor,
                                                              resourceToDownload,
                                                              tags } }"
                             style="text-decoration: none; color: inherit;" tag="button">
                  Подобрать
                </router-link>
              </button>
            </div>

          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MainDataService from "@/services/MainDataService";
import ModalInfo from "@/components/Main/MainModalInfo";
import VueTagsInput from '@sipec/vue3-tags-input';
import MainHeader from "@/components/Main/MainHeader";

export default {
  name: "Main",
  components: {MainHeader, ModalInfo, VueTagsInput},
  data() {
    return {

      // Накопленные ошибки работы приложения
      errors: [],

      // Переменные форм
      resourceOptions: [{id: 0, name: 'Росминтруд'}],
      educationLevels: [{id: 0, name: 'Бакалавриат'},
        {id: 1, name: 'Специалитет'},
        {id: 2, name: 'Магистратура'}
      ],
      educationLevel: '',
      specializationCode: '',
      subjMajor: '',
      resourceToDownload: '',
      tags: [],
      tag: '',
      autocompleteItems: [{text: 'Базы данных'},
        {text: 'Программист'},
        {text: 'Аналитик'},
        {text: 'Системный администратор'},
        {text: 'Автоматизированные системы'}
      ],

      // Переменные блока "Справка"
      helpData: {
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
    // Метод сброса введённых пользователем данных
    resetInput() {
      this.educationLevel = '';
      this.specializationCode = '';
      this.subjMajor = '';
      this.resourceToDownload = '';
      this.tag = '';
      this.tags = [];
    },

    // Метод получения справки о приложении
    getInfo() {
      return MainDataService.getInfo();
    },

    // Метод проверки формы ввода: обязателен уровень образования и ресурса стандартов
    checkForm() {
      if (this.educationLevel && this.resourceToDownload) {
        return true;
      }

      this.errors = [];

      if (!this.educationLevel) {
        this.errors.push('Требуется указать уровень образования');
      }

      if (!this.resourceToDownload) {
        this.errors.push('Требуется указать ресурс поиска');
      }
    }
  },

  // Справочная информация прогружается единожды при старте приложения
  /*created: function () {
    this.getInfo().then((response) => {
      this.helpData = response.data;
    });
  },*/

  computed: {
    filteredItems() {
      return this.autocompleteItems.filter(i => {
        return i.text.toLowerCase().indexOf(this.tag.toLowerCase()) !== -1;
      });
    },
  },

}
</script>

<style scoped>

/*.btn {
  border-color: white;
}

.tagsInputClass {
  margin-top: 1px;
  flex-shrink: 0;
  width: 100%;
}

.vue-tags-input {
  max-width: 100%;
}

.help-block {
  margin-bottom: -5px;
}

#reset {
  background-image: url("../../../src/assets/ResetClipArt.png");
  background-repeat: no-repeat;
  background-position: 5px 5px;
  border: none;
  cursor: pointer;
  padding-left: 20px;
  vertical-align: middle;
}

#info {
  background-image: url("../../../src/assets/InfoClipArt.png");
  background-repeat: no-repeat; !* make the background image appear only once *!
  background-position: 5px 5px; !* equivalent to 'top left' *!
  border: none; !* assuming we don't want any borders *!
  cursor: pointer; !* make the cursor like hovering over an <a> element *!
  padding-left: 20px; !* make text start to the right of the image *!
  vertical-align: middle; !* align the text vertically centered *!
}

#searchStandards {
  background-image: url("../../../src/assets/OkClipArt.png");
  backface-visibility: hidden;
  background-repeat: no-repeat; !* make the background image appear only once *!
  background-position: 5px 5px; !* equivalent to 'top left' *!
  border: none; !* assuming we don't want any borders *!
  cursor: pointer; !* make the cursor like hovering over an <a> element *!
  padding-left: 20px; !* make text start to the right of the image *!
  vertical-align: middle; !* align the text vertically centered *!
}

#enterInfo {
  background-image: url("../../../src/assets/IconAddInfo.png");
  backface-visibility: hidden;
  background-repeat: no-repeat; !* make the background image appear only once *!
  background-position: 5px 5px; !* equivalent to 'top left' *!
  border: none; !* assuming we don't want any borders *!
  cursor: pointer; !* make the cursor like hovering over an <a> element *!
  padding-left: 20px; !* make text start to the right of the image *!
  vertical-align: middle; !* align the text vertically centered *!
}*/

</style>