package com.example.crm.service;

import com.example.crm.entity.AppUser;
import com.example.crm.entity.Role;
import com.example.crm.exception.AuthException;
import com.example.crm.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    @Autowired
    AppUserRepository appUserRepo;

    @Autowired
    RoleService roleService;

    public List<AppUser> getAppUsers() {
        return appUserRepo.findAll();
    }

    public AppUser getAppUser(Long id) {
        return appUserRepo.findById(id)
                .orElseThrow(() -> new AuthException("Not found App User"));
    }

    public AppUser getAppUserByUsername(String username) {
        return appUserRepo.findByUsername(username)
                .orElseThrow(() -> new AuthException("Not found App User"));
    }

    public AppUser createAppUser(AppUser user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        return appUserRepo.save(user);
    }

    public AppUser updateAppUser(AppUser user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        AppUser currentUser = appUserRepo.findById(user.getId())
                .orElseThrow(() -> new AuthException("Not found App User"));
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(encoder.encode(user.getPassword()));

        return appUserRepo.save(currentUser);
    }

    public String deleteAppUser(Long id) {
        AppUser user = appUserRepo.findById(id)
                .orElseThrow(() -> new AuthException("Not found App User"));
        appUserRepo.delete(user);

        return "deleted";
    }

    public AppUser addRole(Long userId, Long roleId) {
        AppUser user = appUserRepo.findById(userId)
                .orElseThrow(() -> new AuthException("Not found App User"));
        Role role = roleService.getRole(roleId);

        List<Role> roles = user.getRoles();
        if (!roles.contains(role)) {
            roles.add(role);
        }

        user.setRoles(roles);

        return appUserRepo.save(user);
    }
}
