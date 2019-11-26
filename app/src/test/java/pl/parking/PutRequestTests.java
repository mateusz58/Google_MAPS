package pl.parking;

import android.util.Log;

import org.junit.Test;

import java.io.IOException;

import pl.parking.controller.api.ApiController;
import pl.parking.model.Car;
import pl.parking.service.ParkingService;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertTrue;

public class PutRequestTests {

    @Test
    public void shouldSendPutRequestForChangingCar() {
        Car car = new Car();
        car.setStatus("CANCELLED");
        try {
            ApiController apiController =
                    ParkingService.getRetrofitInstance().create(ApiController.class);
            Call<String> call =
                    apiController.putCar(
                            "76", "Token f4398f50af0af87c6cf460cd354ef834517ebdd4");
            Response response = call.execute();

            Thread.sleep(1000);

            assertTrue(response != null);
        } catch (IOException | InterruptedException e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        }
    }
}
