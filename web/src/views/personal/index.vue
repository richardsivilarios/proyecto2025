<template>
<!--personal-->
<q-layout view="hHh lpR fFf">
     <q-page-container>
      <div>
        <q-table
         class="my-sticky-header-column-table"
      flat bordered
          title="Detalle Usuario"
          color="primary"
          table-class="text-grey-8"
          table-header-class="text-brown"        
          ref="tableRef"
          :rows="state.tableData"
          :columns="columns"
          row-key="idusuario"
          virtual-scroll
          :loading="loading"
          :filter="state.blurry"
          binary-state-sort
          :visible-columns="visibleColumns"
          :rows-per-page-options="[0]"
        >
         <template v-slot:top>
                <div class="row full-width q-col-gutter-sm items-center">
                    <div class="col-xs-12 col-sm-auto q-mb-md">
                        <q-btn
                            color="primary"
                            icon="add"
                            label="+ usuario"
                            @click="onEdit()"
                        />
                    </div>
                    
                    <div class="col-xs-12 col-sm-4 q-mb-md">
                        <q-input
                            dense
                            debounce="300"
                            v-model="state.blurry"
                            placeholder="Buscar (Nombre + Apellidos)"
                            @keyup.enter="queryUsuarioTableFun"
                            clearable
                        >
                            <template v-slot:append>
                                <q-icon name="search" @click="queryUsuarioTableFun" class="cursor-pointer" />
                            </template>
                        </q-input>
                    </div>

                    <div class="col-xs-12 col-sm-3 q-mb-md">
                        <q-btn-toggle
                            v-model="state.statusFilter"
                            toggle-color="primary"
                            size="sm"
                            no-caps
                            @update:model-value="queryUsuarioTableFun"
                            :options="[
                                {label: 'Activos', value: '1'},
                                {label: 'Bajas', value: '0'},
                                {label: 'Todos', value: '2'}
                            ]"
                            class="full-width"
                        />
                    </div>
                    
                    <div class="col-xs-12 col-sm-3 q-mb-md">
                        <q-btn-toggle
                            v-model="state.typeFilter"
                            toggle-color="accent"
                            size="sm"
                            no-caps
                            @update:model-value="queryUsuarioTableFun"
                            :options="[
                                {label: 'ESTUDIANTE', value: 'ESTUDIANTE'},
                                {label: 'DOCENTE', value: 'DOCENTE'},
                                {label: 'TODOS', value: 'TODOS'}
                            ]"
                            class="full-width"
                        />
                    </div>
                </div>
            </template>
      
        </q-table>

     
     </div>
       <div class="q-pa-md row justify-center">
            <Pagination
                :current="state.current"
                :total="state.total"
                :page-size="state.size"
                @changePage="queryUsuarioTableFun"
            />
        </div>
    </q-page-container>
</q-layout view="hHh lpR fFf">

</template>
<script setup >
import {ref, reactive, onMounted} from 'vue'
import {queryUsuarioTable} from '../../api/usuario/usuario'
import {errorMsg} from '../../utils/message'
import Pagination from '../../components/Pagination.vue'
// ESTADO DE LA TABLA
const state = reactive ({
    blurry:'',
    tableData:[],
    current:1,
    size:10, 
    total: 0,
    statusFilter: '2', 
    typeFilter: 'TODOS', 
})

const columns = [
 {name:'idusuario',label:'Id Usuario',align:'center',field:'idusuario',sortable:true},
 {name:'nombre',label:'Nombre',align:'left',field:'nombre',sortable:true},
 {name:'estado',label:'Estado',align:'center',field:'estado',sortable:true},
  ]

 const visibleColumns = ref(['idusuario','nombre','estado'])

// Funcion principal para consultar la tabla
const queryUsuarioTableFun = () => {
    let statusParam = null;
    if (state.statusFilter === '1' || state.statusFilter === '0') {
        statusParam = parseInt(state.statusFilter);
    } 
    const typeParam = state.typeFilter === '' ? null : state.typeFilter
    const params = {
        blurry: state.blurry,
        size: state.size,
        currentPage: state.current,
        status: statusParam, 
        type: typeParam, 
    }
    queryUsuarioTable(params)
    .then((res)=> {
        if (res.success) {
            state.tableData = res.data.records
            state.total = res.data.total
        } else {
            errorMsg(res.mgs || 'Error al cargar los datos de usuario.')
        }
    })
    .catch(()=>{
        errorMsg('Error al cargar los datos')
    })
}


onMounted(() => {
 // CORREGIDO: Se usa el nombre de función correcto
 queryUsuarioTableFun()
})

</script>
<style lang="sass">
.my-sticky-header-column-table
  height: 80vh
  width: 100%
  max-width: 100%
  background: white

  tr th
    position: sticky
    z-index: 2
    background: #00b4ff

  thead tr:first-child th
    top: 0
    z-index: 3

  td:first-child, th:first-child
    position: sticky
    left: 0
    background-color: #e0f7ff
</style>
