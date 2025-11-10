package taller1.grupo.vueadmin.system.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;
import taller1.grupo.vueadmin.system.entity.dto.ParaleloDto;
import taller1.grupo.vueadmin.system.entity.dto.QueryParaleloDto;

@Repository
public interface ParalelosMapper  extends BaseMapper<ParaleloDto> {
@Select({
"<script>",
"SELECT codp,nombre,estado",
" FROM academico.paralelo",
" <where>",
"<if test='dto.blurry != null and dto.blurry != \"\"'>",
" (p.nombre ILIKE '%' || #{dto.blurry} || '%' ",
"</if>",
// Filtro por Estado (status) - Debe llevar AND si no es la primera condición
"<if test='dto.status != null'>",
"  AND p.estado = #{dto.status}",
"</if>",
"</where>", // CIERRA EL BLOQUE WHERE
" ORDER BY codp DESC ",
"</script>"
})
IPage<ParaleloDto> queryParalelosTable(Page<?> page, @Param("dto") QueryParaleloDto queryDto);
}
