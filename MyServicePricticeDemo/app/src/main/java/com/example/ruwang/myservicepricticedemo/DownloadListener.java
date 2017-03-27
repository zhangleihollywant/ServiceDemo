package com.example.ruwang.myservicepricticedemo;

/**
 * <b>Create Date:</b> 17/3/24<br>
 * <b>Author:</b> Zhanglei<br>
 * <b>Description:</b> 下载过程中的各种状态监听<br>
 */

public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFiled();

    void onPaused();

    void onCanceled();
}
