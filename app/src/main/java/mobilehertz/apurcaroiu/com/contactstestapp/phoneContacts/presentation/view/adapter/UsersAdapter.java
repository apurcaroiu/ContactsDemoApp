package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
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
    private OnLoadListener mOnLoadListener;

    private RecyclerView mRecyclerView;

    private int mViewItemsThreshold = 2;
    private boolean mIsLoading;
    private int mLastVisibleItem;
    private int mTotalItemCount;
    private int mCurrentPageLoading = 0;

    public UsersAdapter(Context mContext, RecyclerView recyclerView) {
        this.mUserViewModelList = new ArrayList<>(20);
        this.mContext = mContext;
        this.mRecyclerView = recyclerView;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        mRecyclerView.setOnScrollChangeListener((view, i, i1, i2, i3) -> {
            mTotalItemCount = linearLayoutManager.getItemCount();
            mLastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            if (!mIsLoading && mTotalItemCount <= (mLastVisibleItem + mViewItemsThreshold)) {
                if (mOnLoadListener != null) {
                    //lazy load next page
                    mCurrentPageLoading = mCurrentPageLoading +1;
                    mOnLoadListener.onLoadMoreItems(mCurrentPageLoading);
                }
                mIsLoading = true;
            }
        });
    }

    public interface OnUserClickListener {
        void onItemClicked(UserViewModel userviewModel);
    }

    public interface OnLoadListener{
        void onLoadMoreItems(int pageNumber);
    }

    public void setOnLoadListener(OnLoadListener onLoadListener){
        this.mOnLoadListener = onLoadListener;
    }

    public void updateUserList(Collection<UserViewModel> userViewModels){
        if ( this.mUserViewModelList != null){
            this.mUserViewModelList.addAll(userViewModels);
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
        if (mContext != null){
            Glide.with(holder.itemView.getContext()).load(userViewModel.getPictureUrl()).into(holder.profilePicture);
        }

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

    public void resetLoadingState(){
        mIsLoading = false;
    }
}
