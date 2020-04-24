# Tangram-Android
Tangram  demo
1.引入依赖
    // gradle
    implementation 'com.alibaba.android:tangram:3.3.6@aar'
    // 最新版本引入了rxjava，需要自行添加rx依赖
    implementation 'io.reactivex.rxjava2:rxjava:2.2.10'
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
    //glide
    implementation 'com.github.bumptech.glide:glide:4.11.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'

2.初始化 Tangram 环境
        TangramBuilder.init(context, object : IInnerImageSetter {
            override fun <IMAGE : ImageView> doLoadImageUrl(view: IMAGE, url: String?) {
                Glide.with(context).load(url).into(view)
            }
        }, ImageView::class.java)

3.初始化 TangramBuilder
        val builder = TangramBuilder.newInnerBuilder(context)




