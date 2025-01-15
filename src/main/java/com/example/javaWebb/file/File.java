package com.example.javaWebb.file;

import com.example.javaWebb.folder.Folder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

@RequiredArgsConstructor
@Data
@Entity(name = "files")
public class File {

    @Id
    private final UUID id;

    @Column(nullable = false)
    private String name;

    private String content;

    @ManyToOne
    private final Folder folder;

    public File() {this.id = null; this.folder = null;}
}
