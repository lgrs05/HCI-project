package com.hci.hci_project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hci.hci_project.ConversationFragment.OnListFragmentInteractionListener;
import com.hci.hci_project.dummy.DummyContent.DummyItem;

import java.util.List;
import java.util.Random;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyConversationRecyclerViewAdapter extends RecyclerView.Adapter<MyConversationRecyclerViewAdapter.ViewHolder> {

    private final List<User> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyConversationRecyclerViewAdapter(List<User> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_conversation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        String Classes[] = {
                "Precalculus I", "Precalculus II", "Calculus I",
                "Chemistry I", "Chemistry Lab I", "Chemistry II", "Chemistry Lab II", "Engineering Graphics", "Basic Spanish 1",
                "Basic Spanish 2", "Basic English 1", "Basic English 2", "Calculus II", "Calculus III", "Differential Equations",
                "Physics I","Physics Lab I", "Physics II", "Physics Lab II", "Algorithms","Advanced Spanish 1",
                "Advanced Spanish 2", "Advanced English 1", "Advanced English 2"
        };

        int random = new Random().nextInt(24);

        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.format("%s %s", mValues.get(position).getFirst(), mValues.get(position).getLast()));
        holder.mContentView.setText(Classes[random]);

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
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public User mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
