package com.example.javaWebb.folder;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FolderController {

    private final FolderService folderService;

    public ResponseEntity<?> addFolder(@RequestBody FolderDTO dto) {
        try {
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public static class FolderDTO {
        private String name;
    }
}
