package com.ebay.services;

import com.ebay.mappers.UserRoleMapper;
import com.ebay.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleMapper mapper;

    public UserRole findByRole(String userRole) {
        return mapper.findByRole(userRole);
    }

}
