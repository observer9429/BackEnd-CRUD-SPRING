package com.security.basico.basic.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    //el fetch=FetchType.EAGER , es Cuando cargues un usuario, carga también sus roles
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",  // conexion entre user y roles --> user_roles
        joinColumns = @JoinColumn(name = "user_id"),//columna que apunta al usuario
        inverseJoinColumns = @JoinColumn(name = "role_id")//columna que apunta al rol
    )
    private Set<Role> roles = new HashSet<>();

    // ======================
    // getters y setters
    // ======================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }
 
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }
 
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    
}

