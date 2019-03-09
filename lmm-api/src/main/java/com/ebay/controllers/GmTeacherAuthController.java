package com.ebay.controllers;

import com.ebay.common.Result;
import com.ebay.models.GmTeacher;
import com.ebay.services.GmTeacherAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/gm_teacher_auth")
public class GmTeacherAuthController {
		@Autowired
		private GmTeacherAuthService service;
		@Autowired
		private GmTeacherController gmTeacherController;

		//		批量授权
		@RequestMapping("/auth/batch")
		@ResponseBody
		public Result authRole(String teacherIds, String roleIds) {
//				if (StringUtils.isEmpty(roleIds)) {
//						//清除权限 删除
//						List<Integer> teacherIds =
//				}
//				GmTeacher teacher = gmTeacherController.findById(id);
//				if (ObjectUtils.isEmpty(teacher)) return Result.fail("未找到该教师");
//				//全删
//				teacherRoleRelationService.deleteByTeacherId(id);
//				// 全增
//				if (!StringUtils.isEmpty(roleIds)) {
//						String[] roleIdArray = roleIds.split(",");
//						for (String roleId : roleIds) {
//								Result result = teacherRoleRelationService.insert(id, Integer.valueOf(roleId));
//								if (!result.isSuccess()) return result;
//						}
//				}
//				teacher.setRole(roleIds);
//				service.update(teacher);
//
				return Result.success();
		}

}
