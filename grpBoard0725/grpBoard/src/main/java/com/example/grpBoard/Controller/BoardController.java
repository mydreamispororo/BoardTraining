package com.example.grpBoard.Controller;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/board/list")
    public String getList() {
        return "board/list";
    }

    @GetMapping("/board/write")
    public String getWrite() {
        return "board/write";
    }
}
