package one.example.com.mysample.main.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import one.example.com.mysample.main.webservice.bean.TopMovieListInfoEntity;

/**
 * 直接和UI打交道
 */
public class MoveViewModel extends AndroidViewModel {
    MediatorLiveData<List<TopMovieListInfoEntity>> mMoveTopLiveData;

    public MoveViewModel(@NonNull Application application) {
        super(application);
        mMoveTopLiveData = new MediatorLiveData<>();
    }

    public LiveData<List<TopMovieListInfoEntity>> getData() {
        return mMoveTopLiveData;
    }

    public void requestMoveData(){

    }
}
