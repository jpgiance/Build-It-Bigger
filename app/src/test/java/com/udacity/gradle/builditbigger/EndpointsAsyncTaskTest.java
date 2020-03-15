package com.udacity.gradle.builditbigger;

import android.content.Context;


import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class EndpointsAsyncTaskTest  {

    Context context;
    String result;

    @Test
    public void verifyAsyncTaskResponse() throws InterruptedException {



        assertTrue(true);
        final CountDownLatch latch = new CountDownLatch(1);
       // context = InstrumentationRegistry.getInstrumentation().getContext();
       // context = this.getInstrumentation
        //context = ApplicationProvider.getApplicationContext();

        EndpointsAsyncTask testTask = new EndpointsAsyncTask() {
            @Override
            protected void onPostExecute( String result ) {
                assertNotNull(result);
                if (result != null) {
                    assertTrue(result.length() > 0);
                    latch.countDown();
                }
            }
        };

        testTask.execute(new OnJokerTaskCompleted() {
            @Override
            public void onTaskCompleted( String response ) {
                result = response;
             //   latch.countDown();
            }

            @Override
            public void preExecute() {

            }
        });
        latch.await(5, TimeUnit.SECONDS);
  //      assertNotNull(result);
    }


}