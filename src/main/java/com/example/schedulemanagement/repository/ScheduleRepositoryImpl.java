package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto save(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("contents", schedule.getContents());
        parameters.put("username", schedule.getUsername());
        parameters.put("password", schedule.getPassword());
        parameters.put("created_at", schedule.getCreatedAt());
        parameters.put("updated_at", schedule.getUpdatedAt());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), schedule.getUsername(), schedule.getContents(), schedule.getCreatedAt(), schedule.getUpdatedAt());


    }

    @Override
    public List<ScheduleResponseDto> findAll(String username, LocalDateTime updatedAt) {
        String sql = "SELECT * FROM schedule s WHERE s.username = ? OR DATE(s.updated_at) = ? ORDER BY s.updated_at DESC ";
        return jdbcTemplate.query(
                sql,
                new Object[]{username, updatedAt},
                scheduleRowMapper()
        );
    }

    @Override
    public Schedule findByIdOrElseThrow(Long id) {
        String sql = "SELECT * FROM schedule s where s.id = ?";
        List<Schedule> result = jdbcTemplate.query(sql, scheduleRowMapper2(), id);
        return result.stream().findAny().orElseThrow(() -> {
            log.warn("조회 실패: 존재하지 않는 ID {}", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = " + id);
        });
    }

    @Override
    public void updateSchedule(Long id, String username, String contents, LocalDateTime updatedAt) {
        jdbcTemplate.update("UPDATE schedule SET username = ?, contents = ?, updated_at = ? WHERE id = ?", username, contents, updatedAt, id);
    }

    @Override
    public void deleteSchedule(Long id) {
        jdbcTemplate.update("DELETE FROM schedule WHERE id = ?", id);
    }


    //Dto 반환
    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("contents"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        };
    }

    //엔티티 반환
    private RowMapper<Schedule> scheduleRowMapper2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("password"),
                        rs.getString("username"),
                        rs.getString("contents"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        };
    }
}
