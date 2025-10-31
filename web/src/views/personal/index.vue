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
          :rows="state.tableData"
          :columns="columns"
          row-key="idusuario"
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
import {queryUsuarioTable} from '../../api/usuario/usuario'
import {errorMsg} from '../../utils/message'

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
 {name:'idusuario',label:'Id Usuario',align:'center',field:'idusuario',sortable:true},
 {name:'nombre',label:'Nombre',align:'left',field:'nombre',sortable:true} ]

 const visibleColumns = ref(['idusuario','nombre'])

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
        // status activo inactivo todos
        status: statusParam, 
        // TODOS DOCENTES ESTUDIANTES
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
