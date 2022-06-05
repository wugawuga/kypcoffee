package team.kyp.kypcoffee.controller;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.kyp.kypcoffee.domain.LoginCommand;
import team.kyp.kypcoffee.domain.MemberJpa;
import team.kyp.kypcoffee.repository.MemberRepository;
import team.kyp.kypcoffee.service.AuthService;

@SpringBootTest
class LoginControllerTest {

    @Autowired
    private AuthService authService;

    @Test
    public void 아이디가_틀리거나_없을때_예외처리() throws Exception {
        //given
        LoginCommand loginCommand = new LoginCommand();
        loginCommand.setId("id");
        loginCommand.setPw("pw");

        //then
        assertThatThrownBy(() -> authService.authenticate(loginCommand))
        .isInstanceOf(NoSuchElementException.class)
        .hasMessage("아이디가 존재하지 않습니다");
    }

    @Test
    public void 비밀번호가_틀렸을때_예외처리() throws Exception {
        //given
        LoginCommand loginCommand = new LoginCommand();
        loginCommand.setId("dddd");
        loginCommand.setPw("pw");

        //then
        assertThatThrownBy(() -> authService.authenticate(loginCommand))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("비밀번호를 확인확인확인");

    }
}