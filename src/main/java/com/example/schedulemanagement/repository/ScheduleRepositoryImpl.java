package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.PagedScheduleResponseDto;
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
        parameters.put("user_email", schedule.getUserEmail());
        parameters.put("password", schedule.getPassword());
        parameters.put("created_at", schedule.getCreatedAt());
        parameters.put("updated_at", schedule.getUpdatedAt());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), schedule.getUserEmail(), schedule.getContents(), schedule.getCreatedAt(), schedule.getUpdatedAt());


    }

    @Override
    public List<ScheduleResponseDto> findAll(String userEmail, LocalDateTime updatedAt) {
        String sql = "SELECT * FROM schedule s WHERE s.user_email = ? OR DATE(s.updated_at) = ? ORDER BY s.updated_at DESC ";
        return jdbcTemplate.query(
                sql,
                new Object[]{userEmail, updatedAt},
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
    public void updateSchedule(Long id, String userEmail, String contents, LocalDateTime updatedAt) {
        jdbcTemplate.update("UPDATE schedule SET user_email = ?, contents = ?, updated_at = ? WHERE id = ?", userEmail, contents, updatedAt, id);
    }

    @Override
    public void deleteSchedule(Long id) {
        jdbcTemplate.update("DELETE FROM schedule WHERE id = ?", id);
    }

    @Override
    public List<PagedScheduleResponseDto> findPagedSchedules(String userEmail, LocalDateTime updatedAt, int page, int size) {
        String sql = """
        SELECT s.*, u.name
        FROM schedule s
        JOIN user u ON s.user_email = u.email
        WHERE s.user_email = ? OR DATE(s.updated_at) = ?
        ORDER BY s.updated_at DESC
        LIMIT ? OFFSET ?
    """;
        return jdbcTemplate.query(
                sql,
                new Object[]{userEmail, updatedAt, size, page * size},
                scheduleRowMapper3()
        );

    }

    @Override
    public Long countSchedules(String userEmail, LocalDateTime updatedAt) {
        String sql = "SELECT count(*) FROM schedule s WHERE s.user_email = ? OR DATE(s.updated_at) = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userEmail, updatedAt}, Long.class);
    }


    //Dto 반환
    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("user_email"),
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
                        rs.getString("user_email"),
                        rs.getString("contents"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        };
    }

    //엔티티 반환
    private RowMapper<PagedScheduleResponseDto> scheduleRowMapper3() {
        return new RowMapper<PagedScheduleResponseDto>() {
            @Override
            public PagedScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PagedScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("user_email"),
                        rs.getString("name"),
                        rs.getString("contents"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        };
    }
}
