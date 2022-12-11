package com.rykim.telebot;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class GoogleFormUtil {

    public boolean submitGoolgeForm(String message) {
        String my_google_form_user_url = "https://docs.google.com/forms/u/0/d/e/1FAIpQLSf3EdNRlHD-HNOGynVcyk2ptXTE5ykxuYTviuwKSvIWXBin4g/formResponse";
        try {
            URL url = new URL(my_google_form_user_url);

            //PREPARE PARAMS
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("entry." + 250598709, message);
            params.put("entry." + 1161597747, "KAKAO");
            StringBuilder postData = new StringBuilder();
            byte[] postDataBytes;
            try {
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (postData.length() != 0) {
                        postData.append('&');
                    }
                    postData.append(URLEncoder.encode(param.getKey(), StandardCharsets.UTF_8.name()));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()), StandardCharsets.UTF_8.name()));
                }
                postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8.name());
            } catch (Exception e) {
                System.out.println("error");
                return false;
            }
/************************************/
            //SEND POST
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            //GET RESPONSE
            int response = conn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                Reader bin = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8.name()));
                InputStream in = conn.getInputStream();
                in.close();
            }
            conn.disconnect();

/************************************/
            //OR OPEN THE PRE-FILLED FORM
            URL prefilled_url = new URL(my_google_form_user_url + "?usp=pp_url&" + postData);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(prefilled_url.toURI());
            }

        } catch (
                IOException e1) {
            e1.printStackTrace();
            return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
