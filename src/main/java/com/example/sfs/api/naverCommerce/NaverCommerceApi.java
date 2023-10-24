package com.example.sfs.api.naverCommerce;

import com.example.sfs.api.naverCommerce.dto.*;
import com.example.sfs.util.CommonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class NaverCommerceApi {
    @Value("${api.naverCommerce.clientId}")
    private String clientId;
    @Value("${api.naverCommerce.secret}")
    private String clientSecret;
    private String COOKIE_NAME = "NC-TOKEN";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final CommonUtil commonUtil = new CommonUtil();

    private String generateSignature(Long timestamp) {
        String clientId = this.clientId;
        String clientSecret = this.clientSecret;
        String password = StringUtils.joinWith("_", clientId, timestamp);
        // bcrypt 해싱
        String hashedPw = BCrypt.hashpw(password, clientSecret);
        // base64 인코딩
        String clientSecretSign = Base64.getUrlEncoder().encodeToString(hashedPw.getBytes(StandardCharsets.UTF_8));
        return clientSecretSign;
    }

    public OAuthTokenResponseDto getOAuthToken(HttpServletResponse response) {
        String requestUrl = "https://api.commerce.naver.com/external/v1/oauth2/token";

        String clientId = this.clientId;
        Long timestamp = System.currentTimeMillis();
        String clientSecretSign = generateSignature(timestamp);
        OAuthTokenRequestDto oAuthTokenRequestDto = new OAuthTokenRequestDto(clientId, timestamp, clientSecretSign);

        Map<String, Object> params = objectMapper.convertValue(oAuthTokenRequestDto, Map.class);

        HttpUrl.Builder httpBuilder = HttpUrl.parse(requestUrl).newBuilder();
        if (params != null) {
            for(Map.Entry<String, Object> param : params.entrySet()) {
                httpBuilder.addQueryParameter( param.getKey(), String.valueOf( param.getValue() ) );
            }
        }
        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .post(RequestBody.create(MediaType.parse("application/json"), ""))
                .build();
        try {
            OkHttpClient client = new OkHttpClient();
            Response httpResponse = client.newCall(request).execute();
            String responseBody = httpResponse.body().string();
            OAuthTokenResponseDto oAuthToken = objectMapper.readValue(responseBody, OAuthTokenResponseDto.class);
            this.setAccessToken(response, oAuthToken);
            return oAuthToken;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SmartStoreProductResponseDto getSmartStoreProduct(String accessToken, String channelProductNo) {
        String requestUrl = "https://api.commerce.naver.com/external/v2/products/channel-products"
                + "/" + channelProductNo;

        HttpUrl.Builder httpBuilder = HttpUrl.parse(requestUrl).newBuilder();
        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader("Authorization", "Bearer " + accessToken)
                .get()
                .build();
        try {
            OkHttpClient client = new OkHttpClient();
            Response httpResponse = client.newCall(request).execute();
            String responseBody = httpResponse.body().string();
            return objectMapper.readValue(responseBody, SmartStoreProductResponseDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public RegisterSmartStoreProductResponseDto registerSmartStoreProduct(String accessToken, String requestBody) {
        String requestUrl = "https://api.commerce.naver.com/external/v2/products";

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,requestBody);
        HttpUrl.Builder httpBuilder = HttpUrl.parse(requestUrl).newBuilder();
        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader("Authorization", "Bearer " + accessToken)
                .post(body)
                .build();
        try {
            OkHttpClient client = new OkHttpClient();
            Response httpResponse = client.newCall(request).execute();
            String responseBody = httpResponse.body().string();
            return objectMapper.readValue(responseBody, RegisterSmartStoreProductResponseDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SmartStoreImageResponseDto uploadSmartStoreImages(String accessToken, List<File> images) {
        String requestUrl = "https://api.commerce.naver.com/external/v1/product-images/upload";

        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for(File image : images) {
            MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
            String mimeType = fileTypeMap.getContentType(image.getName());
            MediaType MEDIA_TYPE = MediaType.parse(mimeType);
            multipartBuilder.addFormDataPart("imageFiles", image.getName(), RequestBody.create(MEDIA_TYPE, image));
        }
        RequestBody body = multipartBuilder.build();


        HttpUrl.Builder httpBuilder = HttpUrl.parse(requestUrl).newBuilder();
        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader("Authorization", "Bearer " + accessToken)
                .addHeader("content-type", "multipart/form-data")
                .post(body)
                .build();
        try {
            OkHttpClient client = new OkHttpClient();
            Response httpResponse = client.newCall(request).execute();
            String responseBody = httpResponse.body().string();
            return objectMapper.readValue(responseBody, SmartStoreImageResponseDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SmartStoreImageResponseDto uploadSmartStoreImages(String accessToken, File image) {
        String requestUrl = "https://api.commerce.naver.com/external/v1/product-images/upload";

        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
        String mimeType = fileTypeMap.getContentType(image.getName());
        MediaType MEDIA_TYPE = MediaType.parse(mimeType);
        multipartBuilder.addFormDataPart("imageFiles", image.getName(), RequestBody.create(MEDIA_TYPE, image));
        RequestBody body = multipartBuilder.build();


        HttpUrl.Builder httpBuilder = HttpUrl.parse(requestUrl).newBuilder();
        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader("Authorization", "Bearer " + accessToken)
                .addHeader("content-type", "multipart/form-data")
                .post(body)
                .build();
        try {
            OkHttpClient client = new OkHttpClient();
            Response httpResponse = client.newCall(request).execute();
            String responseBody = httpResponse.body().string();
            return objectMapper.readValue(responseBody, SmartStoreImageResponseDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAccessToken(HttpServletResponse response, OAuthTokenResponseDto oAuthToken){
        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(this.COOKIE_NAME, oAuthToken.getAccessToken());
        cookie.setDomain("localhost"); // 도메인 나중에 생기면 변경
        cookie.setMaxAge(oAuthToken.getExpiresIn());
        cookie.setPath("/");
//        cookie.setSecure(true);
        response.addCookie(cookie);
    }
}
