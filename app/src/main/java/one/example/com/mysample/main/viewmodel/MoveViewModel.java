package one.example.com.mysample.main.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import one.example.com.mysample.main.db.entity.SubjectsEntity;
import one.example.com.mysample.main.viewmodel.repository.MoveTopRepository;

/**
 * 直接和UI打交道
 */
public class MoveViewModel extends AndroidViewModel {
    MediatorLiveData<List<SubjectsEntity>> mMoveTopLiveData;
    MoveTopRepository mMoveTopRepository;

    public MoveViewModel(@NonNull Application application) {
        super(application);
        mMoveTopLiveData = new MediatorLiveData<>();
        mMoveTopRepository = new MoveTopRepository();
    }

    public LiveData<List<SubjectsEntity>> getSubjectData() {
        return mMoveTopLiveData;
    }


    public void requestMoveData(int start, int con) {
        mMoveTopRepository.getNetData(start, con);
        mMoveTopRepository.querySubjects(mMoveTopLiveData, 10, 0);
    }
}
