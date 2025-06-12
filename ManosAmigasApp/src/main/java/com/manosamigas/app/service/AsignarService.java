package com.manosamigas.app.service;
import com.manosamigas.app.dto.AsignarDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class AsignarService {
    private final JdbcTemplate jdbcTemplate;
    public AsignarService(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }
    @Transactional
    public void asignar(AsignarDto dto) {
        String cupoSql = "SELECT e.cupo_maximo, COUNT(a.id_asignacion) FROM Eventos e LEFT JOIN Asignaciones a ON e.id_evento = a.id_evento WHERE e.id_evento = ? GROUP BY e.cupo_maximo";
        jdbcTemplate.queryForObject(cupoSql, (rs, rowNum) -> {
            if (rs.getInt(2) >= rs.getInt(1)) throw new RuntimeException("No hay cupos disponibles");
            return true;
        }, dto.getIdEvento());
        String sql = "INSERT INTO Asignaciones (id_voluntario, id_evento, fecha_asignacion) VALUES (?, ?, GETDATE())";
        jdbcTemplate.update(sql, dto.getIdVoluntario(), dto.getIdEvento());
    }
}