package com.example.ezhong.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);


    @Test
    public void useAppContext() throws Exception {


       if(isExist(R.id.email))
       {
           Log.v("Exist","+++++++++++++++++++++++++++");

       }
        else
       {
           Log.v("Not Exist","-------------------------");
       }

        //String httpresponse = getrequest();
        String httpresponse = sendrequest();
        Log.v("HTTP RESPONSE","*******************************************************");
        Log.v("HTTP RESPONSE",httpresponse);
        Log.v("HTTP RESPONSE","*****************************************");

    }


    public String getrequest() throws IOException {

        GetExample example = new GetExample();
        //String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
        String response = example.run("https://github.com/erictrademe/FitnessCalculator/blob/master/README.md");

        return response;
    }


    public String sendrequest() throws IOException{

        PostTest example = new PostTest();
        String json = example.bowlingJson("test", "test");
        String response = example.post("http://www.roundsapp.com/post", json);

        return response;
    }



    public boolean isExist(final int id)
    {
        try {
            onView(withId(id))
                    .check(matches(isDisplayed()));
            return true;
        } catch (AssertionError error) {
            return false;
        } catch (Exception e) {
            return false;
        }

    }




    public class GetExample {
        OkHttpClient client = new OkHttpClient();

        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            }
        }
    }


}
