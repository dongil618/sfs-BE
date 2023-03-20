package com.example.sfs.util.api;

import com.example.sfs.dto.api.PapagoResult;
import com.example.sfs.util.PropertiesLoader;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class PapagoApi {

    public String changeEnToKor(String text) {
        String targetText = "";
        String resultText = "";
        try {
            String baseUrl = "https://openapi.naver.com/v1/papago/n2mt";

            Properties properties = PropertiesLoader.fromResource("application-secret.properties");
            String clientId = properties.getProperty("NAVER_PAPAGO_API_CLIENT_ID");
            String clientSecret = properties.getProperty("NAVER_PAPAGO_API_SECRET_KEY");

            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("X-Naver-Client-Id", clientId);
            requestHeaders.put("X-Naver-Client-Secret", clientSecret);

            Map<String, String> params = new HashMap<>();
            targetText = URLEncoder.encode(text, "UTF-8");
            params.put("text", targetText);
            params.put("source", "en");
            params.put("target", "ko");

            String resultMessage = post(baseUrl, requestHeaders, params);
            JsonParser parser = new JsonParser();
            JsonObject messageObject =  parser.parse(resultMessage).getAsJsonObject().get("message").getAsJsonObject();
            JsonObject resultObject = messageObject.getAsJsonObject().get("result").getAsJsonObject();
            Gson gson = new Gson();
            PapagoResult papagoResult = gson.fromJson(resultObject, PapagoResult.class);

            resultText = URLDecoder.decode(papagoResult.getTranslatedText());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("인코딩 실패", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultText;
    }

    public String getLangCode(String text) {
        String targetText = "";
        String langCode = "";
        try {
            String baseUrl = "https://openapi.naver.com/v1/papago/detectLangs";

            Properties properties = PropertiesLoader.fromResource("application-secret.properties");
            String clientId = properties.getProperty("NAVER_PAPAGO_API_CLIENT_ID");
            String clientSecret = properties.getProperty("NAVER_PAPAGO_API_SECRET_KEY");

            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("X-Naver-Client-Id", clientId);
            requestHeaders.put("X-Naver-Client-Secret", clientSecret);

            Map<String, String> params = new HashMap<>();
            targetText = URLEncoder.encode(text, "UTF-8");
            params.put("query", targetText);

            String resultMessage = post(baseUrl, requestHeaders, params);
            JsonParser parser = new JsonParser();
            JsonElement jsonElement =  parser.parse(resultMessage).getAsJsonObject().get("langCode");
            Gson gson = new Gson();
            langCode = gson.fromJson(jsonElement, String.class);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("인코딩 실패", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return langCode;
    }

    private static String post(String apiUrl, Map<String, String> requestHeaders, Map<String, String> params){
        HttpURLConnection con = connect(apiUrl);
        URIBuilder builder = new URIBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            builder.addParameter(param.getKey(), param.getValue());
        };
        String postParams = builder.toString();  // ?text=~&target=~&source=~ -> ?빼야함
        postParams = removeFirstChar(postParams);
        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postParams.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    private static String removeFirstChar(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.substring(1);
    }
}
