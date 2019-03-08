package com.ebay.services;

import com.ebay.common.Result;
import com.ebay.mappers.RoleModuleRelationMapper;
import com.ebay.mappers.TeacherRoleRelationMapper;
import com.ebay.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import sun.security.pkcs11.Secmod;

@Service
public class RoleModuleRelationService {
		@Autowired
		private RoleModuleRelationMapper mapper;
		@Autowired
		private UserModuleService userModuleService;

		public void deleteByModuleId(int moduleId) {
				mapper.deleteByModuleId(moduleId);
		}

		public void deleteByRoleId(int roleId) {
				mapper.deleteByRoleId(roleId);
		}

		public Result insert(int roleId, int moduleId) {
				UserModule module = userModuleService.findById(moduleId);
				if (ObjectUtils.isEmpty(module)) return Result.fail("模块不存在");
				//新增
				RoleModuleRelation relation = new RoleModuleRelation();
				relation.setRoleId(roleId);
				relation.setModuleId(moduleId);
				mapper.insert(relation);
				return Result.success();
		}
}
