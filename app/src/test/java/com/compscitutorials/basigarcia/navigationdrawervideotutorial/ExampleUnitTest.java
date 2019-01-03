package com.compscitutorials.basigarcia.navigationdrawervideotutorial;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
        @Test
        public void overrideProperty() throws IOException {
            String out = new Scanner(new URL("https://polar-plains-14145.herokuapp.com/allocations").openStream(), "UTF-8").useDelimiter("\\A").next();
        }
        @Test
        public void Login-Test() throws IOException {

        }

    }




