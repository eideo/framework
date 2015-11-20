package bea.com.IService;

import java.util.List;

import bea.com.pojo.Lanmut;
import bea.com.pojo.Moxingt;
import bea.com.pojo.Wenzhangt;

public interface IWenZhangService {
    public List<Lanmut> QueryLanMuCategory(int fatherId); //实现递归查询
    public List<Lanmut> QueryLanMuCategory(); //实现递归查询子节点
    public List getAllWenZhangByLanMuId(int lnmuId,int page,int rows);
    public List getAllWenZhang(int page,int rows); 
    public List<Moxingt> getMoXing(); //实现递归查询子节点
    public List getlanMuById(int mId); //实现递归查询子节点
	public int addWenZhang(Wenzhangt entity);
	public boolean modifyWenZhang(Wenzhangt entity);
	public List<Wenzhangt> getWenZhangByWid(int wId);
	public List<Wenzhangt> getWenZhangByLanMuid(int wId);
	
	public boolean deleteWenZhang(int wid);
}
