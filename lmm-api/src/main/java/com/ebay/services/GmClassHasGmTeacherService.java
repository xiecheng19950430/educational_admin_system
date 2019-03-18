package com.ebay.services;

import com.ebay.mappers.GmClassHasGmTeacherMapper;
import com.ebay.models.GmClassHasGmTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GmClassHasGmTeacherService {
		@Autowired
		private GmClassHasGmTeacherMapper mapper;

		public void deleteByTeacherId(int teacherId) {
				mapper.deleteByTeacherId(teacherId);
		}

		public List<GmClassHasGmTeacher> queryByTeacherId(int teacherId) {
				return mapper.queryByTeacherId(teacherId);
		}

		public void delete(int id) {
				mapper.delete(id);
		}

		public void save(int teacherId, int classId) {
				mapper.save(teacherId, classId);
		}
}
