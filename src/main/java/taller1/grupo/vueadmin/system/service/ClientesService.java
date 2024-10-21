package taller1.grupo.vueadmin.system.service;

import taller1.grupo.vueadmin.system.entity.Clientes;
import taller1.grupo.vueadmin.system.entity.dto.Clientes2Dto;
import taller1.grupo.vueadmin.system.entity.dto.ClientesDto;
import taller1.grupo.vueadmin.system.entity.dto.QueryDto;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

public interface ClientesService {
    public List<Clientes> getClientesList(String blurry);

    public IPage<ClientesDto> queryClientesTable(QueryDto queryDto);

    void editClientes(Clientes2Dto clientes2Dto);
}