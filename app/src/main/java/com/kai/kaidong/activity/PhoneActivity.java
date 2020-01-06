package com.kai.kaidong.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.kai.kaidong.R;
import com.kai.kaidong.base.BaseActivity;
import com.kai.kaidong.util.PopUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import static android.Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;
import static androidx.core.content.FileProvider.getUriForFile;

public class PhoneActivity extends BaseActivity implements View.OnClickListener {
    private String imagePaths;
    private Uri cameraUri;
    ImageView imageViewphone;
    ImageView imageViewvideo;
    RelativeLayout relativeLayoutone,relativeLayouttwo,relativeLayoutthere;
    private RecyclerView recyclerView;

    //随便定义的静态值  调用相机的时候用的 在onActivityResult里面和requestCode 值相对应
    private static final int REQUEST_TAKE_PHOTO_CODE = 123;
    private static final int REQUEST_PERMISSION_CODE = 111;
    private static final int REQUEST_CODE = 200;
    private static final int ACTION_VIDEO_CAPTURES = 100;

    private List<String> list = new ArrayList<>();
    private List<Uri> listurl = new ArrayList<>();
    private PhoneAdapter phoneAdapter;
    private VideoAdapter videoAdapter;
    Uri mUri;

    private File file;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        list.clear();
        listurl.clear();
        imageViewphone = findViewById(R.id.image_phone);
        imageViewvideo = findViewById(R.id.image_video);
        recyclerView = findViewById(R.id.phome_recy);
        imageViewphone.setOnClickListener(this);
        imageViewvideo.setOnClickListener(this);

    }

    @Override
    protected int findView() {
        return R.layout.activity_phone;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_phone:
                Toast.makeText(this,"xiangji",Toast.LENGTH_LONG).show();
                showPopuWindow();
                break;
            case R.id.image_video:
                Toast.makeText(this,"luxiang",Toast.LENGTH_LONG).show();
                showVideoPopuWindow();
                break;
        }

    }
    private void showVideoPopuWindow() {
        View view = PopUtil.getInstance().makePopupWindow(PhoneActivity.this, R.layout.activity_phone, R.layout.video_popu, Gravity.BOTTOM);
        PopUtil.getInstance().dimBackground(PhoneActivity.this,1.0f,0.5f);
        relativeLayoutthere = view.findViewById(R.id.re_there);
        relativeLayoutone = view.findViewById(R.id.re_one);
        relativeLayouttwo = view.findViewById(R.id.re_two);
        relativeLayoutthere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUtil.getInstance().mPopupWindowdismiss();
                PopUtil.getInstance().dimBackground(PhoneActivity.this,0.5f,1.0f);
            }
        });
        relativeLayoutone.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(PhoneActivity.this, new String[]{READ_PHONE_STATE, WRITE_EXTERNAL_STORAGE,CAMERA}, 1);
               openCamera();
            }
        });
        relativeLayouttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopUtil.getInstance().mPopupWindowdismiss();
                PopUtil.getInstance().dimBackground(PhoneActivity.this,0.5f,1.0f);
                ImageSelectorUtils.openPhoto(PhoneActivity.this, REQUEST_CODE, false, 9-list.size());
            }
        });
    }

    private void showPopuWindow() {
        View view = PopUtil.getInstance().makePopupWindow(PhoneActivity.this, R.layout.activity_phone, R.layout.phone_popu, Gravity.BOTTOM);
        PopUtil.getInstance().dimBackground(PhoneActivity.this,1.0f,0.5f);
        relativeLayoutthere = view.findViewById(R.id.re_there);
        relativeLayoutone = view.findViewById(R.id.re_one);
        relativeLayouttwo = view.findViewById(R.id.re_two);
        relativeLayoutthere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUtil.getInstance().mPopupWindowdismiss();
                PopUtil.getInstance().dimBackground(PhoneActivity.this,0.5f,1.0f);
            }
        });
        relativeLayoutone.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                PopUtil.getInstance().mPopupWindowdismiss();
                PopUtil.getInstance().dimBackground(PhoneActivity.this,0.5f,1.0f);
               // 1.首先判断有没有开启权限
                if (!checkPermission()) { //没有或没有全部授权
                    requestPermissions(); //请求权限
                }
                else {
       //如果开启了 直接调用相机
                    takePhoto();//拍照逻辑
                }
            }
        });
        relativeLayouttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopUtil.getInstance().mPopupWindowdismiss();
                PopUtil.getInstance().dimBackground(PhoneActivity.this,0.5f,1.0f);
                ImageSelectorUtils.openPhoto(PhoneActivity.this, REQUEST_CODE, false, 9-list.size());
            }
        });
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
                    takePhoto();//开始拍照或从相册选取照片
                } else {
                    Toast.makeText(this, "该功能需要授权方可使用", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void takePhoto() {
        // 步骤一：创建存储照片的文件
        String  path = getFilesDir() + File.separator + "images" + File.separator;
        Log.i("TAG","PATH"+path);
        file = new File(path, "test.jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //步骤二：Android 7.0及以上获取文件 Uri
            //com.rk.myfeaturesapp是自己App的包名fileprovider是死值
            mUri = getUriForFile(PhoneActivity.this, "com.kai.kaidong.fileprovider", file);
        } else {
            //步骤三：获取文件Uri
            mUri = Uri.fromFile(file);
        }
        //步骤四：调取系统拍照
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        startActivityForResult(intent,REQUEST_TAKE_PHOTO_CODE);
    }
    @SuppressLint("WrongConstant")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_TAKE_PHOTO_CODE) {//获取系统照片上传

            Bitmap bm = null;
            try {
                bm = getBitmapFormUri(mUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            list.add(String.valueOf(mUri));
            phoneAdapter = new PhoneAdapter(this,list);
            recyclerView.setLayoutManager(new GridLayoutManager(this,3, OrientationHelper.VERTICAL,false));
            recyclerView.setAdapter(phoneAdapter);
            if(list.size()>0){
                recyclerView.setVisibility(View.VISIBLE);
            }
            else {
                recyclerView.setVisibility(View.INVISIBLE);
            }
            if(list.size()>8){
                imageViewphone.setVisibility(View.GONE);
                imageViewvideo.setVisibility(View.GONE);
            }
        }
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);
            for (int i = 0;i<images.size();i++){
                list.add(images.get(i));
            }
            if(list.size()>0){
                recyclerView.setVisibility(View.VISIBLE);
            }
            else {
                recyclerView.setVisibility(View.INVISIBLE);
            }
            if(list.size()>8){
                imageViewphone.setVisibility(View.GONE);
                imageViewvideo.setVisibility(View.GONE);
            }
            phoneAdapter = new PhoneAdapter(this,list);
            recyclerView.setLayoutManager(new GridLayoutManager(this,3, OrientationHelper.VERTICAL,false));
            recyclerView.setAdapter(phoneAdapter);

        }
        if (resultCode == RESULT_OK && requestCode == ACTION_VIDEO_CAPTURES) {//获取系统照片上传
            Uri uri=data.getData();
            listurl.add(uri);
            if (listurl.size()>0){
                imageViewphone.setVisibility(View.GONE);
                imageViewvideo.setVisibility(View.GONE);
            }else {
                imageViewphone.setVisibility(View.VISIBLE);
                imageViewvideo.setVisibility(View.VISIBLE);
            }
            videoAdapter = new VideoAdapter(this,listurl);
            recyclerView.setLayoutManager(new LinearLayoutManager(PhoneActivity.this));
            recyclerView.setAdapter(videoAdapter);
            Log.i("TAG", "直接返回视频数据"+uri.getPath());
            //https://blog.csdn.net/luanpeng825485697/article/details/78543467

        }
    }
    public Bitmap getBitmapFormUri(Uri uri) throws FileNotFoundException, IOException {
        InputStream input = getContentResolver().openInputStream(uri);

        //这一段代码是不加载文件到内存中也得到bitmap的真是宽高，主要是设置inJustDecodeBounds为true
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;//不加载到内存
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.RGB_565;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        int originalWidth = onlyBoundsOptions.outWidth;
        int originalHeight = onlyBoundsOptions.outHeight;
        if ((originalWidth == -1) || (originalHeight == -1))
            return null;

        //图片分辨率以480x800为标准
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比，由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (originalWidth / ww);
        } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (originalHeight / hh);
        }
        if (be <= 0)
            be = 1;
        //比例压缩
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//设置缩放比例
        bitmapOptions.inDither = true;
        bitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        input = getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();

        return compressImage(bitmap);//再进行质量压缩
    }
//进行二次压缩

    public Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
            if (options<=0)
                break;
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    private void openCamera() {
        try {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//            intent.setAction("android.media.action.VIDEO_CAPTURE");
            intent.addCategory("android.intent.category.DEFAULT");
            // 保存录像到指定的路径
            imagePaths = Environment.getExternalStorageDirectory().getPath()
                    + "/temp/" + (System.currentTimeMillis() + ".mp4");
            // 必须确保文件夹路径存在，否则拍照后无法完成回调
            File vFile = new File(imagePaths);
            if (!vFile.exists()) {
                File vDirPath = vFile.getParentFile();
                vDirPath.mkdirs();
            } else {
                if (vFile.exists()) {
                    vFile.delete();
                }
            }
            cameraUri = getUriForFile(PhoneActivity.this, "com.kai.kaidong.fileprovider", vFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                List<ResolveInfo> resInfoList = this.getPackageManager()
                        .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    this.grantUriPermission(packageName, cameraUri,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
            }
            this.startActivityForResult(intent, ACTION_VIDEO_CAPTURES);
        } catch (Exception e) {
            e.printStackTrace();

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
    public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneHolder>{

        private Context context;
        private List<String> list;

        public PhoneAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public PhoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PhoneHolder(LinearLayout.inflate(context,R.layout.phone_layout,null));
        }

        @Override
        public void onBindViewHolder(@NonNull PhoneHolder holder, int position) {
            Log.i("TAG",list.get(position)+"++++++++++++++++++++++++++++++++++");
            Glide.with(context)
                    .load(list.get(position))
                    .into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("TAG",list.get(position)+"++++++++++++++++++++++++++++++++++");
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class PhoneHolder extends RecyclerView.ViewHolder{

            private ImageView imageView;

            public PhoneHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.phone_layout_image);
            }
        }

    }
    public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder>{

        private Context context;
        private List<Uri> list;

        public VideoAdapter(Context context, List<Uri> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VideoHolder(LinearLayout.inflate(context,R.layout.phone_layout,null));
        }

        @Override
        public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
            Glide
                    .with(context)
                    .load(list.get(position))
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class VideoHolder extends RecyclerView.ViewHolder{

            private ImageView imageView;

            public VideoHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.phone_layout_image);
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TAG","onDestroy");
        String path;  path = getFilesDir() + File.separator + "images" + File.separator;
        delete(path);
    }


    private boolean delete(String delFile) {
        File file = new File(delFile);
        if (!file.exists()) {
            Toast.makeText(getApplicationContext(), "删除文件失败:" + delFile + "不存在！", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (file.isFile())
                return deleteSingleFile(delFile);
            else
                return deleteDirectory(delFile);
        }
    }

    /** 删除单个文件
     * @param filePath$Name 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    private boolean deleteSingleFile(String filePath$Name) {
        File file = new File(filePath$Name);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                Log.i("TAG", "Copy_Delete.deleteSingleFile: 删除单个文件" + filePath$Name + "成功！");
                return true;
            } else {
                Toast.makeText(getApplicationContext(), "删除单个文件" + filePath$Name + "失败！", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(getApplicationContext(), "删除单个文件失败：" + filePath$Name + "不存在！", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    /** 删除目录及目录下的文件
     * @param filePath 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    private boolean deleteDirectory(String filePath) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator))
            filePath = filePath + File.separator;
        File dirFile = new File(filePath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            Toast.makeText(getApplicationContext(), "删除目录失败：" + filePath + "不存在！", Toast.LENGTH_SHORT).show();
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (File file : files) {
            // 删除子文件
            if (file.isFile()) {
                flag = deleteSingleFile(file.getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (file.isDirectory()) {
                flag = deleteDirectory(file
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            Toast.makeText(getApplicationContext(), "删除目录失败！", Toast.LENGTH_SHORT).show();
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            Log.i("TAG", "Copy_Delete.deleteDirectory: 删除目录" + filePath + "成功！");
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "删除目录：" + filePath + "失败！", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
