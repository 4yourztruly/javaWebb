package com.example.javaWebb.folder;

import com.example.javaWebb.file.File;
import com.example.javaWebb.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Data
@Entity(name = "folders")
public class Folder {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "folder")
    private final List<File>files;

    @ManyToMany
    private final List<Folder> foldersMany;

    @ManyToOne
    private final User user;

    public Folder() {this.id = null; this.files = null; this.user =null; this.foldersMany = null;}

}
