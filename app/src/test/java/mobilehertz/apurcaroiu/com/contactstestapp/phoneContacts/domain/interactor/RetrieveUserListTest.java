package mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import mobilehertz.apurcaroiu.com.contactstestapp.phoneContacts.domain.gateway.UserGateway;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by apurcaroiu on 9/19/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class RetrieveUserListTest {

    private RetrieveUserList retrieveUserList;

   @Mock
   private UserGateway mockUserGateway;

    @Before
    public void setUp() throws Exception {
          retrieveUserList = new RetrieveUserList(mockUserGateway);
    }

    @Test
    public void  testRetrieveUserList(){
        retrieveUserList.execute(0,10,"seed");
        verify(mockUserGateway,times(1)).getUsers(0,10,"seed");
    }
}