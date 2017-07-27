package tab.tablayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tederen on 2017/7/20.
 */

public class TabLayout extends HorizontalScrollView {

    private Context mContext;

    private float viewWidth;
    private LinearLayout linear;
    private Paint mPaint;
    //耽搁label高度
    private int layoutHeight;
    //单个label宽度
    private float layoutWidth;
    //字体大小
    private int labelTextSize;
    //指示调高夫大小
    private int labelSize;
    //未选中的颜色
    private int unseleTextColor;
    //字体颜色
    private int labelTextColor;
    //显示个数
    private int labelCount;
    //选中的位置
    private int selPosition = 0;
    //当前的指示线的起始位置
    private float lastStartX;
    //默认分20次移动到自动位置
    private int MOVE_NUMBER = 15;
    //当前移动的次数
    private int CURRENT_NUMBER = 0;
    //开始移动线
    private boolean startMove;
    //向左还是向右
    private boolean isAbout;
    //数据源
    private List<String> labelTitles = new ArrayList<>();
    //ViewPager
    private ViewPager pager;

    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;


        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.TabLayout);
        viewWidth = t.getDimension(R.styleable.TabLayout_view_width, 0);
        layoutHeight = (int) t.getDimension(R.styleable.TabLayout_view_height, dip2px(context,50));
        unseleTextColor = t.getColor(R.styleable.TabLayout_unsele_text_color, Color.BLACK);
        labelTextColor = t.getColor(R.styleable.TabLayout_label_text_color, Color.BLACK);
        labelTextSize = (int) t.getDimension(R.styleable.TabLayout_label_text_size, 12);
        labelTextSize = px2dip(context,labelTextSize);
        labelSize = (int) t.getDimension(R.styleable.TabLayout_label_size, dip2px(context,2));
        labelCount = t.getInteger(R.styleable.TabLayout_label_count, 0);
        Log.e("msg", "labelTextSize = " + labelTextSize);
        t.recycle();
        init();
    }

    private void initClhild() {
        Log.e("msg", "initClhild: " + viewWidth);
        if (labelCount != 0 && viewWidth != 0) {
            layoutWidth = viewWidth / labelCount;
        }
        for (int i = 0; i < labelTitles.size(); i++) {
            TextView view = new TextView(mContext);
            view.setHeight(layoutHeight);
            view.setWidth((int) layoutWidth);
            view.setMaxLines(1);
            view.setEllipsize(TextUtils.TruncateAt.END);
            if (i == 0) {
                view.setTextSize(labelTextSize + 2);
                view.setTextColor(labelTextColor);
            } else {
                view.setTextSize(labelTextSize);
                view.setTextColor(unseleTextColor);
            }
            view.setGravity(Gravity.CENTER);
            view.setText(labelTitles.get(i));
            linear.addView(view);
            onClick(i, view);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        layoutHeight = getMeasuredHeight();

    }
    public void setLabelTitles(List<String> labelTitles) {
        this.labelTitles.clear();
        this.labelTitles = labelTitles;
        initClhild();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(labelTextColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(labelSize);
        linear = new LinearLayout(mContext);
        linear.setOrientation(LinearLayout.HORIZONTAL);
        linear.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(linear);
    }

    public void setPager(ViewPager pager) {
        this.pager = pager;
        if (pager != null) {
            pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    // TODO: 2017/7/24 版本2中实现 以下功能 
//                    Log.e("msg","onPageScrolled :   positionOffset-> " + positionOffset +" positionOffsetPixels-> "+positionOffsetPixels);
//                    Log.e("msg","比例: " +  positionOffsetPixels/viewWidth);
//                    Log.e("msg","label moveto: " +  layoutWidth * positionOffsetPixels/viewWidth);
//                    Log.e("msg","position: " +  position);
//                    /**
//                     * 向右拖动
//                     */
//                    if (position < selPosition)
//                    {
//                        Log.e("msg","xiang: " +  position);
//                        smoothScrollTo((int) (linear.getWidth() - (linear.getChildCount() - selPosition) * layoutWidth - (viewWidth - layoutWidth) / 2 - layoutWidth * positionOffsetPixels/viewWidth), 0);
//                    }else {//向左
//                       smoothScrollTo((int) (linear.getWidth() - (linear.getChildCount() - selPosition) * layoutWidth - (viewWidth - layoutWidth) / 2 + layoutWidth * positionOffsetPixels/viewWidth), 0);
//
//                    }

                }

                @Override
                public void onPageSelected(int position) {

                    if (position < linear.getChildCount()) {
                        moveLabel(position);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    Log.e("msg","state: " +  state);
                }
            });

        }
    }

    private void moveLabel(int position) {
        setCurrentItem(position);
        smoothScrollTo((int) (linear.getWidth() - (linear.getChildCount() - selPosition) * layoutWidth - (viewWidth - layoutWidth) / 2), 0);
    }

    private void onClick(final int i, View view) {

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pager != null && pager.getChildCount() <= linear.getChildCount()) {

                    pager.setCurrentItem(i);

                } else {
                    moveLabel(i);
                }

            }
        });
    }

    public void setCurrentItem(int position) {
        if (linear.getChildCount() > position) {
            for (int i = 0; i < linear.getChildCount(); i++) {
                ((TextView) linear.getChildAt(i)).setTextColor(unseleTextColor);
                ((TextView) linear.getChildAt(i)).setTextSize(labelTextSize);
            }
            ((TextView) linear.getChildAt(position)).setTextColor(labelTextColor);
            ((TextView) linear.getChildAt(position)).setTextSize(labelTextSize + 2);//选中项字体放大
            if (position >= selPosition)
                isAbout = false;
            else
                isAbout = true;

            selPosition = position;
            CURRENT_NUMBER = 0;
            startMove = true;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (startMove) {
            if (CURRENT_NUMBER <= MOVE_NUMBER) {
                if (!isAbout)
                    canvas.drawLine(lastStartX + (selPosition * layoutWidth - lastStartX) / MOVE_NUMBER * CURRENT_NUMBER, layoutHeight, lastStartX + layoutWidth + (selPosition * layoutWidth - lastStartX) / MOVE_NUMBER * CURRENT_NUMBER, layoutHeight, mPaint);
                else
                    canvas.drawLine(lastStartX - (lastStartX - selPosition * layoutWidth) / MOVE_NUMBER * CURRENT_NUMBER, layoutHeight, lastStartX + layoutWidth - (lastStartX - selPosition * layoutWidth) / MOVE_NUMBER * CURRENT_NUMBER, layoutHeight, mPaint);
                CURRENT_NUMBER++;

                if (CURRENT_NUMBER <= MOVE_NUMBER)
                    invalidate();
                else {
                    lastStartX = selPosition * layoutWidth;
                    startMove = false;
                }
            }

        } else {
            canvas.drawLine(lastStartX, layoutHeight, layoutWidth + lastStartX, layoutHeight, mPaint);
        }

    }


    /**
     * dip转px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px转dip
     *
     * @param context
     * @param pxValue
     * @return
     */
    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
