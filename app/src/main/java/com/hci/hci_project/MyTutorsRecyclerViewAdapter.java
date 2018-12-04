package com.hci.hci_project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.hci.hci_project.TutorsFragment.OnListFragmentInteractionListener;
import com.hci.hci_project.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTutorsRecyclerViewAdapter extends RecyclerView.Adapter<MyTutorsRecyclerViewAdapter.ViewHolder> implements Filterable {

    private  List<Map.Entry<User, String>> mValues;
    private  List<Map.Entry<User, String>> mValuesFiltered;
    private final OnListFragmentInteractionListener mListener;

    public MyTutorsRecyclerViewAdapter(List<Map.Entry<User, String>> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        mValuesFiltered = items;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mValuesFiltered = mValues;
                } else {
                    List<Map.Entry<User, String>> filteredList = new ArrayList<>();
                    for (Map.Entry<User, String> row : mValues) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getKey().getName().toLowerCase().contains(charString.toLowerCase()) || row.getValue().toLowerCase().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    mValuesFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mValuesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mValuesFiltered = (ArrayList<Map.Entry<User, String>>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tutors, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Map.Entry<User, String> entry = mValuesFiltered.get(position);
        holder.mItem = entry;
        holder.mIdView.setText(String.format("%s %s", entry.getKey().getFirst(),
                entry.getKey().getLast()));
        holder.mEmail.setText(entry.getKey().getEmail());

        holder.mContentView.setText(entry.getValue());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValuesFiltered.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mEmail;
        public Map.Entry<User, String> mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            mEmail = (TextView) view.findViewById(R.id.email);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
