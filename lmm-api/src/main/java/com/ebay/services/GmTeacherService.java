package com.ebay.services;

import com.ebay.mappers.GmTeacherMapper;
import com.ebay.models.GmTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GmTeacherService {
		@Autowired
		private GmTeacherMapper mapper;

		public List<GmTeacher> getTeacherList(Integer classId, String workNo, Integer isDelete, String name, Integer page, Integer size) {
				Integer start = null;
				if (page != null)
						start = (page - 1) * size;
				return mapper.getTeacherList(classId, workNo, isDelete, name, start, size);
		}

		public GmTeacher findById(int id) {
				return mapper.findById(id);
		}

		public GmTeacher findByClassId(Integer classId, String role) {
				return mapper.findByClassId(classId, role);
		}

		public GmTeacher findByLoginId(String loginId) {
				return mapper.findByLoginId(loginId);
		}

		public int count(String workNo, Integer isDelete, String name) {
				return mapper.count(workNo, isDelete, name);
		}

		public int insert(GmTeacher gmTeacher) {
				//默认角色teacher
				gmTeacher.setRole("teacher");
				return mapper.insert(gmTeacher);
		}

		public int update(GmTeacher gmTeacher) {
				return mapper.update(gmTeacher);
		}

		public int delete(int id) {
				return mapper.deleteById(id);
		}

		public List<GmTeacher> queryByClassId(int classId) {
				return mapper.queryByClassId(classId);

		}
}
