package com.forestry.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.forestry.dao.sys.DepartmentDao;
import com.forestry.model.sys.Department;

import core.dao.BaseDao;

/**
 * @框架唯一的升级和技术支持地址：http://shop111863449.taobao.com
 */
@Repository
public class DepartmentDaoImpl extends BaseDao<Department> implements DepartmentDao {

	public DepartmentDaoImpl() {
		super(Department.class);
	}

}
