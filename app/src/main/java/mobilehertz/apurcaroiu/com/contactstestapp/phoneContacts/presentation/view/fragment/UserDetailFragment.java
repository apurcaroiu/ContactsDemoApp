package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.fragment;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mobilehertz.apurcaroiu.com.contactstestapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "phone";
    private static final String ARG_PARAM3 = "email";
    private static final String ARG_PARAM4 = "address";
    private static final String ARG_PARAM5 = "coverUrl";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;
    private String mParam5;

    Unbinder mUnbinder;

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.detail_name)
    TextView name;
    @BindView(R.id.phoneNumber)
    TextView phoneNumber;
    @BindView(R.id.emailTxt)
    TextView email;
    @BindView(R.id.addressTxt)
    TextView address;
    @BindView(R.id.cover_img)
    ImageView coverImg;

    @BindView(R.id.cardViewAddress)
    CardView cardViewAddress;

    @BindView(R.id.cardViewPhone)
    CardView cardViewPhone;

    @BindView(R.id.cardViewEmail)
    CardView cardViewEmail;

    public UserDetailFragment() {
        // Required empty public constructor
        setRetainInstance(true);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserDetailFragment newInstance(String param1, String param2, String param3, String param4, String param5) {
        UserDetailFragment fragment = new UserDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
            mParam5 = getArguments().getString(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_detail, container, false);
        mUnbinder = ButterKnife.bind(this,view);
        setUserData();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    private void setUserData() {
        name.setText(mParam1);
        phoneNumber.setText(mParam2);
        email.setText(mParam3);
        address.setText(mParam4);
        Glide.with(getContext()).load(mParam5).into(coverImg);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onUserDetailFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onUserDetailFragmentInteraction(Uri uri);
    }

    public void startNavigation(String address){
        Log.d("UserDetailsFragment", " not implemented");
    }

    public void sendEmailToUser(String emailAdress){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{emailAdress});
        i.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        i.putExtra(Intent.EXTRA_TEXT   , "Body");
        try {
            startActivity(Intent.createChooser(i, "Sending email"));
        } catch (android.content.ActivityNotFoundException ex) {
            Log.d("UserDetailsFragment", " no clients found");
        }
    }

    public void callUser(String phoneNumber){
        Intent callIntent = new Intent(Intent.ACTION_CALL);

        callIntent.setData(Uri.parse("tel:" + phoneNumber));

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }

    @OnClick(R.id.cardViewPhone)
    public void OnCardViewPhoneClicked(){
        callUser(phoneNumber.getText().toString());
    }

    @OnClick(R.id.cardViewEmail)
    public void OnCardViewEmailClicked(){
        sendEmailToUser(email.getText().toString());
    }

    @OnClick(R.id.cardViewAddress)
    public void OnCardViewAddressClicked(){
       startNavigation(address.getText().toString());
    }
}
