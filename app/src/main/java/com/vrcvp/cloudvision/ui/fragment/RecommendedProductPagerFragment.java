package com.vrcvp.cloudvision.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vrcvp.cloudvision.Config;
import com.vrcvp.cloudvision.R;
import com.vrcvp.cloudvision.bean.ProductBean;
import com.vrcvp.cloudvision.ui.activity.ProductDetailActivity;

/**
 * 推荐产品信息Pager页面Fragment
 * Created by yinglovezhuzhu@gmail.com on 2016/9/20.
 */
public class RecommendedProductPagerFragment extends BaseFragment {

    public static RecommendedProductPagerFragment newInstance(ProductBean product) {
        RecommendedProductPagerFragment fragment = new RecommendedProductPagerFragment();
        if(null != product) {
            Bundle args = new Bundle();
            args.putParcelable(Config.EXTRA_DATA, product);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_recommended_product_pager, container, false);
        initView(contentView);
        return contentView;
    }

    private void initView(View contentView) {
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击事件
                startActivity(new Intent(getActivity(), ProductDetailActivity.class));
            }
        });

        final ImageView ivImg = (ImageView) contentView.findViewById(R.id.iv_recommended_product_pager_img);



        final TextView tvDesc = (TextView) contentView.findViewById(R.id.tv_recommended_product_pager_desc);


        final TextView tvPrice = (TextView) contentView.findViewById(R.id.tv_recommended_product_pager_price);

        Bundle args = getArguments();
        if(null != args && args.containsKey(Config.EXTRA_DATA)) {
            final ProductBean product = args.getParcelable(Config.EXTRA_DATA);
            if(null != product) {
                loadImage(product.getImageUrl(), R.drawable.default_img2, R.drawable.default_img2, ivImg);
                tvDesc.setText(product.getName());
                tvPrice.setText(String.format(getString(R.string.str_price_format_with_currency), product.getPrice()));

                contentView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 点击事件
                        Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                        intent.putExtra(Config.EXTRA_DATA, product);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
