package com.example.javaWebb.user;

import com.example.javaWebb.folder.Folder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Data
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    private final UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Folder>folders = new ArrayList<>();

    public User() {this.id = null;}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.id = UUID.randomUUID();
        Folder folder = new Folder("home", this, null, null);
        addFolder(folder);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void addFolder(Folder folder) {
        folders.add(folder);
    }
}
