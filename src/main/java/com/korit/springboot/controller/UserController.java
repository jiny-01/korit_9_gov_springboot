package com.korit.springboot.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// 1
@RestController
public class UserController {

    private String username = "test12";
    private String password = "1234";


    //ResponseEntity - 응답정보들이 담겨있는 객체
//    @GetMapping("/users")
//    public ResponseEntity<Map<String, String>> getUsers (HttpServletResponse response) {
//        response.setStatus(400);
//        response.setContentType("application/json");
//        return Map.of("username", username, "password", password);
//    }

    @GetMapping("/users")
    public ResponseEntity<Map<String, String>> getUsers (HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("username", username, "password", password));
    }

    @GetMapping("/info")    //Get 메서드 관리할 URL
    public ResponseEntity<String> printInfo () {
        return ResponseEntity.ok("UserController!!!");
    }
    //http://localhost:8080/info 응답옴

    @GetMapping("/info2")    //Get 메서드 관리할 URL
    public ResponseEntity<String> printInfo2 () {
        return ResponseEntity.ok("UserController!!!");
    }

//    @ResponseBody
//    @GetMapping("/users")
//    public Map<String, String> getUsers() {
//        return Map.of("username", username, "password", password);
//    }
    // @ResponseBody 없으면 viewName 이 되어버려서 500 에러뜸

//    @GetMapping("/users")
//    public ResponseEntity<Map<String, String>> getUsers() {
//        return responseEntity Map.of("username", username, "password", password);
//    }

    //@Controller 인데 @ResponseBody 안 붙인 채로 (SSR) String 리턴이라면
    //-> html 파일 경로
    //@Controller 인데 @ResponseBody 있으면 => 문자열 데이터의 값


}
