package com.example.tangramandroid;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;
import com.tmall.wireless.tangram.util.IInnerImageSetter;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * @author : C4_雍和
 * 描述 :
 * 主要功能 :
 * 维护人员 : C4_雍和
 * date : 20-4-24 上午11:26
 */
public class TextOneActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TangramEngine mEngine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        initTangram();
    }

    private void initTangram() {
        //2.初始化 Tangram 环境
        TangramBuilder.init(this, new IInnerImageSetter() {
            @Override
            public <IMAGE extends ImageView> void doLoadImageUrl(@NonNull IMAGE view,
                                                                 @Nullable String url) {
                Glide.with(view).load(url).into(view);
            }
        }, ImageView.class);
        //3.初始化 TangramBuilder
        TangramBuilder.InnerBuilder builder = TangramBuilder.newInnerBuilder(this);
        //4.注册自定义的卡片和组件
        builder.registerCell("InterfaceCell", CustomInterfaceView.class);
        //5.生成TangramEngine实例
        mEngine = builder.build();
        //6.绑定业务 support 类到 engine
        //7.绑定 recyclerView
        mEngine.bindView(mRecyclerView);
        //8.监听 recyclerView 的滚动事件
        //9.设置悬浮类型布局的偏移（可选）
        //10.设置卡片预加载的偏移量（可选）
        //11.加载数据并传递给 engine
        byte[] bytes = Utils.getAssetsFile(this, "dataOne.json");
        if (bytes != null) {
            String json = new String(bytes);
            try {
                JSONArray data = new JSONArray(json);
                mEngine.setData(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }





    }
}
