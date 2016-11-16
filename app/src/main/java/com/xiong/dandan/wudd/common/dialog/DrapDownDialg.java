package com.xiong.dandan.wudd.common.dialog;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.xiong.dandan.wudd.R;
import com.xiong.dandan.wudd.libs.utils.MyPopWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xionglh on 2016/8/2.
 */
public class DrapDownDialg extends LinearLayout {


    private List<String> mName = new ArrayList<>();
    private DrapDownDialgOncliclk mDrapDownDialgOncliclk;
    private MyPopWindow myPopWindow;
    private ListView mListView;

    public interface DrapDownDialgOncliclk {
        void OnItemClickListener(String str);
    }

    public DrapDownDialg(Context context) {
        super(context);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_common_list_item, this);
        this.setBackgroundColor(getResources().getColor(R.color.base_theme_color));
        myPopWindow = new MyPopWindow();
        mListView= (ListView) findViewById(R.id.lv_view_common_list_item);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mDrapDownDialgOncliclk != null)
                    mDrapDownDialgOncliclk.OnItemClickListener(mName.get(i));
                myPopWindow.closePopWindow();
            }
        });
    }

    public void show(List<String> stringList, View setView) {
        myPopWindow.closePopWindow();
        mName=stringList;
        CustomListDialogAdapter mCustomListDialogAdapter = new CustomListDialogAdapter(getContext(), stringList);
        mListView.setAdapter(mCustomListDialogAdapter);
        myPopWindow.loadPopWindow(this, setView, setView.getWidth(), FrameLayout.LayoutParams.WRAP_CONTENT);
    }

    public void show(List<String> stringList, View setView,int width) {
        myPopWindow.closePopWindow();
        mName=stringList;
        CustomListDialogAdapter mCustomListDialogAdapter = new CustomListDialogAdapter(getContext(), stringList);
        mListView.setAdapter(mCustomListDialogAdapter);
        myPopWindow.loadPopWindow(this, setView, width, FrameLayout.LayoutParams.WRAP_CONTENT);
    }

    public void setDrapDownDialgOncliclk(DrapDownDialgOncliclk drapDownDialgOncliclk) {
        this.mDrapDownDialgOncliclk = drapDownDialgOncliclk;
    }
}
