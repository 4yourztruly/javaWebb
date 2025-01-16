package com.example.javaWebb.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileRepository fileRepository;

    public File addFile(String name, String content) {
        File file = new File();
        return file;
    }
}
