package com.korit.springboot.controller;

import com.korit.springboot.dto.ReqFormDataDto4;
import com.korit.springboot.dto.ReqJsonDto2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class RequestDataController2 {

    // POST 요청 데이터 받는 방법 1 - Map 을 통해 JSON 데이터 받는 방법
    @PostMapping("/req/data1")
    public ResponseEntity<?> reqPost1(@RequestBody Map<String, Object> data) {
        System.out.println(data);
        System.out.println(data.get("additionalProp2"));
        return ResponseEntity.ok().build();
    }

    // POST 요청 데이터 받는 방법 2 - DTO 을 통해 JSON 데이터 받는 방법
    @PostMapping("/req/data2")
    public ResponseEntity<?> reqPost2(@RequestBody ReqJsonDto2 data2) {
        System.out.println(data2);
        return ResponseEntity.ok().build();
    }

    // POST 요청 데이터 받는 방법 3 - 파일 데이터를 받는 방법
    // 파일 전송을 위해서는 요청 Content-Type 이 꼭 multipart/formdata
    @PostMapping(value = "/req/data3", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> reqPost3(@RequestPart("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return ResponseEntity.ok().build();
    }

    // POST 요청 데이터 받는 방법 4 - 파일 데이터를 받는 방법
    // 파일 전송을 위해서는 요청 Content-Type 이 꼭 multipart/formdata
    @PostMapping(value = "/req/data4", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> reqPost4(ReqFormDataDto4 dto) {
        System.out.println(dto);
        System.out.println(dto.getFile().getOriginalFilename());
        return ResponseEntity.ok().build();
    }

    // POST 요청 데이터 받는 방법 5 - 여러 파일 데이터를 받는 방법
    // 파일 전송을 위해서는 요청 Content-Type 이 꼭 multipart/formdata
    @PostMapping(value = "/req/data5", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> reqPost5(@RequestPart("files") List<MultipartFile> files) {
        files.forEach(file -> System.out.println(file.getOriginalFilename()));
        return ResponseEntity.ok().build();
    }


    // PUT 요청 데이터 받는 방법 1 - JSON 데이터만 보통 사용
    //HTTP 요청 바디(body)에 들어 있는 데이터를 자바 객체로 받고 싶을 때
    //PUT 요청 - 전체 수정
    @PutMapping("/req/data1/{id}")
    public ResponseEntity<?> reqPut1(@PathVariable int id, @RequestBody ReqJsonDto2 dto) {
        System.out.println(dto);
        return ResponseEntity.ok().build();
    }

    //----@PathVariable int id 주는 이유: 수정할 대상 지정하기 위함
    //PATCH 요청 - 부분수정
    @PatchMapping("/req/data1/{id}")
    public ResponseEntity<?> reqPatch(@PathVariable int id, @RequestBody ReqJsonDto2 dto) {
        System.out.println(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/req/data1/{id}")
    public ResponseEntity<?> reqDelete(@PathVariable int id, @RequestBody Map<String, Object> data) {
        System.out.println(id);
        return ResponseEntity.ok().build();
    }



}
