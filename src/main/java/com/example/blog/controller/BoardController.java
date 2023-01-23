package com.example.blog.controller;

import com.example.blog.config.auth.PrincipalDetail;
import com.example.blog.domain.Board;
import com.example.blog.domain.User;
import com.example.blog.service.BoardService;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "게시글 5개 조회", notes = "게시글을 조회합니다.")
    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.findListBoards(pageable));
        return "index";
    }

    @ApiOperation(value = "일상 게시글 조회", notes = "일상 게시글을 조회합니다.")
    @GetMapping("/daily")
    public String daily(Model model, @PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.findListBoardByCategory(pageable, "daily"));
        return "daily";
    }

    @ApiOperation(value = "데이트 게시글 조회", notes = "데이트 게시글을 조회합니다.")
    @GetMapping("/date")
    public String date(Model model, @PageableDefault(size =5, sort = "id", direction =  Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.findListBoardByCategory(pageable, "date"));
        return "date";
    }

    @ApiOperation(value = "유저 ID로 게시글 상세 조회", notes = "유저 ID로 게시글을 상세 조회합니다.")
    @GetMapping("/board/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.viewDetailsOfBoards(id));
        return "board/detail";
    }

    @ApiOperation(value = "게시글 상세 보기")
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
