package com.ebay.models;

import lombok.Data;

@Data
public class UserRole {
		private Integer id;
		private String role;
		private String roleName;

		private String moduleIds;
		private String moduleUrls;

		//弃用
		private Integer moduleId;

}
