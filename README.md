# TabLayout
一、效果图</br></br>
1.初始位置</br></br>
![image](https://github.com/yangzhidan/TabLayout/blob/master/pic/Screenshot_2017-07-27-09-48-23.png)</br></br>
2.</br></br>
![image](https://github.com/yangzhidan/TabLayout/blob/master/pic/Screenshot_2017-07-27-09-48-27.png)</br></br>
3.居中</br></br>
![image](https://github.com/yangzhidan/TabLayout/blob/master/pic/Screenshot_2017-07-27-09-48-31.png)</br></br>
4.</br></br>
![image](https://github.com/yangzhidan/TabLayout/blob/master/pic/Screenshot_2017-07-27-09-48-59.png)</br></br>


二、用法</br></br>

    <!--layout_width 和 view_width 必须保持一致, 不支持px布局--></br>
    <tab.tablayout.TabLayout</br>
        android:id="@+id/tablayout"</br>
        android:layout_width="360dp"</br>
        android:layout_height="wrap_content"</br>
        tab:label_count="4" // 显示title数量</br>
        tab:label_size="4dp" // 线条的高度</br>
        tab:label_text_color="@color/c_main" //选中时的颜色</br>
        tab:label_text_size="16sp"//字体大小，必须是sp</br>
        tab:unsele_text_color="@color/colorPrimary"//未选中时的颜色</br>
        tab:view_height="50dp"//空间真实的高度</br>
        tab:view_width="360dp"/>//控件真实的宽度，必须和layout_width一致</br>
    <android.support.v4.view.ViewPager</br>
        android:id="@+id/pager"</br>
        android:layout_width="match_parent"</br>
        android:layout_height="match_parent"</br>
        /></br>
