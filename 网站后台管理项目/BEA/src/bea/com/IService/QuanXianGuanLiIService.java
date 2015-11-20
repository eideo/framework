package bea.com.IService;
import java.util.List;

import bea.com.pojo.*;
public interface QuanXianGuanLiIService {
    public	List getGuanLiZu();
	public  List getGuanliyuan();
    public	boolean deleteEmployee(Integer employeeId);
    public List<Bument> QueryBase();
    public	List QueryQuanXian();
    public List<Rolet> QueryUnit() ;
    public int addguanliyuan(Rolet entity);
    public List QueryBuMen();
    public List QueryBuMenRolet();
    public int addYonghut(Yonghut yonghut);
	public List QuerygetIP();
	public boolean deleteguanlizu(Integer guanlizuId);
	public List QueryDingDanBydingDanId(int gunaliyuanId);
	public boolean modifyonghuy(Yonghut  entity);
}
