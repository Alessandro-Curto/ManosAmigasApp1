package com.manosamigas.app.service;
import com.manosamigas.app.dto.RegistrarAsistenciaDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class RegistrarAsistenciaService {
    private final JdbcTemplate jdbcTemplate;
    public RegistrarAsistenciaService(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }
    @Transactional
    public void registrarAsistencia(Long idAsignacion, RegistrarAsistenciaDto dto) {
        String sql = "UPDATE Asignaciones SET asistio = ?, horas_trabajadas = ? WHERE id_asignacion = ?";
        jdbcTemplate.update(sql, dto.isAsistio(), dto.isAsistio() ? dto.getHorasTrabajadas() : 0, idAsignacion);
    }
}