package bea.com.Action;
import bea.com.pojo.*;
import bea.com.Action.*;
import bea.com.IService.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import bea.com.util.*;

import javax.xml.crypto.Data;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class QuanXianGuanLiAction extends BaseAction {
	private JSONObject JSObj;
	private String MSG;
	private JSONArray JSArr;
	private QuanXianGuanLiIService  quanXianGuanLiIService;
    public JSONObject getJSObj() {
		return JSObj;
	}
	public void setJSObj(JSONObject jSObj) {
		JSObj = jSObj;
	}
    public JSONArray getJSArr() {
		return JSArr;
	}
	public void setJSArr(JSONArray jSArr) {
		JSArr = jSArr;
	}
	public String getMSG() {
		return MSG;
	}
	public void setMSG(String mSG) {
		MSG = mSG;
	}
	public QuanXianGuanLiIService getQuanXianGuanLiIService(){
		return quanXianGuanLiIService;
	}
	public void setQuanXianGuanLiIService(QuanXianGuanLiIService quanXianGuanLiIService){
		this.quanXianGuanLiIService=quanXianGuanLiIService;
	}
	
	public String getGuanLiZu(){
		List getGuanLiZu=this.quanXianGuanLiIService.getGuanLiZu();
		JSONObject jsObj=new JSONObject();
		JSONArray jsArr=new JSONArray();
		for(int i=0;i<getGuanLiZu.size();i++){
			Object[] obj= (Object[])getGuanLiZu.get(i);
			jsObj.clear();
			jsObj.put("guanlizuid", obj[0]);
			jsObj.put("guanlizu","<font color='#1A76CB'>管理组ID  </font>"+obj[0].toString()+"<input value="+obj[1].toString()+"> </input>");
			jsObj.put("binary", obj[2]);
			try {
				 jsObj.put("describe","<input value="+obj[3].toString()+"> </input>");
			} catch (Exception e) {
				// TODO: handle exception
			}
				
			
			jsArr.add(jsObj);
		}
		HashMap<String,Object> jsMap = new HashMap<String,Object>();
		jsMap.put("",jsArr);
		JSArr=jsArr;
		return SUCCESS;
	}
	public String getguanliyuan(){
		List getGuanLiYuan=this.quanXianGuanLiIService.getGuanliyuan();
		JSONObject jsObj=new JSONObject();
		JSONArray jsArr=new JSONArray();
		for(int i=0;i<getGuanLiYuan.size();i++){
			Object[] obj= (Object[])getGuanLiYuan.get(i);
			jsObj.clear();
			jsObj.put("ID", obj[0]);
			jsObj.put("yonghuming", obj[1]);
			jsObj.put("name", obj[2]);
			jsObj.put("bumen", obj[3]);
			jsObj.put("denglucishu", obj[4]);
			jsObj.put("dengchushijian", obj[5].toString());
			if(obj[6].toString().equals("true")){
				jsObj.put("duorendenglu", "允许");
			}
			else{
				jsObj.put("duorendenglu", "不允许");
			}
			if(obj[7].toString().equals("true")){
				jsObj.put("zhuTai", "启用");
			}
			else{
				jsObj.put("zhuTai", "不启用");
			}
			jsArr.add(jsObj);
		}
		HashMap<String,Object> jsMap=new HashMap<String,Object>();
		jsMap.put("rows", jsArr);
		JSObj=JSONObject.fromObject(jsMap);
		return SUCCESS;
	}
	public String deleteEmployee(){
		int employeeID=Integer.parseInt(this.getRequest().getParameter("employeeId").toString().trim());
		if(this.quanXianGuanLiIService.deleteEmployee(employeeID)){
			MSG="OK";
		}
		return SUCCESS;
	}
	public String deleteguanlizu(){
		String arr=this.getRequest().getParameter("arr");
		String[] gid= arr.split(",");
		
		for(int i=0;i<=gid.length;i++){
			int guanlizuId=Integer.parseInt(gid[i]);
			if(this.quanXianGuanLiIService.deleteguanlizu(guanlizuId)){
				
			}
		}
		MSG="OK";
		return SUCCESS;
	}
	public String loadAddGuanLiYuan() {
		List<Bument> lstBument = this.quanXianGuanLiIService.QueryBase();
    	List<Rolet> lstRolet = this.quanXianGuanLiIService.QueryUnit();
    	
    	
    	this.getRequest().setAttribute("lstBument", lstBument);
    	this.getRequest().setAttribute("lstRolet", lstRolet);
    	
    	return SUCCESS;
	}
	public String getAuthority(){
		List workNum=this.quanXianGuanLiIService.QueryQuanXian();
		JSONObject jsObj=new JSONObject();
		JSONArray jsArr=new JSONArray();
		for(int i=0;i<workNum.size();i++){
			Object[] obj= (Object[])workNum.get(i);
			jsObj.clear();
			jsObj.put("id", obj[0]);
			jsObj.put("quanxianName",obj[1]);
			jsObj.put("quanxianzhi",obj[2]);
			jsArr.add(jsObj);
		}
		HashMap<String,Object> jsMap = new HashMap<String,Object>();
		jsMap.put("",jsArr);
		JSArr=jsArr;
		return SUCCESS;
	}
	public String addguanliyuanzu(){
		   Rolet  lvRolet=new Rolet();
	       String mingcheng=encodingFunction.getMethodEncoding(this.getRequest().getParameter("mingcheng").toString().trim());
	       String miaoshu =encodingFunction.getMethodEncoding(this.getRequest().getParameter("miaoshu").toString().trim());
	       String authorityNum =encodingFunction.getMethodEncoding(this.getRequest().getParameter("authorityNum").toString().trim());

	       lvRolet.setName(mingcheng);
	       lvRolet.setDescribeD(miaoshu);
	       lvRolet.setQian(authorityNum);
	       MSG=this.quanXianGuanLiIService.addguanliyuan(lvRolet)+"";
	       return SUCCESS;
	} 
	public String getBuMen(){
		List buMen=this.quanXianGuanLiIService.QueryBuMen();
		JSONObject jsObj=new JSONObject();
		JSONArray jsArr=new JSONArray();
		for(int i=0;i<buMen.size();i++){
			Object[] obj= (Object[])buMen.get(i);
			jsObj.clear();
			jsObj.put("buMenId", obj[0]);
			jsObj.put("buMenMingCheng", obj[1]);
			jsArr.add(jsObj);
		}
		HashMap<String,Object> jsMap = new HashMap<String,Object>();
		jsMap.put("",jsArr);
		JSArr=jsArr;
		return SUCCESS;
		
	}
	public String getRolet(){
		List buMen=this.quanXianGuanLiIService.QueryBuMenRolet();
		JSONObject jsObj=new JSONObject();
		JSONArray jsArr=new JSONArray();
		for(int i=0;i<buMen.size();i++){
			Object[] obj= (Object[])buMen.get(i);
			jsObj.clear();
			jsObj.put("RoletId", obj[0]);
			jsObj.put("RoletMingCheng", obj[1]);
			jsArr.add(jsObj);
		}
		HashMap<String,Object> jsMap = new HashMap<String,Object>();
		jsMap.put("",jsArr);
		JSArr=jsArr;
		return SUCCESS;
	}
	public String getIP(){
		List buMen=this.quanXianGuanLiIService.QuerygetIP();
		JSONObject jsObj=new JSONObject();
		JSONArray jsArr=new JSONArray();
		for(int i=0;i<buMen.size();i++){
			Object[] obj= (Object[])buMen.get(i);
			jsObj.clear();
			jsObj.put("id", obj[0]);
			jsObj.put("loginIp", obj[1]);
			jsArr.add(jsObj);
		}
		HashMap<String,Object> jsMap = new HashMap<String,Object>();
		jsMap.put("",jsArr);
		JSArr=jsArr;
		return SUCCESS;
	}
	public String addyonghu(){
		String yonghuming=encodingFunction.getMethodEncoding(this.getRequest().getParameter("yonghuming").toString());
		String name=encodingFunction.getMethodEncoding(this.getRequest().getParameter("name").toString());
		boolean zhuangtai=Boolean.getBoolean(this.getRequest().getParameter("zhuangtai").toString());
		boolean duorendenglu=Boolean.getBoolean(this.getRequest().getParameter("duorendenglu").toString().trim());
		Date degnlutime=dateFunction.string2Date(this.getRequest().getParameter("degnlutime").toString().trim());
		int bumenId=Integer.parseInt(this.getRequest().getParameter("bumenId").toString().trim());
		int ChuangJianRen=Integer.parseInt(this.getRequest().getParameter("ChuangJianRen").toString().trim());
		String phone=encodingFunction.getMethodEncoding(this.getRequest().getParameter("phone").toString());
		Yonghut yonghut=new Yonghut();
		yonghut.setBumenId(bumenId);
		yonghut.setYongHuMing(yonghuming);
		yonghut.setRealName(name);
		yonghut.setDuorendenglu(duorendenglu);
		yonghut.setRoleId(ChuangJianRen);
		yonghut.setZhuTai(zhuangtai);
		yonghut.setCreateTime(degnlutime);
		yonghut.setTeltPhone(phone);
		if(this.quanXianGuanLiIService.addYonghut(yonghut)>0){
			MSG="OK";
		}
		return SUCCESS;
	}
	public String getguanliyuanBygunaliyuanId(){
		int gunaliyuanId = Integer.parseInt(this.getRequest().getParameter("gunaliyuanId").toString().trim());
		List dd = quanXianGuanLiIService.QueryDingDanBydingDanId(gunaliyuanId);
		JSONArray jsArr = new JSONArray();
		JSONObject jsObj = new JSONObject();
		for(int i = 0;i<dd.size();i++){
			Object[] obj = (Object[])dd.get(i);
			jsObj.put("ID", obj[0]);
			jsObj.put("yonghuming", obj[1]);
			jsObj.put("name", obj[2]);
			jsObj.put("bumen", obj[3]);
			jsObj.put("denglucishu", obj[4]);
			jsObj.put("zuihoudengluIP", obj[5]);
			jsObj.put("dengchushijian", obj[6].toString());
			jsObj.put("duorendenglu", obj[7]);
			jsObj.put("zhuTai", obj[8]);
			jsObj.put("roleId", obj[9]);
			jsObj.put("phone", obj[10]);
			jsArr.add(jsObj);
		}
		JSArr = jsArr;	
		return SUCCESS;
	}
	 public String modifyonghu(){
		    Yonghut yonghut=new Yonghut();
		    int gunaliyuanid=Integer.parseInt(this.getRequest().getParameter("gunaliyuanId").toString().trim());
		    String yonghuming=encodingFunction.getMethodEncoding(this.getRequest().getParameter("yonghuming").toString());
			String name=encodingFunction.getMethodEncoding(this.getRequest().getParameter("name").toString());
			int bumenId=Integer.parseInt(this.getRequest().getParameter("bumenId").toString().trim());
			int guanlizuId=Integer.parseInt(this.getRequest().getParameter("guanlizuId").toString().trim());
			boolean zhuangtai=Boolean.getBoolean(this.getRequest().getParameter("zhuangtai").toString());
			boolean duorendenglu=Boolean.getBoolean(this.getRequest().getParameter("duorendenglu").toString().trim());
			Date degnlutime=dateFunction.string2Date(this.getRequest().getParameter("degnlutime").toString().trim());
			String phone=encodingFunction.getMethodEncoding(this.getRequest().getParameter("phone").toString());
			
			yonghut.setId(gunaliyuanid);
			yonghut.setYongHuMing(yonghuming);
			yonghut.setRealName(name);
			yonghut.setBumenId(bumenId);
			yonghut.setRoleId(guanlizuId);
			yonghut.setZhuTai(zhuangtai);
			yonghut.setDuorendenglu(duorendenglu);
			yonghut.setCreateTime(degnlutime);
			yonghut.setTeltPhone(phone);
			
			MSG=this.quanXianGuanLiIService.modifyonghuy(yonghut)+"";
			return SUCCESS;
	} 
}
