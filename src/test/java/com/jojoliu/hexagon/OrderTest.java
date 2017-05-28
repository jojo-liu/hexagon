package com.jojoliu.hexagon;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

/**
 * Created by Jojo on 23/05/2017.
 */
public class OrderTest {
    @Test
    public void loginTest() throws Exception{
        String url="http://localhost:8080/rest/order/order_123_666_888";
        CloseableHttpClient httpclient= HttpClients.createDefault();
        HttpPost httppost=new HttpPost(url);
        httpclient.execute(httppost);
    }
}
