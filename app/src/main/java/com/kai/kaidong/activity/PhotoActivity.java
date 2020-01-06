package com.kai.kaidong.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kai.kaidong.BuildConfig;
import com.kai.kaidong.R;
import com.kai.kaidong.adapter.ImageAdapter;
import com.kai.kaidong.base.BaseActivity;
import com.kai.kaidong.util.BitmapUtils;
import com.kai.kaidong.util.CompressHelper;
import com.kai.kaidong.util.FileUtils;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class PhotoActivity extends BaseActivity {

    //临时目录
    public static final String mSaveTemporaryPath = Environment.getExternalStorageDirectory() + "/" + BuildConfig.APPLICATION_ID + "/temporary/";
    private final int PHOTO_CAMERA_REQUEST_CODE = 2003;
    private ImageAdapter imageAdapter = null;
    private Uri mUri;
    private static final int REQUEST_PERMISSION_CODE = 111;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void init() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_selectImage_image);
        Button button = (Button) findViewById(R.id.btn_selectImage_button);
        imageAdapter = new ImageAdapter(PhotoActivity.this);
        recyclerView.setAdapter(imageAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(PhotoActivity.this, 3));
        imageAdapter.setOnPicClickListener(new ImageAdapter.OnPicClickListener() {
            @Override
            public void onPicClick() {
                openCamera();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(PhotoActivity.this, "我只是个按钮", Toast.LENGTH_SHORT).show();
//                if (!checkPermission()) { //没有或没有全部授权
//                    requestPermissions(); //请求权限
//                }else {
//                    openCamera();
//                }
                List<File> dataList = imageAdapter.getDataList();
                for (int i = 0;i<dataList.size();i++){
                    File file = dataList.get(i);
                    File compressToFile = CompressHelper.getDefault(PhotoActivity.this).compressToFile(file);
                    RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), compressToFile);
                    //MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), imageBody);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("uploadImage", compressToFile.getName(), imageBody);
                    Log.i("TAG",body+"__________________________");
                }
            }
        });
    }




    @Override
    protected int findView() {
        return R.layout.activity_photo;
    }

    private File imgFile;

    /**
     * 拍照
     */
    private void openCamera() {
        imgFile = new File(mSaveTemporaryPath, "photo.jpg");
        if(!imgFile.getParentFile().exists())
            imgFile.getParentFile().mkdirs();


        mUri = Uri.fromFile(imgFile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //步骤二：Android 7.0及以上获取文件 Uri
            //com.rk.myfeaturesapp是自己App的包名fileprovider是死值
            mUri = getUriForFile(PhotoActivity.this,"com.kai.kaidong.fileprovider", imgFile);
        } else {
            //步骤三：获取文件Uri
            mUri = Uri.fromFile(imgFile);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        startActivityForResult(intent,PHOTO_CAMERA_REQUEST_CODE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭界面的时候删除文件
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (File file : imageAdapter.getDataList()) {
                    FileUtils.deleteFolderFile(file);
                }
                FileUtils.deleteFolderFile(imgFile);
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK&& requestCode == PHOTO_CAMERA_REQUEST_CODE) {
                /*拍照返回*/
                    Log.i("TAG","-----onActivityResult:" + imgFile.getAbsolutePath());

                    File fileDir = new File(mSaveTemporaryPath);
                    if (!fileDir.exists()) {
                        fileDir.mkdirs();
                    }
                    File compressFile = FileUtils.createImageFile(fileDir);//创建临时文件，覆盖原来的File文件的时候，不知道问什么有时名字会自动加上时间戳
                    if (compressFile != null) {
                        BitmapUtils.compressPicture(imgFile.getAbsolutePath(), compressFile);

                        Log.i("TAG","" + compressFile.getAbsolutePath());

                        //将照片文件放入集合中
                        imageAdapter.getDataList().add(compressFile);

                        imageAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(this, "获取图片失败，请重新上传", Toast.LENGTH_SHORT).show();
                    }

        }


    }

    private static Uri getUriForFile(Context context, String name, File vFile) {
        Uri cameraUri;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            cameraUri = Uri.fromFile(vFile);
        } else {
            cameraUri = FileProvider.getUriForFile(context, name, vFile);
        }
        return cameraUri;
    }
    //检查权限
    private boolean checkPermission() {
        //是否有权限
        boolean haveCameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        boolean haveWritePermission = ContextCompat.checkSelfPermission(this,
                WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        return haveCameraPermission && haveWritePermission;
    }
    // 请求所需权限
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermissions() {
        requestPermissions(new String[]{Manifest.permission.CAMERA, WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                boolean allowAllPermission = false;
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {//被拒绝授权
                        allowAllPermission = false;
                        break;
                    }
                    allowAllPermission = true;
                }
                if (allowAllPermission) {
                    openCamera();//开始拍照或从相册选取照片
                } else {
                    Toast.makeText(this, "该功能需要授权方可使用", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
