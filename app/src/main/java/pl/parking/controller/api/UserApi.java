package pl.parking.controller.api;

import com.magnet.android.mms.async.Call;
import com.magnet.android.mms.async.StateChangedListener;

import java.util.List;
import pl.parking.model.ParkingsResult;

public interface UserApi {

    Call<List<ParkingsResult>> getParkings(StateChangedListener listener);

}
