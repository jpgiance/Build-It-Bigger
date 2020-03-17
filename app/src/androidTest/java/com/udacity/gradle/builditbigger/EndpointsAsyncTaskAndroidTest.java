package com.udacity.gradle.builditbigger;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class EndpointsAsyncTaskAndroidTest {


    @Test
    public void verifyAsyncTaskResponse() throws InterruptedException {



        assertTrue(true);
        final CountDownLatch latch = new CountDownLatch(1);

        EndpointsAsyncTask testTask = new EndpointsAsyncTask(new OnJokerTaskCompleted() {
            @Override
            public void onTaskCompleted( String response ) {

                assertTrue(!response.isEmpty());
                latch.countDown();
            }

            @Override
            public void onTaskInit() {

            }

        });

        testTask.execute();
        latch.await(5, TimeUnit.SECONDS);
    }

}
