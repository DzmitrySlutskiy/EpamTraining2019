package com.epam.testing.jsonParsing;

import com.epam.http.IHttpClient;
import com.epam.model.ListUsers;
import com.epam.model.User;
import com.epam.modelGson.DateGsonAdapter;
import com.epam.modelGson.UserGson;
import com.epam.testing.mockito.ResourceMock;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class JsonParseTest {


    @Mock
    private IHttpClient httpClient;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(httpClient.get(ArgumentMatchers.anyString()))
                .thenReturn(ResourceMock.readResource("generated.json"));

    }

    @Test
    public void parseJson() throws JSONException {
        String response = httpClient.get("http://myBackend.com/userList");
        List<User> users = new ListUsers(response).getUsersList();
        Assert.assertEquals("Laurie Armstrong", users.get(0).getName());
        Assert.assertEquals("lauriearmstrong@hatology.com", users.get(0).getEmail());
        Assert.assertEquals(users.size(), 7);
        Assert.assertEquals(users.get(0).getTag().getValues()[2], "Lorem");
        Assert.assertEquals(users.get(0).getTag().getValues()[3], "in");

    }

    @Test
    public void parseGson() {
        String response = httpClient.get("http://myBackend.com/userList");
        Gson gson = new Gson().newBuilder().registerTypeAdapter(Date.class, new DateGsonAdapter()).create();
        List<UserGson> users = gson.fromJson(response, new TypeToken<ArrayList<UserGson>>() {}.getType());

        Assert.assertEquals("Laurie Armstrong", users.get(0).getName());
        Assert.assertEquals("lauriearmstrong@hatology.com", users.get(0).getEmail());
        Assert.assertEquals(users.size(), 7);
        Assert.assertEquals(users.get(0).getTags()[2], "Lorem");
        Assert.assertEquals(users.get(0).getTags()[3], "in");


    }
}
