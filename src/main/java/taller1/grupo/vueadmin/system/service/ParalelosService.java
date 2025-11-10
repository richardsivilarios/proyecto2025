package taller1.grupo.vueadmin.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import taller1.grupo.vueadmin.system.entity.dto.ParaleloDto;
import taller1.grupo.vueadmin.system.entity.dto.QueryParaleloDto;

public interface ParalelosService {

    IPage<ParaleloDto> queryUsuarioTable(QueryParaleloDto queryDto);

   }