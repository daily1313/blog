package com.example.blog.controller.api;

import com.example.blog.config.auth.PrincipalDetail;
import com.example.blog.domain.Board;
import com.example.blog.domain.Reply;
import com.example.blog.dto.ReplyRequestDto;
import com.example.blog.dto.ResponseDto;
import com.example.blog.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    //게시글 작성
    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성하였습니다.")
    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.writeBoard(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    //게시글 삭제
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제하였습니다.")
    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable Long id){
        boardService.deleteBoard(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    //게시글 수정
    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정하였습니다.")
    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable Long id, @RequestBody Board board){
        System.out.println("BoardApiController : update : id : "+id);
        System.out.println("BoardApiController : update : board : "+board.getTitle());
        System.out.println("BoardApiController : update : board : "+board.getContent());
        boardService.updateBoard(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    // 데이터 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다.
    // dto 사용하지 않은 이유는!!
    //댓글 작성
    @ApiOperation(value = "댓글 작성", notes = "댓글을 작성하였습니다.")
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@PathVariable("boardId") Long boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principalDetail) {

        boardService.writeReply(principalDetail.getUser(), boardId, reply);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제 하였습니다.")
    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable Long replyId) {
        boardService.deleteReply(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
