package com.busanit.service;

import com.busanit.domain.ReplyDTO;
import com.busanit.entity.Board;
import com.busanit.entity.Reply;
import com.busanit.repository.ReplyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReplyService {

    private ReplyRepository replyRepository;

    //댓글등록
    public Long register(ReplyDTO replyDTO){
        Reply reply =dtoToEntity(replyDTO);

        replyRepository.save(reply);
        return reply.getRno();
    }
    //특정 게시글의 댓글 목록
    public Page<ReplyDTO> getList(Long bno, Pageable pageable) {
        Page<Reply> resultPage = (Page<Reply>) replyRepository.findByBoardOrderByRno(Board.builder().bno(bno).build(), pageable);
        return resultPage.map(reply -> entityToDTO(reply));
    }

//    public Slice<Reply> getRepliesList(Long bno,Pageable pageable){
//        return replyRepository.findByBoard_Bno(bno,pageable);
//    }

    //댓글 수정
    public void modify(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
    }

    //댓글 삭제
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }

    //ReplyDTO를 Reply 엔티티로 변환
    private Reply dtoToEntity(ReplyDTO replyDTO) {
        Board board = Board.builder().bno(replyDTO.getBno()).build();
        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(board)
                .build();
        return reply;
    }

    //Reply엔티티를 ReplyDTO로 변경
    ReplyDTO entityToDTO(Reply reply){
        ReplyDTO dto = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegTime())
                .modDate(reply.getUpdateTime())
                .build();
        return dto;
    }
}
