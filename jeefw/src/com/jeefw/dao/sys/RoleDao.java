package com.jeefw.dao.sys;

import com.jeefw.model.sys.Role;

import core.dao.Dao;

/**
 * 角色的数据持久层的接口
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
public interface RoleDao extends Dao<Role> {

	void deleteSysUserAndRoleByRoleId(Long roleId);

}
