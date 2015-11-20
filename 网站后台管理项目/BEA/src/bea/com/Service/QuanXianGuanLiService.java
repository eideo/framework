package bea.com.Service;
import java.util.List;

import javax.management.Query;

import bea.com.pojo.*;
import bea.com.IDAO.*;
import bea.com.Action.*;
import bea.com.IService.*;
public class QuanXianGuanLiService implements QuanXianGuanLiIService{
   private IDAO idao;
   public IDAO getIdao(){
	   return idao;
   }
   public void setIdao(IDAO idao){
	   this.idao=idao;
   }
   @Override
   public List getGuanLiZu(){
	   StringBuffer QueryString=new StringBuffer();
	   QueryString.append("SELECT rolet.id, rolet.name,rolet.qian,rolet.describeD " +
	   		" FROM Rolet as rolet"  );
	   try { 
		   return idao.findByHql(QueryString.toString());
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
  }
   @Override
   public List getGuanliyuan(){
	   try {
		   StringBuffer QueryString=new StringBuffer();
	       QueryString.append("SELECT yonghu.id, yonghu.yongHuMing,yonghu.realName," +
	       		" bumen.buMenName,yonghu.deLuCount,yonghu.createTime,yonghu.duorendenglu, " +
	       		"yonghu.zhuTai" +
	       		" FROM Yonghut as yonghu,Bument as bumen,Rolet as rolet " +
	       		" where  yonghu.bumenId=bumen.id  and yonghu.roleId=rolet.id "  );
		   return idao.findByHql(QueryString.toString());
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
  }
   @Override
	public boolean deleteEmployee(Integer employeeId) {
		// TODO Auto-generated method stub
	   Yonghut employee;
		try{
			employee=idao.getById(Yonghut.class, employeeId);
			return idao.remove(employee);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
   @Override
  	public boolean deleteguanlizu(Integer guanlizuId) {
  		// TODO Auto-generated method stub
  	   Rolet rolet;
  		try{
  			rolet=idao.getById(Rolet.class, guanlizuId);
  			return idao.remove(rolet);
  		}catch(Exception e){
  			e.printStackTrace();
  			return false;
  		}
  	}
   @Override
	public List<Bument> QueryBase() {
		// TODO Auto-generated method stub
		try{
			//StringBuffer queryString=new StringBuffer();
			return idao.findByHql("FROM Bument");
		}
		catch (Exception ex){
		    return null;
		}
	}
   @Override
	public List<Rolet> QueryUnit() {
		// TODO Auto-generated method stub
		try{
			//StringBuffer queryString=new StringBuffer();
			return idao.findByHql("FROM Rolet");
		}
		catch (Exception ex){
		    return null;
		}
	}
   @Override
  	public List QueryQuanXian() {
  		StringBuffer QueryString = new StringBuffer();
  		QueryString.append("SELECT  authority.id,authority.quanxianName,authority.binary " +
  				            "  FROM Quanxiant as authority"  );	
         try {
  			return idao.findByHql(QueryString.toString());
  		} catch (Exception e) {
  			e.printStackTrace();
  			return null;
  		}
  	}
   @Override
   public int addguanliyuan(Rolet  entity) {
   	try{
   		return  idao.addAndGetId4Integer(entity);
   	    }
   		catch(Exception e){
   		    e.printStackTrace();
   	         return 0;
   	    }
   }
   @Override
	public List QueryBuMen() {
		// TODO Auto-generated method stub
		 try{
  		 StringBuffer queryString=new StringBuffer();
  		 queryString.append("SELECT dt.id,dt.buMenName  FROM Bument as dt");
  		 return idao.findByHql(queryString.toString());
  	 }catch(Exception e){
  		 e.printStackTrace();
  		 return null;
  	 }
	}
   @Override
  	public List QueryBuMenRolet() {
  		// TODO Auto-generated method stub
  		 try{
    		 StringBuffer queryString=new StringBuffer();
    		 queryString.append("SELECT dt.id,dt.name  FROM Rolet as dt");
    		 return idao.findByHql(queryString.toString());
    	 }catch(Exception e){
    		 e.printStackTrace();
    		 return null;
    	 }
  	}
  	 @Override
   	public List QuerygetIP() {
   		// TODO Auto-generated method stub
   		 try{
     		 StringBuffer queryString=new StringBuffer();
     		 queryString.append("SELECT dt.id,dt.loginIp  FROM Yonghulogt as dt");
     		 return idao.findByHql(queryString.toString());
     	 }catch(Exception e){
     		 e.printStackTrace();
     		 return null;
     	 }
   	}
   @Override
	public int addYonghut(Yonghut yonghut) {
		// TODO Auto-generated method stub
		try{
			return idao.addAndGetId4Integer(yonghut);
		}catch(Exception e){
			return 0;
		}
	}
   public List QueryDingDanBydingDanId(int gunaliyuanId) {
		try{
		    StringBuffer queryString=new StringBuffer();
		    queryString.append("SELECT yonghu.id, yonghu.yongHuMing,yonghu.realName," +
			   		" bumen.id,yonghu.deLuCount,yhlogt.loginIp, yonghu.createTime,yonghu.duorendenglu," +
			   		" yonghu.zhuTai, yonghu.roleId,yonghu.teltPhone " +
			   		"  FROM Yonghut as yonghu,Bument as bumen,Rolet as rolet,Yonghulogt as yhlogt " +
			   		"  where  yonghu.bumenId=bumen.id   and yonghu.roleId=rolet.id  and" +
			   		"  yonghu.roleId=yhlogt.yongHuId AND yonghu.id=?");
			    return idao.findByHql(queryString.toString(),new Object[]{gunaliyuanId});
		}catch (Exception ex){
			    return null;
		}
	}
   @Override
   public boolean modifyonghuy(Yonghut  entity) {
   try{
   		
   		return 	idao.edit(entity);
   	}
   	catch (Exception ex){
   		ex.printStackTrace();
   	    return false;
   	}
   }
}
