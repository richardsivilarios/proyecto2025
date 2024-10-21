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
          row-key="Pedido"
          v-model:pagination="pagination"
          :loading="loading"
          :filter="state.blurry"
          binary-state-sort
          @request="onRequest"
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
        :cliente="state.selectedCliente"
        @refresh-table="getModulo1DClientesTableFun"
      />
    </q-page-container>
  </q-layout>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import { getModulo1DClientesTable } from '../../../api/modulo1/modulo1';
import { errorMsg } from '../../../utils/message';
import { date } from 'quasar';
import EditDialog from './editDClientes.vue';
import { editCliente } from '../../../api/modulo1/modulo1';

const state = reactive({
  blurry: '',
  tableData: [],
  current: 1,
  size: 10,
  total: 0,
  selectedCliente: null
});

const columns = [
  { name: 'idpedido', label: 'Mi Id de Pedido', align: 'center', field: 'idpedido', sortable: true },
  { name: 'fechapedido', label: 'Fecha de pedido', align: 'left', field: 'fechapedido', sortable: true, format: val => date.formatDate(val, 'DD-MM-YYYY') },
  { name: 'importeventas', label: 'Importe ventas', align: 'left', field: 'importeventas', sortable: true },
  { name: 'nombrecompania', label: 'Nombre de la Compañia', align: 'left', field: 'nombrecompania', sortable: true },
  { name: 'fechaenvio', label: 'Fecha de envio', align: 'left', field: 'fechaenvio', sortable: true, format: val => date.formatDate(val, 'DD-MM-YYYY') },
  { name: 'actions', label: 'Actions', align: 'center', field: 'actions', sortable: false }
];

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

const onEdit = async (row) => {
  try {
    // Asume que 'row.id' contiene el ID del cliente
    console.log('devuelto row');
    console.log(row);
    const { data } = await editCliente({ id: row.idpedido });  // Cambia 'idpedido' si el campo es diferente
    state.selectedCliente = { ...data };  // Asigna los datos del cliente al estado
    dialogVisible.value = true;  // Muestra el diálogo de edición
  } catch (error) {
    console.error('Error al cargar los datos del cliente:', error);
  }
};


const onDelete = (row) => {
  console.log('Deleting row', row.idpedido);
};

onMounted(() => {
  getModulo1DClientesTableFun();
});
</script>
