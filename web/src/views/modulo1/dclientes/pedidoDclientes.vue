<template>
  <q-dialog v-model="dialogVisible" persistent>
    <q-card>
      <q-card-section>
        <div class="text-h6">Pedidos del Cliente</div>
      </q-card-section>
<q-table
          color="primary"
          card-class="bg-amber-5 text-brown"
          table-class="text-grey-8"
          table-header-class="text-brown"
          flat
          bordered
          ref="tableRef"
          title="Pedidos"
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
              <q-btn @click="onDelete(props.row)" fab-mini dense square outline icon="delete" color="negative" aria-label="Eliminar" />
              <q-btn @click="onPedidos(props.row)" fab-mini dense square outline icon="table_view" color="negative" aria-label="Detalle" />
            </q-td>
          </template>
        </q-table>

        <q-pagination
          v-model="pagination.page"
          :max="Math.ceil(state.total / pagination.rowsPerPage)" 
          @update:model-value="getModulo1DClientesTableFun"
        />

      <!-- Diálogo de edición -->
      <edit-dialog 
        v-model="dialogVisible"
        :clienteObj="state.selectedCliente"
        @get-list="getModulo1DClientesTableFun"
      ></edit-dialog>
    </q-card>
  </q-dialog>
</template>
<script setup>
import { ref, reactive, onMounted, watch } from 'vue';
import { getModulo1DClientesPedidosTable } from '../../../api/modulo1/modulo1';
import { errorMsg } from '../../../utils/message';

// Recibir el idcliente como prop
const props = defineProps(['idcliente']);
const dialogVisible = ref(true);

const state = reactive({
  blurry: '',
  tableData: [],
  total: 0
});
const pagination = ref({
  page: 1,
  rowsPerPage: 10
});

const getModulo1DClientesPedidosTableFun = () => {
  const params = {
    blurry: state.blurry,
    size: pagination.value.rowsPerPage,
    currentPage: pagination.value.page
  };
  getModulo1DClientesPedidosTable(props.idcliente, params).then(res => {
    if (res.success && res.data && res.data.records) {
      state.tableData = res.data.records;
      state.total = res.data.total;
    } else {
      errorMsg(res.msg);
    }
  });
};

// Llamar a la función cuando se recibe el idcliente
onMounted(() => {
  getModulo1DClientesPedidosTableFun();
});

watch(() => props.idcliente, () => {
  getModulo1DClientesPedidosTableFun();
});
</script>