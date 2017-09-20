package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import mobilehertz.apurcaroiu.com.contactstestapp.R;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.model.UserViewModel;

/**
 * Created by apurcaroiu on 9/19/2017.
 */

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<UserViewModel> mUserViewModelList;
    private Context mContext;

    private OnUserClickListener mOnUserClickListener;

    public UsersAdapter(Context mContext) {
        this.mUserViewModelList = Collections.emptyList();
        this.mContext = mContext;
    }

    public interface OnUserClickListener {
        void onItemClicked(UserViewModel userviewModel);
    }

    public void updateUserList(Collection<UserViewModel> userViewModels){
        if ( this.mUserViewModelList != null){
            this.mUserViewModelList = (List<UserViewModel>)userViewModels;
            this.notifyDataSetChanged();
        }
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_card, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final UserViewModel userViewModel = mUserViewModelList.get(position);
        holder.name.setText(userViewModel.getFirstName());
        Glide.with(mContext).load(userViewModel.getPictureUrl()).into(holder.profilePicture);

        holder.itemView.setOnClickListener(view -> {
            if (mOnUserClickListener != null){
                mOnUserClickListener.onItemClicked(userViewModel);
            } else {
                Log.d("UsersAdapter","onClickListener not set");
            }

        });
    }

    @Override
    public int getItemCount() {
        return (this.mUserViewModelList != null)? mUserViewModelList.size():0;
    }

    public void setOnUserClickListener(OnUserClickListener onUserClickListener){
        this.mOnUserClickListener = onUserClickListener;

    }
}
