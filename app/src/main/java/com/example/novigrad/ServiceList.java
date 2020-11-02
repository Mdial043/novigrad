package com.example.novigrad;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ServiceList extends ArrayAdapter<Service> {
private Activity context;
private List<Service> sericeList;
public ServiceList(Activity context, List<Service> sericeList)
{
    super  (context,R.layout.list_layout, sericeList);
    this.context= context;
    this.sericeList= sericeList;
}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =context.getLayoutInflater();
        View ListViewItem = inflater.inflate( R.layout.list_layout,null,true );

        TextView textViewName= (TextView) ListViewItem.findViewById( R.id.textViewName );
        Service service = sericeList.get( position );

        textViewName.setText(service.getServiceName());
        return ListViewItem;
    }
}
