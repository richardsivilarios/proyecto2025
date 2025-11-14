// Archivo: web/src/api/paralelos/paralelos.js
import request from '../../utils/request'

// Consulta principal
export function queryParalelosTable(params){
    return request({
        //url: '/api/sys/paraleloos/table',
        url: '/api/sys/paralelos/table',
        method: 'get',
        params
    })
}
