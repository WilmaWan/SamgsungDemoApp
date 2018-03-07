package wan.wilma.samsungdemoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class QRCodeActivity extends AppCompatActivity {

    QRCodeReaderView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        sys_init();
        setContentView(R.layout.activity_qrcode);

//        LinearLayout linearLayout = new LinearLayout(QRCodeActivity.this);
//        linearLayout.setHorizontalGravity(LinearLayout.VERTICAL);
//        surfaceView = new QRCodeReaderView(this);
//        linearLayout.addView(surfaceView);
//        //创建一个 宽度MATCH_PARENT 高度0 权重1的布局参数
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
//        surfaceView.setLayoutParams(layoutParams);
//        addContentView(surfaceView, layoutParams);


        surfaceView = new QRCodeReaderView(this);
//        surfaceView.requestFocus();//获取焦点
//        surfaceView.setFocusableInTouchMode(true);//设置为可触控

//        surfaceView.setZOrderOnTop(true);	// 置到Top层
//        surfaceView.getHolder().setFormat(PixelFormat.TRANSPARENT);	// 设置背景为透明

        // 添加3D SurfaceView (GLSurfaceView)
        setContentView(surfaceView);
    }

    private void sys_init(){
        // 去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);		//强制为横屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	//强制为竖屏

//        // 获取屏幕的分辨率
//        screenWidth  = getWindowManager().getDefaultDisplay().getWidth();       // 屏幕宽（像素，如：480px）
//        screenHeight = getWindowManager().getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）
//        normalLayerZ = -((float)screenHeight/2.0f);
//        System.out.println("screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);
    }
}
