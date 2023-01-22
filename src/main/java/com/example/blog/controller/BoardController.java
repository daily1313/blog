package com.example.blog.controller;

import com.example.blog.config.auth.PrincipalDetail;
import com.example.blog.domain.Board;
import com.example.blog.domain.User;
import com.example.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.findListBoards(pageable));
        return "index";
    }

    @GetMapping("/daily")
    public String daily(Model model, @PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.findListBoardByCategory(pageable, "daily"));
        return "daily";
    }

    @GetMapping("/date")
    public String date(Model model, @PageableDefault(size =5, sort = "id", direction =  Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.findListBoardByCategory(pageable, "date"));
        return "date";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.viewDetailsOfBoards(id));
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Long id, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        Board board = boardService.viewDetailsOfBoards(id);
        User boardWriter = board.getUser();

        if(boardWriter.equals(principalDetail.getUser())) {
            model.addAttribute("board", boardService.viewDetailsOfBoards(id));
            return "/board/updateForm";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }







}
