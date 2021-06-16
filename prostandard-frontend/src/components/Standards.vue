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