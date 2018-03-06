package com.mecby.base.net.img;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

/**
 * @Description: 使用picasso框架加载图片
 */
public class PicassoLoader implements ILoader {
    @Override
    public void init(Context context) {
       /* try {
            Class.forName("com.bumptech.glide.Glide");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Must be dependencies Glide!");
        }*/
    }

    @Override
    public void loadNet(ImageView target, String url, Options options) {
        if(TextUtils.isEmpty(url))
            return;
        load(getPicasso(target.getContext()).load(url), target, options);
    }

    @Override
    public void loadResource(ImageView target, int resId, Options options) {
        load(getPicasso(target.getContext()).load(resId), target, options);
    }

    @Override
    public void loadAssets(ImageView target, String assetName, Options options) {
        load(getPicasso(target.getContext()).load("file:///android_asset/" + assetName), target, options);
    }

    @Override
    public void loadFile(ImageView target, File file, Options options) {
        load(getPicasso(target.getContext()).load(file), target, options);
    }

    @Override
    public void clearMemoryCache(Context context) {
//        Picasso.with(context);
    }

    @Override
    public void clearDiskCache(Context context) {
        //        Picasso.with(context);
    }

    private Picasso getPicasso(Context context) {
        return Picasso.with(context);
    }

    private void load(RequestCreator request, ImageView target, Options options) {
        if (options == null) options = Options.defaultOptions();

        if (options.loadingResId != Options.RES_NONE) {
            request.placeholder(options.loadingResId);
        }
        if (options.loadErrorResId != Options.RES_NONE) {
            request.error(options.loadErrorResId);
        }
        request.into(target);
    }
}
