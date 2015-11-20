package bea.com.Service;

import java.util.ArrayList;
import java.util.List;

import bea.com.IDAO.IDAO;
import bea.com.IService.IuserService;
import bea.com.pojo.Quanxiant;
import bea.com.pojo.Yonghulogt;
import bea.com.pojo.Yonghut;
import bea.com.util.MD5Util;

public class userService implements IuserService{
   private IDAO dao;
   
	public IDAO getDao() {
	return dao;
}

public void setDao(IDAO dao) {
	this.dao = dao;
}

	@Override
	public int addUser(Yonghut entity) {
		try {
			return dao.addAndGetId4Integer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List getQYongHu(Yonghut entity) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("from Yonghut u where u.yongHuMing=? and u.pwd=?");
	try {
		List list= dao.findByHql(queryString.toString(),new Object[] {entity.getYongHuMing(),MD5Util.md5(entity.getPwd())});	
		return list;
	} catch (Exception e) {
		return null;
	}	
	
	}

	@Override
	public List<Yonghut> getYongHu(String name, String pwd) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("from Yonghut as u where u.yongHuMing=? and u.pwd=?");
	try {
		return dao.findByHql(queryString.toString(),new Object[] {name,MD5Util.md5(pwd)});	
		//return list;
	} catch (Exception e) {
		return null;
	}	
	}

	@Override
	public List getBinary(int yonghuId) {
		try {
			return dao.findByHql("select rt.qian from Yonghut as u , Rolet as rt where rt.id=u.roleId and u.id=?",new Object[]{yonghuId});
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int addLOG(Yonghulogt entity) {
		try {
			return dao.addAndGetId4Integer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean modifyYongHu(Yonghut entity) {
		try {
			return dao.edit(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Yonghulogt> getLOG(int id) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("from Yonghulogt as u where u.yongHuId=? and u.dengChuFou=false");
	try {
		return dao.findByHql(queryString.toString(),new Object[] {id});			
	} catch (Exception e) {
		return null;
	}	
	}

	@Override
	public boolean modifyLog(Yonghulogt entity) {
		try {
			return dao.edit(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	
	

}
