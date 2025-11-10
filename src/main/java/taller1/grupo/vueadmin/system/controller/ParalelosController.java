package taller1.grupo.vueadmin.system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Asegurar todas las anotaciones

import lombok.RequiredArgsConstructor;
import taller1.grupo.vueadmin.common.exception.BadRequestException;
import taller1.grupo.vueadmin.common.utils.ResultUtil;
import taller1.grupo.vueadmin.logs.annotation.Log;
import taller1.grupo.vueadmin.system.entity.dto.QueryParaleloDto;
import taller1.grupo.vueadmin.system.service.ParalelosService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sys")
public class ParalelosController extends ResultUtil {
   private final ParalelosService paralelosService;

    @Log("Consultar lista de Paralelos con filtros")
    @GetMapping("/paralelos/table")
    public ResponseEntity<Object> queryParalelosTable(QueryParaleloDto queryDto) {
        try {
            return success(true, paralelosService.queryUsuarioTable(queryDto));
        } catch (BadRequestException e) {
            return fail(false, e.getMsg());
        }
    }

}
