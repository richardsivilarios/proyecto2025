package taller1.grupo.vueadmin.system.entity.dto;

import lombok.Setter;

import java.util.Date;

import lombok.Getter;

@Setter
@Getter
public class Modulo1top10DClientesDto {
    private Long idpedido;
    private Date fechapedido;
    private float importeventas;
    private String nombrecompania;
    private Date fechaenvio;
}