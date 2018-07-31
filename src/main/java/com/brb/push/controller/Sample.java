package com.brb.push.controller;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONException;

public class Sample {
    public static void main(String[] args) throws JSONException {    	
    	try {
    		   String jsonResponse;
    		   
    		   URL url = new URL("https://onesignal.com/api/v1/notifications");
    		   HttpURLConnection con = (HttpURLConnection)url.openConnection();
    		   con.setUseCaches(false);
    		   con.setDoOutput(true);
    		   con.setDoInput(true);

    		   con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    		   con.setRequestProperty("Authorization", "Basic MjI0NmM5NjItYjFkYS00NzM1LThkYWUtOTI4ODcwNGIwZDEz");
    		   con.setRequestMethod("POST");

    		 /*  String strJsonBody = "{"
    		                      +   "\"app_id\": \"25748714-c526-48cf-8204-104bd57448db\","
    		                      +   "\"included_segments\": [\"All\"],"
    		                      +   "\"data\": {\"foo\": \"bar\"},"
    		                      +   "\"contents\": {\"en\": \"English Message\"}"
    		                      + "}";*/
    		         
    		   
    		   
    		   String strJsonBody = "{"
                       +   "\"app_id\": \"25748714-c526-48cf-8204-104bd57448db\","
                       +   "\"include_player_ids\": [\"74f09f83-850e-4004-857d-6b1317425708\"],"
               //        +   "\"data\": {\"foo\": \"bar\"},"
                       + "\"headings\": {\"en\": \"타이틀 \"},"
                       +   "\"contents\": {\"en\": \"test 가나다라마바사 \"}"
                       + "}";
    		   
    		   
    		   System.out.println("strJsonBody:\n" + strJsonBody);

    		   byte[] sendBytes = strJsonBody.getBytes("UTF-8");
    		   con.setFixedLengthStreamingMode(sendBytes.length);

    		   OutputStream outputStream = con.getOutputStream();
    		   outputStream.write(sendBytes);

    		   int httpResponse = con.getResponseCode();
    		   System.out.println("httpResponse: " + httpResponse);

    		   if (  httpResponse >= HttpURLConnection.HTTP_OK
    		      && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
    		      Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
    		      jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
    		      scanner.close();
    		   }
    		   else {
    		      Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
    		      jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
    		      scanner.close();
    		   }
    		   System.out.println("jsonResponse:\n" + jsonResponse);
    		   
    		} catch(Throwable t) {
    		   t.printStackTrace();
    		}
    }
}