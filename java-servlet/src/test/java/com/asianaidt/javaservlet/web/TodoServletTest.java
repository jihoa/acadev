package com.asianaidt.javaservlet.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TodoServletTest {
    // mockito : 테스트를 편하게 하도록 하는 모의객체(Mock)를 만드는 Mocking 프레임워크.
    // Mock : 모의객체는 실제 구현체가 없고, 인터페이스/메서드/필드를 참조할 수 있는 객체.

    @Mock HttpServletRequest request;
    @Mock HttpServletResponse response;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTodoServlet() throws IOException {
        // when : 어떤 동작을 할때 ~
        // thenReturn : 단순 반환값
        // thenThrow : 예외를 던짐
        when(request.getParameter("username"))
                .thenReturn("user2");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(printWriter);

        TodoServlet todoServlet = new TodoServlet();
        todoServlet.doGet(request, response);
        String result = stringWriter.toString().replaceAll("\r\n","");

        assertEquals(result, "<html><body><h1>user2</h1></body></html>");

        //assertEquals(result, "<html><body><h1>user1</h1></body></html>");

        assertEquals(result, "<html><body><h1>user2</h1></body></html>");

    }


}