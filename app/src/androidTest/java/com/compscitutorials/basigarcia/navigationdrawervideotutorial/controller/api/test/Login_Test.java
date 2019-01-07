/* AUTO-GENERATED FILE. DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * rest2mobile plugin because Reservation_Test.java already exists. You
 * can merge the code below into Reservation_Test.java manually.
 */
/**
 * File generated by Magnet rest2mobile 1.1 - May 18, 2016 2:51:55 PM
 * @see {@link http://developer.magnet.com}
 */

package com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.test;


import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.FactoryAPI;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.FactoryAPIFactory;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.UsersResult;
import com.magnet.android.mms.MagnetMobileClient;
import com.magnet.android.mms.async.Call;
import com.magnet.android.mms.exception.SchemaException;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
* This is generated stub to test {@link FactoryAPI}
* <p>
* All test cases are suppressed by defaullt. To run the test, you need to fix all the FIXMEs first :
* <ul>
* <li>Set proper value for the parameters
* <li>Remove out the @Suppress annotation
* <li>(optional)Add more asserts for the result
* <ul><p>
*/

public class Login_Test extends InstrumentationTestCase {
  private FactoryAPI factoryAPI;



  final String TAG="Login_Test";

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    // Instantiate a controller
    MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(this.getInstrumentation().getTargetContext());
    FactoryAPIFactory controllerFactory = new FactoryAPIFactory(magnetClient);
    factoryAPI = controllerFactory.obtainInstance();
    assertNotNull(factoryAPI);
  }

    @SmallTest
    public void testGetUsers ()throws SchemaException, ExecutionException, InterruptedException {

      Call<List<UsersResult>> callObject = factoryAPI.getUsers(null);
      assertNotNull(callObject);
      List<UsersResult> result = callObject.get();
      assertNotNull(result);

      for (int i = 0; i <result.size() ; i++) {

        Log.i(TAG, "testGetUsers  Password: "+result.get(i).getPassword()+"\n");

        Log.i(TAG, "testGetUsers   Login: "+result.get(i).getLogin()+"\n");

      }
    }

  }


