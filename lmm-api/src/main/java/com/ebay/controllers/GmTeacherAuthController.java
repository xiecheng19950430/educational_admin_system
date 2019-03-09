package com.ebay.controllers;

import com.ebay.common.Result;
import com.ebay.models.GmTeacher;
import com.ebay.services.GmTeacherAuthService;
import com.ebay.services.TeacherRoleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/gm_teacher_auth")
public class GmTeacherAuthController {
		@Autowired
		private GmTeacherAuthService service;
		@Autowired
		private GmTeacherController gmTeacherController;
		@Autowired
		private TeacherRoleRelationService teacherRoleRelationService;


}
