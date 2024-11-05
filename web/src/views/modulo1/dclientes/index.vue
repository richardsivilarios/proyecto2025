<template>
  <q-layout view="hHh lpR fFf">
    <q-page-container>
      <div class="q-pa-md">
        <q-table
          color="primary"
          card-class="bg-amber-5 text-brown"
          table-class="text-grey-8"
          table-header-class="text-brown"
          flat
          bordered
          ref="tableRef"
          title="Detalle de Clientes"
          :rows="state.tableData"
          :columns="columns"
          row-key="idcliente"  
          v-model:pagination="pagination"
          :loading="loading"
          :filter="state.blurry"
          binary-state-sort
          @request="onRequest"
          :visible-columns="visibleColumns"
        >
          <template v-slot:top-right>
            <q-input borderless dense debounce="300" v-model="state.blurry" placeholder="Buscar">
              <template v-slot:append>
                <q-icon name="search" @click="getModulo1DClientesTableFun" />
              </template>
            </q-input>
          </template>
          <template v-slot:body-cell-actions="props">
            <q-td :props="props">
              <q-btn @click="onEdit(props.row)" fab-mini dense square outline icon="mode_edit" color="primary" aria-label="Edit" class="q-mr-sm" />
              <q-btn @click="onDelete(props.row)" fab-mini dense square outline icon="delete" color="negative" aria-label="Delete" />
            </q-td>
          </template>
        </q-table>
        <q-pagination
          v-model="pagination.page"
          :max="Math.ceil(state.total / pagination.rowsPerPage)" 
          @update:model-value="getModulo1DClientesTableFun"
        />
      </div>

      <!-- Diálogo de edición -->
      <edit-dialog 
        v-model="dialogVisible"
        :clienteObj="state.selectedCliente"
        @get-list="getModulo1DClientesTableFun"
      ></edit-dialog>
    </q-page-container>
  </q-layout>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import { getModulo1DClientesTable,delModulo1 } from '../../../api/modulo1/modulo1';
import { errorMsg } from '../../../utils/message';
import EditDialog from './editDClientes.vue';

const state = reactive({
  blurry: '',
  tableData: [],
  current: 1,
  size: 10,
  total: 0,
  selectedCliente: null
});

const columns = [
  { name: 'idcliente', label: 'Mi Id Cliente', align: 'center', field: 'idcliente', sortable: true },
  { name: 'compania', label: 'Compania', align: 'left', field: 'compania', sortable: true},
  { name: 'nombre', label: 'Nombre', align: 'left', field: 'nombre', sortable: true},
  { name: 'apellidos', label: 'Apellidos', align: 'left', field: 'apellidos', sortable: true },
  { name: 'cargo', label: 'Cargo', align: 'left', field: 'cargo', sortable: true},
  { name: 'telefonotrabajo', label: 'Telefono Trabajo', align: 'left', field: 'telefonotrabajo', sortable: true},
  { name: 'numerofax', label: 'Numero de Fax', align: 'left', field: 'numerofax', sortable: true},
  
  { name: 'actions', label: 'Acciones', align: 'center', field: 'actions', sortable: false,},
];

const visibleColumns = ref(['idcliente','apellidos','telefonotrabajo','actions']);
const dialogVisible = ref(false);
const loading = ref(false);
const pagination = ref({
  sortBy: 'nombre',
  descending: false,
  page: 1,
  rowsPerPage: 10
});

const getModulo1DClientesTableFun = () => {
  loading.value = true;
  const params = {
    blurry: state.blurry,
    size: state.size,
    currentPage: pagination.value.page
  };
  getModulo1DClientesTable(params).then(res => {
    loading.value = false;
    if (res.success) {
      state.tableData = res.data.records;
      console.log('datos');
      console.log(state.tableData);
      state.total = res.data.total;
    } else {
      errorMsg(res.msg);
    }
  }).catch(() => {
    loading.value = false;
  });
};

const onRequest = (props) => {
  const { page, rowsPerPage, sortBy, descending } = props.pagination;
  pagination.value.sortBy = sortBy || '';
  pagination.value.descending = descending;
  pagination.value.page = page;
  pagination.value.rowsPerPage = rowsPerPage;
  state.current = page;
  state.size = rowsPerPage;
  getModulo1DClientesTableFun();
};

const onEdit = (row) => {
  if (!row || !row.idcliente) {
    console.error('Fila inválida o ID de cliente no encontrado:', row);
    return;
  }
  state.selectedCliente = { ...row };
  dialogVisible.value = true;
};

const onDelete = (row) => {
  console.log('file seleccionada', row.idcliente);
  delModulo1({id:row.idcliente}).then(res => {
   // loading.value = false;
    if (res.success) {
      console.log('se elimino correctamente');
      getModulo1DClientesTableFun();
    } else {
      errorMsg('error en el envio de datos');
    }
  }).catch(() => {
    loading.value = false;
  });
};

onMounted(() => {
  getModulo1DClientesTableFun();
});
</script>
