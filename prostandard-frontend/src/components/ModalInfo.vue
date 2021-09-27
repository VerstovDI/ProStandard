<template>
  <transition name="modal">
    <div class="modal" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Справка о приложении ProStandard</h5>
                <!-- Кнопка "Крестик" в правом верхнем углу модального окна !-->
<!--            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>-->
          </div>
          <div class="modal-body">
            <p><b>О приложении:</b> {{infoData.aboutApp}}</p>
            <p><b>Версия приложения:</b> {{infoData.appVersion}}</p>
            <p><b>О разработчике:</b> {{infoData.developerInfo}}</p>
            <p><b>Дата последнего обновления:</b> {{infoData.lastUpdateInfo}}</p>
          </div>
          <!-- Кнопка "Ок" модального окна справки !-->
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="$emit('close')">
              Ок</button>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>

export default {
  name: "ModalInfo",
  props: {
    // Свойство infoData, хранящее
    infoData: {
          type: String,
          required: true
    }
  },
  data() {
    return {
    }
  }
}
</script>

<style>
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.7);
  display: table;
  transition: opacity .5s ease;
}

.modal-dialog {
   display: table-cell;
   vertical-align: middle;
 }

.modal-content {
   background: #fff;
   width: 750px;
   border-radius: 5px;
   box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
   transition: all .5s ease;
   margin: 0 auto;
   padding: 20px 30px;
 }

.modal-footer {
   margin-top: 15px;
 }
</style>