package bea.com.Action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.text.AbstractDocument.Content;

import org.apache.commons.digester.ObjectParamRule;

import bea.com.IService.IWenZhangService;
import bea.com.pojo.Lanmut;
import bea.com.pojo.Moxingt;
import bea.com.pojo.Wenzhangt;
import bea.com.util.dateFunction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WenZhangAction extends BaseAction{
	private JSONObject JSObj;
	private JSONArray JSArr;
	private IWenZhangService wenzhangservice;
	private String MSG;
	
	
	
	public IWenZhangService getWenzhangservice() {
		return wenzhangservice;
	}
	public void setWenzhangservice(IWenZhangService wenzhangservice) {
		this.wenzhangservice = wenzhangservice;
	}
	public String getMSG() {
		return MSG;
	}
	public void setMSG(String mSG) {
		MSG = mSG;
	}
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
	
	public String getLanMuFather(){
		List<Lanmut> fatherList=wenzhangservice.QueryLanMuCategory();//获取属性明细表
		JSONArray childList;//子列表
		JSONArray jsArr = new JSONArray();
		JSONObject jsObj = new JSONObject();
		Lanmut fatherCategory;
		for (int i=0;i<fatherList.size();i++){//循环处理行
               fatherCategory=fatherList.get(i);
               jsObj.clear();
               //onclick=\"addStu()\"
               jsObj.put("id", fatherCategory.getId());//添加id
               jsObj.put("text", fatherCategory.getLanMuName());
               if (getLanMuChild(fatherCategory.getId()/*父类的ID*/)!=null){
                   jsObj.put("state", "closed");
                   jsObj.put("children",getLanMuChild(fatherCategory.getId()));//递归调用绑定子项
               }
               jsArr.add(jsObj);//将jsObj添加到jsArr
		}
		HashMap<String,Object> jsMap = new HashMap<String,Object>();
		jsMap.put("",jsArr);
		JSArr=jsArr;
		return SUCCESS;
	} 
	
	private JSONArray getLanMuChild(int fatherId){
		JSONObject jsObj = new JSONObject();
		List<Lanmut> childList=wenzhangservice.QueryLanMuCategory(fatherId);
		if (childList.size()==0){
			return null;
		}
		else{
		    JSONArray childArr= new JSONArray();
		    Lanmut childCategory;
		    for (int i=0;i<childList.size();i++){
			    childCategory=childList.get(i);
                jsObj.clear();
                //<a  class=\"easyui-linkbutton\"  data-options=\"iconCls:'icon-add',plain:true\">添加</a>
                jsObj.put("id", childCategory.getId());
              //  jsObj.put("text", childCategory.getLanMuName()+"<img src='img/edit_add.png' style='height:10px'  onclick='addZiLanMu("+childCategory.getId()+")'><img>");
                jsObj.put("text", childCategory.getLanMuName());
                if (getLanMuChild(childCategory.getId())!=null){
                    jsObj.put("state", "closed");
                    jsObj.put("children",getLanMuChild(childCategory.getId()));//递归调用绑定子项
            }
                childArr.add(jsObj);
		}
		    return childArr;
		}
		
	}
	
	/**
	 * 绑定模型下拉框
	 * @return
	 */
	public String getMoXing(){
		List<Moxingt> lstBuMen=this.wenzhangservice.getMoXing();
		
		JSONArray jsArr=new JSONArray();
		JSONObject jsObj=new JSONObject();
		for(int i=0;i<lstBuMen.size();i++){
			Moxingt m=lstBuMen.get(i);
			jsObj.clear();
			jsObj.put("mId", m.getId());
			jsObj.put("mName", m.getMoXingName());
			jsArr.add(jsObj);
		}
		JSArr=jsArr;
		return SUCCESS;
	}
	
	/**
	 * 根据栏目Id查询栏目信息
	 * @return
	 */
	public String getLanMuById(){
		int lanMuId=Integer.parseInt(this.getRequest().getParameter("mId"));
		
		List lstBuMen=this.wenzhangservice.getlanMuById(lanMuId);
		
		JSONArray jsArr=new JSONArray();
		JSONObject jsObj=new JSONObject();
		for(int i=0;i<lstBuMen.size();i++){
			Object[] m=(Object[])lstBuMen.get(i);
			jsObj.clear();
			jsObj.put("lName", m[0]);
			jsObj.put("mName", m[1]);
			//jsArr.add(jsObj);
		}
		JSObj=jsObj;
		return SUCCESS;
	}
	
	public String addWenZhang(){
		int pid=Integer.parseInt(this.getRequest().getParameter("pid"));
		String title=this.getRequest().getParameter("title");
		String author=this.getRequest().getParameter("author");
		Date date=dateFunction.string2Date(this.getRequest().getParameter("date"));
		String content=this.getRequest().getParameter("content");
		boolean shouYe=this.getRequest().getParameter("shouYe") != null;
		boolean top=this.getRequest().getParameter("top") != null;
		boolean tuiJian=this.getRequest().getParameter("tuiJian") != null;
		boolean shenHe=this.getRequest().getParameter("shenHe") != null;
		boolean zuo=this.getRequest().getParameter("zuo") != null;
		
		
		Wenzhangt wenZhang=new Wenzhangt();
		wenZhang.setLanMuId(pid);
		wenZhang.setTitle(title);
		wenZhang.setAuthor(author);
		wenZhang.setContent(content);
		wenZhang.setDianJiShu(0);
		wenZhang.setFaBuDtae(date);
		wenZhang.setShou(shouYe);
		wenZhang.setTuiJian(tuiJian);
		wenZhang.setShenHeFou(shenHe);
		wenZhang.setZhiDing(top);
		wenZhang.setZuo(zuo);
		
		MSG=wenzhangservice.addWenZhang(wenZhang)+"";
		
		return SUCCESS;
	}
	
	
	public String  getAllWenZhang(){
		int page=Integer.parseInt(this.getRequest().getParameter("page").toString());
		int rows=Integer.parseInt(this.getRequest().getParameter("rows").toString());
		
		List list=wenzhangservice.getAllWenZhang(page, rows);
		List lst=(List) list.get(0);
		Long total=(Long)list.get(1);;
		JSONArray jsArr = new JSONArray();
		JSONObject jsObj = new JSONObject();
		
		String shouye = null;
		String zuo= null;
		String tuiJian= null;
		String zhiDing = null;
		String shenHe= null;
		
		String shuXing;
		
		for (int i=0;i<lst.size();i++){//循环处理行
			Object[] obj=(Object[]) lst.get(i);
			jsObj.clear();
          
            /**
    		 * String hql1="select w.id,w.lanMuId, w.title,w.content,w.dianJiShu,w.faBuDtae,w.author,w.shou,w.zuo,w.tuiJian,w.zhiDing,w.shenHeFou from  Wenzhangt as w ";
    		 */
    	
			//boolean te=Boolean.parseBoolean(obj[11].toString());
            try {
            	if(Boolean.parseBoolean(obj[7].toString())){
                	shouye="<font color='#C6CBCF'>首</font>"+"|";
                }else{
                	shouye="<font>首</font>"+"|";
                }
            	if(Boolean.parseBoolean(obj[10].toString())){
                	zhiDing="<font color='#C6CBCF'>顶</font>"+"|";
                }else{
                	zhiDing="<font>顶</font>"+"|";
                }
                
                if(Boolean.parseBoolean(obj[9].toString())){
                	tuiJian="<font color='#C6CBCF'>荐</font>"+"|";
                }else{
                	tuiJian="<font>荐</font>"+"|";
                }
                
                if(Boolean.parseBoolean(obj[11].toString())){
                	shenHe="<font color='#C6CBCF'>审</font>"+"|";
                }else{
                	shenHe="<font>审</font>"+"|";
                }
                
               if(Boolean.parseBoolean(obj[8].toString())){
                	zuo="<font color='#C6CBCF'>左</font>";
                }else{
                	zuo="<font>左</font>";
                }
			} catch (Exception e) {
				// TODO: handle exception
			}
           
            shuXing=shouye+zhiDing+tuiJian+shenHe+zuo;
            jsObj.put("id",obj[0]);//添加id
            jsObj.put("lanmuid",obj[1]);//添加id
            jsObj.put("title",obj[2]);//添加id
            jsObj.put("author", obj[6]);
            jsObj.put("shuXing", shuXing);
            jsObj.put("dianJiShu", obj[4]);
            jsObj.put("fabuShiJian", obj[5].toString());
            jsArr.add(jsObj);//将jsObj添加到jsArr
		}
		HashMap<String, Object> jsMap=new HashMap<String, Object>();
		jsMap.put("total", total);
		jsMap.put("rows", jsArr);
		this.JSObj=JSONObject.fromObject(jsMap);
		return SUCCESS;
	}
	
	
	
	public String getAllWenZhangByLanMuId(){
		
		int lanmuid=Integer.parseInt(this.getRequest().getParameter("lanmuid").toString());
		int page=Integer.parseInt(this.getRequest().getParameter("page").toString());
		int rows=Integer.parseInt(this.getRequest().getParameter("rows").toString());
		
		List list=wenzhangservice.getAllWenZhangByLanMuId(lanmuid, page, rows);
		List lst=(List) list.get(0);
		Long total=(Long)list.get(1);;
		JSONArray jsArr = new JSONArray();
		JSONObject jsObj = new JSONObject();
		
		String shouye = null;
		String zuo= null;
		String tuiJian= null;
		String zhiDing = null;
		String shenHe= null;
		
		String shuXing;
		
		for (int i=0;i<lst.size();i++){//循环处理行
			Object[] obj=(Object[]) lst.get(i);
			jsObj.clear();
          
            /**
    		 * String hql1="select w.id,w.lanMuId, w.title,w.content,w.dianJiShu,w.faBuDtae,w.author,w.shou,w.zuo,w.tuiJian,w.zhiDing,w.shenHeFou from  Wenzhangt as w ";
    		 */
    	
			boolean te=Boolean.parseBoolean(obj[11].toString());
            try {
            	if(Boolean.parseBoolean(obj[7].toString())){
                	shouye="<font color='#C6CBCF'>首</font>"+"|";
                }else{
                	shouye="<font>首</font>"+"|";
                }
            	if(Boolean.parseBoolean(obj[10].toString())){
                	zhiDing="<font color='#C6CBCF'>顶</font>"+"|";
                }else{
                	zhiDing="<font>顶</font>"+"|";
                }
                
                if(Boolean.parseBoolean(obj[9].toString())){
                	tuiJian="<font color='#C6CBCF'>荐</font>"+"|";
                }else{
                	tuiJian="<font>荐</font>"+"|";
                }
                
                if(Boolean.parseBoolean(obj[11].toString())){
                	shenHe="<font color='#C6CBCF'>审</font>"+"|";
                }else{
                	shenHe="<font>审</font>"+"|";
                }
                
               if(Boolean.parseBoolean(obj[8].toString())){
                	zuo="<font color='#C6CBCF'>左</font>";
                }else{
                	zuo="<font>左</font>";
                }
			} catch (Exception e) {
				// TODO: handle exception
			}
           
            shuXing=shouye+zhiDing+tuiJian+shenHe+zuo;
            jsObj.put("id",obj[0]);//添加id
            jsObj.put("lanmuid",obj[1]);//添加id
            jsObj.put("title",obj[2]);//添加id
            jsObj.put("author", obj[6]);
            jsObj.put("shuXing", shuXing);
            jsObj.put("dianJiShu", obj[4]);
            jsObj.put("fabuShiJian", obj[5].toString());
            jsArr.add(jsObj);//将jsObj添加到jsArr
		}
		HashMap<String, Object> jsMap=new HashMap<String, Object>();
		jsMap.put("total", total);
		jsMap.put("rows", jsArr);
		this.JSObj=JSONObject.fromObject(jsMap);
		return SUCCESS;
	}
	
	
	
	public String  getWenZhangByWid(){
		int wid=Integer.parseInt(this.getRequest().getParameter("wId"));
		List<Wenzhangt> list=wenzhangservice.getWenZhangByWid(wid);
		JSONArray jsArr = new JSONArray();
		JSONObject jsObj = new JSONObject();
		Wenzhangt wenZhang;
	
		for (int i=0;i<list.size();i++){//循环处理行
			wenZhang=list.get(i);
            jsObj.clear();
           
            jsObj.put("title",wenZhang.getTitle());//添加id
            jsObj.put("author", wenZhang.getAuthor());
            jsObj.put("shou", wenZhang.getShou());
            jsObj.put("ding", wenZhang.getZhiDing());
            jsObj.put("jian", wenZhang.getTuiJian());
            jsObj.put("shen", wenZhang.getShenHeFou());
            jsObj.put("zuo", wenZhang.getZuo());
            jsObj.put("content", wenZhang.getContent());
            jsObj.put("dianJiShu", wenZhang.getDianJiShu());
            jsObj.put("fabuShiJian", wenZhang.getFaBuDtae().toString());
            //jsArr.add(jsObj);//将jsObj添加到jsArr
		}
		//HashMap<String,Object> jsMap = new HashMap<String,Object>();
		//jsMap.put("rows",jsArr);
		//JSObj=JSONObject.fromObject(jsMap);
		JSObj=jsObj;
		return SUCCESS;
	}
	
	
	public String modifyWenZhang(){
		int wid=Integer.parseInt(this.getRequest().getParameter("wId"));
		List<Wenzhangt> list=wenzhangservice.getWenZhangByWid(wid);
		
		Wenzhangt wenZhang=list.get(0);
		
		String title=this.getRequest().getParameter("title");
		String author=this.getRequest().getParameter("author");
		Date date=dateFunction.string2Date(this.getRequest().getParameter("date"));
		String content=this.getRequest().getParameter("content");
		boolean shouYe=this.getRequest().getParameter("shouYe") != null;
		boolean top=this.getRequest().getParameter("top") != null;
		boolean tuiJian=this.getRequest().getParameter("tuiJian") != null;
		boolean shenHe=this.getRequest().getParameter("shenHe") != null;
		boolean zuo=this.getRequest().getParameter("zuo") != null;
		
		
		wenZhang.setTitle(title);
		wenZhang.setAuthor(author);
		wenZhang.setContent(content);
		wenZhang.setDianJiShu(0);
		wenZhang.setFaBuDtae(date);
		wenZhang.setShou(shouYe);
		wenZhang.setTuiJian(tuiJian);
		wenZhang.setShenHeFou(shenHe);
		wenZhang.setZhiDing(top);
		wenZhang.setZuo(zuo);
		
		MSG=wenzhangservice.modifyWenZhang(wenZhang)+"";
		return SUCCESS;
		
	}
	
	
	
	
	public String modifyshenHeWenZhang(){
		int wid=Integer.parseInt(this.getRequest().getParameter("wid"));
		List<Wenzhangt> list=wenzhangservice.getWenZhangByWid(wid);
		
		Wenzhangt wenZhang=list.get(0);
		
		wenZhang.setShenHeFou(true);
		
		MSG=wenzhangservice.modifyWenZhang(wenZhang)+"";
		return SUCCESS;
		
	}
	
	
	public String deleteWenZhang(){
		int wid=Integer.parseInt(this.getRequest().getParameter("wid"));
		
		if(this.wenzhangservice.deleteWenZhang(wid)){
			MSG="OK";
			
		}
		
		return SUCCESS;
	}
}
