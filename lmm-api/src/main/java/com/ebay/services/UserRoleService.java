package com.ebay.services;

import com.ebay.mappers.UserRoleMapper;
import com.ebay.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleMapper mapper;

    public UserRole findByRole(String role) {
        return mapper.findByRole(role);
    }

    public int insert(UserRole userRole) {
        return mapper.insert(userRole);
    }

    public List<UserRole> query() {

        return mapper.query();
    }

    public int update(UserRole userRole) {
        return mapper.update(userRole);
    }

    public int delete(int id) {
        return mapper.delete(id);
    }

    public UserRole findById(int id) {
        return mapper.findById(id);
    }

    public List<UserRole> queryByTeacherId(Integer teacherId) {
        return mapper.queryByTeacherId(teacherId);
    }
}
