package com.example.sfs.util.api;

import com.example.sfs.dto.api.NaverKeywordDto;
import com.example.sfs.util.PropertiesLoader;
import com.example.sfs.util.Signatures;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

@Component
public class NaverSearchAdApi {

    public NaverKeywordDto getRelKeyword(String keyword) throws JsonProcessingException {
        String requestUrl = "https://api.searchad.naver.com/keywordstool?showDetail=1&hintKeywords=";
        String path = "/keywordstool";
        long timeStamp = System.currentTimeMillis();
        URL url;
        String times = String.valueOf(timeStamp);
        String resultString = null;

        try {
            keyword = keyword.replace(" ", "");  // 공백제거
            keyword = URLEncoder.encode(keyword, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("fail encoding");
        }

        try {
            Properties properties = PropertiesLoader.fromResource("application-secret.properties");
            String apiKey = properties.getProperty("NAVER_SEARCH_AD_API_KEY");
            String secretKey = properties.getProperty("NAVER_SEARCH_AD_SECRET_KEY");
            long customerId = Long.parseLong(properties.getProperty("NAVER_SEARCH_AD_CUSTOMER_ID"));

            url = new URL(requestUrl + keyword);
            String signature = Signatures.of(times,  "GET", path, secretKey);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("X-Timestamp", times);
            con.setRequestProperty("X-API-KEY", apiKey);
            con.setRequestProperty("X-Customer", String.valueOf(customerId));
            con.setRequestProperty("X-Signature", signature);
            con.setDoOutput(true);

            int responseCode = con.getResponseCode();
            BufferedReader br;

            if(responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            }
            else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            resultString = response.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> jsonMap = om.readValue(resultString, new TypeReference<Map<String, Object>>(){});
        Object getKeywordListObject = ((ArrayList) jsonMap.get("keywordList")).get(0);
        String getKeywordListToString = om.writeValueAsString(getKeywordListObject);
        NaverKeywordDto naverKeywordDto = new NaverKeywordDto(om.readValue(getKeywordListToString, NaverKeywordDto.class));

        return naverKeywordDto;
    }
}
