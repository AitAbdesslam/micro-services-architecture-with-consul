package com.ms.secservice.sec.service;

import com.ms.secservice.sec.entities.AppRole;
import com.ms.secservice.sec.entities.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String rolename);
    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();
}
