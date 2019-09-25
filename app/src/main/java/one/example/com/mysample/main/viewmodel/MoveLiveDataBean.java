package one.example.com.mysample.main.viewmodel;

import java.util.List;

import one.example.com.mysample.main.db.entity.JokeEntity;

/**
 * 用于liveData数据返回时的Bean
 */
public class MoveLiveDataBean {
    private boolean isSuc;//true表示成功
    private List<JokeEntity> list;

    public boolean isSuc() {
        return isSuc;
    }

    public void setSuc(boolean suc) {
        isSuc = suc;
    }

    public List<JokeEntity> getList() {
        return list;
    }

    public void setList(List<JokeEntity> list) {
        this.list = list;
    }
}
