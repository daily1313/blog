package com.example.blog.controller.api;

import com.example.blog.domain.User;
import com.example.blog.dto.ResponseDto;
import com.example.blog.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


//    @Autowired
//    private HttpSession session;

    // Json 데이터를 받으려면 @RequestBody로 받아야함
    // 회원가입

    @ApiOperation(value = "회원가입", notes = "회원가입이 완료되었습니다.")
    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController : save 호출됨");
        userService.join(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바 오브젝트를 JSON으로 변환하여 전송 (JACKSON)
    }

//    // 로그인
//    @PostMapping("/auth/login")
//    public ResponseDto<Integer> login(@RequestBody User user) {
//        System.out.println("UserApiController 호출됨");
//        Optional<User> principal = userService.login(user); // principal (접근주체)
//
//        if(principal != null) {
//            session.setAttribute("principal", principal);
//        }
//
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }

    @ApiOperation(value = "회원정보 수정", notes = "회원정보가 수정되었습니다.")
    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) {

        userService.updateUser(user);
        // 여기서는 트랜잭션이 종료되기 때문에 DB값은 변경이 됐음
        // 하지만 세션값은 변경되지 않은 상태이기 때문에 직접 세션 값을 변경해줘야함.
        // 한마디로 DB는 회원수정이 이뤄졌지만, 실제 웹에서는 세션정보는 DB수정 전 세션이라는 뜻
        // 해결하기 위해서 세션 정보를 직접 변경 해줘야함

        // 세션등록
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }




}