package mx.foodstack.jin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Eduardo Echeverria on 10/11/2016.
 */
public class LaunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // EE: This will tell the system we are ready to launch the home screen and terminate the
        // launch screen.
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}