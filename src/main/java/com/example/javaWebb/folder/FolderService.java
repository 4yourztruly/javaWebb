package com.example.javaWebb.folder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FolderService {

    private final FolderRepository folderRepository;

    public Folder addFolder(String name, String content) {
        Folder folder = new Folder();
        return folder;
    }
}
