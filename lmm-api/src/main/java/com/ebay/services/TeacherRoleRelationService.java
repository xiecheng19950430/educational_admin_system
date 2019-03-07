package com.ebay.services;

import com.ebay.common.Result;
import com.ebay.mappers.TeacherRoleRelationMapper;
import com.ebay.models.TeacherRoleRelation;
import com.ebay.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class TeacherRoleRelationService {
		@Autowired
		private TeacherRoleRelationMapper mapper;
		@Autowired
		private UserRoleService userRoleService;

		public Result insert(int teacherId, int roleId) {
				UserRole role = userRoleService.findById(roleId);
				if (ObjectUtils.isEmpty(role)) return Result.fail("角色不存在");
				//新增
				TeacherRoleRelation relation = new TeacherRoleRelation();
				relation.setTeacherId(teacherId);
				relation.setRoleId(roleId);
				mapper.insert(relation);
				return Result.success();
		}

		public void deleteByTeacherId(int teacherId) {
				mapper.deleteByTeacherId(teacherId);
		}

		public void deleteByRoleId(int roleId) {
				mapper.deleteByRoleId(roleId);
		}
}
