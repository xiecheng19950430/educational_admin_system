package com.ebay.models;

import lombok.Data;

@Data
public class UserRole {
    private Integer id;
    private String roleName;
    private String moduleIds;
}
