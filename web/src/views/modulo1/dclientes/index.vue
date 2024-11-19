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
          <template v-slot:top>
            <q-btn icon="new">Nuevo</q-btn>
            <q-space />
          </template>
          <template v-slot:top-right>
            <q-input borderless dense debounce="300" v-model="state.blurry" placeholder="Buscar">
              <template v-slot:append>
                <q-icon name="search" @click="getModulo1DClientesTableFun" />
              </template>
            </q-input>
          </template>
          <template v-slot:body-cell-actions="props">
            <q-td :props="props">
              <q-btn
                @click="onEdit(props.row)"
                fab-mini
                dense
                square
                outline
                icon="mode_edit"
                color="primary"
                aria-label="Editar"
                class="q-mr-sm"
              />
              <q-btn
                @click="onDelete(props.row)"
                fab-mini
                dense
                square
                outline
                icon="delete"
                color="negative"
                aria-label="Eliminar"
              />
              <q-btn
                @click="onPedidos(props.row)"
                fab-mini
                dense
                square
                outline
                icon="table_view"
                color="negative"
                aria-label="Detalle"
              />
            </q-td>
          </template>
        </q-table>
        <q-pagination
          v-model="pagination.page"
          :max="Math.ceil(state.total / pagination.rowsPerPage)"
          @update:model-value="getModulo1DClientesTableFun"
        />
      </div>

      <!-- Componente de pedidos -->
      <pedidoDclientes
        v-model="pedidoDialogVisible"
        :cliente-obj="state.selectedCliente"
        @close="pedidoDialogVisible = false"
      />
    </q-page-container>
  </q-layout>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import { getModulo1DClientesTable, delModulo1 } from '../../../api/modulo1/modulo1';
import { errorMsg } from '../../../utils/message';
import { useQuasar } from 'quasar';
import pedidoDclientes from './pedidoDclientes.vue';

const $q = useQuasar();
const state = reactive({
  blurry: '',
  tableData: [],
  current: 1,
  size: 10,
  total: 0,
  selectedCliente: null,
});

const columns = [
  { name: 'idcliente', label: 'ID Cliente', align: 'center', field: 'idcliente', sortable: true },
  { name: 'nombre', label: 'Nombre', align: 'left', field: 'nombre', sortable: true },
  { name: 'actions', label: 'Acciones', align: 'center', field: 'actions', sortable: false },
];

const visibleColumns = ref(['idcliente', 'nombre', 'actions']);
const loading = ref(false);
const pagination = ref({ sortBy: 'nombre', descending: false, page: 1, rowsPerPage: 10 });

const pedidoDialogVisible = ref(false);

const getModulo1DClientesTableFun = () => {
  loading.value = true;
  const params = {
    blurry: state.blurry,
    size: state.size,
    currentPage: pagination.value.page,
  };
  getModulo1DClientesTable(params)
    .then((res) => {
      loading.value = false;
      if (res.success) {
        state.tableData = res.data.records;
        state.total = res.data.total;
      } else {
        errorMsg(res.msg);
      }
    })
    .catch(() => {
      loading.value = false;
      errorMsg('Error al cargar los datos');
    });
};

const onPedidos = (row) => {
  //console.log('richard sivila:', row.idcliente);
  
  if (!row || !row.idcliente) return;
  state.selectedCliente = { ...row }; // Asignar cliente seleccionado
  
  //console.log('state.selectedCliente');
  //console.log(state.selectedCliente);
  pedidoDialogVisible.value = true; // Mostrar diálogo
};

onMounted(() => {
  getModulo1DClientesTableFun();
});
</script>
