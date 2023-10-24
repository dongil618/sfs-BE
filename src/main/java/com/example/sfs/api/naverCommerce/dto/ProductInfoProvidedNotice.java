package com.example.sfs.api.naverCommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInfoProvidedNotice {

    /**
     * 상품정보제공고시 타입
     * 상품 요약 정보를 나타내는 타입
     * 하위 요소 중 하나를 선택해서 입력해야 하며 입력한 타입의 필드 정보가 등록됨
     * WEAR(의류 상품 요약 정보, wear 필드에 정보 입력)
     * SHOES(구두/신발 상품 요약 정보, shoes 필드에 정보 입력)
     * BAG(가방 상품 요약 정보, bag 필드에 정보 입력)
     * FASHION_ITEMS(패션잡화(모자/벨트/액세서리) 상품 요약 정보, fashionItems 필드에 정보 입력)
     * SLEEPING_GEAR(침구류/커튼 상품 요약 정보, sleepingGear 필드에 정보 입력)
     * FURNITURE(가구(침대/소파/싱크대/DIY제품) 상품 요약 정보, furniture 필드에 정보 입력)
     * IMAGE_APPLIANCES(영상가전(TV류) 상품 요약 정보, imageAppliances 필드에 정보 입력)
     * HOME_APPLIANCES(가정용 전기제품(냉장고/세탁기/식기세척기/전자레인지) 상품 요약 정보, homeAppliances 필드에 정보 입력)
     * SEASON_APPLIANCES(계절가전(에어컨/온풍기) 상품 요약 정보, seasonAppliances 필드에 정보 입력)
     * OFFICE_APPLIANCES(사무용기기(컴퓨터/노트북/프린터) 상품 요약 정보, officeAppliances 필드에 정보 입력)
     * OPTICS_APPLIANCES(광학기기(디지털카메라/캠코더) 상품 요약 정보, opticsAppliances 필드에 정보 입력)
     * MICROELECTRONICS(소형전자(MP3/전자사전 등) 상품 요약 정보, microElectronics 필드에 정보 입력)
     * NAVIGATION(내비게이션 상품 요약 정보, navigation 필드에 정보 입력)
     * CAR_ARTICLES(자동차용품(자동차부품/기타 자동차용품) 상품 요약 정보, carArticles 필드에 정보 입력)
     * MEDICAL_APPLIANCES(의료기기 상품 요약 정보, medicalAppliances 필드에 정보 입력)
     * KITCHEN_UTENSILS(주방용품 상품 요약 정보, kitchenUtensils 필드에 정보 입력)
     * COSMETIC(화장품 상품 요약 정보, cosmetic 필드에 정보 입력)
     * JEWELLERY(귀금속/보석/시계류 상품 요약 정보, jewellery 필드에 정보 입력)
     * FOOD(식품(농ㆍ축ㆍ수산물) 상품 요약 정보, food 필드에 정보 입력)
     * GENERAL_FOOD(가공식품 상품 요약 정보, generalFood 필드에 정보 입력)
     * DIET_FOOD(건강기능식품 상품 요약 정보, dietFood 필드에 정보 입력)
     * KIDS(영유아용품 상품 요약 정보, kids 필드에 정보 입력)
     * MUSICAL_INSTRUMENT(악기 상품 요약 정보, musicalInstrument 필드에 정보 입력)
     * SPORTS_EQUIPMENT(스포츠용품 상품 요약 정보), sportsEquipment 필드에 정보 입력
     * BOOKS(서적 상품 요약 정보, books 필드에 정보 입력)
     * RENTAL_ETC(물품대여 서비스(서적, 유아용품, 행사용품 등) 상품 요약 정보, rentalEtc 필드에 정보 입력)
     * DIGITAL_CONTENTS(디지털 콘텐츠(음원, 게임, 인터넷강의 등) 상품 요약 정보, digitalContents 필드에 정보 입력)
     * GIFT_CARD(상품권/쿠폰 상품 요약 정보, giftCard 필드에 정보 입력)
     * MOBILE_COUPON(모바일 쿠폰 상품 요약 정보, mobileCoupon 필드에 정보 입력)
     * MOVIE_SHOW(영화/공연 상품 요약 정보, movieShow 필드에 정보 입력)
     * ETC_SERVICE(기타 용역 상품 요약 정보, etcService 필드에 정보 입력)
     * BIOCHEMISTRY(생활화학제품 요약 정보, biochemistry 필드에 정보 입력)
     * BIOCIDAL(살생물제품 요약 정보, biocidal 필드에 정보 입력)
     * CELLPHONE(휴대폰 요약 정보, cellPhone 필드에 정보 입력)
     * ETC(기타 상품 요약 정보, etc 필드에 정보 입력)
     */
    private String productInfoProvidedNoticeType;

    /**
     * 의류 상품정보제공고시
     */
    private Wear wear;

    /**
     * 구두/신발 상품정보제공고시
     */
    private Shoes shoes;

    /**
     * 가방 상품정보제공고시
     */
    private Bag bag;

    /**
     * 패션잡화(모자/벨트/액세서리) 상품정보제공고시
     */
    private FashionItems fashionItems;
//
//    /**
//     * 침구류/커튼 상품정보제공고시
//     */
//    private SleepingGear sleepingGear;
//
//    /**
//     * 가구(침대/소파/싱크대/DIY제품) 상품정보제공고시
//     */
//    private Furniture furniture;
//
//    /**
//     * 영상가전(TV류) 상품정보제공고시
//     */
//    private ImageAppliances imageAppliances;
//
//    /**
//     * 가정용 전기제품(냉장고/세탁기/식기세척기/전자레인지) 상품정보제공고시
//     */
//    private HomeAppliances homeAppliances;
//
//    /**
//     * 계절가전(에어컨/온풍기) 상품정보제공고시
//     */
//    private SeasonAppliances seasonAppliances;
//
//    /**
//     * 사무용기기(컴퓨터/노트북/프린터) 상품정보제공고시
//     */
//    private OfficeAppliances officeAppliances;
//
//    /**
//     * 광학기기(디지털카메라/캠코더) 상품정보제공고시
//     */
//    private OpticsAppliances opticsAppliances;
//
//    /**
//     * 소형전자(MP3/전자사전 등) 상품정보제공고시
//     */
//    private MicroElectronics microElectronics;
//
//    /**
//     * 내비게이션 상품정보제공고시
//     */
//    private Navigation navigation;
//
//    /**
//     * 자동차용품(자동차부품/기타 자동차용품) 상품정보제공고시
//     */
//    private CarArticles carArticles;
//
//    /**
//     * 의료기기 상품정보제공고시
//     */
//    private MedicalAppliances medicalAppliances;
//
//    /**
//     * 주방용품 상품정보제공고시
//     */
//    private KitchenUtensils kitchenUtensils;
//
//    /**
//     * 화장품 상품정보제공고시
//     */
//    private Cosmetic cosmetic;
//
//    /**
//     * 귀금속/보석/시계류 상품정보제공고시
//     */
//    private Jewellery jewellery;
//
//    /**
//     * 식품(농수산물) 상품정보제공고시
//     */
//    private Food food;
//
//    /**
//     * 가공식품 상품정보제공고시
//     */
//    private GeneralFood generalFood;
//
//    /**
//     * 건강기능식품 상품정보제공고시
//     */
//    private DietFood dietFood;
//
//    /**
//     * 어린이제품요약정보 상품정보제공고시
//     */
//    private Kids kids;
//
//    /**
//     * 악기 상품정보제공고시
//     */
//    private MusicalInstrument musicalInstrument;
//
//    /**
//     * 스포츠용품 상품정보제공고시
//     */
//    private SportsEquipment sportsEquipment;
//
//    /**
//     * 서적 상품정보제공고시
//     */
//    private Books books;
//
//    /**
//     * 물품대여 서비스(서적, 유아용품, 행사용품 등) 상품정보제공고시
//     */
//    private RentalEtc rentalEtc;
//
//    /**
//     * 디지털 콘텐츠 상품정보제공고시
//     */
//    private DigitalContents digitalContents;
//
//    /**
//     * 상품권/쿠폰 상품정보제공고시
//     */
//    private GiftCard giftCard;
//
//    /**
//     * 모바일 쿠폰 상품정보제공고시
//     */
//    private MobileCoupon mobileCoupon;
//
//    /**
//     * 영화/공연 상품정보제공고시
//     */
//    private MovieShow movieShow;
//
//    /**
//     * 기타 용역 상품정보제공고시
//     */
//    private EtcService etcService;
//
//    /**
//     * 생활화학제품 상품정보제공고시
//     */
//    private Biochemistry biochemistry;
//
//    /**
//     * 살생물제품 상품정보제공고시
//     */
//    private Biocidal biocidal;
//
//    /**
//     * 휴대폰 상품정보제공고시
//     */
//    private CellPhone cellPhone;
//
//    /**
//     * 기타 재화 상품정보제공고시
//     */
//    private Etc etc;
}
