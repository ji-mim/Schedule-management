package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.UserResponseDto;
import com.example.schedulemanagement.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
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
}
