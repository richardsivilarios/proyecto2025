package taller1.grupo.vueadmin.system.entity.dto;

import lombok.Data;

@Data 
public class ParaleloDto {
     private Long codp;
     private String nombre;
     private Integer estado; // 1: Activo, 0: Baja

}
