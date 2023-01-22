package com.example.blog.repository;

import com.example.blog.domain.Board;
import com.example.blog.domain.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findByCategory(Pageable pageable, String category);
    List<Board> findAllByUserOrderByIdDesc(User user);
}
