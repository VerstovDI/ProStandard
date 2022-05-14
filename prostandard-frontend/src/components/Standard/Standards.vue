<template>
  <div class="root-main">
    <StandardsHeader></StandardsHeader>
    <div class="container">

      <div class="row justify-content-center">

        <div class="col-md-4">
          <!-- Кнопка "Назад", возвращающая пользователя обратно на главную страницу !-->
          <div class="row p-3 m-2">
              <button type="button" class="btn btn-secondary" @click="$router.push('/')" id="toHome">
                Назад
              </button>
          </div>
          <div class="row p-3 m-2">
            <button type="button" class="btn btn-success" id="changeStandards">
              Редактировать
            </button>
          </div>
        </div>

        <div class="wrapper">
          <div class="container">
            <div class="row">
              <div class="col-md-8 col-md-offset-2">
                <!-- Таблица с профстандартами !-->
<!--                <StandardsTable :standardList="standards"></StandardsTable>-->
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
  <StandardsTable></StandardsTable>
</template>

<script>
//import MainDataService from "@/services/MainDataService";
import StandardsHeader from "@/components/Standard/StandardsHeader";
import StandardsTable from "@/components/Standard/StandardsTable";

export default {
  name: "Standards",
  components: { StandardsTable, StandardsHeader },
  /*props: {
    // Проброшенные фильтры поиска с родительского компонента Main.vue на текущий (дочерний) компонент с Standards.vue
    educationLevel: String,
    specializationCode: String,
    subjMajor: String,
    resourceToDownload: String,
    tags: Array
  },*/

  data() {
    return {
      // Накопленные ошибки работы приложения
      errors: [],

      standards: []
    }
  },
  /*methods: {
    getStandards() {
      let searchData = {
        educationLevel: this.educationLevel,
        specializationCode: this.specializationCode,
        subjMajor: this.subjMajor,
        resourceToDownload: this.resourceToDownload,
        keywords: this.tags.map(obj => obj.text).join(',')
      };
      console.log(searchData);

      /!*const searchStandardsRequest = async () => {
        try {
          const resp = await MainDataService.searchStandards(searchData);
          this.standards = resp.data;
        } catch (err) {
          console.error(err);
        }
      }*!/
      MainDataService.searchStandards(searchData).then(response => {
        this.standards = response.data;
      }).catch(e => {
        if (e.request) {
          console.log(e.request)
        }
        if (e.response) {
          console.log(e.response)
        }
      })

      console.log(this.standards);
      },
  },
  mounted: function () {
    this.getStandards();
  }*/
}
</script>

<style scoped>

</style>