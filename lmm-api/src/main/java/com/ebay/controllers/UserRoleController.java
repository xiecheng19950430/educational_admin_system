package com.ebay.controllers;

import com.ebay.common.Result;
import com.ebay.common.utils.BeanUtil;
import com.ebay.common.utils.StringUtils;
import com.ebay.models.UserRole;
import com.ebay.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/role")
public class UserRoleController {
		@Autowired
		private UserRoleService service;
		@Autowired
		private TeacherRoleRelationService teacherRoleRelationService;
		@Autowired
		private RoleModuleRelationService roleModuleRelationService;

		//列表
		@RequestMapping("/list")
		@ResponseBody
		public Result list() {
				List<UserRole> list = service.query();
				return Result.success(list);
		}


		//更新基础信息
		@RequestMapping("/save")
		@ResponseBody
		public Result save(UserRole userRole) {
				if (StringUtils.isEmpty(userRole.getRole())) return Result.fail("参数错误：角色代码不得为空");
				UserRole old = service.findByRole(userRole.getRole());
				Integer id = old.getId();
				if (ObjectUtils.isEmpty(old)) {
						//新增
						service.insert(userRole);
				} else {
						//修改
						BeanUtil.copyNotNullBean(userRole, old);
						service.update(old);
				}
				return service.authModule(userRole.getRole(), userRole.getModuleIds());

		}

		//逻辑删除
		@RequestMapping("/delete")
		@ResponseBody
		public Result delete(@RequestParam int id) {
				int n = service.delete(id);
				// 删除关联的模块授权
				roleModuleRelationService.deleteByRoleId(id);
				//  删除关联的角色授权
				teacherRoleRelationService.deleteByRoleId(id);

				return Result.success();
		}


}
