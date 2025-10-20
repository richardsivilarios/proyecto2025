<template>
<q-layout view="hHh lpR fFf">
 <q-page-container>
    <div class="q-pa-md">
        <q-table
        title="Lista de Usuarios"
        :columns="columns"
        :rows="state.tableData"
        row-key="idusuario"
        virtual-scroll
        :visible-columns="visibleColumns"
        >
        <template v-slot:top-left>
            <q-btn
            color="primary"
            icon="add"
            label="nuevo cliente"
            @click="onEdit()"
            >
            </q-btn>

        </template> 
        </q-table>


    </div>
 </q-page-container>
</q-layout>
</template>
<script setup>
import {ref,reactive,onMounted} from 'vue'
import {queryUsuarioTable} from '../../api/usuario/usuario'
import { errorMsg } from '../../utils/message';
import Pagination from '../../components/Pagination.vue'

const state = reactive({
blurry:'',
tableData:[],
userObj:{},
current:1,
size: 10,
total:0,
selectedUsuario: null,
}
)
const columns = [
    {name:'idusuario',label:'ID Usuario',align:'center',field:'idusuario',sortable:true},
    {name:'nombre',label:'Nombre',align:'center',field:'nombre',sortable:false},
    {name:'ap',label:'Apellido \n Paterno',align:'left',field:'ap',sortable:true},
    {name:'am',label:'Apellido \n Materno',align:'center',field:'am',sortable:true}
]
const visibleColumns = ref(['nombre', 'ap'])
const queryUsuarioTableFun=()=>{
//loading.value = true 
const params = {
    blurry: state.blurry,
    size: state.size,
    currentPage: state.current
}
queryUsuarioTable(params)
.then((res)=>{
//loading.value=false
if (res.success) {
    state.tableData = res.data.records
    state.total = res.data.total
} else {
    errorMsg(res.msg)
}
})
.catch(()=>{
    //loading.value= false
    errorMsg('Error al cargar los datos')
})}  

const onEdit = () => {
    console.log('ADICIONAR UN USUARIO')
}
onMounted(() => {
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
