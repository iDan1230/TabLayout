package tab.tablayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.pager)
    ViewPager pager;

    private List<String> stringList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        stringList.add("C");
        stringList.add("C++");
        stringList.add("C#");
        stringList.add("Java EE");
        stringList.add("Android");
        stringList.add("IOS");
        stringList.add("PHP");
        stringList.add("GO");
        stringList.add("HTML");
        stringList.add("Python");
        stringList.add("Objective-C");
        stringList.add("JavaScript");
        stringList.add("Ruby");
        stringList.add("kotlin");


        tablayout.setLabelTitles(stringList);
        pager.setAdapter(new myPagerAdapter(stringList));
        tablayout.setPager(pager);
    }



    class myPagerAdapter extends PagerAdapter {

        private List<TextView> viewList = new ArrayList<>();
        public myPagerAdapter(List<String> texts) {
            for (int i = 0; i < texts.size(); i++) {
                TextView textView = new TextView(MainActivity.this);
                textView.setText(texts.get(i));
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(40);
                viewList.add(textView);
            }
        }

        @Override
        public int getCount() {
            return stringList ==null ? 0 : stringList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }
    }
}
