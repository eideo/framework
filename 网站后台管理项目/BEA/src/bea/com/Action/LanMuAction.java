package bea.com.Action;

import java.util.HashMap;
import java.util.List;

import bea.com.IService.IlanMuGuanLiService;
import bea.com.pojo.Lanmut;
import bea.com.util.encodingFunction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LanMuAction extends BaseAction{
   private JSONArray jsArr;
   private JSONObject jsObj;
   private String MSG;
   private IlanMuGuanLiService lanMuGuanLiService;
public JSONArray getJsArr() {
	return jsArr;
}
public void setJsArr(JSONArray jsArr) {
	this.jsArr = jsArr;
}
public JSONObject getJsObj() {
	return jsObj;
}
public void setJsObj(JSONObject jsObj) {
	this.jsObj = jsObj;
}
public String getMSG() {
	return MSG;
}
public void setMSG(String mSG) {
	MSG = mSG;
}
public IlanMuGuanLiService getLanMuGuanLiService() {
	return lanMuGuanLiService;
}
public void setLanMuGuanLiService(IlanMuGuanLiService lanMuGuanLiService) {
	this.lanMuGuanLiService = lanMuGuanLiService;
}
   public String getAllMoXing(){
	   List lck=this.lanMuGuanLiService.getMoXing();
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		for(int i=0;i<lck.size();i++){
			Object[] obj=(Object[]) lck.get(i);
			jsonObject.clear();
			jsonObject.put("id", obj[0]);
			jsonObject.put("text", obj[1]);
			jsonArray.add(jsonObject);
		}
		this.jsArr=jsonArray;
		return SUCCESS;
   }
   
   public String getGenLM(){
	   String id=this.getRequest().getParameter("id").toString();	 
	   List lst=lanMuGuanLiService.getLanMu(id);
	   JSONArray jsonArray=new JSONArray();
	   JSONObject jsonObject=new JSONObject();
	   for(int i=0;i<lst.size();i++){
		   Object[] obj=(Object[]) lst.get(i);
		   jsonObject.clear();
		   jsonObject.put("id", obj[0]);
		   jsonObject.put("parentId",obj[1]);
		   jsonObject.put("name", obj[2]+"("+obj[5]+")");
		   jsonObject.put("sort", obj[3]);
		   jsonObject.put("state", obj[6]);
		   jsonArray.add(jsonObject);
	   }
	   this.jsArr=jsonArray;
	   return SUCCESS;
   }
   
   public String getLanMuFather(){
	   
	   List fatherList=lanMuGuanLiService.getAllLanMu();
	   JSONArray jsonArray=new JSONArray();
	   JSONObject jsonObject=new JSONObject();
	   for(int i=0;i<fatherList.size();i++){
		   Object[] obj=(Object[]) fatherList.get(i);
		   jsonObject.clear();
		   jsonObject.put("id", obj[0]);
		   //jsonObject.put("parentId",obj[1]);
		   jsonObject.put("name", obj[2]+"("+obj[5]+")");
		   jsonObject.put("sort", obj[3]);
		   if(getLanMUChild(Integer.parseInt(obj[0].toString()))!=null){
			   jsonObject.put("state", "closed");  
			   jsonObject.put("children", getLanMUChild(Integer.parseInt(obj[0].toString())));
		   }		   
		   jsonArray.add(jsonObject);
	   }
	   this.jsArr=jsonArray;
	   return SUCCESS;
   }
   
   private JSONArray getLanMUChild(int fatherID){
	   JSONObject jsonObject=new JSONObject();
	   List childList=lanMuGuanLiService.getAllLanMu(fatherID);
	   if(childList.size()==0){
		   return null;
	   }else {
		JSONArray childArr=new JSONArray();
		for(int i=0;i<childList.size();i++){
			Object[] obj=(Object[]) childList.get(i);
			jsonObject.clear();
			jsonObject.put("id", obj[0]);
		    //jsonObject.put("parentId",obj[1]);
		    jsonObject.put("name", obj[2]+"("+obj[5]+")");
		    jsonObject.put("sort", obj[3]);
		    if(getLanMUChild(Integer.parseInt(obj[0].toString()))!=null){
		    	jsonObject.put("state", "closed");
		    	jsonObject.put("children", getLanMUChild(Integer.parseInt(obj[0].toString())));
		    }
		    childArr.add(jsonObject);			
		}
		return childArr;
	}
   }
   
   public void removeLanMu(){
	    int id=Integer.parseInt(this.getRequest().getParameter("id"));
	    boolean t=this.lanMuGuanLiService.deleteLanMu(id);
	    HashMap<String, Object> jsMap=new HashMap<String, Object>();	
	    if(t){
	    	jsMap.put("msg","ok");
	    	super.writeJson(jsMap);
	    }
	}
   
   public void addLanM(){
	   int pid=Integer.parseInt(this.getRequest().getParameter("pid").toString());
	   int sort=Integer.parseInt(this.getRequest().getParameter("sort").toString());
	   int moXingID=Integer.parseInt(this.getRequest().getParameter("moXing").toString());
	   String name=encodingFunction.getMethodEncoding(this.getRequest().getParameter("name").toString());
	   Lanmut l=new Lanmut();
	   l.setLanMuName(name);
	   l.setMoXingId(moXingID);
	   l.setPid(pid);
	   l.setSort(sort);
	   int i=this.lanMuGuanLiService.addLanMu(l);
	   HashMap<String, Object> jsMap=new HashMap<String, Object>();
	   if(i>0){
	    	jsMap.put("msg","ok");
	    	super.writeJson(jsMap);
	    }
   }
   public String getEditLM(){
	   int id=Integer.parseInt(this.getRequest().getParameter("id").toString());	 
	   List lst=lanMuGuanLiService.getLMEDIT(id);
	   JSONArray jsonArray=new JSONArray();
	   JSONObject jsonObject=new JSONObject();
	   for(int i=0;i<lst.size();i++){
		   Object[] obj=(Object[]) lst.get(i);
		   jsonObject.clear();
		   jsonObject.put("id", obj[0]);
		   jsonObject.put("pid", obj[1]);
		   jsonObject.put("name",obj[2]);
		   jsonObject.put("sort", obj[3]);
		   jsonObject.put("moXing", obj[4]);
		   jsonArray.add(jsonObject);
	   }
	   this.jsArr=jsonArray;
	   return SUCCESS;
   }
   
   public void editLanM(){
	   int pid=Integer.parseInt(this.getRequest().getParameter("pid").toString());
	   int sort=Integer.parseInt(this.getRequest().getParameter("sort").toString());
	   int moXingID=Integer.parseInt(this.getRequest().getParameter("moXing").toString());
	   int id=Integer.parseInt(this.getRequest().getParameter("id").toString());
	   String name=encodingFunction.getMethodEncoding(this.getRequest().getParameter("name").toString());
	   Lanmut l=new Lanmut();
	   l.setLanMuName(name);
	   l.setMoXingId(moXingID);
	   l.setPid(pid);
	   l.setSort(sort);
	   l.setId(id);
	   boolean i=this.lanMuGuanLiService.modifyLanMu(l);
	   HashMap<String, Object> jsMap=new HashMap<String, Object>();
	   if(i){
	    	jsMap.put("msg","ok");
	    	super.writeJson(jsMap);
	    }
   }
   
   public void removePiLiang(){
	   String arr=this.getRequest().getParameter("arr").toString();
	   String[] strArr=arr.split(",");
	   boolean t=false;
	   for(int i=0;i<strArr.length;i++){
		   String id=strArr[i];		   
		   t=this.lanMuGuanLiService.deleteLanMu(Integer.parseInt(id));		   
	   }
	    HashMap<String, Object> jsMap=new HashMap<String, Object>();	
	    if(t){
	    	jsMap.put("msg","ok");
	    	super.writeJson(jsMap);
	    } 
   }
   
   
public String getLanMuFatherbyMX(){
	   int id=Integer.parseInt(this.getRequest().getParameter("id").toString());
	   List fatherList=lanMuGuanLiService.getLanMuBYmX(id);
	   JSONArray jsonArray=new JSONArray();
	   JSONObject jsonObject=new JSONObject();
	   for(int i=0;i<fatherList.size();i++){
		   Object[] obj=(Object[]) fatherList.get(i);
		   jsonObject.clear();
		   jsonObject.put("id", obj[0]);
		   //jsonObject.put("parentId",obj[1]);
		   jsonObject.put("name", obj[2]+"("+obj[5]+")");
		   jsonObject.put("sort", obj[3]);
		   if(getLanMUChild(Integer.parseInt(obj[0].toString()))!=null){
			   jsonObject.put("state", "closed");  
			   jsonObject.put("children", getLanMUChildBYMX(Integer.parseInt(obj[0].toString()),id));
		   }		   
		   jsonArray.add(jsonObject);
	   }
	   this.jsArr=jsonArray;
	   return SUCCESS;
   }
   
private JSONArray getLanMUChildBYMX(int fatherID,int moxingID){
	   JSONObject jsonObject=new JSONObject();
	   List childList=lanMuGuanLiService.getLanMuBYmX(fatherID,moxingID);
	   if(childList.size()==0){
		   return null;
	   }else {
		JSONArray childArr=new JSONArray();
		for(int i=0;i<childList.size();i++){
			Object[] obj=(Object[]) childList.get(i);
			jsonObject.clear();
			jsonObject.put("id", obj[0]);
		    //jsonObject.put("parentId",obj[1]);
		    jsonObject.put("name", obj[2]+"("+obj[5]+")");
		    jsonObject.put("sort", obj[3]);
		    if(getLanMUChild(Integer.parseInt(obj[0].toString()))!=null){
		    	jsonObject.put("state", "closed");
		    	jsonObject.put("children", getLanMUChildBYMX(Integer.parseInt(obj[0].toString()),Integer.parseInt(obj[4].toString())));
		    }
		    childArr.add(jsonObject);			
		}
		return childArr;
	}
}
public void addXinLanM(){
	   int sort=Integer.parseInt(this.getRequest().getParameter("sort").toString());
	   int moXingID=Integer.parseInt(this.getRequest().getParameter("moXing").toString());
	   String name=encodingFunction.getMethodEncoding(this.getRequest().getParameter("name").toString());
	   Lanmut l=new Lanmut();
	   l.setLanMuName(name);
	   l.setMoXingId(moXingID);
	   l.setPid(0);
	   l.setSort(sort);
	   int i=this.lanMuGuanLiService.addLanMu(l);
	   HashMap<String, Object> jsMap=new HashMap<String, Object>();
	   if(i>0){
	    	jsMap.put("msg","ok");
	    	super.writeJson(jsMap);
	    }
}
}
