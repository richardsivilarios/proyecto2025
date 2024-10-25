package taller1.grupo.vueadmin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import taller1.grupo.vueadmin.system.entity.*;
import taller1.grupo.vueadmin.system.entity.dto.ClientesDto;

@Repository
public interface ClientesMapper extends BaseMapper<Clientes> {
    IPage<ClientesDto> queryClientesTable(Page<?> page, @Param("blurry") String blurry);

    @Select("SELECT \"Id\", \"Compañía\", \"Apellidos\", \"Nombre\", \"Dirección de correo electrónico\", \"Cargo\", \"Teléfono del trabajo\", \"Teléfono particular\", \"Teléfono móvil\", \"Número de fax\", \"Dirección\", \"Ciudad\", \"Estado o provincia\", \"C Postal\", \"País o región\", \"Página Web\", \"Notas\", \"Datos adjuntos\"\r\n"
            + //
            "\tFROM northwind.\"Clientes\" WHERE \"id\"=:clientesId ")
    List<Clientes> getClientesByClientesId(@Param("clientesId") Long clientesId);
}