package com.udacity.gradle.builditbigger;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AndroidEndpointsAsyncTaskTest {


    }


//    final CountDownLatch signal = new CountDownLatch(1);
//    String response;
//
//    @Test
//    public void verifyAsyncTaskResponse() {
//
//        EndpointsAsyncTask task = new EndpointsAsyncTask();
//        task.execute(this);
//        //new EndpointsAsyncTask().execute(this);
//
//        try {
//
//            signal.await(5, TimeUnit.SECONDS);
//            assert response.contentEquals("This is totally a funny joke");
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//
//    @Override
//    public void onTaskCompleted( String response ) {
//        this.response = response;
//        signal.countDown();
//    }
//
//    @Override
//    public void preExecute() {
//
//    }
