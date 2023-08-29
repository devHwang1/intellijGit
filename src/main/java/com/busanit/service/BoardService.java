package com.busanit.service;

import com.busanit.domain.BoardSpecification;
import com.busanit.entity.Board;
import com.busanit.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BoardService {
    private BoardRepository boardRepository;

    public Page<Board> getBoardList(String keyword,Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Page<Board> searchTitle(String keyword, Pageable pageable){
        return boardRepository.findByTitleContaining(keyword,pageable);

    }

    public Page<Board> searchContent(String keyword, Pageable pageable){
        return boardRepository.findByContentContaining(keyword,pageable);

    }

    public List<Board> searchKeyword(String title, String content){
        Specification<Board> spec = Specification.where(BoardSpecification.equalTitle(title));
        if(content != null){
            spec = spec.and(BoardSpecification.equalContent(content));
        }
        return boardRepository.findAll(spec);
    }
}
