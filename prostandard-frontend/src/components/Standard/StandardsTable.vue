<template>
  <table class="table">
    <thead>
    <tr>
      <th scope="col">Рег. номер</th>
      <th scope="col">Код профстандарта</th>
      <th scope="col">Наименование профстандарта</th>
      <th scope="col">Обращение</th>
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
    </tbody>
  </table>
</template>

<script>
export default {
  name: "StandardsTable",
  data() {
    return {
      standards: [],
    };
  },
  created: function () {
    this.fillTable();
  },
  methods: {
    fillTable: function () {
      // вызвать метод подбора стандартов
    }
  }
}
</script>

<style scoped>

</style>