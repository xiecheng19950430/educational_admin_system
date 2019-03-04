package com.ebay.controllers;

import com.ebay.common.Result;
import com.ebay.common.utils.BeanUtil;
import com.ebay.common.utils.MD5Util;
import com.ebay.models.GmTeacher;
import com.ebay.services.GmTeacherService;
import com.ebay.services.IimportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/gm_teacher")
public class GmTeacherController {
		@Autowired
		private GmTeacherService service;
		@Autowired
		private IimportService iimportService;

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
				return Result.success();
		}

		//更新基础信息
		@RequestMapping("/update")
		@ResponseBody
		public Result update(GmTeacher gmTeacher) {
				GmTeacher old = service.findById(gmTeacher.getId());
				BeanUtil.copyNotNullBean(gmTeacher, old);
				int r = service.update(old);
				return Result.success();
		}

		//通过主键查询教师信息
		@RequestMapping("/findById")
		@ResponseBody
		public Result findById(int id) {
				GmTeacher gmTeacher = service.findById(id);
				return Result.success(gmTeacher);
		}

		//Excel导入教师信息到数据库
		@RequestMapping("/importExcel")
		@ResponseBody
		public Result importExcel(@RequestParam("teacherFile") MultipartFile teacherFile) throws ParseException {
				iimportService.importTeacherExcel(teacherFile);
				return Result.success();
		}


		//添加班主任，展示教师列表
		@RequestMapping("/head/list")
		@ResponseBody
		public Result list(int classId) {
				List<GmTeacher> list = service.getTeacherList(classId, null, 0, null, null, null);
				return Result.build().put("list", list).result();
		}

		//绑定班级班主任信息
		@RequestMapping("/bindHeadmaster")
		@ResponseBody
		public Result bindHeadmaster(int classId) {
				GmTeacher gmTeacher = service.findByClassId(classId, "headmaster");
				if (ObjectUtils.isEmpty(gmTeacher)) {
						gmTeacher.setRoleName("headmaster");
						service.update(gmTeacher);
				} else {
						return Result.fail("该班级已绑定班主任信息!");
				}
				return Result.success();
		}


}
