package com.ebay.services;

import com.ebay.mappers.UserModuleMapper;
import com.ebay.models.UserModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserModuleService {
		@Autowired
		private UserModuleMapper mapper;

		public UserModule findByUrl(String url) {
				return mapper.findByUrl(url);
		}

		public int insert(UserModule module) {
				return mapper.insert(module);
		}

		public List<UserModule> query() {
				return mapper.query();
		}

		public int update(UserModule module) {
				return mapper.update(module);
		}

		public int delete(int id) {
				return mapper.delete(id);
		}

		public UserModule findById(Integer id) {
				return mapper.findById(id);
		}
}
