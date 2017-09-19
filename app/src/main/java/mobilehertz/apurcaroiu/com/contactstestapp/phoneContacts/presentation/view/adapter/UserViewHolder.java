package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobilehertz.apurcaroiu.com.contactstestapp.R;

/**
 * Created by apurcaroiu on 9/19/2017.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.picture)
    ImageView profilePicture;

    @BindView(R.id.name)
    TextView name;

    public UserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
