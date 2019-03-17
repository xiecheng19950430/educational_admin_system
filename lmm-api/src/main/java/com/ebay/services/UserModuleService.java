package com.ebay.services;

import com.ebay.mappers.UserModuleMapper;
import com.ebay.models.UserModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserModuleService {
		@Autowired
		private UserModuleMapper mapper;
		@Autowired
		private RoleModuleRelationService roleModuleRelationService;

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

		public void deleteWithChildren(int id) {
				List<UserModule> children = mapper.queryByPid(id);
				if (!CollectionUtils.isEmpty(children)) {
						children.forEach(m -> this.deleteWithChildren(m.getId()));
				}
				mapper.delete(id);
				//				清理授权角色关系
				roleModuleRelationService.deleteByModuleId(id);
		}

		public UserModule findById(Integer id) {
				return mapper.findById(id);
		}

		public List<UserModule> queryByTeacherId(int teacherId) {
				return mapper.queryByTeacherId(teacherId);
		}

		public List<UserModule> queryByRoleId(int roleId) {
				return mapper.queryByRoleId(roleId);
		}
}
