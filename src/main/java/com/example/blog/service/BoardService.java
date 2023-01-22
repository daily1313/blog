package com.example.blog.service;

import com.example.blog.domain.Board;
import com.example.blog.domain.Reply;
import com.example.blog.domain.User;
import com.example.blog.dto.ReplyRequestDto;
import com.example.blog.repository.BoardRepository;
import com.example.blog.repository.ReplyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Transactional(readOnly = true)
    public List<Board> findAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    @Transactional
    public void writeBoard(Board board, User user) {
        board.setCount(0); //조회 수
        board.setUser(user);

        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> findListBoards(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    //카테고리별 글 목록 조회
    @Transactional
    public Page<Board> findListBoardByCategory(Pageable pageable, String category) {
        return boardRepository.findByCategory(pageable, category);
    }

    @Transactional(readOnly = true)
    public List<Board> 작성글목록(User user) {
        return boardRepository.findAllByUserOrderByIdDesc(user);
    }

    //게시글 상세 조회

    @Transactional(readOnly = true)
    public Board viewDetailsOfBoards(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                ()-> {
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없음");
                }
        );
        board.setCount(board.getCount() + 1);
        return board;
    }

    // 게시글 삭제

    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    // 게시글 수정



    @Transactional
    public void updateBoard(Long id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없음");
                });
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        board.setCategory(requestBoard.getCategory());
    }


    // 댓글 작성

    // 데이터를 받을 때, 원래 컨트롤러에서 dto를 만들어서 받는게 좋음
    // dto를 사용하지 않는 이유는, 큰 프로젝트에서는 데이터가 오고가는게 많으니 꼭 써야함
    // 댓글쓰기
    @Transactional
    public void writeReply(User user, Long boardId, Reply requestReply) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 ID를 찾을 수 없습니다.");
                });
        requestReply.setUser(user);
        requestReply.setBoard(board);

        replyRepository.save(requestReply);
    }

    // 댓글 삭제

    @Transactional
    public void deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }



}
