package bea.com.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import bea.com.IDAO.IDAO;
import bea.com.IService.IlanMuGuanLiService;
import bea.com.pojo.Lanmut;

public class lanMuGuanLiService implements IlanMuGuanLiService{
    private IDAO dao;
    
	public IDAO getDao() {
		return dao;
	}

	public void setDao(IDAO dao) {
		this.dao = dao;
	}

	@Override
	public List getMoXing() {
		try {
			return dao.findByHql("select t.id ,t.moXingName from Moxingt t");
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List getLanMu(String id) {
		String hql=null;
		Map<String, Object>  params=new HashMap<String, Object>();
		if(id==null || id.equals("")){
			hql="select t.id,t.pid,t.lanMuName,t.sort,tm.id,tm.moXingName from Moxingt tm ,Lanmut t where tm.id=t.moXingId and t.id is null";
		}else{
			hql="select t.id,t.pid,t.lanMuName,t.sort,tm.id,tm.moXingName from Moxingt tm ,Lanmut t where tm.id=t.moXingId and t.id =:id";
			params.put("id", id);
		}
		try {
			List l=dao.find(hql, params);			
			for(int i=0;i<l.size();i++){
				Object[] obj=(Object[]) l.get(i);
				String pid=obj[1].toString();
				if(pid!=null && !pid.isEmpty()){
					l.add("closed");
				}else{
					l.add("open");
				}
			}
			return l;
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List getAllLanMu() {
		try {
			return dao.findByHql("select t.id,t.pid,t.lanMuName,t.sort,tm.id,tm.moXingName from Moxingt tm ,Lanmut t where tm.id=t.moXingId and t.pid=0");
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List getAllLanMu(int id) {
		try {
			return dao.findByHql("select t.id,t.pid,t.lanMuName,t.sort,tm.id,tm.moXingName from Moxingt tm ,Lanmut t where tm.id=t.moXingId and t.pid=?",new Object[]{id});
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean deleteLanMu(int id) {
		Lanmut l;
		try {
			l=dao.getById(Lanmut.class, id);
			return dao.remove(l);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean modifyLanMu(Lanmut entity) {
		try {
			return dao.edit(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int addLanMu(Lanmut entity) {
		try {
			return dao.addAndGetId4Integer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List getLMEDIT(int id) {
		try {
			return dao.findByHql("select t.id,t.pid,t.lanMuName,t.sort,tm.id,tm.moXingName from Moxingt tm ,Lanmut t where tm.id=t.moXingId and t.id=?",new Object[]{id});
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List getLanMuBYmX(int moxingID) {
		try {
			return dao.findByHql("select t.id,t.pid,t.lanMuName,t.sort,tm.id,tm.moXingName from Moxingt tm ,Lanmut t where tm.id=t.moXingId and t.pid=0 and tm.id=?",new Object[]{moxingID});
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List getLanMuBYmX(int fatherid, int moxingID) {
		try {
			return dao.findByHql("select t.id,t.pid,t.lanMuName,t.sort,tm.id,tm.moXingName from Moxingt tm ,Lanmut t where tm.id=t.moXingId and t.pid=? and tm.id=?",new Object[]{fatherid,moxingID});
		} catch (Exception e) {
			return null;
		}
	}

}
