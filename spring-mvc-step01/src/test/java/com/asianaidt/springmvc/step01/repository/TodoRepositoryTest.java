package com.asianaidt.springmvc.step01.repository;

import com.asianaidt.springmvc.step01.domain.Todo;
import com.asianaidt.springmvc.step01.service.TodoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("TodoRepository 클래스 테스트")
@ExtendWith({SpringExtension.class})
@ContextConfiguration(locations = {"classpath:META-INF/config/step01-servlet.xml"})
class TodoRepositoryTest {
    @Autowired
    TodoRepository todoRepository;
    @Autowired TodoService todoService ;

    @BeforeEach
    void setup() {
        //todoRepository = new TodoRepository();
        Todo todo = new Todo();
        todo.setId(0);
        todo.setUsername("tester1");
        todo.setDescription("test");
        todo.setTargetDate(new Date());
        todoRepository.save(todo);

        Todo todo1 = new Todo();
        todo1.setId(5);
        todo1.setUsername("tester5");
        todo1.setDescription("test");
        todo1.setTargetDate(new Date());

        todoRepository.save(todo1);
    }

    @AfterEach
    void clean() {
        todoRepository.clearData();
    }

    @Test
    @DisplayName("Todo 조회")
    void todoFindAll() {
        // given

        // when
        List<Todo> todos = todoRepository.findAll();

        // then
        assertThat(todos.size()).isEqualTo(4);
        assertThat(todos.get(3).getUsername()).isEqualTo("tester5" );
    }

    @Test
    @DisplayName("사용자이름으로 Todo 조회 테스트")
    void findByUsernameTest() {
        // given

        // when
        List<Todo> todos = todoRepository.findByUsername("tester1");

        //then
        assertThat(todos.size()).isEqualTo(1);
        assertThat(todos.stream()
                .filter(todo -> todo.getUsername().equals("tester1"))
                .findFirst()
                .map(Todo::getUsername).get()).isEqualTo("tester1");

    }
}