package cc.ricksimon.android.filteringplurk.bean;

/**
 * Created by Simon on 2018/2/12.
 */

public abstract class BaseBean {
    private String beanType;

    public void setBeanType(String beanSimpleName){
        beanType = beanSimpleName;
    }
    public String getBeanType(){
        return beanType;
    }

    public abstract void setBeanType();
}
