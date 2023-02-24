package com.example.sfs.util.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CategoryCrawler {
    private static String naverShoppingUrl = "https://search.shopping.naver.com/search/all?query=";
    /*
    네이버 쇼핑 검색 -> 상위 5개 카테고리 중 가장 많은 것
     */
    public String getProductCategory(String productName) {
        Map<String, Integer> productCategoryDict = new HashMap<>();
        try {
            String requestUrlPath = naverShoppingUrl + URLEncoder.encode(productName, "UTF-8");
            Document doc = Jsoup.connect(requestUrlPath).get();
            Elements categoryDivs = doc.select("div[class=basicList_depth__SbZWF]");

            List<String> productCategoryList = new ArrayList<>();
            for(Element categoryDiv : categoryDivs){
                String productCategory = "";
                Elements categoryElements = categoryDiv.getElementsByTag("span");
                for(Element categoryElement : categoryElements) {
                    productCategory += (categoryElement.text() + "/");
                }
                productCategory = productCategory.substring(0, productCategory.length() - 1);
                productCategoryList.add(productCategory);
                productCategoryDict = getCountsToDict(productCategoryList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        String maxKey = null;
        for (String key : productCategoryDict.keySet()) {
            if (maxKey == null || productCategoryDict.get(key) > productCategoryDict.get(maxKey)) {
                maxKey = key;
            }
        }
        return maxKey;
    }

    public Map<String, Integer> getCountsToDict(List<String> stringList) {
        Map<String, Integer> counts = new HashMap<>();
        for(String string : stringList) {
            if (counts.containsKey(string)) {
                counts.put(string, counts.get(string) + 1);
            } else {
                counts.put(string, 1);
            }
        }
        return counts;
    }
}
