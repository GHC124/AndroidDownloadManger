
package com.zxt.download2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class TestActivity extends Activity implements OnClickListener {

    protected static final String TAG = "TestActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        findViewById(R.id.download_add).setOnClickListener(this);
        findViewById(R.id.download_add2).setOnClickListener(this);
        findViewById(R.id.download_list).setOnClickListener(this);

        DownloadListener mDownLoadListener = new DownloadListener() {
            @Override
            public void onDownloadFinish(String filepath) {
                Log.e(TAG, "filepath:" + filepath);

            }

            @Override
            public void onDownloadStart() {
                Log.i(TAG, "----开始下载");
            }

            @Override
            public void onDownloadPause() {
                Log.i(TAG, "----暂停下载");
            }

            @Override
            public void onDownloadStop() {
                Log.i(TAG, "----停止下载");
            }

            @Override
            public void onDownloadFail() {
                Log.i(TAG, "----下载失败");
            }

            @Override
            public void onDownloadProgress(int finishedSize, int totalSize, double progressPercent) {
                Log.i(TAG, "已下载：" + progressPercent);
            }

        };


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.download_add:
                DownloadTask  downloadTask = new DownloadTask(
                        "http://apache.etoak.com/ant/ivy/2.3.0-rc1/apache-ivy-2.3.0-rc1-src.zip");
                downloadTask.setFileName("apache-ivy.zip");
                downloadTask.setTitle("apache-ivy");
                downloadTask.setFilePath("/sdcard/");
                DownloadTaskManager.getInstance(this).startDownload(downloadTask);

                Toast.makeText(this, "add 1", 1).show();
                break;
            case R.id.download_add2:
                DownloadTask downloadTask2 = new DownloadTask(
                        "http://mirror.bjtu.edu.cn/apache/axis/axis2/java/core/1.6.2/axis2-eclipse-service-plugin-1.6.2.zip");
//                downloadTask.addDownloadListener(mDownLoadListener);
                // 这里下载保存的文件名，要定一个规范
                downloadTask2.setFileName("axis2.zip");
                downloadTask2.setTitle("axis2");
                downloadTask2.setFilePath("/sdcard/");
                
                DownloadTaskManager.getInstance(this).startDownload(downloadTask2);
                break;
            case R.id.download_list:
                Toast.makeText(this, "list", 1).show();
                Intent i = new Intent(this, Download2Activity.class);
                startActivity(i);
                break;
            default:
                break;
        }

    }

}