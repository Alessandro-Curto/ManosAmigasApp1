package com.manosamigas.app.rest;
import com.manosamigas.app.dto.AsignarDto;
import com.manosamigas.app.service.AsignarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/asignaciones")
public class AsignarRest {
    private final AsignarService asignarService;
    public AsignarRest(AsignarService asignarService) { this.asignarService = asignarService; }
    @PostMapping("/{eventoId}/voluntarios/{voluntarioId}")
    public ResponseEntity<?> asignarVoluntarioAEvento(@PathVariable Long eventoId, @PathVariable Long voluntarioId) {
        try {
            AsignarDto asignarDto = new AsignarDto();
            asignarDto.setIdEvento(eventoId);
            asignarDto.setIdVoluntario(voluntarioId);
            asignarService.asignar(asignarDto);
            return ResponseEntity.ok().body("{\"message\": \"Voluntario asignado correctamente.\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}