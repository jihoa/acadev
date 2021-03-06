package com.asianaidt.springmvc.step03.todo.service;

import com.asianaidt.springmvc.step03.todo.domain.Todo;
import com.asianaidt.springmvc.step03.todo.persistence.mybatis.TodoMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// MockitoExtension: Mockito framework를 juni5에 통합
// @Mock : create 모의객체
// @InjectMocks : create 객체 and  모의 의존성 주입

@Transactional
//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MybatisTodoServiceImplTest {
    Logger logger = LoggerFactory.getLogger(MybatisTodoServiceImplTest.class);
    //@InjectMocks
    @Autowired
    private TodoService todoService;
    //private TodoService todoService = new MybatisTodoServiceImpl();
    @Autowired
    private TodoMapper todoMapper;

    @Test
    @DisplayName("테스트설명")
    void when_then() throws Exception {
        // given

        // when

        // then
     }



    @Test
    @DisplayName("todoService 생성확인")
    void whenTodoServiceInjected_thenNotNull() {
        assertThat(todoService).isNotNull();
        assertThat(todoMapper).isNotNull();
    }

    @Test
    @DisplayName("사용자 이름으로 조회")
    void whenGetTodosByUserSuccess_thenCorrectResponse() {
        // given
        List<Todo> todoList = new ArrayList<>();
        final Todo todo1 = new Todo();
        todo1.setId(1L);
        todo1.setUsername("arori tester");
        todo1.setDescription("mybatis 연동 테스트");
        todo1.setTargetDate(new Date());
        todoList.add(todo1);
        todoService.saveTodo(todo1);
        //given(todoMapper.getTodosByUser(todo1.getUsername())).willReturn(todoList);

        // when
        List<Todo> resultList= todoMapper.getTodosByUser(todo1.getUsername());
        logger.info("Username: {}", todoList.get(0).getUsername());
        // then
        assertThat(todo1.getUsername()).isEqualTo(resultList.get(0).getUsername());
        //verify(todoMapper).getTodosByUser(any(String.class));
    }


}