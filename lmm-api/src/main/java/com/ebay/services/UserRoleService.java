package com.ebay.services;

import com.ebay.common.Result;
import com.ebay.mappers.UserRoleMapper;
import com.ebay.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserRoleService {
		@Autowired
		private UserRoleMapper mapper;
		@Autowired
		private RoleModuleRelationService roleModuleRelationService;

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

		public Result authModule(String role, String moduleIdsStr) {
				UserRole userRole = mapper.findByRole(role);
				if (ObjectUtils.isEmpty(role)) return Result.fail("未找到该角色");
				//全删
				roleModuleRelationService.deleteByRoleId(userRole.getId());
				// 全增
				if (!StringUtils.isEmpty(moduleIdsStr)) {
						String[] moduleIds = moduleIdsStr.split(",");
						for (String moduleId : moduleIds) {
								Result result = roleModuleRelationService.insert(userRole.getId(), Integer.valueOf(moduleId));
								if (!result.isSuccess()) return result;
						}
				}

				return Result.success();
		}
}
