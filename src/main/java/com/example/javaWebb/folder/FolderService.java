package com.example.javaWebb.folder;

import com.example.javaWebb.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FolderService {

    private final FolderRepository folderRepository;

    public Folder addFolder(String name, String folderName, User user) {
        Optional<Folder> isUnique = folderRepository.findFolderByNameAndUserId(name, user.getId());

        if(isUnique.isPresent()) {
            throw new IllegalArgumentException("name of folder already exists please pick choose another name.");
        }

        Optional<Folder> inputFolder = folderRepository.findFolderByNameAndUserId(folderName, user.getId());
        if(inputFolder.isEmpty()) {
            throw new NoSuchElementException("folder does not exist");
        }

        Folder folderObject = folderRepository.findFolderByNameAndUserId(folderName,user.getId()).get();
        Folder folder = new Folder(name, user, folderObject, null);
        folderRepository.save(folder);
        folderObject.getSubFolders().add(folder);
        folderRepository.save(folderObject);
        return folder;
    }

    public Optional<Folder> findFolderByNameAndUserId(String name, UUID id) {
        return folderRepository.findFolderByNameAndUserId(name, id);
    }

    public void saveFolder(Folder folder) {
        folderRepository.save(folder);
    }
}
