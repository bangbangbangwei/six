package com.dc.day02ri;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

public class Madd extends Application {

    private File six1;

    @Override
    public void onCreate() {
        super.onCreate();
        six1 = new File(Environment.getExternalStorageDirectory(), "six1");
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this).diskCache(new UnlimitedDiskCache(six1)).build();
        ImageLoader.getInstance().init(build);
    }
}
