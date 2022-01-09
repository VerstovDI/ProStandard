<template>
  <!-- root страницы !-->

  <!-- Пробрасываем данные формы на компонент вывода стандартов !-->
<!--  <Standards v-on: :education-level="this.educationLevel" :specialization-code="this.specializationCode"
              :subj-major="this.subjMajor" :resource-to-download="this.resourceToDownload" :keywords="this.tags"/>-->

  <div class="root-main">
    <div class="row pt-5 pb-5">
      <div class="container">
        <div class="row">
          <div class="col text-center">
            <h4>Выберите необходимые параметры для формирования компетенций</h4>
          </div>
        </div>
      </div>
    </div>

    <!-- Контейнер с формами ввода !-->
    <div class="container">

      <div class="row">
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
                       placeholder="Введите наименование специальности...">
              </div>

              <!-- Форма "Направление обучения" !-->
              <div class="form-group">
                <label for="subjMajor">
                  <b>Направление обучения</b>
                </label>
                <input type="text" v-model="subjMajor"
                       name="subjMajor" class="form-control" id="subjMajor"
                       placeholder="Введите направление обучения...">
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

              <!-- Форма "Ключевые слова" !-->
              <label for="subjMajor">
                <b>Ключевые слова</b>
              </label>
              <div>
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
            <button type="button" class="btn btn-info" @click="showModal = true;">
<!--              data-bs-toggle="modal" data-bs-target="#helpModal"-->
              Справка
            </button>
            <ModalInfo :helpData="helpData" v-if="showModal" @close="showModal = false">
<!--              <template v-slot:body>
                Hello, modal!
              </template>-->
            </ModalInfo>
          </div>

          <!-- Кнопка "Подобрать" !-->
          <div class="row p-3 m-2">
<!--            <router-link to="/standards" tag="button">-->
              <button type="button" class="btn btn-success"
                      @click="$router.push('/standards'); sendSearchFilters()" id="searchStandards">
                Подобрать
                <!-- TODO: вынести sendSearchFilters() на mount() страницы с профстандартами, а не на вызов по клику !-->
              </button>
<!--            </router-link>-->
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
import VueTagsInput from '@sipec/vue3-tags-input';

export default {
  name: "Main",
  components: { ModalInfo, VueTagsInput },
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
      tags: [],
      tag: '',
      autocompleteItems: [ { text: 'Базы данных'},
                           { text: 'Программист' },
                           { text: 'Аналитик' },
                           { text: 'Системный администратор' },
                           { text: 'Автоматизированные системы' }
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

    // Метод получения подобранных профстандартов
    sendSearchFilters() {
      let requestData = {
        educationLevel: this.educationLevel,
        specializationCode: this.specializationCode,
        subjMajor: this.subjMajor,
        resourceToDownload: this.resourceToDownload,
        keywords: this.getTags
      };
      console.log(requestData);
      MainDataService.searchStandards(requestData).catch(e => {
        if (e.request) {
          console.log(e.request)
        }
        if (e.response) {
          console.log(e.response)
        }
      });
    },

    // Метод получения справки о приложении
    getInfo() {
      return MainDataService.getInfo();
    }
  },

  // Справочная информация прогружается единожды при старте приложения
  mounted: function () {
    this.getInfo().then((response) => {
      this.helpData = response.data;
    });
  },

  computed: {
    filteredItems() {
      return this.autocompleteItems.filter(i => {
        return i.text.toLowerCase().indexOf(this.tag.toLowerCase()) !== -1;
      });
    },

    getTags() {
      return this.tags.map(obj => obj.text);
    }
  },

}
</script>

<style>
  .btn {
    border-color: white;
  }
  /*.root-main {
    background-size: contain;
    background: url("~@/assets/MephiBlur.jpg") center center;
  }*/
</style>