package taller1.grupo.vueadmin.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.RequiredArgsConstructor;
import taller1.grupo.vueadmin.system.entity.Personal;
import taller1.grupo.vueadmin.system.entity.dto.ParaleloDto;
import taller1.grupo.vueadmin.system.entity.dto.QueryParaleloDto;
import taller1.grupo.vueadmin.system.mapper.ParalelosMapper;
import taller1.grupo.vueadmin.system.service.ParalelosService;


@Service
@RequiredArgsConstructor
public class ParalelosServiceImpl  implements ParalelosService{
private final ParalelosMapper paralelosMapper;
    @Override
    public IPage<ParaleloDto> queryUsuarioTable(QueryParaleloDto queryDto) {
        Page<ParaleloDto> page = new Page<>();
        page.setCurrent(queryDto.getCurrentPage());
        page.setSize(queryDto.getSize());
        return paralelosMapper.queryParalelosTable(page, queryDto); 
    }

}
