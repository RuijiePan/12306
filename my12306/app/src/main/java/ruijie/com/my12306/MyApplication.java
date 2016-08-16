package ruijie.com.my12306;

import android.app.Application;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadHelper;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import ruijie.com.my12306.components.storage.UserStorage;
import ruijie.com.my12306.db.entity.UserDao;
import ruijie.com.my12306.injector.component.ApplicationComponent;
import ruijie.com.my12306.injector.component.DaggerApplicationComponent;
import ruijie.com.my12306.injector.moudel.ApplicationMoudle;

/**
 * Created by prj on 2016/8/15.
 */

public class MyApplication extends Application {

    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    public static final int MAX_DISK_CACHE_SIZE = 50 * ByteConstants.MB;
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 8;

    private ApplicationComponent mApplicationComponent;

    @Inject UserStorage mUserStorage;
    @Inject OkHttpClient mOkHttpClient;
    @Inject UserDao mUserDao;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
        initUser();
        FileDownloader.init(this, new FileDownloadHelper.OkHttpClientCustomMaker() {
            @Override
            public OkHttpClient customMake() {
                return mOkHttpClient;
            }
        });
        initFrescoConfig();
    }

    private void initComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationMoudle(new ApplicationMoudle(this))
                .build();
        mApplicationComponent.inject(this);
    }

    private void initUser() {

    }

    private void initFrescoConfig() {
        final MemoryCacheParams bitmapCacheParams =
                new MemoryCacheParams(MAX_MEMORY_CACHE_SIZE, // Max total size of elements in the cache
                        Integer.MAX_VALUE,                     // Max entries in the cache
                        MAX_MEMORY_CACHE_SIZE, // Max total size of elements in eviction queue
                        Integer.MAX_VALUE,                     // Max length of eviction queue
                        Integer.MAX_VALUE);

        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory.newBuilder(this,mOkHttpClient)
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .setBitmapMemoryCacheParamsSupplier(()->bitmapCacheParams)
                .setMainDiskCacheConfig(DiskCacheConfig.newBuilder(this).setMaxCacheSize(MAX_DISK_CACHE_SIZE).build())
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this,config);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
