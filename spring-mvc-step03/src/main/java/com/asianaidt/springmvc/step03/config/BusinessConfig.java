package com.asianaidt.springmvc.step03.config;

import com.asianaidt.springmvc.step03.todo.domain.Todo;
import com.asianaidt.springmvc.step03.todo.dto.RequestDto;
import com.asianaidt.springmvc.step03.todo.repository.TodoRepository;
import com.asianaidt.springmvc.step03.todo.repository.basic.BasicTodoRepository;
import com.asianaidt.springmvc.step03.todo.service.MybatisTodoServiceImpl;
import com.asianaidt.springmvc.step03.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class BusinessConfig {
    // 생성자 DI
    private final DataSource dataSource;
    private final EntityManager entityManager;
    public BusinessConfig(DataSource dataSource, EntityManager entityManager) {
        this.dataSource = dataSource;
        this.entityManager = entityManager;
    }

//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }

    @Bean
    public TodoService todoService() {
        return new MybatisTodoServiceImpl();
        //return new TodoServiceImpl(todoRepository()) ;
    }

    @Bean
    public TodoRepository todoRepository() {
        return new BasicTodoRepository();
        //return new JdbcTodoRepository(dataSource);
        //return new JdbcTemplateTodoRepository(dataSource);
        //return new JpaTodoRepository(entityManager);
    }

    private final ModelMapper modelMapper = new ModelMapper();
    @Bean
    public ModelMapper userMapper() {
        // 매핑 전략 설정
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.createTypeMap(Todo.class, RequestDto.class)
                .addMapping(Todo::getUsername, RequestDto::setUsername)
                .addMapping(Todo::getDescription, RequestDto::setDescription)
                .addMapping(Todo::getTargetDate, RequestDto::setTargetDate);
        return modelMapper;
    }
}
