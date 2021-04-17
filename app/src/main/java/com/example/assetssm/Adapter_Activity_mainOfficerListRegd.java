package com.example.assetssm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_Activity_mainOfficerListRegd extends RecyclerView.Adapter<Adapter_Activity_mainOfficerListRegd.ViewHolder> implements Filterable {
    private ArrayList<POJO_Activity_mainOfficerListRegd> mArrayList;
    private ArrayList<POJO_Activity_mainOfficerListRegd> mFilteredList;


    public Adapter_Activity_mainOfficerListRegd(ArrayList<POJO_Activity_mainOfficerListRegd> arrayList) {
        mArrayList = arrayList;
        mFilteredList = arrayList;
    }

    @Override
    public Adapter_Activity_mainOfficerListRegd.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_mainall_carditem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter_Activity_mainOfficerListRegd.ViewHolder viewHolder, int i) {

        viewHolder.tv_name.setText(mFilteredList.get(i).getName());
        viewHolder.tv_version.setText(mFilteredList.get(i).getVer());
        viewHolder.tv_api_level.setText(mFilteredList.get(i).getApi());
        int ii = i + 1;
        viewHolder.serialNo_cardAll.setText("" + ii);
    }


    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;
                } else {

                    ArrayList<POJO_Activity_mainOfficerListRegd> filteredList = new ArrayList<>();

                    for (POJO_Activity_mainOfficerListRegd POJOActivitymainAll : mArrayList) {

                        if (POJOActivitymainAll.getApi().toLowerCase().contains(charString)
                                || POJOActivitymainAll.getName().toLowerCase().contains(charString)
                                || POJOActivitymainAll.getVer().toLowerCase().contains(charString)
                        ) {

                            filteredList.add(POJOActivitymainAll);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<POJO_Activity_mainOfficerListRegd>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_version, tv_api_level, serialNo_cardAll;

        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_version = (TextView) view.findViewById(R.id.tv_version);
            tv_api_level = (TextView) view.findViewById(R.id.tv_api_level);
            serialNo_cardAll = view.findViewById(R.id.serialNo_cardAll);

        }
    }

}