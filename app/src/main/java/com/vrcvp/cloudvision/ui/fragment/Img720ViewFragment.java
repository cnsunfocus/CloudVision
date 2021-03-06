package com.vrcvp.cloudvision.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.opensource.widget.ZoomableImageView;
import com.vrcvp.cloudvision.Config;
import com.vrcvp.cloudvision.R;
import com.vrcvp.cloudvision.utils.StringUtils;

/**
 * 720看图Fragment
 * Created by yinglovezhuzhu@gmail.com on 2016/9/29.
 */

public class Img720ViewFragment extends BaseFragment {

    /**
     * 新建一个实例
     * @param image 图片数据
     * @return Img720ViewFragment实例对象
     */
    public static Img720ViewFragment newInstance(String image) {
        Img720ViewFragment fragment = new Img720ViewFragment();
        Bundle args = new Bundle();
        args.putString(Config.EXTRA_DATA, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_720_view_img, container, false);

        initView(contentView);

        return contentView;
    }

    private void initView(View contentView) {
        final ZoomableImageView imageView = (ZoomableImageView) contentView.findViewById(R.id.iv_img_720_view_img);

        Bundle args = getArguments();
        if(null != args && args.containsKey(Config.EXTRA_DATA)) {
            final String image = args.getString(Config.EXTRA_DATA);
            // 显示图片
            if(!StringUtils.isEmpty(image)) {
                loadImage(image, R.drawable.default_img2, R.drawable.default_img2, imageView);
            }
        }
    }
}
