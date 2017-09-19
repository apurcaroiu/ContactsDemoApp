package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.presenter;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.User;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.interactor.DefaultObserver;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.interactor.RetrieveUserList;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.mapper.UserViewModelMapper;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.model.UserViewModel;
import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.presentation.view.UsersListView;

/**
 * Created by apurcaroiu on 9/18/2017.
 */

public class UserListPresenter implements Presenter {

    private UsersListView mUsersListView;
    private RetrieveUserList mRetrieveUserList;
    private UserViewModelMapper mUserViewModelMapper;

    public UserListPresenter(RetrieveUserList mRetrieveUserList, UserViewModelMapper mUserViewModelMapper) {
        this.mRetrieveUserList = mRetrieveUserList;
        this.mUserViewModelMapper = mUserViewModelMapper;
    }

    public void setView(@NonNull UsersListView usersListView){
        this.mUsersListView = usersListView;
    }

    public void getContactsList(int pageNumber, int pageSize, String seed){
       this.showViewLoading();
       this.mRetrieveUserList.execute(pageNumber, pageSize, seed)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeWith(new UserListObservable());
    }

    private void showViewLoading() {
        this.mUsersListView.showLoading();
    }

    private void hideViewLoading() {
        this.mUsersListView.hideLoading();
    }

    private void updateContactList(Collection<User> users){
        final Collection<UserViewModel> userViewModels = this.mUserViewModelMapper.transform(users);
       this.mUsersListView.displayUserList(userViewModels);
    }


    @Override
    public void create() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    private class   UserListObservable extends DefaultObserver<List<User>>{
        @Override
        public void onSubscribe(Disposable d) {
            super.onSubscribe(d);
        }

        @Override
        public void onSuccess(List<User> value) {
            super.onSuccess(value);
            UserListPresenter.this.hideViewLoading();
            UserListPresenter.this.updateContactList(value);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            UserListPresenter.this.hideViewLoading();
        }
    }
}
