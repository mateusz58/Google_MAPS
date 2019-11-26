package pl.parking.controller.api;

import com.magnet.android.mms.async.Call;
import com.magnet.android.mms.async.StateChangedListener;

import java.util.List;

import pl.parking.model.ParkingReservationResult;
import pl.parking.model.ParkingsResult;
import pl.parking.model.UsersResult;

public interface UserApi {

    Call<List<UsersResult>> getUsers(StateChangedListener listener);

    Call<List<ParkingsResult>> getParkings(StateChangedListener listener);

    Call<List<ParkingReservationResult>> parkingReservations(StateChangedListener listener);
}
