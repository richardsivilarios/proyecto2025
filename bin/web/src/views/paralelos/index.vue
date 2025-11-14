<template>
<!--paralelos q-table-->
<q-layout view="hHh lpR fFf">
     <q-page-container>
      <div>
        <q-table
         class="my-sticky-header-column-table"
      flat bordered
          title="Detalle Paralelos"
          color="primary"
          table-class="text-grey-8"
          table-header-class="text-brown"
          :rows="state.tableData"
          :columns="columns"
          row-key="codp"
          virtual-scroll
          :visible-columns="visibleColumns"
        >
        </q-table>

     
     </div>
    </q-page-container>
</q-layout view="hHh lpR fFf">

</template>
<script setup >
import {ref, reactive, onMounted} from 'vue'
import {queryParalelosTable} from '../../api/paralelos/paralelos'
import {errorMsg} from '../../utils/message'
import { date } from 'quasar'
// ESTADO DE LA TABLA
const state = reactive ({
    blurry:'',
    tableData:[],
    current:1,
    size:10, 
    total: 0,
    statusFilter: '', 
    typeFilter: '', 
})

const columns = [
 {name:'codp',label:'Cod Paralelo',align:'center',field:'codp',sortable:true},
 {name:'nombre',label:'Nombre',align:'left',field:'nombre',sortable:true},
 {name:'estado',label:'Estado',align:'left',field:'estado',sortable:true},

 ]

 const visibleColumns = ref(['codp','nombre','estado'])

// Funcion principal para consultar la tabla
const queryParalelosTableFun = () => {
    let statusParam = null;
    if (state.statusFilter === '1' || state.statusFilter === '0') {
        statusParam = parseInt(state.statusFilter);
    } 
    const typeParam = state.typeFilter === '' ? null : state.typeFilter
    const params = {
        blurry: state.blurry,
        size: state.size,
        currentPage: state.current,
        // status activo inactivo todos
        status: statusParam, 
        // TODOS DOCENTES ESTUDIANTES
        type: typeParam, 
    }
    queryParalelosTable(params)
    .then((res)=> {
        if (res.success) {
            state.tableData = res.data.records
            state.total = res.data.total
        } else {
            errorMsg(res.mgs || 'Error al cargar los datos del paralelo.')
        }
    })
    .catch(()=>{
        errorMsg('Error al cargar los datos')
    })
}


onMounted(() => {
 // CORREGIDO: Se usa el nombre de función correcto
 queryParalelosTableFun()
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
