package com.example.grpBoard.mappers;

import com.example.grpBoard.dto.BoardDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    @Insert("insert into grp_board values(null, #{grpBoardNotice}, #{grpBoardWriter}, #{grpBoardSubject}, #{grpBoardContent}, #{grpBoardUploadName}, #{grpBoardUploadUrl}, #{grpBoardUploadSize}, #{grpBoardUploadTrans}, 0, #{grpBoardReplyGrp}, 1, 1, now())")
    void setBoardWrite(BoardDto boardDto);
}
