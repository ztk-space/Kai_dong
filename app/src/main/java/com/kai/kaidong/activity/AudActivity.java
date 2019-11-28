package com.kai.kaidong.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;
//引用第三方audiovisualizer
public class AudActivity extends BaseActivity  implements View.OnClickListener{

    private static final int PERM_REQ_CODE = 23;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {

        findViewById(R.id.v_blob_btn).setOnClickListener(this);
        findViewById(R.id.v_blast_btn).setOnClickListener(this);
        findViewById(R.id.v_wave_btn).setOnClickListener(this);
        findViewById(R.id.v_bar_btn).setOnClickListener(this);
        findViewById(R.id.v_stream_btn).setOnClickListener(this);
        findViewById(R.id.v_circle_line_btn).setOnClickListener(this);
        findViewById(R.id.v_hifi_btn).setOnClickListener(this);
    }

    @Override
    protected int findView() {
        return R.layout.activity_aud;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_blob_btn:
                if (checkAudioPermission())
                    launchBlobActivity();
                else
                    requestAudioPermission();
                break;
            case R.id.v_blast_btn:
                if (checkAudioPermission())
                    launchBlastActivity();
                else
                    requestAudioPermission();
                break;
            case R.id.v_wave_btn:
                if (checkAudioPermission())
                    launchWaveActivity();
                else
                    requestAudioPermission();
                break;
            case R.id.v_bar_btn:
                if (checkAudioPermission())
                    launchSpikyWaveActivity();
                else
                    requestAudioPermission();
                break;
            case R.id.v_stream_btn:
                if (checkAudioPermission())
                    launchMusicStreamActivity();
                else
                    requestAudioPermission();
                break;
            case R.id.v_circle_line_btn:
                if (checkAudioPermission()) {
                    launchCircleLinectivity();
                } else
                    requestAudioPermission();
                break;
            case R.id.v_hifi_btn:
                if (checkAudioPermission()) {
                    Intent intent = new Intent(AudActivity.this, HiFiActivity.class);
                    startActivity(intent);
                } else
                    requestAudioPermission();
                break;
        }
    }
    private boolean checkAudioPermission() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestAudioPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERM_REQ_CODE);
    }

    private void launchBlobActivity() {
        Intent intent = new Intent(AudActivity.this, BlobActivity.class);
        startActivity(intent);
    }

    private void launchBlastActivity() {
        Intent intent = new Intent(AudActivity.this, BlastActivity.class);
        startActivity(intent);
    }

    private void launchWaveActivity() {
        Intent intent = new Intent(AudActivity.this, WaveActivity.class);
        startActivity(intent);
    }

    private void launchSpikyWaveActivity() {
        Intent intent = new Intent(AudActivity.this, BarActivity.class);
        startActivity(intent);
    }

    private void launchMusicStreamActivity() {
        Intent intent = new Intent(AudActivity.this, MusicStreamActivity.class);
        startActivity(intent);
    }
    private void launchCircleLinectivity() {
        Intent intent = new Intent(AudActivity.this, CircleLineActivity.class);
        startActivity(intent);
    }
}
