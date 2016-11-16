package com.xiong.dandan.wudd.common.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiong.dandan.wudd.R;

import java.util.List;

public class CustomListDialogAdapter extends BaseAdapter
{
    private Context mContext;
    private List<String> mStringList;

    public CustomListDialogAdapter(Context context, List<String> stringList)
    {
        mContext = context;
        mStringList = stringList;
    }
    
    @Override
    public int getCount()
    {
        return mStringList == null ? 0 : mStringList.size();
    }

    @Override
    public Object getItem(int position)
    {
        if (mStringList != null) return mStringList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        String content = mStringList.get(position);
        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_custom_list_dialog_item, null);
            holder = new ViewHolder();
            holder.setTextView((TextView)convertView.findViewById(R.id.custom_list_dialog_item_textview));
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
        
        holder.getTextView().setText(content);
        
        return convertView;
    }
    
    private class ViewHolder
    {
        private TextView mTextView;

        public TextView getTextView()
        {
            return mTextView;
        }

        public void setTextView(TextView textView)
        {
            this.mTextView = textView;
        }
    }
}
