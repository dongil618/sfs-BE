package com.example.sfs.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupTest {
    public static void main(String[] args) {
        String pageUrl = "https://www.ficelle.co.kr/category/shoes-bag-acc/34?page=";
        String baseUrl = pageUrl.split("/")[0] + "//" + pageUrl.split("/")[2];

        for (int i = 1; i < 4; i++) {
            try {
                String pageIndexUrl = pageUrl + Integer.toString(i);
                Document doc = Jsoup.connect(pageIndexUrl).get();
                Elements thumbnailTags = doc.select(".thumbnail");
                if (thumbnailTags.isEmpty() == true) {
                    break;
                }
                for(Element thumbnailTag : thumbnailTags) {
                    String name = thumbnailTag.getElementsByTag("img").attr("alt");
                    String detailPageUrl = baseUrl + thumbnailTag.getElementsByTag("a").attr("href");
                    String thumbnailImageUrl = "https:" + thumbnailTag.getElementsByTag("img").attr("src");
                    System.out.println("name :" + name + ", detailPageUrl : " + detailPageUrl + ", thumbnailImageUrl : " + thumbnailImageUrl);
                }
                System.out.println("page " + i + " 크롤링했습니다.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("완료!");
    }
}
