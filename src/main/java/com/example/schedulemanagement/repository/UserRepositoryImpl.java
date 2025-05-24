package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.UserResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.entity.User;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserResponseDto save(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("email", user.getEmail());
        parameters.put("name", user.getName());
        parameters.put("created_at", user.getCreatedAt());
        parameters.put("updated_at", user.getUpdatedAt());

        jdbcInsert.execute(new MapSqlParameterSource(parameters));

        return new UserResponseDto(user.getEmail(), user.getName(), user.getCreatedAt(), user.getUpdatedAt());
    }

    @Override
    public User findByIdOrElseThrow(String email) {
        String sql = "SELECT * FROM user u WHERE u.email = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper(), email);

        return users.stream().findAny().orElseThrow(() -> {
            log.warn("조회 실패: 존재하지 않는 email {}", email);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists email = " + email);
        });
    }


    //엔티티 반환
    private RowMapper<User> userRowMapper() {
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        };
    }

}
