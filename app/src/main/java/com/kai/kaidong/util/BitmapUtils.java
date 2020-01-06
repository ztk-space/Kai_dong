package com.kai.kaidong.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * seiya 创建时间：2017/2/24 16:13
 * 邮箱：hjqjl@qq.com
 */

public class BitmapUtils {
    /**
     * 拍照图片压缩
     *
     * @param srcPath      要压缩的图片文件路径
     * @param compressFile 压缩之后的文件
     * @return
     */
    public static File compressPicture(String srcPath, File compressFile) {

        if (compressFile != null && compressFile.exists()) {
            compressFile.delete();
        }

        FileOutputStream fos = null;
        BitmapFactory.Options op = new BitmapFactory.Options();

        op.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, op);
        op.inJustDecodeBounds = false;

        // 缩放图片的尺寸
        float w = op.outWidth;
        float h = op.outHeight;
        float hh = 1024f;//
        float ww = 768f;//
        // 最长宽度或高度1024
        float be = 1.0f;
        if (w > h && w > ww) {
            be = (float) (w / ww);
        } else if (w < h && h > hh) {
            be = (float) (h / hh);
        }
        if (be <= 0) {
            be = 1.0f;
        }
        op.inSampleSize = (int) be;// 设置缩放比例,这个数字越大,图片大小越小.
        bitmap = BitmapFactory.decodeFile(srcPath, op);
        int desWidth = (int) (w / be);
        int desHeight = (int) (h / be);
        bitmap = Bitmap.createScaledBitmap(bitmap, desWidth, desHeight, true);

        try {
            fos = new FileOutputStream(compressFile);
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fos);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (null != bitmap && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }
        return compressFile;
    }
}
