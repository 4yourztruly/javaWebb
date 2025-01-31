package com.example.javaWebb.file;

import com.example.javaWebb.folder.Folder;
import com.example.javaWebb.folder.FolderService;
import com.example.javaWebb.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileRepository fileRepository;
    private final FolderService folderService;

    public File addFile(String name, String content, User user, String folder) {
        Optional<File> exists = fileRepository.findFileByNameAndUserId(name, user.getId());
        if(exists.isPresent()) {
            throw new IllegalArgumentException("file already exists, pick another name");
        }

        Optional<Folder> folderExist = folderService.findFolderByNameAndUserId(folder, user.getId());
        if(folderExist.isEmpty()) {
            throw new NoSuchElementException("folder does not exist.");
        }

        File file = new File(name, content, user, folderExist.get());
        fileRepository.save(file);
        folderExist.get().getFiles().add(file);
        folderService.saveFolder(folderExist.get());
        return file;
    }

    public String deleteFile(String name, User user) {
        Optional<File> file = fileRepository.findFileByNameAndUserId(name, user.getId());
        if(file.isEmpty()) {
            throw new NoSuchElementException();
        }
        String deletedName = file.get().getName();
        file.get().getFolder().getSubFolders().remove(file);
        fileRepository.delete(file.get());
       return deletedName + "file deleted";
    }

    public File downloadFile(String name, User user) {
        Optional<File> file = fileRepository.findFileByNameAndUserId(name, user.getId());
        if(file.isEmpty()) {
            throw new NoSuchElementException("File does not exist");
        }
        return file.get();
    }
}
