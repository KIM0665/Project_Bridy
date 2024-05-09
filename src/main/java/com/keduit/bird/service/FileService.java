package com.keduit.bird.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
// java의 롬복이 데려오기
public class FileService {
    public String uploadFile(String uploadPath, String originalFileName,
                             byte[] fileData) throws Exception{

        UUID uuid = UUID.randomUUID();
        // ID 랜덤 생성(Universally Unique Identifier) = 고유의 식별자 만들기
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        // 확장자 만들기
        String saveFileName = uuid.toString() + extension;
        // 랜덤 ID.확장자 형식으로 만듬.
        String fileUploadFillUrl = uploadPath + "/" + saveFileName;
        // 파일의 실제 경로와 / ID + 확장자를 붙여 풀 네임을 표시
        FileOutputStream fos = new FileOutputStream(fileUploadFillUrl);
        // FileOutputStream : 파일에 바이트 단위로 데이터를 쓰기 위한 클래스
        fos.write(fileData);
        // 읽어내기
        fos.close();
        // 닫기 필요!
        return saveFileName;
    }

    public void deleteFile(String filePath) throws Exception{

        File deleteFile = new File(filePath);
        // 저장된 파일의 경로를 이용하여 파일 객체 생성함

        //해당 파일의 존재여부 확인 후 삭제
        if(deleteFile.exists()){
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        }else{
            log.info("파일이 존재하지 않습니다.");
        }
    }
}
