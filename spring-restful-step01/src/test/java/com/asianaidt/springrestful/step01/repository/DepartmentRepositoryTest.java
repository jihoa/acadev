package com.asianaidt.springrestful.step01.repository;

import com.asianaidt.springrestful.step01.entity.Department;
import com.asianaidt.springrestful.step01.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = "dev")
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @DisplayName("MemberRespository 생성확인")
    void whenMemberRepositoryInjected_thenNotNull() {
        assertThat(departmentRepository).isNotNull();
    }

    @Test
    @DisplayName("jpa onetomany test")
    void onetomanymapping() {
        Department dept = new Department();
        dept.setMembers(
                Arrays.asList(
                        new Member("d_member1", "d_passworrd", "d_test@test.com")));

        Department result = departmentRepository.save(dept);

        System.out.println(" getUsename: "+result.getMembers().get(0).getUsername());
        assertThat(result.getMembers().get(0).getUsername()).isEqualTo("d_member1");

    }

    @Test
    @DisplayName("전체조회")
    void whenFindAllSuccess_thenCorrectResponse() throws Exception {
        // given
        Department dept = new Department();
        dept.setMembers(
                Arrays.asList(
                        new Member("d_member1", "d_passworrd", "d_test@test.com")));

        Department result = departmentRepository.save(dept);

        // when
        List<Department> resultList = departmentRepository.findAll();
        // then
        assertThat(resultList.size()).isGreaterThan(0);
     }

    @Test
    @DisplayName("부서아이디로 조회")
    void whenFindAllByIdSuccess_thenCorrectResponse() throws Exception {
        // given
        Department dept = new Department();
        dept.setMembers(
                Arrays.asList(
                        new Member("d_member1", "d_passworrd", "d_test@test.com")));
        departmentRepository.save(dept);

        // when
        Optional<Department> returnValue = departmentRepository.findById(dept.getId());

        // then
        assertThat(returnValue.get().getName()).isEqualTo(dept.getName());
     }

}