package com.example.javaWebb.file;

import com.example.javaWebb.security.JWTService;
import com.example.javaWebb.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(name = "/api/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final JWTService jwtService;

    @PostMapping("/add-file")
    public ResponseEntity<?> addFile(@AuthenticationPrincipal User user, @RequestBody FileDTO dto) {
        try{
            String username = user.getUsername();
            System.out.println(username);
            var file = fileService.addFile(dto.name,dto.content);
            return ResponseEntity.ok().body(file.getName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-file")
    public ResponseEntity<?> deleteFile(@RequestBody DeleteFileDTO dto) {
        try{
            var file = fileService.deleteFile(dto.id);
            return ResponseEntity.ok().body(file);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/download-file")
    public ResponseEntity<?> downloadFile(@RequestBody DownloadFileDTO dto) {
        try{
            var file = fileService.downloadFile(dto.id);
            return ResponseEntity.ok().body(file);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Data
    public static class FileDTO {
        private String name;
        private String content;
    };

    @Data
    public static class DeleteFileDTO {
        private UUID id;
    };

    @Data
    public static class DownloadFileDTO {
        private UUID id;
    };

}
