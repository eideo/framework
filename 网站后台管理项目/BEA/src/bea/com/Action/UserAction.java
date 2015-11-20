package bea.com.Action;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.aspectj.weaver.patterns.HasMemberTypePattern;

import bea.com.IService.IuserService;
import bea.com.pojo.Yonghulogt;
import bea.com.pojo.Yonghut;
import bea.com.util.DateParse;
import bea.com.util.MD5Util;
import bea.com.util.encodingFunction;

public class UserAction extends BaseAction{
    private  Yonghut yonghut;
    private  IuserService userService;
    private  String  pwd;
    private String MSG;   
    
	public String getMSG() {
		return MSG;
	}

	public void setMSG(String mSG) {
		MSG = mSG;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public IuserService getUserService() {
		return userService;
	}

	public void setUserService(IuserService userService) {
		this.userService = userService;
	}

	public Yonghut getYonghut() {
		return yonghut;
	}

	public void setYonghut(Yonghut yonghut) {
		this.yonghut = yonghut;
	}
    public void regUser(){
    	int id=Integer.parseInt(this.getRequest().getParameter("id").toString());
    	this.yonghut.setBumenId(1);
    	this.yonghut.setCreateTime(new Date());
    	this.yonghut.setRoleId(6);
    	this.yonghut.setZhuTai(true);
    	this.yonghut.setPwd(MD5Util.md5(pwd));
    	int t=userService.addUser(yonghut);
    	HashMap<String, Object> jsMap = new HashMap<String, Object>();
    	if(t>0){
    		jsMap.put("cf", "ok");
    		jsMap.put("MSG", "恭喜,注册成功!");   		
    	}else{
    		jsMap.put("MSG", "注册失败!");
    	}
    	super.writeJson(jsMap);
    }
    
    public void  denglu(){
    	Yonghulogt tYonghulogt=new Yonghulogt();
    	String name=encodingFunction.getMethodEncoding(this.getRequest().getParameter("name").toString());
    	String lgpwd=encodingFunction.getMethodEncoding(this.getRequest().getParameter("lgpwd").toString());
    	List<Yonghut> t=this.userService.getYongHu(name, lgpwd);
    	InetAddress ip = null;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	String  te=ip.getHostAddress();
    	
    	Yonghut u=t.get(0);
    	HashMap<String, Object> jsMap=new HashMap<String, Object>();
        if(u!=null){
        	this.getSession().setAttribute("userId", u.getId());
        	List lst=this.userService.getBinary(u.getId());
        	this.getSession().setAttribute("biy", lst.get(0));
        	tYonghulogt.setLoginTime(DateParse.date2String(new Date()));
        	tYonghulogt.setYongHuId(u.getId());
        	tYonghulogt.setLoginIp(te);
        	tYonghulogt.setDengChuFou(false);
        	boolean k=false;
        	if (u.getDeLuCount()==null) {
				u.setDeLuCount(1);
			}else{
				u.setDeLuCount(u.getDeLuCount()+1);
			}
        	k=this.userService.modifyYongHu(u);
        	int flat=this.userService.addLOG(tYonghulogt);
        	if(flat>0 && k){
        		jsMap.put("cf", "ok");
            	jsMap.put("MSG", "登录成功!");
            	
            	
            	HttpServletResponse response = ServletActionContext.getResponse();  
    			//清除登陆页面缓存  
    			response.setHeader("Pragma","No-cache");   
    			response.setHeader("Cache-Control","no-cache");  
    			response.setHeader("Cache-Control", "no-store");  
    			response.setDateHeader("Expires", 0);
            	
            	
        	}       	   		
        }else{
        	jsMap.put("MSG", "登录失败，用户名或密码错误！");
        }
        super.writeJson(jsMap);
    }
    
    public void tuichu(){
    	int id=Integer.parseInt(this.getRequest().getParameter("id").toString());   	
    	List<Yonghulogt> l=this.userService.getLOG(id);
    	Yonghulogt u=l.get(0);
    	u.setLgoutTime(DateParse.date2String(new Date()));
    	u.setDengChuFou(true);
    	boolean f=this.userService.modifyLog(u);
    	HashMap<String, Object> jsMap = new HashMap<String, Object>();
    	if(f){
    		jsMap.put("cf", "ok");
    		this.getSession().removeAttribute("userId");
        	this.getSession().removeAttribute("binary");
    	}
    	super.writeJson(jsMap);
    }
    
    public void getuserId(){  
    	HashMap<String, Object> jsMap=new HashMap<String, Object>();
    	int id=Integer.parseInt(this.getSession().getAttribute("userId").toString());
    	String bin=this.getSession().getAttribute("biy").toString();
    	jsMap.put("id", id);
    	jsMap.put("biy", bin);
    	super.writeJson(jsMap);
    }
}
