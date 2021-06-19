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
          <div class="row p-3 m-2">
            <router-link to="/">
              <button type="button" class="btn btn-secondary" id="reset">Назад</button>
            </router-link>
          </div>
          <div class="row p-3 m-2">
            <button type="button" class="btn btn-success" id="getStandards">Ок</button>
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
                    <tr>
                      <td>1</td>
                      <td>1460</td>
                      <td>10.015</td>
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
export default {
  name: "Standards"
}
</script>

<style scoped>

</style>