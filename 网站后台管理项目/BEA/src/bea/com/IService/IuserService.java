package bea.com.IService;

import java.util.List;

import bea.com.pojo.Quanxiant;
import bea.com.pojo.Yonghulogt;
import bea.com.pojo.Yonghut;


public interface IuserService {
public int addUser(Yonghut entity);
public List getQYongHu(Yonghut entity);
public List<Yonghut> getYongHu(String name,String pwd);
public List getBinary(int yonghuId);
public int addLOG(Yonghulogt entity);
public boolean modifyYongHu(Yonghut entity);
public List<Yonghulogt> getLOG(int id);
public boolean modifyLog(Yonghulogt entity);
}
