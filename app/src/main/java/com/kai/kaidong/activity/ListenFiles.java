package com.kai.kaidong.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListenFiles implements Runnable {
    private File dir;

    List<String> list = new ArrayList<String>();

    public ListenFiles(File fileDir){
        this.dir = fileDir;

        File[] files = dir.listFiles();

        for (int i = 0; i < files.length; i++) {
            list.add(files[i].getName());
        }
    }

    public boolean listenFiles(File file) {

        for (int i = 0; i < list.size(); i++) {
            if (file.getName().equals(list.get(i))) {
                return true;
            }
        }
        return false;// 存在新文件返回假
    }

    @Override
    public void run() {
        File[] Nowfiles = dir.listFiles();
        for (int j = 0; j < Nowfiles.length; j++) {
            boolean flag = listenFiles(Nowfiles[j]);
            if (flag == false) {
                Nowfiles[j].delete();
            }
        }
        try {
            Thread.sleep(3000);
            run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ListenFiles lf = new ListenFiles(new File("E:/老项目"));
        new Thread(lf).start();
        System.out.println("监视文件中......");

    }
}
