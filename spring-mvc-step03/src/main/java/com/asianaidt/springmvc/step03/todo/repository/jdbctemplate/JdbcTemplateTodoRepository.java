package com.asianaidt.springmvc.step03.todo.repository.jdbctemplate;

import com.asianaidt.springmvc.step03.todo.domain.Todo;
import com.asianaidt.springmvc.step03.todo.repository.TodoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 * com.asianaidt.springmvc.step03.todo.repository.jdbctemplate
 * L JdbcTemplateTodoRepository.java
 * JdbcTemplate 을 이용한 데이터 처리. 인자가 많아지거나 순서가 맍지 않을 경우 에로사항 있음.
 * NamedParameterJdbcTemplate 가 JdbcTemplate 의 대안이 될 수 있다.
 * 또한 SimpleJdbcInsert 사용 시 이름 그대로 Insert 구문이 간단해진다.
 * @date : 2021-06-07 오전 10:29
 * @author : YHKIM
 **/
public class JdbcTemplateTodoRepository implements TodoRepository {
    // 생성자 DI
    private final JdbcTemplate jdbcTemplate;
    public JdbcTemplateTodoRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * <pre>
     * RowMapper : SQL의 결과(record type)를 객체(Object Type)에 매칭
     * ResultSet -> RowMapper.mapRow(개발자구현) -> Object
     * </pre>
     **/
    private RowMapper<Todo> todoRowMapper() {
        return ((rs, rowNum) -> {
            Todo todo = new Todo();
            todo.setId(rs.getLong("id"));
            todo.setUsername(rs.getString("user_name"));
            todo.setDescription(rs.getString("description"));
            todo.setTargetDate(rs.getDate("target_date"));

            return todo;
        });
    }

    @Override
    public List<Todo> findAll() {
        return jdbcTemplate.query("select * from todo", todoRowMapper());
    }

    @Override
    public List<Todo> findByUsername(String user) {
        return jdbcTemplate.query("select * from todo where user_name = ?", todoRowMapper(), user);
    }

    @Override
    public Optional<Todo> findById(long id) {
        List<Todo> result = jdbcTemplate.query("select * from todo where id = ?", todoRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Todo save(Todo todo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todo").usingGeneratedKeyColumns("id");

        Map<String, Object> params = new ConcurrentHashMap<>();
        params.put("user_name", todo.getUsername());
        params.put("description", todo.getDescription());
        params.put("target_date", todo.getTargetDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        todo.setId(key.longValue());
        return todo;
    }

    @Override
    public void delete(Todo todo) {

    }

    @Override
    public void update(Todo todo) {

    }
}
