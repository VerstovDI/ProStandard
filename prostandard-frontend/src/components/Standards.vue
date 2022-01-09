<template>
  <div class="root-main">
    <div class="row pt-5 pb-5">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col text-center">
            <h4>Результат подбора стандартов:</h4>
          </div>
        </div>
      </div>
    </div>

    <div class="container">

      <div class="row justify-content-center">

        <div class="col-md-4">
          <!-- Кнопка "Назад", возвращающая пользователя обратно на главную страницу !-->
          <div class="row p-3 m-2">
              <button type="button" class="btn btn-secondary" @click="$router.push('/')" id="reset">
                Назад
              </button>
          </div>
          <div class="row p-3 m-2">
            <button type="button" class="btn btn-success" id="getStandards">
              Ок
            </button>
          </div>
        </div>

        <div class="wrapper">
          <div class="container">
            <div class="row">
              <div class="col-md-8 col-md-offset-2">

                <div class="fresh-table toolbar-color-blue">

                  <!-- Изменение фона таблицы: full-color-blue, full-color-azure, full-color-green, full-color-red, full-color-orange
                  Изменение фона тулбара страницы: toolbar-color-blue, toolbar-color-azure, toolbar-color-green, toolbar-color-red, toolbar-color-orange
                  -->

                  <div class="toolbar">
                    <button id="alertBtn" class="btn btn-default">Alert</button>
                  </div>

                  <table id="fresh-table" class="table">
                    <thead>
                    <th data-field="id">№</th>
                    <th data-field="name" data-sortable="true">Регистрационный номер</th>
                    <th data-field="salary" data-sortable="true">Код профессионального стандарта</th>
                    <th data-field="country" data-sortable="true">Наименование</th>
                    <th data-field="actions" data-formatter="operateFormatter" data-events="operateEvents">Действия</th>
                    </thead>
                    <tbody>
                    <tr v-for="standard in standards" v-bind:key="standard.regNumber">
                      <td> {{ standard.regNumber }}</td>
                      <td> {{ standard.profstandardCode }}</td>
                      <td> {{ standard.profstandardName }}</td>
                      <td>Специалист по организации архитектурно-строительного проектирования</td>
                      <td></td>
                    </tr>
                    </tbody>
                  </table>
                </div>


              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import MainDataService from "@/services/MainDataService";

export default {
  name: "Standards",
  props: {
    // Проброшенные фильтры поиска с родительского компонента Main.vue на текущий (дочерний) компонент с Standards.vue
    educationLevel: String,
    specializationCode: String,
    subjMajor: String,
    resourceToDownload: String,
    keywords: Array
  },

  data() {
    return {
      // Накопленные ошибки работы приложения
      errors: [],

      standards: []
    }
  },

  getStandards() {
    let searchData = {
      educationLevel: this.props.educationLevel,
      specializationCode: this.props.specializationCode,
      subjMajor: this.props.subjMajor,
      resourceToDownload: this.props.resourceToDownload,
      keywords: this.props.getTags
    };
    MainDataService.getStandards(searchData).then(response => {
      this.standards = response.data;
    });
    console.log(this.standards);
  },

  // TODO: добавить что-то в стиле RefreshList
  mounted: function () {
    //this.getStandards();
  }
}
</script>

<style scoped>

</style>