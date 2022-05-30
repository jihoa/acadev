package com.asianaidt.springmvc.step03.todo.repository.jdbc;

import com.asianaidt.springmvc.step03.todo.domain.Todo;
import com.asianaidt.springmvc.step03.todo.repository.TodoRepository;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor
public class JdbcTodoRepository implements TodoRepository {
    private final DataSource dataSource;

    public JdbcTodoRepository(final DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<Todo> findAll() {
        String sql = "select * from Todo";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            List<Todo> todoList = new ArrayList<>();
            while (rs.next()) {
                Todo todo = new Todo();
                todo.setId(rs.getLong("id"));
                todo.setUsername(rs.getString("user_name"));
                todo.setDescription(rs.getString("description"));
                todo.setTargetDate(rs.getDate("target_date"));

                todoList.add(todo);
            }
            return todoList;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, ps, rs);
        }
    }

    @Override
    public List<Todo> findByUsername(final String user) {
        String sql = "select * from Todo where user_name = ? ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            rs = ps.executeQuery();

            List<Todo> todoList = new ArrayList<>();

            while (rs.next()) {
                Todo todo = new Todo();
                todo.setId(rs.getLong("id"));
                todo.setUsername(rs.getString("user_name"));
                todo.setDescription(rs.getString("description"));
                todo.setTargetDate(rs.getDate("target_date"));

                todoList.add(todo);
            }
            return todoList;

        } catch (Exception se) {
            throw new IllegalStateException(se);
        } finally {
            close(conn, ps, rs);
        }
    }

    private void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }


    @Override
    public Optional<Todo> findById(long id) {
        String sql = "select * from Todo where id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Todo todo = new Todo();
                todo.setId(rs.getLong("id"));
                todo.setUsername(rs.getString("user_name"));
                todo.setDescription(rs.getString("description"));
                todo.setTargetDate(rs.getDate("target_date"));

                return Optional.of(todo);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, ps, rs);
        }
    }

    @Override
    public Todo save(Todo todo) {
        String sql = "insert into Todo(user_name, description, target_date, isDone) values (?, ? , ?, 'N')";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, todo.getUsername());
            ps.setString(2, todo.getDescription());
            ps.setDate(3, new Date(todo.getTargetDate().getTime()));
            ps.execute();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                todo.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return todo;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, ps, rs);
        }
    }

    @Override
    public void delete(Todo todo) {
        String sql = "delete from Todo where id =?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, todo.getId());
            ps.execute();

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, ps, rs);
        }

    }

    @Override
    public void update(Todo todo) {
        String sql = "update Todo set description =?, target_date=? where id =?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, todo.getDescription());
            ps.setDate(2, new Date(todo.getTargetDate().getTime()));
            ps.setLong(3, todo.getId());
            ps.execute();

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, ps, rs);
        }
    }
}
