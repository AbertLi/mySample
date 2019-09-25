package one.example.com.mysample.main.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import one.example.com.mysample.main.viewmodel.repository.JokeRepository;

/**
 * 直接和UI打交道
 */
public class MoveViewModel extends AndroidViewModel {
    MediatorLiveData<MoveLiveDataBean> mMoveTopLiveData;
    JokeRepository mMoveTopRepository;

    public MoveViewModel(@NonNull Application application) {
        super(application);
        mMoveTopLiveData = new MediatorLiveData<>();
        mMoveTopRepository = new JokeRepository();
    }

    public LiveData<MoveLiveDataBean> getSubjectData() {
        return mMoveTopLiveData;
    }


    public void requestMoveData(int start, int con) {
        mMoveTopRepository.getNetData(start, con,mMoveTopLiveData);//请求服务器
        mMoveTopRepository.querySubjects(mMoveTopLiveData, con, start);
    }

    public void requestNet(int start, int con) {
        mMoveTopRepository.getNetData(start, con,mMoveTopLiveData);//请求服务器
    }
}
