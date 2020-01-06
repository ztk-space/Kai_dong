package com.kai.kaidong.util;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtils {

    /**
     * 在目标文件路径创建临时文件
     *
     * @param dir
     * @return
     */
    public static File createImageFile(File dir) {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        Log.v("qq", "FileUtils----imageFileName:" + imageFileName);
        try {
            File imageFile = File.createTempFile(imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    dir      /* directory */);
            return imageFile;
        } catch (IOException e) {
            //do noting
            return null;
        }
    }

    /**
     * 删除文件，如果是文件删除文件
     * 如果是文件夹，删除里面的所有文件，并删除文件夹
     *
     * @param file
     */
    public static void deleteFolderFile(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isFile()) {//如果是文件
            file.delete();
        } else if (file.isDirectory()) {//如果是文件夹
            File[] childFiles = file.listFiles();
            for (int i = 0; i < childFiles.length; i++) {
                deleteFolderFile(childFiles[i]);
            }
            file.delete();//删除文件夹
        }
    }
}
