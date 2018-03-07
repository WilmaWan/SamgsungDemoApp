package wan.wilma.samsungdemoapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.client.android.BeepManager;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.camera.CameraManager;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 0xa1;
    private final int CAMERA_PERMISSION_REQUEST = 0;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    QRCodeReaderView surfaceView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(this);
        grantCameraPermissionsThenStartScanning();
        Button btnBarcode = (Button) findViewById(R.id.btnBarcode);
        Button btnQRcode = (Button) findViewById(R.id.btnQRcode);
        Button btnSign = (Button) findViewById(R.id.btnSign);

        btnBarcode.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // 檢查允許使用相機權限
//                grantCameraPermissionsThenStartScanning();
//                if (mDeniedCameraAccess == true)
//                    return;
                //batch scan
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                intent.setAction(Intents.Scan.ACTION);
                intent.putExtra(Intents.Scan.WIDTH, 1600); //調整掃描視窗寬度(Optional)
                intent.putExtra(Intents.Scan.HEIGHT, 850); //調整掃描視窗高度(Optional)
                intent.putExtra(Intents.Scan.PROMPT_MESSAGE, "請將條碼置於鏡頭範圍進行掃描");
                intent.putExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS, 10L);
                intent.putExtra(CaptureActivity.SCAN_MODE_NAME, CaptureActivity.SCAN_BATCH_MODE);
                BeepManager.VIBRATE_MODE = true;
                CameraManager.FORCE_FLASH_MODE = false;
                CameraManager.FORCE_AUTO_FOCUS = true;
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        btnQRcode.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
//                surfaceView = new QRCodeReaderView(v.getContext());
//                setContentView(surfaceView);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DecoderActivity.class);
                startActivity(intent);
            }
        });

        btnSign.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
                Intent intent = new Intent();
//                bundle.putString("Account", valuestring);
                intent.setClass(MainActivity.this, SignActivity.class);
//                intent.putExtras(bundle);
                startActivity(intent);
//                finish();

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length <= 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Cannot write images to external storage", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void grantCameraPermissionsThenStartScanning()
    {
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CAMERA }, CAMERA_PERMISSION_REQUEST);
        }
    }

    /**
     * Checks if the app has permission to write to device storage
     * <p/>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity the activity from which permissions are checked
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

}
