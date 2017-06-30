package com.example.administrator.tabzzf;

import android.content.Context;
import java.util.List;
import android.app.Activity;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListAdapterClass extends BaseAdapter {

    Context context;
    List<Trash> valueList;

    public ListAdapterClass(List<Trash> listValue, Context context) {
        this.context = context;
        this.valueList = listValue;
    }

    @Override
    public int getCount() {
        return this.valueList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.valueList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewItem viewItem = null;

        if (convertView == null) {
            viewItem = new ViewItem();

            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.listview_item, null);

            viewItem.TextViewTrashId = (TextView) convertView.findViewById(R.id.textView0);
            viewItem.TextViewTrashName = (TextView) convertView.findViewById(R.id.textView1);
            viewItem.TextViewTrashLatitude = (TextView) convertView.findViewById(R.id.textView2);
            viewItem.TextViewTrashLongitude = (TextView) convertView.findViewById(R.id.textView3);
            viewItem.TextViewTrashDescription = (TextView) convertView.findViewById(R.id.textView4);
            viewItem.TextViewTrashIsCleaned = (TextView) convertView.findViewById(R.id.textView5);
            viewItem.TextViewTrashSize = (TextView) convertView.findViewById(R.id.textView6);

            convertView.setTag(viewItem);
        } else {
            viewItem = (ViewItem) convertView.getTag();
        }
        viewItem.TextViewTrashId.setText(valueList.get(position).TrashId);
        viewItem.TextViewTrashName.setText(valueList.get(position).TrashName);
        viewItem.TextViewTrashLatitude.setText(valueList.get(position).TrashLatitude);
        viewItem.TextViewTrashLongitude.setText(valueList.get(position).TrashLongitude);
        viewItem.TextViewTrashDescription.setText(valueList.get(position).TrashDescription);
        viewItem.TextViewTrashIsCleaned.setText(valueList.get(position).TrashIsCleaned);
        viewItem.TextViewTrashSize.setText(valueList.get(position).TrashSize);

        return convertView;
    }
}

class ViewItem {
    TextView TextViewTrashId;
    TextView TextViewTrashName;
    TextView TextViewTrashLatitude;
    TextView TextViewTrashLongitude;
    TextView TextViewTrashDescription;
    TextView TextViewTrashIsCleaned;
    TextView TextViewTrashSize;


}

