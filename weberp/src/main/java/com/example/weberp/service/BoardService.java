package com.example.weberp.service;

import com.example.weberp.entity.Weberp;
import com.example.weberp.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //글 작성 처리
    public void write(Weberp weberp, MultipartFile file) throws Exception{ // throws Exception : 예외처리
        //저장할 경로 지정
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        //파일 이름을 랜덤으로 지정해 줌
        UUID uuid = UUID.randomUUID();

        //랜덤이름 _ 원래파일이름
        String fileName = uuid + "_" + file.getOriginalFilename();

        //saveFile 이라는 새로운 파일을 생성함, projectPath 에 넣을 것이고 name 이라는 이름으로 닮긴다.
        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        weberp.setErpFilename(fileName); //저장된 파일 이름
        weberp.setErpFilepath("/files" + fileName); //저장된파일 경로와 이름

        boardRepository.save(weberp);

    }

    //게시글 리스트 처리
    public Page<Weberp> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable); //findAll : 리스트에 Weberp 를 반환해줌, db에 있는 정보를 가져옴
    }

    //특정 게시글 불러오기
    public Weberp boardView(int erpNum) {

        return boardRepository.findById(erpNum).get();
    }

    //특정 게시글 삭제
    public void boardDelete(int erpNum) {
        boardRepository.deleteById(erpNum);
    }
}
