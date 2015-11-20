package bea.com.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import bea.com.IDAO.IDAO;

import bea.com.IService.IWenZhangService;
import bea.com.pojo.Lanmut;
import bea.com.pojo.Moxingt;
import bea.com.pojo.Wenzhangt;

public class WenZhangService implements IWenZhangService{
 	private IDAO dao; //DATA ACCESS OBJECT     FOR MANIPULATE DATABASE
	public IDAO getDao() {
		return dao;
	}

	public void setDao(IDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Lanmut> QueryLanMuCategory(int fatherId) {

		List<Lanmut> list=null;
		try{
			StringBuffer queryString=new StringBuffer();
			//if (fatherId==0){
			    queryString.append("FROM Lanmut AS lanmu " +
			    		"WHERE  lanmu.pid=?");
			    //list=dao.findByHql(queryString.toString());
			//}
			//else{
			   // queryString.append("FROM 属性明细表 AS wuliaoleibie WHERE wuliaoleibie.属性明细pubIdFar=?");
			    list=dao.findByHql(queryString.toString(),new Object[]{fatherId});
			//}
			return list;
		}
		catch (Exception ex){
			return null;
		}
	}

	@Override
	public List<Lanmut> QueryLanMuCategory() {
		return QueryLanMuCategory(0);
	}

	@Override
	public List<Moxingt> getMoXing() {
		List<Moxingt> list=null;
		try{
			StringBuffer queryString=new StringBuffer();
			//if (fatherId==0){
			    queryString.append("FROM Moxingt AS Moxingt" );
		
			    list=dao.findByHql(queryString.toString());
			//}
			return list;
		}
		catch (Exception ex){
			return null;
		}
	}

	@Override
	public List getlanMuById(int mId) {
		List list=null;
		try{
			StringBuffer queryString=new StringBuffer();
			//if (fatherId==0){
			    queryString.append("select lanmu.lanMuName,m.moXingName FROM Moxingt as m,Lanmut as lanmu where m.id=lanmu.moXingId and lanmu.id=?" );
		
			    list=dao.findByHql(queryString.toString(),new Object[]{mId});
			//}
			return list;
		}
		catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	@Override
	public int addWenZhang(Wenzhangt entity) {
		// TODO Auto-generated method stub
		try {
			return dao.addAndGetId4Integer(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * private Integer id;
	private String url;
	private String title;
	private String content;
	private Integer dianJiShu;
	private Date faBuDtae;
	private String picture;
	private String author;
	private Integer sort;
	private Boolean shou;
	private Boolean zuo;
	private Boolean tuiJian;
	private Boolean zhiDing;
	private Integer lanMuId;
	private Boolean shenHeFou;
	 */
	
	
	
	
	@Override
	public List getAllWenZhang(int page,int rows) {
		String hql1="select w.id,w.lanMuId, w.title,w.content,w.dianJiShu,w.faBuDtae,w.author,w.shou,w.zuo,w.tuiJian,w.zhiDing,w.shenHeFou from  Wenzhangt as w ";
		String hql2="select count(*) from  Wenzhangt as w";
		try {
			return dao.showPage(hql1, hql2, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Wenzhangt> getWenZhangByWid(int wId) {
		List<Wenzhangt> list=null;
		try{
			StringBuffer queryString=new StringBuffer();
			//if (fatherId==0){
			    queryString.append("FROM Wenzhangt AS we where we.id=?" );
		
			    list=dao.findByHql(queryString.toString(),new Object[]{wId});
			//}
			return list;
		}
		catch (Exception ex){
			return null;
		}
	}

	@Override
	public List<Wenzhangt> getWenZhangByLanMuid(int lanMuId) {
		List<Wenzhangt> list=null;
		try{
			StringBuffer queryString=new StringBuffer();
			//if (fatherId==0){
			    queryString.append("FROM Wenzhangt AS we where we.lanMuId=?" );
		
			    list=dao.findByHql(queryString.toString(),new Object[]{lanMuId});
			//}
			return list;
		}
		catch (Exception ex){
			return null;
		}
	}

	@Override
	public boolean modifyWenZhang(Wenzhangt entity) {
		// TODO Auto-generated method stub
		try {
			return dao.edit(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteWenZhang(int wid) {
		Wenzhangt wen;
		try{
			wen=dao.getById(Wenzhangt.class,wid);
			return dao.remove(wen);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List getAllWenZhangByLanMuId(int lnmuId, int page, int rows) {
		HashMap<String, Object> params=new HashMap<String, Object>();
		params.put("lanmuid", lnmuId);
		
		
		String hql1="select w.id,w.lanMuId, w.title,w.content,w.dianJiShu,w.faBuDtae,w.author,w.shou,w.zuo,w.tuiJian,w.zhiDing,w.shenHeFou from  Wenzhangt as w where w.lanMuId = :lanmuid";
		String hql2="select count(*) from  Wenzhangt as w where w.lanMuId = :lanmuid";
		
		try {
			return dao.findByHql(hql1, hql2, params, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
