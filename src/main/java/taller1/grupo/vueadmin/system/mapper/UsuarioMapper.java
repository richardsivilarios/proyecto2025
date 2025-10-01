package taller1.grupo.vueadmin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import taller1.grupo.vueadmin.system.entity.Personal;

@Repository
public interface UsuarioMapper extends BaseMapper<Personal> {

    IPage<Personal> queryUsuarioTable(Page<?> page, @Param("blurry") String blurry);

   
}