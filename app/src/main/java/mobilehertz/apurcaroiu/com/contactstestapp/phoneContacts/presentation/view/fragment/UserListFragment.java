package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mobilehertz.apurcaroiu.com.contactstestapp.R;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.data.entity.datamapper.UserEntityAutoMapper;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.data.gatewayimpl.UserGatewayImpl;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.interactor.RetrieveUserList;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.mapper.UserViewModelMapper;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.model.UserViewModel;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.presenter.UserListPresenter;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.UsersListView;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.adapter.UsersAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentUserInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserListFragment extends Fragment implements UsersListView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "phone";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Unbinder mUnbinder;

    @BindView(R.id.recyclerUsers)
    RecyclerView mRvContacts;

    private UsersAdapter mUsersAdapter;

    private OnFragmentUserInteractionListener mListener;

    private UserListPresenter mUserListPresenter;

    final UserGatewayImpl mUserGateway = new UserGatewayImpl(new UserEntityAutoMapper());

    public UserListFragment() {
        setRetainInstance(true);
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserListFragment newInstance(String param1, String param2) {
        UserListFragment fragment = new UserListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mUserListPresenter = new UserListPresenter(new RetrieveUserList(mUserGateway), new UserViewModelMapper());
        mUsersAdapter = new UsersAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        mUnbinder = ButterKnife.bind(this,view);
        initializeRecyclerView();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(UserViewModel userViewModel) {
        if (mListener != null) {
            mListener.onUserFragmentInteraction(userViewModel);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mUserListPresenter.setView(this);
        if (savedInstanceState == null){
            this.mUserListPresenter.getContactsList(0,10,"seed");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentUserInteractionListener) {
            mListener = (OnFragmentUserInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentUserInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void hideError() {

    }

    @Override
    public void displayUserList(Collection<UserViewModel> userViewModels) {
           if (mUserListPresenter != null && userViewModels != null){
               this.mUsersAdapter.updateUserList(userViewModels);
           }
    }

    @Override
    public void viewUser(UserViewModel userViewModel) {
         mListener.onUserFragmentInteraction(userViewModel);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mUserListPresenter.resume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.mRvContacts.setAdapter(null);
        mUnbinder.unbind();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mUserListPresenter.destroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.mUserListPresenter.pause();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentUserInteractionListener {
        // TODO: Update argument type and name
        void onUserFragmentInteraction(UserViewModel userViewModel);
    }

    private void initializeRecyclerView() {
        this.mUsersAdapter.setOnUserClickListener(onUserClickListener);
        this.mRvContacts.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mRvContacts.setAdapter(mUsersAdapter);
    }

    private UsersAdapter.OnUserClickListener onUserClickListener = userviewModel -> {
              if (mUserListPresenter != null && userviewModel != null){
                  this.mUserListPresenter.onUserModelClicked(userviewModel);
              }
    };
}
