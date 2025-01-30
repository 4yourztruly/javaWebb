package com.example.javaWebb.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileRepository fileRepository;

    public File addFile(String name, String content) {
        File file = new File();
        return file;
    }

    public String deleteFile(UUID id) {
        Optional<File> file = fileRepository.findById(id);
        if(file.isEmpty()) {
            throw new NoSuchElementException();
        }
        fileRepository.delete(file.get());
       return "File deleted";
    }

    public File downloadFile(UUID id) {
        Optional<File> file = fileRepository.findById(id);
        if(file.isEmpty()) {
            throw new NoSuchElementException("File does not exist");
        }
        return file.get();
    }
}
