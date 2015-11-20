package bea.com.IService;

import java.util.List;

import bea.com.pojo.Lanmut;

public interface IlanMuGuanLiService {
   public List  getMoXing();
   public List  getLanMu(String id);
   public List  getAllLanMu();
   public List  getAllLanMu(int id);
   public boolean deleteLanMu(int id);
   public boolean modifyLanMu(Lanmut entity);
   public int addLanMu(Lanmut entity);
   public List getLMEDIT(int id);
   public List getLanMuBYmX(int moxingID);
   public List getLanMuBYmX(int fatherid,int moxingID);
}
