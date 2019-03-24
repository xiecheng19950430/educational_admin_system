package com.ebay.controllers;

import com.ebay.common.Result;
import com.ebay.common.utils.BeanUtil;
import com.ebay.models.UserModule;
import com.ebay.services.UserModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/module")
public class UserModuleController {
		@Autowired
		private UserModuleService service;


		//列表
		@RequestMapping("/list")
		@ResponseBody
		public Result list() {
				List<UserModule> list = service.query();
				return Result.success(list);
		}

		//树列表
		@RequestMapping("/list/tree")
		@ResponseBody
		public Result treeList() {
				List<UserModule> list = service.query();
				List<UserModule> heads = list.stream().filter(module -> module.getPid() == null).collect(Collectors.toList());
				this.setTree(list, heads);
				return Result.success(heads);
		}

		private void setTree(List<UserModule> list, List<UserModule> pModules) {
				pModules.forEach(pModule -> {
						List<UserModule> childes = list.stream().filter(module -> Objects.equals(pModule.getId(), module.getPid())).collect(Collectors.toList());
						pModule.setChildren(childes);
						if (!CollectionUtils.isEmpty(childes)) this.setTree(list, childes);
				});
		}


		//更新基础信息
		@RequestMapping("/save")
		@ResponseBody
		public Result save(UserModule module) {
				UserModule old = service.findById(module.getId());
				int n;
				if (ObjectUtils.isEmpty(old)) {
						//新增
						n = service.insert(module);
				} else {
						//修改
						BeanUtil.copyNotNullBean(module, old);
						n = service.update(old);
				}
				return n > 0 ? Result.success() : Result.fail("操作失败");
		}

		//删除
		@RequestMapping("/delete")
		@ResponseBody
		public Result delete(@RequestParam int id) {
                //同时删除子模块
				service.deleteWithChildren(id);

				return Result.success();
		}


}
