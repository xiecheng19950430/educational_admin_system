package com.ebay.controllers;

import com.ebay.common.Result;
import com.ebay.common.utils.*;
import com.ebay.models.*;
import com.ebay.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/gm_teacher")
public class GmTeacherController {
		@Autowired
		private GmTeacherService service;
		@Autowired
		private IimportService iimportService;
		@Autowired
		private TeacherRoleRelationService teacherRoleRelationService;
		@Autowired
		private UserModuleService userModuleService;
		@Autowired
		private UserRoleService userRoleService;
		@Autowired
		private GmClassHasGmTeacherService gmClassHasGmTeacherService;
		@Autowired
		private GmClassService gmClassService;

		//列表
		@RequestMapping("/list")
		@ResponseBody
		public Result getTeacherList(Integer isDelete,
																 String name,
																 String workNo,
																 Integer page,
																 Integer size) {
				//默认非删除
				if (isDelete == null)
						isDelete = 0;
				List<GmTeacher> list = service.getTeacherList(null, workNo, isDelete, name, page, size);
				int count = service.count(workNo, isDelete, name);
				list.forEach(gmTeacher -> {
						List<String> classNameList = gmClassService.queryNameByTeacherId(gmTeacher.getId());
						String classNames = StringUtils.join(classNameList, ",");
						gmTeacher.setClassNames(classNames);
				});

				return Result.build().put("list", list).put("count", count).result();
		}

		//根据classId，获得教师列表
		@RequestMapping("/teacherList")
		@ResponseBody
		public Result teacherList(Integer classId) {
				List<GmTeacher> list = service.getTeacherList(classId, null, null, null, null, null);
				return Result.build().put("list", list).result();
		}

		//新增
		@RequestMapping("/insert")
		@ResponseBody
		public Result insert(GmTeacher gmTeacher) {
				//判断登陆名重复
				List<GmTeacher> list = service.getTeacherList(null, gmTeacher.getWorkNo(), 0, null, null, null);
				if (list.size() > 0) {
						return Result.fail("登录名重复，请重新输入！");
				}
				gmTeacher.setIsDelete(0);
				//如果密码为空，默认123456
				if (gmTeacher.getPassword() == null) {
						String pwd = MD5Util.encode("123456");
						gmTeacher.setPassword(pwd);
				}

				int r = service.insert(gmTeacher);
				return Result.success();
		}

		//登陆
		@RequestMapping("/login")
		@ResponseBody
		public Result login(String loginId, String password) {
				GmTeacher gmTeacher = service.findByLoginId(loginId);
				if (gmTeacher == null) {
						return Result.fail("账号或密码不正确");
				}
				//password=MD5Util.encode(password);
				if (!password.equals(gmTeacher.getPassword())) {
						return Result.fail("账号或密码不正确");
				}
				if (gmTeacher.getRoleName() != null && gmTeacher.getRoleName() != "") {

				}
				if (gmTeacher.getIsDelete() != null && gmTeacher.getIsDelete() == 1) {
						return Result.fail("该账号已停用，请联系管理员");
				}

				//超管无限制
				if (!Objects.equals("superAdmin", gmTeacher.getRole())) {
//						如果未有绑定角色，以默认为准
						if (!StringUtils.isEmpty(gmTeacher.getRole()) && StringUtils.isEmpty(gmTeacher.getRoleIds())) {
								UserRole role = userRoleService.findByRole(gmTeacher.getRole());
								if (!ObjectUtils.isEmpty(role)) {
										List<String> roles = new ArrayList<>();
										roles.add(role.getId().toString());
										gmTeacher.setRoles(roles);

										List<UserModule> moduleList = userModuleService.queryByRoleId(role.getId());
										List<String> urls = moduleList.stream().map(UserModule::getUrl).collect(Collectors.toList());
										gmTeacher.setUrls(urls);
								}
						} else {
								//获取角色role
								List<UserRole> roleList = userRoleService.queryByTeacherId(gmTeacher.getId());
								List<String> roles = roleList.stream().map(UserRole::getRole).collect(Collectors.toList());
								gmTeacher.setRoles(roles);

								//获取授权的模块url
								List<UserModule> moduleList = userModuleService.queryByTeacherId(gmTeacher.getId());
								List<String> urls = moduleList.stream().map(UserModule::getUrl).collect(Collectors.toList());
								gmTeacher.setUrls(urls);
						}

				}


				return Result.success(gmTeacher);
		}

		//逻辑删除
		@RequestMapping("/delete")
		@ResponseBody
		public Result delete(int id, Integer flag) {
				if (flag != null && flag == 1) {
						service.delete(id);
				} else {
						GmTeacher old = service.findById(id);
						//初始状态为删除
						old.setIsDelete(1);
						int r = service.update(old);
				}

				//删除关联的角色授权
				teacherRoleRelationService.deleteByTeacherId(id);

				return Result.success();
		}

		//更新基础信息
		@RequestMapping("/update")
		@ResponseBody
		public Result update(GmTeacher gmTeacher) {
				GmTeacher old = service.findById(gmTeacher.getId());
				int n;
				if (ObjectUtils.isEmpty(old)) {
						//新增
						n = service.insert(gmTeacher);
				} else {
						//修改
						BeanUtil.copyNotNullBean(gmTeacher, old);
						n = service.update(old);
				}
				return n > 0 ? Result.success() : Result.fail("操作失败");
		}

		//通过主键查询教师信息
		@RequestMapping("/findById")
		@ResponseBody
		public Result findById(int id) {
				GmTeacher gmTeacher = service.findById(id);
				return Result.success(gmTeacher);
		}

		//Excel导入教师信息到数据库
		@RequestMapping("/importTeacherExcel")
		@ResponseBody
		public Result importTeacherExcel(@RequestParam("teacherFile") MultipartFile teacherFile) throws ParseException {
				iimportService.importTeacherExcel(teacherFile);
				return Result.success();
		}


//		//添加班主任，展示教师列表
//		@RequestMapping("/head/list")
//		@ResponseBody
//		public Result list(int classId) {
//				List<GmTeacher> list = service.getTeacherList(classId, null, 0, null, null, null);
//				return Result.build().put("list", list).result();
//		}

//		//绑定班级班主任信息
//		@RequestMapping("/bindHeadmaster")
//		@ResponseBody
//		public Result bindHeadmaster(int classId) {
//				GmTeacher gmTeacher = service.findByClassId(classId, "headmaster");
//				if (ObjectUtils.isEmpty(gmTeacher)) {
//						gmTeacher.setRoleName("headmaster");
//						service.update(gmTeacher);
//				} else {
//						return Result.fail("该班级已绑定班主任信息!");
//				}
//				return Result.success();
//		}

		//授权
		@RequestMapping("/auth")
		@ResponseBody
		public Result authRole(@RequestParam int id, String roleIdsStr) {
				GmTeacher teacher = service.findById(id);
				if (ObjectUtils.isEmpty(teacher)) return Result.fail("未找到该教师");
				//全删
				teacherRoleRelationService.deleteByTeacherId(id);
				// 全增
				List<UserRole> roleList = userRoleService.query();
				List<String> roleNames = new ArrayList<>();
				if (!StringUtils.isEmpty(roleIdsStr)) {
						String[] roleIds = roleIdsStr.split(",");
						for (String roleId : roleIds) {
								Result result = teacherRoleRelationService.insert(id, Integer.valueOf(roleId));
								if (!result.isSuccess()) return result;
								roleList.stream()
										.filter(userRole -> userRole.getId() == Integer.valueOf(roleId))
										.findAny()
										.ifPresent(role -> roleNames.add(role.getRoleName()));
						}
				}
				teacher.setRoleIds(roleIdsStr);
				String roleNamesStr = String.join(",", roleNames);
				teacher.setRoleNames(roleNamesStr);
				service.update(teacher);
				return Result.success();
		}

		//		批量授权
		@RequestMapping("/auth/batch")
		@ResponseBody
		public Result batchAuthRole(@RequestParam String teacherIds, String roleIds) {
				String[] tids = teacherIds.split(",");
				for (String teacherId : tids) {
						Result result = this.authRole(Integer.valueOf(teacherId), roleIds);
						if (!result.isSuccess()) return result;
				}
				return Result.success();
		}

		//获取班级对应教师信息
		@RequestMapping("/list/classId")
		@ResponseBody
		public Result queryByClassId(@RequestParam int classId) {
				List<GmTeacher> teacherList = service.queryByClassId(classId);
				return Result.success(teacherList);
		}

		//教师关联班级
		@RequestMapping("/bind/class")
		@ResponseBody
		public Result bindClass(@RequestParam String teacherIds, String classIds) {
				String[] tids = teacherIds.split(",");
				for (String teacherId : tids) {
						//全删全曾
						gmClassHasGmTeacherService.deleteByTeacherId(Integer.valueOf(teacherId));
						if (!StringUtils.isEmpty(classIds)) {
								String[] cIds = classIds.split(",");
								for (String classId : cIds) {
										gmClassHasGmTeacherService.save(Integer.valueOf(teacherId), Integer.valueOf(classId));
								}
						}
				}
				return Result.success();
		}

		//修改密码
		@RequestMapping("/change/password")
		@ResponseBody
		public Result bindClass(@RequestParam String loginId, @RequestParam String password, @RequestParam String passwordNew) {
				GmTeacher teacher = service.findByLoginId(loginId);
				if (ObjectUtils.isEmpty(teacher)) return Result.fail("未找到该教师");
				if (!Objects.equals(teacher.getPassword(), password)) return Result.fail("原密码错误");
				service.updatePassword(teacher.getId(), passwordNew);
				return Result.success();
		}


}
