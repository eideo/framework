package bea.com.DAO;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	 //��ǰҳ��
    private int currentPage;
    //��ҳ��
    private int totalsPage;
    //ÿҳ��ʾ��¼����
    private int pageSize;
    //�ܼ�¼����
    private int totalsCount;
    //��ѯ���ؽ��
    private List<T> result = new ArrayList<T>();
    //��ҳ����
    private String uri;
    public String getUri() {

           return uri;

    }
    public void setUri(String uri) {

           this.uri = uri;

    }
    public int getCurrentPage() {

           return currentPage;

    }
    public void setCurrentPage(int currentPage) throws Exception {

           if (currentPage < 0) {

                  currentPage = 0;

           }

           this.currentPage = currentPage;

    }
    public int getTotalsPage() {
           try {

                  if (totalsCount % pageSize == 0) {

                         totalsPage = totalsCount / pageSize;

                  } else {

                         totalsPage = (totalsCount / pageSize) + 1;

                  }

           } catch (Exception e) {

                  throw new RuntimeException(e);

           }

           return totalsPage;

    }
    public void setTotalsPage(int totalsPage) {

           if (totalsPage < 0) {

                  totalsPage = 0;

           }

           this.totalsPage = totalsPage;

    }
    public int getPageSize() {

           return pageSize;

    }
    public void setPageSize(int pageSize) {

           if (pageSize <= 0) {

                  pageSize = 20;

           }

           this.pageSize = pageSize;

    }
    public int getTotalsCount() {

           return totalsCount;

    }
    public void setTotalsCount(int totalsCount) {

           if (totalsCount < 0) {

                  totalsCount = 0;

           }

           this.totalsCount = totalsCount;

    }
    public List<T> getResult() {

           return result;

    }
    public void setResult(List<T> result) {

           this.result = result;

    }
}
