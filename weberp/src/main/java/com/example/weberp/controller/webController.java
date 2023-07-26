package com.example.weberp.controller;

import com.example.weberp.dto.WeberpDto;
import com.example.weberp.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/board")
public class webController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/main")
    public String webMain() {
        return "main";
    }

    @GetMapping("/login")
    public String webLogin() {
        return "login";
    }

    @GetMapping("/list")
    public String webBoardList(Model model, @PageableDefault(page = 0, size = 5, sort = "erpId", direction = Sort.Direction.DESC) Pageable pageable) {
                                                                //page 1페이지 0, size : 보여줄 갯수, sort : 뭘 기준으로 가져올 것인지?, direction : 오름차순 내림차순

        Page<WeberpDto> list = boardService.boardList(pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        
        model.addAttribute("list", list); //boardService의 boardList를 "list" 라는 이름으로 받아서 넘기겠다.
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);


        return "boardlist";
    }

    @GetMapping("/upload") //강의 write 로 작성되어 있는 부분임
    public String webBoardUpload() {
        return "boardupload";
    }

    @PostMapping("/write") //강의에는 writepro 로 되어있음
    public String webBoardWrite(WeberpDto weberpDto, Model model, MultipartFile file) throws Exception {

        boardService.write(weberpDto, file);

        if(weberpDto != null) {

            model.addAttribute("message", "글 작성이 완료되었습니다.");

        }else {
            model.addAttribute("message", "글 작성을 실패하였습니다.");

        }
            model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @GetMapping("/view") //localhoset:7777/board/view?erpNum=1
    public String webBoardView(Model model, int erpNum) {

        model.addAttribute("board", boardService.boardView(erpNum));
        return "boardview";
    }

    @GetMapping("/delete")
    public String webBoardDelete(int erpNum) {

        boardService.boardDelete(erpNum);
        return "redirect:/board/list";
    }

    @GetMapping("/modify/{erpNum}")
    public String webBoardModify(@PathVariable("erpNum") int erpNum, Model model) { // /{erpNum} 부분이 @Path~(erpNum) 로 인식이 돼서 int erpNum로 들어감

        model.addAttribute("board", boardService.boardView(erpNum));
        return "boardmodify";
    }

    @PostMapping("/update/{erpNum}")
    public String webBoardUpdate(@PathVariable("erpNum") int erpNum, WeberpDto weberpDto, MultipartFile uploadFile) throws Exception {

        weberpDto weberpTemp = boardService.boardView(erpNum); //기존에 있는 글을 담아옴
        weberpTemp.setErpId(weberpDto.getErpId()); //새로 입력한 내용을 기존에 있던 내용에 덮어 씌움
        weberpTemp.setErpTitle(weberpDto.getErpTitle());
        weberpTemp.setErpContent(weberpDto.getErpContent());

        boardService.write(weberpTemp, uploadFile);

        return "redirect:/board/list";
    }



    }



