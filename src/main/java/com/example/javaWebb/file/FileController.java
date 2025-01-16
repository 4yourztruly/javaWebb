package com.example.javaWebb.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/add-file")
    public ResponseEntity<?> addFile(@RequestBody FileDTO dto) {
        try{
            //var File file = fileService.addFile(dto.name,dto.content);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Data
    public static class FileDTO {
        private String name;
        private String content;
    };

}
