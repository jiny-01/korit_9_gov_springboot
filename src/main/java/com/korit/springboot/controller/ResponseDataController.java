package com.korit.springboot.controller;

import com.korit.springboot.dto.RespJsonDto;
import com.korit.springboot.dto.RespJsonDto2;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ResponseDataController {

    // 응답 데이터 - 문자열
    @GetMapping("/resp/data1")
    public ResponseEntity<String> getString() {
        return ResponseEntity.ok("문자열 응답");    //타입: text/plain - 텍스트
    }

    //---Map / List - JSON 응답옴
    // 응답 데이터 - MAP
    @GetMapping("/resp/data2")
    public ResponseEntity<Map<String, Object>> getMap() {
        return ResponseEntity.ok(Map.of("key1", "value1", "key2", "value2"));
    }

    // 응답 데이터 - LIST
    @GetMapping("/resp/data3")
    public ResponseEntity<List<?>> getList() {
//        return ResponseEntity.ok(List.of("a", "b", "c"));   //JSON 객체 응답
        return ResponseEntity.ok(List.of(1, 2, 3, 4));
    }

    // 응답 데이터 - 객체 (DTO)
    // 키 값을 보기 위해서 보통 응답 DTO 많이 씀
    @GetMapping("/resp/data4")
    public ResponseEntity<RespJsonDto> getObject() {
        RespJsonDto respJsonDto = new RespJsonDto();
        respJsonDto.setName("김준일");
        respJsonDto.setEmail("test@gmail.com");
        respJsonDto.setRespJsonDto2(new RespJsonDto2());
        return ResponseEntity.ok(respJsonDto);
    }

    //http://localhost:8080/resp/data5?filename=download_test.txt
    //하면 txt 파일 다운로드 가능
    @GetMapping("/resp/data5")
    public ResponseEntity<Resource> download(@RequestParam String filename) {
        Resource resource = new ClassPathResource("static/" + filename);
        System.out.println(resource);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }


    @GetMapping("/resp/data6")
    public ResponseEntity<Resource> downloadUTF8(@RequestParam String filename) {
        Resource resource = new ClassPathResource("static/" + filename);
        System.out.println(resource);

        String contentDisposition = ContentDisposition.builder("attachment")
                .filename(filename, StandardCharsets.UTF_8)          //한글 인코딩
                .build().toString();  //ContentDisposition -> String
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

    //.filename(filename, StandardCharsets.UTF_8) = "attachment; filename=\"" + filename + "\""
}
