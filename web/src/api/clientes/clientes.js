import request from "../../utils/request";

/**
 * Obtener lista de registros
 * @param params
 * @returns {AxiosPromise}
 */
export function getClientesList(params){
    return request({
        //url: '/api/sys/clientes/list',
        url: '/api/sys/clientes/table',
        method: 'get',
        params
    })
}
