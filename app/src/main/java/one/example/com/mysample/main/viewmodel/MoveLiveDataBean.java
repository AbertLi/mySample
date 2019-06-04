package one.example.com.mysample.main.viewmodel;

import java.util.List;

import one.example.com.mysample.main.db.entity.SubjectsEntity;

/**
 * 用于liveData数据返回时的Bean
 */
public class MoveLiveDataBean {
    private boolean isSuc;//true表示成功
    private List<SubjectsEntity> list;

    public boolean isSuc() {
        return isSuc;
    }

    public void setSuc(boolean suc) {
        isSuc = suc;
    }

    public List<SubjectsEntity> getList() {
        return list;
    }

    public void setList(List<SubjectsEntity> list) {
        this.list = list;
    }
}
