<template>
  <div id="standardsTable">

  </div>

<!--  <table class="table">
    <thead>
    <tr>
      <th scope="col">Рег. номер</th>
      <th scope="col">Код профстандарта</th>
      <th scope="col">Наименование профстандарта</th>
    </tr>
    </thead>
    <tbody>
    <tr v-if="standards.length === 0">
      <td>Не найдено удовлетворяющих критериям стандартов</td>
    </tr>
    <tr v-else v-for="standard in standards" :key="standard.id">
      <td>{{ standard.id }}</td>
      <td>{{ standard.regNumber }}</td>
      <td>{{ standard.profstandardCode }}</td>
      <td>{{ standard.profstandardName }}</td>
    </tr>
    </tbody>-->
</template>

<script>
import VGrid from "@revolist/vue3-datagrid";

export default {
  name: "StandardsTable",
  /*props: {
    standardList: Array
  },*/
  data() {
    return {
      data: [
        { 'id': 1, 'first_name': 'Jesse', 'last_name': 'Simmons', 'date': '2016-10-15 13:43:27', 'gender': 'Male' },
        { 'id': 2, 'first_name': 'John', 'last_name': 'Jacobs', 'date': '2016-12-15 06:00:53', 'gender': 'Male' },
        { 'id': 3, 'first_name': 'Tina', 'last_name': 'Gilbert', 'date': '2016-04-26 06:26:28', 'gender': 'Female' },
        { 'id': 4, 'first_name': 'Clarence', 'last_name': 'Flores', 'date': '2016-04-10 10:28:46', 'gender': 'Male' },
        { 'id': 5, 'first_name': 'Anne', 'last_name': 'Lee', 'date': '2016-12-06 14:38:38', 'gender': 'Female' }
      ],
      columns: [
        {
          field: 'id',
          label: 'ID',
          width: '40',
          numeric: true
        },
        {
          field: 'first_name',
          label: 'First Name',
        },
        {
          field: 'last_name',
          label: 'Last Name',
        },
        {
          field: 'date',
          label: 'Date',
          centered: true
        },
        {
          field: 'gender',
          label: 'Gender',
        }
      ],
      // Настройки таблицы
      /*columns: [
        { prop: "regNumber", name: "s" },
        { prop: "profstandardCode", name: "b" },
        { prop: "profstandardName", name: "c" }
      ],*/
      rows: [{ regNumber: '1', profstandardCode: 't', profstandardName: '23'}],

      standards: []
    };
  },
  components: { VGrid, },
}
</script>

<style scoped>
revo-grid {
  height: 100%;
}
</style>