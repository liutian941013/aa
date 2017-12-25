package com.example.liuheng20171123;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by liufan on 2017/11/23.
 */

public class Mapp extends Application {
    File cacheFile = new File(Environment.getExternalStorageDirectory() + "/" + "imgages");

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this).diskCache(new UnlimitedDiskCache(cacheFile)).build();
        ImageLoader.getInstance().init(build);
    }
}
