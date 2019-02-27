package com.ebay.mappers;

import com.ebay.models.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleMapper {

    UserRole findByRole(@Param("userRole") String userRole);
}
