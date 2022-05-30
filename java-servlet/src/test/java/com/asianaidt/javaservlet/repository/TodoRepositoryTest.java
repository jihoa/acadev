package com.asianaidt.javaservlet.repository;

import com.asianaidt.javaservlet.domain.Todo;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TodoRepositoryTest {
    TodoRepository todoRepository;

    @BeforeEach
    void init() {
        todoRepository = new TodoRepository();
    }

    @AfterEach
    public void afterEach() {
        // 테스트를 위해 저장된 데이터를 삭제한다.
        todoRepository.clearData();
    }
    
    @Test
    //@DisplayName("오늘의 할일 목록 조회 테스트")
    //@RepeatedTest(10)
    void testGetAll() {
        //given
        AtomicLong sequence = new AtomicLong(0);
        List<Todo>  dataList = new ArrayList<>();
        Todo data1 = Todo.builder().id(sequence.incrementAndGet()).description("user1에 대한 상세설명").username("user1").targetDate(new Date()).build();
        Todo data2 = Todo.builder().id(sequence.incrementAndGet()).description("user2에 대한 상세 설명").username("user2").targetDate(new Date()).build();
        dataList.add(data1);
        dataList.add(data2);
        todoRepository.save(data1);
        todoRepository.save(data2);

        //when
        List<Todo> result = todoRepository.findAll();

        //then
        // assertThat(실제값).isEqualTo(기대값)
        assertThat(result.get(0).getUsername()).isEqualTo(dataList.get(0).getUsername());
        //assertThat(result.size()).isEqualTo(2);

    }

    //@Test
    @RepeatedTest(2)
    void testSaveTodo() {
        // given
        Todo todo1 = Todo.builder()
                    .username("yhkim")
                    .description("테스트 계정")
                    .targetDate(new Date())
                    .build();

        // when
        todoRepository.save(todo1);
        List<Todo> result = todoRepository.findByUsername("yhkim");

        String username = result.stream().filter(a -> a.getUsername().equals("yhkim")).findFirst().map(Todo::getUsername).orElseThrow(() -> new NoSuchElementException());
        System.out.println("username: "+ username);
        // then
        assertThat(result.stream()
                .filter(todo -> todo.getUsername().equals("yhkim"))
                .findFirst().map(Todo::getUsername).get())
                .isEqualTo(todo1.getUsername());
    }

    @Test
    void testFindById() {
        // given
        Todo todo1 = Todo.builder()
                .username("yhkim")
                .description("테스트 계정")
                .targetDate(new Date())
                .build();
        todoRepository.save(todo1);

        // when
        List<Todo> todoList = todoRepository.findByUsername("yhkim1");
        long id = todoList.stream().filter(a -> a.getUsername().equals("yhkim")).findFirst().map(Todo::getId).orElseThrow(() -> new NoSuchElementException());
        Optional<Todo> result = todoRepository.findById(id);

        //then
        assertThat(result.get().getId() == id);
    }
}