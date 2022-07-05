package databit.com.br.datamobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import databit.com.br.datamobile.R;
import databit.com.br.datamobile.domain.MenuOs;

/**
 * Created by Sidney on 11/03/2016.
 */
public class AdapterMenuOs extends BaseAdapter {
    private Context mContext;
    private List<MenuOs> mList;
    private LayoutInflater mLayoutInflater;
    private int extraPadding;


    public AdapterMenuOs(Context c, List<MenuOs> l) {
        mContext = c;
        mList = l;
        mLayoutInflater = mLayoutInflater.from(c);

        float scale = c.getResources().getDisplayMetrics().density;
        extraPadding = (int)(8 * scale + 0.5f);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.menu_os , parent, false);


            holder = new ViewHolder();
            convertView.setTag(holder);

            holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            //holder.vwDivider = convertView.findViewById(R.id.vw_divider);
            holder.tvLabel = (TextView) convertView.findViewById(R.id.tv_label);

        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ivIcon.setImageResource(mList.get(position).getIcon());
        holder.tvLabel.setText(mList.get(position).getLabel());

        //BACKGROUND

        if (position == 0) {
            ((ViewGroup) convertView).getChildAt(0).setBackgroundResource(R.drawable.menuinicio_background);
        }
        else if (position == mList.size() -1) {
            ((ViewGroup) convertView).getChildAt(0).setBackgroundResource(R.drawable.menufim_background);
        }
        else {
            ((ViewGroup) convertView).getChildAt(0).setBackgroundResource(R.drawable.menumeio_background);
        }

        // H_LINE

        //holder.vwDivider.setVisibility(position == mList.size() - 2 ? View.VISIBLE : View.GONE);

        // EXTRA PADDING
        /*((ViewGroup) convertView).getChildAt(0).setPadding(0,
                position == 0 || position == mList.size() - 1 ? extraPadding :0,
                0,
                position == mList.size() - 1 ? extraPadding : 0);
        */


        return convertView;
    }



    public static class ViewHolder {
        ImageView ivIcon;
        TextView tvLabel;
        View vwDivider;
    }

}

