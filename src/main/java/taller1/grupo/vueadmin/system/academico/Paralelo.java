package taller1.grupo.vueadmin.system.academico;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.*;
import taller1.grupo.vueadmin.common.utils.BaseEntity;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@TableName(value = "paralelo", schema = "academico")
public class Paralelo extends BaseEntity {
private Integer codp; 
private String nombre;
private Integer estado; 
}
