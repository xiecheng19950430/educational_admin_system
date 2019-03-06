package com.ebay.models;

import lombok.Data;

import java.util.List;

@Data
public class UserModule {
		private Integer id;
		private String moduleName;
		private String url;
		private Integer pid;

		private List<UserModule> children;
}
