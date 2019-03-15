package com.ebay.controllers;

import com.ebay.common.Result;
import com.ebay.common.utils.BeanUtil;
import com.ebay.models.*;
import com.ebay.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/gm_class")
public class GmClassController {
		@Autowired
		private GmClassService service;
		@Autowired
		private GmTeacherService gmTeacherService;
		@Autowired
		private TeacherRoleRelationService teacherRoleRelationService;
		@Autowired
		private UserRoleService userRoleService;

		//列表
		@RequestMapping("/list")
		@ResponseBody
		public Result getClassList(String name,
															 Integer isDelete,
															 Integer status,
															 Integer year,
															 Integer page,
															 Integer size) {
				//默认非删除
				if (isDelete == null)
						isDelete = 0;
				List<GmClass> list = service.getClassList(name, isDelete, status, year, page, size);
				int count = service.count(name, isDelete, status, year);
				return Result.build().put("list", list).put("count", count).result();
		}

		//新增
		@RequestMapping("/insert")
		@ResponseBody
		public Result insert(GmClass gmClass) {
				gmClass.setIsDelete(0);
				service.insert(gmClass);
				return Result.success();
		}

		//批量生成
		@RequestMapping("/generate")
		@ResponseBody
		public Result generate(@RequestParam int num) {
				//判断当前是否有当前学年
//初中默认3个年级
//				新一年
				Calendar cale = Calendar.getInstance();
				int year = cale.get(Calendar.YEAR);
//				全删
				service.deleteByYear(year);
				//				全增
				Result result = this.getLatestClassNo();
				if (result.isSuccess()) {
						int lastClassNo = Integer.valueOf(result.getData().toString());
						int startNum = lastClassNo + 1;
						this.generateClass(num, "一", year, startNum);
				}
				result = this.getLatestClassNo();
				if (result.isSuccess()) {
						int lastClassNo = Integer.valueOf(result.getData().toString());
						int startNum = lastClassNo + 1;
						this.generateClass(num, "二", year, startNum);
				}
				result = this.getLatestClassNo();
				if (result.isSuccess()) {
						int lastClassNo = Integer.valueOf(result.getData().toString());
						int startNum = lastClassNo + 1;
						this.generateClass(num, "三", year, startNum);
				}

				return Result.success();
		}

		private void generateClass(int classNum, String gradeNo, int year, int startNum) {
				for (int i = 0; i < classNum; i++) {
						GmClass gmClass = new GmClass();
						gmClass.setClassNo(String.valueOf(startNum));
						gmClass.setYear(year);
						gmClass.setAmount(50);
						gmClass.setStatus(0);
						gmClass.setIsDelete(0);
						gmClass.setGrade(gradeNo);
						gmClass.setName(gradeNo + "(" + (i + 1) + ")班");
						service.insert(gmClass);

						startNum++;
				}
		}

		//更新基础信息
		@RequestMapping("/latest/no")
		@ResponseBody
		public Result getLatestClassNo() {
				Integer classNo = service.getLatestClassNo();
				if (classNo == null) {
						Calendar cale = Calendar.getInstance();
						int year = cale.get(Calendar.YEAR);
						int yearLast = year % 100;
						classNo = yearLast * 100;
				}
				return Result.success(classNo);
		}

		//更新基础信息
		@RequestMapping("/update")
		@ResponseBody
		public Result update(GmClass gmClass) {
				GmClass old = service.findById(gmClass.getId());
				BeanUtil.copyNotNullBean(gmClass, old);
				int r = service.update(old);
				if (gmClass.getName() == old.getName()) {
						return Result.fail("修改后的班级名称重名，请前去修改！");
				}
				return Result.success();
		}

		//逻辑删除
		@RequestMapping("/delete")
		@ResponseBody
		public Result delete(int id, Integer flag) {
				if (flag != null && flag == 1) {
						service.delete(id);
				} else {
						GmClass old = service.findById(id);
						//初始状态为删除
						old.setIsDelete(1);
						int r = service.update(old);
				}
				return Result.success();
		}

		@RequestMapping("/bind/head")
		@ResponseBody
		public Result bindHeadTeacher(@RequestParam int id, @RequestParam int teacherId) {
				GmClass gmClass = service.findById(id);
				if (ObjectUtils.isEmpty(gmClass)) return Result.fail("班级不存在");
				GmTeacher teacher = gmTeacherService.findById(teacherId);
				if (ObjectUtils.isEmpty(teacher)) return Result.fail("教师不存在");
				gmClass.setTeacherId(teacherId);
				gmClass.setTeacherName(teacher.getName());
				service.update(gmClass);
				//给予教师班主任权限
				UserRole role = userRoleService.findByRole("headmaster");
				if (!ObjectUtils.isEmpty(role)) {
						teacherRoleRelationService.insert(teacherId, role.getId());
				}
				return Result.success();
		}

}
