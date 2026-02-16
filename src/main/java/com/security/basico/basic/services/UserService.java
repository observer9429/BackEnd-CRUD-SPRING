package com.security.basico.basic.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.security.basico.basic.dto.UserRequestDto;
import com.security.basico.basic.entities.Role;
import com.security.basico.basic.entities.User;
import com.security.basico.basic.repositories.RoleRepository;
//import com.security.basico.basic.repository.RoleRepository;
//import com.security.basico.basic.repository.UserRepository;
import com.security.basico.basic.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(UserRequestDto dto) {

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("El usuario ya existe");
        }

        User user = new User();
        user.setUsername(dto.getUsername());

        // 🔐 cifrado
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(true);

        Set<Role> roles = new HashSet<>();

        // 👉 si no envían roles → ROLE_USER por defecto
        if (CollectionUtils.isEmpty(dto.getRoles())) {

            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("ROLE_USER no existe en BD"));
            roles.add(userRole);

        } else {

            for (String roleName : dto.getRoles()) {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() ->
                                new RuntimeException("Rol no existe: " + roleName));
                roles.add(role);
            }
        }

        user.setRoles(roles);

        return userRepository.save(user);
    }
}
