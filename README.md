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

    <!--layout_width 和 view_width 必须保持一致, 不支持px布局-->
    <tab.tablayout.TabLayout
        android:id="@+id/tablayout"
        android:layout_width="360dp"
        android:layout_height="wrap_content"
        tab:label_count="4" // 显示title数量
        tab:label_size="4dp" // 线条的高度
        tab:label_text_color="@color/c_main" //选中时的颜色
        tab:label_text_size="16sp"//字体大小，必须是sp
        tab:unsele_text_color="@color/colorPrimary"//未选中时的颜色
        tab:view_height="50dp"//空间真实的高度
        tab:view_width="360dp"/>//控件真实的宽度，必须和layout_width一致
    <View
        android:layout_width="match_parent"
        android:layout_height="1px"
        android:background="#40000000"
        />
    <android.support.v4.view.ViewPager
        android:id="@+id/pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        />
