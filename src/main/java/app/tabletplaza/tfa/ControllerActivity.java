package app.tabletplaza.tfa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.tabletplaza.tfa.testing.Testing_DownloadingModul_Activity;
import app.tabletplaza.tfa.testing.Testing_MasterDetail_Activity;

public class ControllerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
    }


    public void downloadTestingFile(View view) {
        Intent movingToTestingArea = new Intent(this, Testing_DownloadingModul_Activity.class);
        this.startActivity(movingToTestingArea);
    }

    public void masterDetail(View view) {
        Intent movingToTestingArea = new Intent(this, Testing_MasterDetail_Activity.class);
        this.startActivity(movingToTestingArea);
    }
}
