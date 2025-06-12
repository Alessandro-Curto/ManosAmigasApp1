package com.manosamigas.app.rest;
import com.manosamigas.app.dto.RegistrarAsistenciaDto;
import com.manosamigas.app.service.RegistrarAsistenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/asistencia")
public class RegistrarAsistenciaRest {
    private final RegistrarAsistenciaService registrarAsistenciaService;
    public RegistrarAsistenciaRest(RegistrarAsistenciaService registrarAsistenciaService) { this.registrarAsistenciaService = registrarAsistenciaService; }
    @PutMapping("/{idAsignacion}")
    public ResponseEntity<?> registrarAsistencia(@PathVariable Long idAsignacion, @RequestBody RegistrarAsistenciaDto dto) {
        try {
            registrarAsistenciaService.registrarAsistencia(idAsignacion, dto);
            return ResponseEntity.ok().body("{\"message\": \"Asistencia registrada correctamente.\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}