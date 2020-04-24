package com.example.tangramandroid

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tmall.wireless.tangram.TangramBuilder
import com.tmall.wireless.tangram.TangramEngine
import com.tmall.wireless.tangram.util.IInnerImageSetter
import org.json.JSONArray
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    private val context = this
    private lateinit var mEngine: TangramEngine
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)


        //2.初始化 Tangram 环境
        TangramBuilder.init(context, object : IInnerImageSetter {
            override fun <IMAGE : ImageView> doLoadImageUrl(view: IMAGE, url: String?) {
                Glide.with(context).load(url).into(view)
            }
        }, ImageView::class.java)
        //3.初始化 TangramBuilder
        val builder = TangramBuilder.newInnerBuilder(context)
        //4.注册自定义的卡片和组件
        builder.registerCell("InterfaceCell", CustomInterfaceView::class.java)
        //5.生成TangramEngine实例
        mEngine = builder.build()
        //6.绑定业务 support 类到 engine
        //7.绑定 recyclerView
        mEngine.bindView(recyclerView)
        //8.监听 recyclerView 的滚动事件
        //9.设置悬浮类型布局的偏移（可选）
        //10.设置卡片预加载的偏移量（可选）
        //11.加载数据并传递给 engine
        val bytes: ByteArray = Utils.getAssetsFile(this, "data.json")
        if (bytes != null) {
            val json = String(bytes)
            try {
                val data = JSONArray(json)
                mEngine.setData(data)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }
}
