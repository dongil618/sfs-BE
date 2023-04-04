package com.example.sfs.util;


import com.example.sfs.config.CommonConfig;
import com.example.sfs.dto.crawler.ProductDto;
import com.example.sfs.dto.product.PostRegisterProductRequestDto;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CommonUtil {

    /**
     * 폴더가 존재하지 않을 경우에만 폴더 생성
     * @param destPath 폴더 경로
     */
    public static void createFolder(String destPath) {
        File newFolder = new File(destPath);

        if(!newFolder.exists()) {
            try {
                newFolder.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteFolder(String destPath) {
        File deleteFolder = new File(destPath);

        if(deleteFolder.exists()) {
            try {
                deleteFolder.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean existFolder(String destPath) {
        File folder = new File(destPath);

        if(folder.exists()) {
            return true;
        }
        return false;
    }

    public static boolean existFile(String destPath) {
        File file = new File(destPath);

        if(file.exists()) {
            return true;
        }
        return false;
    }

    public static void saveThumbnailImage(ProductDto productDto) throws IOException {
        String productName = productDto.getName();
        String thumbnailImageUrl = productDto.getThumbnailImageUrl();

        // 서버에 저장할 경로(절대 경로)
        String fileName = getThumbnailImageFileName(productDto);
        String destPath = CommonConfig.BASE_IMG_FILE_PATH + "/" + changeSpaceToUnderBar(productName) + "/" + fileName;

        saveImage(thumbnailImageUrl, destPath);
    }

    public static String getThumbnailImageFileName(ProductDto productDto) {
        String productName = productDto.getName();
        String thumbnailImageUrl = productDto.getThumbnailImageUrl();
        String extension = getExtension(thumbnailImageUrl);
        return changeSpaceToUnderBar(productName) + "_thumbnail" + "." + extension;
    }

    public static String getThumbnailImageFilePath(PostRegisterProductRequestDto postRegisterProductRequestDto) {
        String productName = postRegisterProductRequestDto.getProductName();
        String fileName = postRegisterProductRequestDto.getThumbnailImageName();
        return CommonConfig.BASE_IMG_FILE_PATH + "/" + changeSpaceToUnderBar(productName) + "/" + fileName;
    }

    public static void saveDetailImageList(ProductDto productDto) throws IOException {
        String productName = productDto.getName();
        List<String> detailImageUrlList = productDto.getDetailImageUrlList();
        List<String> detailImageFileNameList = getDetailImageFileNameList(productDto);

        for(int i = 0; i < detailImageFileNameList.size(); i++) {
            // 서버에 저장할 경로(절대 경로)
            String fileName = detailImageFileNameList.get(i);
            String destPath = CommonConfig.BASE_IMG_FILE_PATH + "/" + changeSpaceToUnderBar(productName) + "/" + fileName;

            String detailImageUrl = detailImageUrlList.get(i);
            saveImage(detailImageUrl, destPath);

        }
    }

    public static List<String> getDetailImageFileNameList(ProductDto productDto) {
        String productName = productDto.getName();
        List<String> detailImageUrlList = productDto.getDetailImageUrlList();

        List<String> detailImageFileNameList = new ArrayList<>();

        int index = 0;
        for(String detailImageUrl : detailImageUrlList) {
            String extension = getExtension(detailImageUrl);
            String detailImageFileName = changeSpaceToUnderBar(productName) + "_" + index + "." + extension;
            detailImageFileNameList.add(detailImageFileName);

            index++;
        }

        return detailImageFileNameList;
    }

    /**
     * db에 달러($)로 구분된 것을 리스트로 변경해줌 -> 해당 부분은 DB 구조 변경 시 수정해야함.
     * @param postRegisterProductRequestDto
     * @return
     */
    public static List<String> getDetailImageFilePathList(PostRegisterProductRequestDto postRegisterProductRequestDto) {
        String productName = postRegisterProductRequestDto.getProductName();
        List<String> fileNameList = Arrays.asList(postRegisterProductRequestDto.getDetailImageNameList().split("\\$"));

        List<String> detailImageFilePathList = new ArrayList<>();
        for(String fileName : fileNameList) {
            String detailImageFilePath = CommonConfig.BASE_IMG_FILE_PATH + "/" + changeSpaceToUnderBar(productName) + "/" + fileName;
            detailImageFilePathList.add(detailImageFilePath);
        }
        return detailImageFilePathList;
    }

    public static void saveImage(String imageUrl, String fileDestPath) throws IOException {
        URL url = null;
        InputStream in = null;
        OutputStream out = null;

        try {
            url = new URL(imageUrl);
            in = url.openStream();

            // 서버에 저장할 경로(절대 경로)
            String destPathFolder = getDestPathFolder(fileDestPath);
            createFolder(destPathFolder);
            out = new FileOutputStream(fileDestPath);

            while (true) {
                // loop돌면서 이미지 데이터 읽기
                int data = in.read();

                // data가 -1이면 읽기 종료
                if(data == -1) {
                    break;
                }

                // 읽어들인 이미지 데이터 값 서버공간에 저장
                out.write(data);
            }

            // 저장 끝난 후 사용한 객체 close
            in.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    public static String getExtension(String imageUrl) {
        List<String> imageUrlSplit = Arrays.asList(imageUrl.split("\\."));
        int lastIndex = imageUrlSplit.size() - 1;
        if (lastIndex > 0) {
            return imageUrlSplit.get(lastIndex);
        }
        return null;
    }

    public static String changeSpaceToUnderBar(String imageName) {
        imageName = imageName.trim();
        String newName = imageName.replaceAll(" ", "_");
        return newName;
    }

    public static String getDestPathFolder(String fileDestPath) {
        String destPathFolder = "";
        List<String> fileDestPathSplits = Arrays.asList(fileDestPath.split("/"));
        int index = 0;
        int lastIndex = fileDestPathSplits.size() - 1;
        for(String fileDestPathSplit : fileDestPathSplits) {
            if(index == lastIndex) {
                break;
            }
            if(index == lastIndex - 1) {
                destPathFolder += fileDestPathSplit;
            } else {
                destPathFolder += fileDestPathSplit + "/";
            }

            index++;
        }
        return destPathFolder;
    }

    /**
     * 리스트 문자열을 받아 중복 제거
     * @param values
     * @return
     */
    public static List<String> getDeduplication(List<String> values) {
        List<String> newStringList = new ArrayList<>();

        for(String value : values) {
            if(!newStringList.contains(value)){
                newStringList.add(value);
            }
        }

        return newStringList;
    }

    /**
     * 키워드의 조합 구하기(구현중)
     * @param keywordList
     * @return
     */
    public static List<String> getKeywordCombination(List<String> keywordList, int r) {
        int n = keywordList.size();
        List<String> keywordCombinations = new ArrayList<>();

        Iterator<int[]> iterator = CombinatoricsUtils.combinationsIterator(n, r);
        while (iterator.hasNext()) {
            final int[] combinationIndexList = iterator.next();

            List<String> tempKeywordList = new ArrayList<>();
            for(int idx : combinationIndexList) {
                tempKeywordList.add(keywordList.get(idx));
            }

            keywordCombinations.add(String.join(" ", tempKeywordList));
        }

        return keywordCombinations;
    }

    /**
     * 10보다 작은 부등호 "< 10" 를 "0"으로 변경
     * NaverSearchAdApi 사용시 필요
     * @param sign
     * @return
     */
    public static String changeSignToZero(String sign) {
        if(sign.contains("<") || sign.equals("< 10")) {
            return "0";
        }
        return sign;
    }
}
