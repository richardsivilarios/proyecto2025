<template>
  <q-dialog v-model="visible">
    <q-card class="myClass">
      <q-card-section>
        <div class="text-h6">{{ state.title }}</div>
      </q-card-section>

      <q-card-section>
        <q-form @submit="submitForm">
          <q-input v-model="state.form.id" label="Id" :disable="true" />
          <q-input v-model="state.form.compania" label="Compañía" />
          <q-input v-model="state.form.apellidos" label="Apellidos" />
          <q-input v-model="state.form.nombre" label="Nombre" />
          <q-input v-model="state.form.email" label="Dirección de correo electrónico" />
          <q-input v-model="state.form.cargo" label="Cargo" />
          <q-input v-model="state.form.telefonoTrabajo" label="Teléfono del trabajo" />
          <q-input v-model="state.form.telefonoParticular" label="Teléfono particular" />
          <q-input v-model="state.form.telefonoMovil" label="Teléfono móvil" />
          <q-input v-model="state.form.numeroFax" label="Número de fax" />
          <q-input v-model="state.form.direccion" label="Dirección" />
          <q-input v-model="state.form.ciudad" label="Ciudad" />
          <q-input v-model="state.form.estadoProvincia" label="Estado o provincia" />
          <q-input v-model="state.form.codigoPostal" label="Código Postal" />
          <q-input v-model="state.form.paisRegion" label="País o región" />
          <q-input v-model="state.form.paginaWeb" label="Página Web" />
          <q-input v-model="state.form.notas" label="Notas" type="textarea" />
          <q-input v-model="state.form.datosAdjuntos" label="Datos adjuntos" type="textarea" />
        </q-form>
      </q-card-section>

      <q-card-actions align="right">
        <q-btn flat label="Cancelar" color="negative" @click="visible = false" />
        <q-btn flat label="Guardar" color="primary" @click="submitForm" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup>
import { ref, reactive, computed } from 'vue';
import {errorMsg, successMsg} from "../../../utils/message";
import {resetForm} from "../../../utils/common";
const props = defineProps({
  dialogVisible: {
    type: Boolean,
    required: true,
    default: false
  },
  clienteObj: Object
});

const emit = defineEmits(['update:dialogVisible', 'get-list']);

const visible = computed({
  get: () => props.dialogVisible,
  set: (val) => emit('update:dialogVisible', val)
});

const state = reactive({
  title: 'Editar Cliente',
  form: {
    id: null,
    compania: '',
    apellidos: '',
    nombre: '',
    email: '',
    cargo: '',
    telefonoTrabajo: '',
    telefonoParticular: '',
    telefonoMovil: '',
    numeroFax: '',
    direccion: '',
    ciudad: '',
    estadoProvincia: '',
    codigoPostal: '',
    paisRegion: '',
    paginaWeb: '',
    notas: '',
    datosAdjuntos: ''
  }
});

const openFun = () => {
  if (props.clienteObj.id) {
    state.title = 'Editar Cliente';
    state.form = { ...props.clienteObj };  // Asigna el objeto recibido a los campos del formulario
  }
};


const submitForm = () => {
  emit('get-list', state.form);
  visible.value = false;
};
</script>
<style>
.myClass {
  max-width: 1200px;
  width: 100%;
}
</style>
