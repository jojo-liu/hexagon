package com.jojoliu.hexagon;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Jojo on 24/05/2017.
 */

public class ClientTest {

//    @Test
    public void getSessionTest() throws Exception{
        String url="http://localhost:8080/rest/user";
        CloseableHttpClient httpclient= HttpClients.createDefault();
        HttpGet httpget=null;
        httpget=new HttpGet(url);
        httpget.addHeader("x-auth-token","e08c60bd-f0b5-4131-9d80-9181fb1b74e7");
        CloseableHttpResponse response=httpclient.execute(httpget);
        Header[] header=response.getHeaders("x-auth-token");
        String token="";
        if(header.length>0){
            token=header[0].getValue();
            System.out.println("获得token:"+token);
        }else{
            System.out.println("in getSession");
            System.out.println("没有返回token");
        }
        String rs=parseResponse(response);
        System.out.println(rs);
    }

    @Test
    public void loginTest() throws Exception{
        String url="http://localhost:8080/login/admin/123456";
        CloseableHttpClient httpclient=HttpClients.createDefault();
//        HttpPost httppost=new HttpPost(url);
//        CloseableHttpResponse response=httpclient.execute(httppost);
        HttpGet httpGet=new HttpGet(url);
        CloseableHttpResponse response=httpclient.execute(httpGet);
        Header[] header=response.getHeaders("x-auth-token");
        String token="";
        if(header.length>0){
            token=header[0].getValue();
            System.out.println("获得token:"+token);
        }else{
            System.out.println("in loginTest");
            System.out.println("没有返回token");
        }
        String rs=parseResponse(response);
        System.out.println(rs);
    }

    public static String parseResponse(HttpResponse response) throws UnsupportedOperationException, IOException{
        String rs="";
        HttpEntity entity=response.getEntity();
        if(entity!=null){
            InputStream instream=entity.getContent();
            rs=convertStreamToString(instream);
        }
        return rs;
    }

    public static String convertStreamToString(InputStream is){
        StringBuilder sb=new StringBuilder();
        byte[] bytes=new byte[4096];
        int size=0;

        try{
            while((size=is.read(bytes))>0){
                String str=new String(bytes,0,size,"UTF-8");
                sb.append(str);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                is.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
