package com.example.sfs.util.crawler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
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

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static String naverShoppingUrl = "https://search.shopping.naver.com/search/all?query=";

    /**
     * 네이버 쇼핑 검색 -> 상위 5개 카테고리 중 가장 많은 것
     * @param productName
     * @return
     */
    public String getProductCategory(String productName) {
        Map<String, Integer> productCategoryDict = new HashMap<>();
        try {
            String requestUrlPath = naverShoppingUrl + URLEncoder.encode(productName, "UTF-8");
            Document doc = Jsoup.connect(requestUrlPath).get();
            Element scriptElement = doc.select("script[id=__NEXT_DATA__]").first();
            List<DataNode> dataNodeList = scriptElement.dataNodes();
            String jsonStr = String.valueOf(dataNodeList.get(0));

            Map<String, Object> dataMap = objectMapper.readValue(jsonStr, Map.class);
            Map<String, Object> props = (Map<String, Object>) dataMap.get("props");
            Map<String, Object> pageProps = (Map<String, Object>) props.get("pageProps");
            Map<String, Object> initialState = (Map<String, Object>) pageProps.get("initialState");
            Map<String, Object> products = (Map<String, Object>) initialState.get("products");
            List<Map<String, Object>> productList = (List<Map<String, Object>>) products.get("list");

            List<String> productCategoryList = new ArrayList<>();
            int i = 0;
            for(Map<String, Object> product : productList) {
                if(i == 5) {
                    break;
                }
                Map<String, Object> item = (Map<String, Object>) product.get("item");
                String productCategory = item.get("category1Name") + "$"
                        + item.get("category2Name") + "$"
                        + item.get("category3Name")
                        + ((item.get("category4Name") != "") ? "$" + item.get("category4Name") : "");
                productCategoryList.add(productCategory);
                productCategoryDict = getCountsToDict(productCategoryList);
                i++;
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

    /**
     * 네이버 쇼핑 검색 -> 상위 5개 카테고리 중 가장 많은 것
     * 구분자 추가
     * @param productName
     * @param separator
     * @return
     */
    public String getProductCategory(String productName, String separator) {
        Map<String, Integer> productCategoryDict = new HashMap<>();
        try {
            String requestUrlPath = naverShoppingUrl + URLEncoder.encode(productName, "UTF-8");
            Document doc = Jsoup.connect(requestUrlPath).get();
            Elements categoryDivs = doc.select("div[class=product_depth__I4SqY]");

            List<String> productCategoryList = new ArrayList<>();
            for(Element categoryDiv : categoryDivs){
                String productCategory = "";
                Elements categoryElements = categoryDiv.getElementsByTag("span");
                for(Element categoryElement : categoryElements) {
                    productCategory += (categoryElement.text() + separator);
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
