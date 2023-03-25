package com.example.sfs.util.register;

import com.example.sfs.dto.product.PostRegisterProductRequestDto;
import com.example.sfs.util.CommonUtil;
import com.example.sfs.util.PropertiesLoader;
import com.example.sfs.config.ChromeDriverContext;
import com.example.sfs.util.SeleniumUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component
public class SmartStoreRegister implements ProductRegister {
    private SeleniumUtil seleniumUtil;
    private ChromeDriverContext context;

    private WebDriver driver;

    public SmartStoreRegister(SeleniumUtil seleniumUtil, ChromeDriverContext context) throws IOException {
        this.seleniumUtil = seleniumUtil;
        this.context = context;
        this.driver = context.setupChromeDriver();
    }

    @Override
    public void registerProduct(PostRegisterProductRequestDto postRegisterProductRequestDto) throws InterruptedException {
        // 바로 상품 등록 url로 접근
        String createProductUrl = "https://sell.smartstore.naver.com/#/products/create";
        driver.get(createProductUrl);
        seleniumUtil.timeSleep(3);
        String loginSelector = "#root > div > div.Layout_wrap__3uDBh > div > div > h2";
        if(seleniumUtil.isExistBtnBySelector(driver, loginSelector, "로그인")) {
            smartStoreLogin(driver);
            driver.get(createProductUrl);
        }

        try {
            seleniumUtil.timeSleep(5);
            closePopUp(driver);
            inputCategory(driver, postRegisterProductRequestDto);
            seleniumUtil.scrollTo(driver, 700);
            inputProductName(driver, postRegisterProductRequestDto);
            inputProductPrice(driver, postRegisterProductRequestDto);
            seleniumUtil.scrollTo(driver, 1200);
            inputProductStock(driver, postRegisterProductRequestDto);
            seleniumUtil.scrollTo(driver, 1300);
            activeOptionSection(driver);
            seleniumUtil.scrollTo(driver, 1750);
            inputProductOption(driver, postRegisterProductRequestDto);
            updateSelectOptionList(driver, postRegisterProductRequestDto);
            seleniumUtil.scrollTo(driver, 2300);
            uploadMainImage(driver, postRegisterProductRequestDto);
            seleniumUtil.timeSleep(2);
            seleniumUtil.scrollTo(driver, 4000);
            writeDetailPageWithSmartEditorOne(driver, postRegisterProductRequestDto);
            inputDeliveryInfo(driver);
            seleniumUtil.scrollTo(driver, 4500);
            seleniumUtil.timeSleep(1);
            stopDisplay(driver);
            saveRegisterProduct(driver);
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public ProductRegisterStrategy getProductRegisterStrategy() {
        return ProductRegisterStrategy.SmartStoreRegister;
    }

    public void smartStoreLogin(WebDriver driver) {
        try {
            Properties properties = PropertiesLoader.fromResource("application-secret.properties");
            String naverId = properties.getProperty("NAVER_ID");
            String naverPw = properties.getProperty("NAVER_PW");

            SeleniumUtil seleniumUtil = new SeleniumUtil();

            // 네이버아이디로 로그인 버튼 클릭
            String naverLoginBtnXpath = "//*[@id='root']/div/div[1]/div/div/div[4]/div[1]/ul/li[2]/button/span";
            seleniumUtil.elementClickByXpath(driver, naverLoginBtnXpath);
            seleniumUtil.timeSleep(driver, 5);

            seleniumUtil.switchWindow(driver, 1); // 창 바꾸기

            // 아이디 비번 입력
            seleniumUtil.setInputDataById(driver, "id", naverId);
            seleniumUtil.setInputDataById(driver, "pw", naverPw);

            // 새로운 창 로그인 버튼 클릭
            String newWindowLoginBtnId = "log.login";
            seleniumUtil.elementClickById(driver, newWindowLoginBtnId);
            seleniumUtil.switchWindow(driver, 0);
            seleniumUtil.findElementById(driver,"seller-lnb", 60);  // 2차 인증이 있다면 기다려줘야하기 때문에 인증 후 스마트스토어 판매자 센터 홈화면의 id값이 나타날 때까지 기다려주기
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closePopUp(WebDriver driver) {
        // popup 여부 확인
        String pageSource = driver.getPageSource();
        Document doc = Jsoup.parse(pageSource);
        String popUpDivSelector = "#seller-content > div";
        Elements popUpDivElements = doc.select(popUpDivSelector);
        System.out.println(popUpDivElements.attr("role"));
        if(!(popUpDivElements.attr("role").equals("dialog"))) {
            return;
        }

        // popup 태그 가져오기
        String popUpSelector = "#seller-content > div > div > div > div.modal-footer > div > div > label";

        try {
            seleniumUtil.executeClickJsBySelector(driver, popUpSelector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inputCategory(WebDriver driver, PostRegisterProductRequestDto postRegisterProductRequestDto) throws InterruptedException {
        List<String> categoryList = Arrays.asList(postRegisterProductRequestDto.getCategory().split("\\$"));
        String categorySearchXpath = "//*[@id=\'productForm\']/ng-include/ui-view[3]/div/div[2]/div/div[1]/div/category-search/div[1]/div[1]/div/label[2]";
        seleniumUtil.elementClickByXpath(driver, categorySearchXpath);
        seleniumUtil.timeSleep(driver, 5);
        int i = 1;
        for(String category : categoryList) {
            String dataGroupUlXpath = String.format("//*[@id=\"productForm\"]/ng-include/ui-view[3]/div/div[2]/div/div[1]/div/category-search/div[3]/div[%d]/ul", i);
            WebElement dataGroupUl = seleniumUtil.findElementByXpath(driver, dataGroupUlXpath);
            List<WebElement> dataGroupLis = dataGroupUl.findElements(By.tagName("li"));
            for(WebElement dataGroupLi : dataGroupLis) {
                if(category.equals(dataGroupLi.getText())) {
                    dataGroupLi.click();
                    seleniumUtil.timeSleep(1);
                }
            }
            i++;
        }
        // KC인증 안전기준 팝업창
        seleniumUtil.timeSleep(1);
        String kcPopUpCloseBtnSelector = "body > div.modal.seller-layer-modal.fade.in > div > div > div.modal-footer > div > button";
        if(seleniumUtil.isExistBtnBySelector(driver, kcPopUpCloseBtnSelector, "확인")) {
            try {
                seleniumUtil.executeClickJsBySelector(driver, kcPopUpCloseBtnSelector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inputProductName(WebDriver driver, PostRegisterProductRequestDto postRegisterProductRequestDto) {
        String productNameXpath = "//*[@id=\'productForm\']/ng-include/ui-view[7]/div/div[2]/div/div/div/div/div/div/input";
        String productName = postRegisterProductRequestDto.getProductName();
        seleniumUtil.setInputDataByXpath(driver, productNameXpath, productName);
    }

    public void inputProductPrice(WebDriver driver, PostRegisterProductRequestDto postRegisterProductRequestDto) {
        String productPriceXpath = "//*[@id=\'prd_price2\']";
        String discountYN = postRegisterProductRequestDto.getDiscountYN();
        Integer discountedAmount = postRegisterProductRequestDto.getDiscountedAmount();
        Integer productPrice = postRegisterProductRequestDto.getPrice();
        if(discountYN.equals("Y")) {
            // 할인가 설정하는 영역
        }
        seleniumUtil.setInputDataByXpath(driver, productPriceXpath, String.valueOf(productPrice));
    }

    public void inputProductStock(WebDriver driver, PostRegisterProductRequestDto postRegisterProductRequestDto) {
        String productStockXpath = "//*[@id=\'stock\']";
        // 나중에 postRegisterProductRequestDto에 재고수량이 생긴다면 추가하기!
        Integer productStock = 999;
        seleniumUtil.setInputDataByXpath(driver, productStockXpath, String.valueOf(productStock));
    }

    public void activeOptionSection(WebDriver driver) {
        // 옵션 영역 활성화 버튼 클릭
        String optionActiveBtnXpath = "//*[@id=\'productForm\']/ng-include/ui-view[11]/div/div/div/div/a";
        seleniumUtil.elementClickByXpath(driver, optionActiveBtnXpath);

        // 선택형 클릭
        String choiceTypeBtnCssSelector = "#productForm > ng-include > ui-view:nth-child(11) > div > fieldset > div > div > div:nth-child(2) > div:nth-child(1) > div > div.seller-input.fix-min-width.form-group > label:nth-child(1)";
        seleniumUtil.elementClickByCssSelector(driver, choiceTypeBtnCssSelector);
    }

    // 함수가 너무 길다 -> 나중에 리팩토링 할 것! 특히 +버튼 눌렀을 때 옵션 추가되는 흐름으로 리팩토링하면 좋을 듯
    public void inputProductOption(WebDriver driver, PostRegisterProductRequestDto postRegisterProductRequestDto) {
        String optionNameXpath = "//*[@id=\'choice_option_name0\']";
        String optionValuePath = "//*[@id=\'choice_option_value0\']";

        String colorList = postRegisterProductRequestDto.getColorList();
        String sizeList = postRegisterProductRequestDto.getSizeList();
        int divNum = 2;
        if(colorList != null && sizeList != null) {
            // 옵션 개수 2개 선택
            divNum = 3;
            String optionNumActiveBtnXpath = "//*[@id=\'productForm\']/ng-include/ui-view[11]/div/fieldset/div/div/div[2]/div[2]/div[2]/div/div/div/div/div[1]";
            seleniumUtil.elementClickByXpath(driver, optionNumActiveBtnXpath);

            String optionNum2Xpath = "//*[@id=\'productForm\']/ng-include/ui-view[11]/div/fieldset/div/div/div[2]/div[2]/div[2]/div/div/div/div/div[2]/div/div[2]";
            seleniumUtil.elementClickByXpath(driver, optionNum2Xpath);

            String optionNameXpath1 = "//*[@id=\'choice_option_name1\']";
            String optionValuePath1 = "//*[@id=\'choice_option_value1\']";

            String colors = postRegisterProductRequestDto.getColorList();
            seleniumUtil.setInputDataByXpath(driver, optionNameXpath, "color");
            seleniumUtil.setInputDataByXpath(driver, optionValuePath, colors);

            String sizes = postRegisterProductRequestDto.getSizeList();
            seleniumUtil.setInputDataByXpath(driver, optionNameXpath1, "size");
            seleniumUtil.setInputDataByXpath(driver, optionValuePath1, sizes);
        }
        if(colorList != null && sizeList == null) {
            String colors = postRegisterProductRequestDto.getColorList();
            seleniumUtil.setInputDataByXpath(driver, optionNameXpath, "color");
            seleniumUtil.setInputDataByXpath(driver, optionValuePath, colors);
        }
        if(sizeList != null && colorList == null) {
            String sizes = postRegisterProductRequestDto.getSizeList();
            seleniumUtil.setInputDataByXpath(driver, optionNameXpath, "size");
            seleniumUtil.setInputDataByXpath(driver, optionValuePath, sizes);
        }

        // 옵션목록으로 적용하기 버튼 클릭
        String applyOptionListBtnXpath = "//*[@id=\'productForm\']/ng-include/ui-view[11]/div/fieldset/div/div/div[2]/div[2]/div[4]/div/div/div[" + divNum + "]/div[1]/a";
        seleniumUtil.elementClickByXpath(driver, applyOptionListBtnXpath);
    }

    public void inputOptionStock(WebDriver driver, PostRegisterProductRequestDto postRegisterProductRequestDto) {
        String optionStockXpath = "//*[@id=\'productForm\']/ng-include/ui-view[11]/div/fieldset/div/div/div[2]/div[3]/div[2]/div[1]/div[2]/div/div[4]/div/div/input";
        // 나중에 postRegisterProductRequestDto에 재고수량이 생긴다면 추가하기!
        Integer optionStock = 999;
        seleniumUtil.setInputDataByXpath(driver, optionStockXpath, String.valueOf(optionStock));
    }

    public void updateSelectOptionList(WebDriver driver, PostRegisterProductRequestDto postRegisterProductRequestDto) {
        // 옵션 재고 수량 입력
        inputOptionStock(driver, postRegisterProductRequestDto);

        // 옵션 목록 전체 선택
        String optionListAllCheckBoxBtnXpath = "//*[@id=\'productForm\']/ng-include/ui-view[11]/div/fieldset/div/div/div[2]/div[3]/div[2]/div[2]/div/div/div/div[1]/div[2]/div/div[2]/div[1]/div[2]/div/label/span";
        seleniumUtil.elementClickByXpath(driver, optionListAllCheckBoxBtnXpath);

        // 선택목록 일괄 수정
        String updateSelectOptionListBtnXpath = "//*[@id=\'productForm\']/ng-include/ui-view[11]/div/fieldset/div/div/div[2]/div[3]/div[2]/div[1]/div[2]/div/div[7]/a";
        seleniumUtil.elementClickByXpath(driver, updateSelectOptionListBtnXpath);
    }

    public void uploadMainImage(WebDriver driver, PostRegisterProductRequestDto postRegisterProductRequestDto) {
        // 대표 이미지 + 버튼 클릭
        String addMainImageBtnXpath = "//*[@id=\'representImage\']/div/div[1]/div/ul/li/div/a";
        seleniumUtil.elementClickByXpath(driver, addMainImageBtnXpath);

        // 내 사진 버튼을 통해 이미지 업로드
        String myImageBtnXpath = "/html/body/label/input";
        String thumbnailImageFilePath = CommonUtil.getThumbnailImageFilePath(postRegisterProductRequestDto);
        seleniumUtil.uploadImageByXpath(driver, myImageBtnXpath, thumbnailImageFilePath);
    }

    public void writeDetailPageWithSmartEditorOne(WebDriver driver, PostRegisterProductRequestDto postRegisterProductRequestDto) throws InterruptedException {
        // 스마트에디터 One으로 작성 버튼
        String smartEditorBtnXpath = "//*[@id=\'productForm\']/ng-include/ui-view[13]/div/div[2]/div/div/ncp-editor-form/div[1]/div/p[4]/button";
        seleniumUtil.elementClickByXpath(driver, smartEditorBtnXpath);
        seleniumUtil.timeSleep( 5);

        // 탭 이동
        List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        int firstTab = 0;
        int lastTab = tabs.size() - 1;
        driver.switchTo().window(tabs.get(lastTab));

        // 스마트 에디터 원의 se-body id값 찾기 => 해당 값은 스마트 에디터 원이 열릴 때마다 갱신되어 내부 버튼 접근하지 못하는 문제를 해결!
        WebElement oneEditorEle = driver.findElement(By.id("one-editor"));
        String divId = oneEditorEle.findElement(By.className("se-body")).getAttribute("id");
        String templateXpath = "//*[@id=\'" + divId + "\']/div[1]/div/header/div[1]/ul/li[12]/button";
        seleniumUtil.elementClickByXpath(driver, templateXpath);
        seleniumUtil.timeSleep(1);

        String myTemplateXpath = "//*[@id=\'" + divId + "\']/div[1]/div/div[1]/aside/div/div[2]/ul/li[2]/button";
        seleniumUtil.elementClickByXpath(driver, myTemplateXpath);
        seleniumUtil.timeSleep(1);

        String latestTemplateXpath = "//*[@id=\'" + divId + "\']/div[1]/div/div[1]/aside/div/div[3]/div[1]/div[2]/ul/li[1]/a/div";
        seleniumUtil.elementClickByXpath(driver, latestTemplateXpath);
        seleniumUtil.timeSleep(1);

        String closeTemplateBtnXpath = "//*[@id=\'" + divId + "\']/div[1]/div/div[1]/aside/div/button";
        seleniumUtil.elementClickByXpath(driver, closeTemplateBtnXpath);
        seleniumUtil.timeSleep(2);

        // 템플릿 제어를 위함
        List<WebElement> pTags = driver.findElements(By.tagName("p"));
        int commentNextIndex = -1;        // c o m m e n t 다음 p태그 인덱스
        int sizeNextIndex = -1;           // /size 다음 p태그 인덱스
        int lastIndex = pTags.size() - 1;
        int index = 0;
        for(WebElement pTag : pTags) {
            if(pTag.getText().equals("c o m m e n t")) {
                commentNextIndex = index + 2;
            }
            if(pTag.getText().equals("/size")) {
                sizeNextIndex = index + 2;
            }
            index ++;
        }

        // 사이즈 가이드 작성
        String pTagIdSizeNext = pTags.get(sizeNextIndex).getAttribute("id");
        String pTagIdSizeNextXpath = "//*[@id=\'" + pTagIdSizeNext + "\']";
        seleniumUtil.elementClickByXpath(driver, pTagIdSizeNextXpath);
        String sizeGuide = postRegisterProductRequestDto.getSizeGuide();
        seleniumUtil.clipboardCopyString(sizeGuide);
        seleniumUtil.performCtrlV(driver);
        seleniumUtil.timeSleep(1);


        // instruction 작성
        String pTagIdCommentNext = pTags.get(commentNextIndex).getAttribute("id");
        String pTagIdCommentNextXpath = "//*[@id=\'" + pTagIdCommentNext + "\']";
        seleniumUtil.elementClickByXpath(driver, pTagIdCommentNextXpath);
        String instruction = postRegisterProductRequestDto.getInstruction();
        seleniumUtil.clipboardCopyString(instruction);
        seleniumUtil.performCtrlV(driver);
        seleniumUtil.timeSleep(1);

        // 상세 페이지 이미지 등록
        String pTagIdLast = pTags.get(lastIndex).getAttribute("id");
        String pTagLastXpath = "//*[@id=\'" + pTagIdLast + "\']";
        seleniumUtil.elementClickByXpath(driver, pTagLastXpath);

        String imageXpath = "//*[@id=\'" + divId + "\']/div[1]/div/header/div[1]/ul/li[1]/button";
        String myImageBtnXpath = "/html/body/label/input";
        List<String> detailImageFilePathList = CommonUtil.getDetailImageFilePathList(postRegisterProductRequestDto);
        seleniumUtil.uploadImageListByXpath(driver, imageXpath, myImageBtnXpath, detailImageFilePathList);
        seleniumUtil.timeSleep(5);

        // 등록버튼 클릭
        String registerBtnXpath = "/html/body/ui-view[1]/ncp-editor-launcher/div[1]/div/button";
        seleniumUtil.getPresenceOfElementByXpath(driver, registerBtnXpath).click();

        // 탭 변경해줘야함.
        driver.switchTo().window(tabs.get(firstTab));

        seleniumUtil.timeSleep(3);
    }

    public void inputDeliveryInfo(WebDriver driver) throws InterruptedException {
        String openDeliverySectionBtnXpath = "//*[@id=\'productForm\']/ng-include/ui-view[18]/div[2]/div/div/div/a";
        seleniumUtil.elementClickByXpath(driver, openDeliverySectionBtnXpath);

        String deliveryFeeTemplateBtnXpath = "//*[@id=\'productForm\']/ng-include/ui-view[18]/div[2]/div[2]/div/div[1]/div/div/div[2]/button";
        seleniumUtil.elementClickByXpath(driver, deliveryFeeTemplateBtnXpath);

        String basicTemplateSelectBtnCssSelector = "body > div.modal.fade.seller-layer-modal.in > div > div > div.modal-body > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > button";
        seleniumUtil.elementClickByCssSelector(driver, basicTemplateSelectBtnCssSelector);
    }

    public void stopDisplay(WebDriver driver) {
        String stopDisplayBtnXpath = "//*[@id=\'productForm\']/ng-include/ui-view[24]/div/div[2]/div/div[2]/div/div[2]/ng-include/div/div[3]/div/div/div/label[2]";
        seleniumUtil.getPresenceOfElementByXpath(driver, stopDisplayBtnXpath).click();
    }

    public void saveRegisterProduct(WebDriver driver) throws InterruptedException {
        String saveBtnXpath = "//*[@id=\'seller-content\']/ui-view/div[3]/div[2]/div[1]/button[3]";
        seleniumUtil.elementClickByXpath(driver, saveBtnXpath);
        String nextBtnXpath = "/html/body/div[1]/div/div/div[3]/div[1]/button[1]";
        seleniumUtil.elementClickByXpath(driver, nextBtnXpath);
        seleniumUtil.timeSleep(5);

        String manageProductBtnSelector = "body > div.modal.fade.seller-layer-modal.in > div > div > div.modal-footer > div > button.btn.btn-default";
        try {
            seleniumUtil.executeClickJsBySelector(driver, manageProductBtnSelector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
