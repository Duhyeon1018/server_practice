package com.busanit501.bootpractice.service;

import com.busanit501.bootpractice.domain.Food;
import com.busanit501.bootpractice.domain.Reply;
import com.busanit501.bootpractice.dto.ReplyDTO;
import com.busanit501.bootpractice.repository.FoodRepository;
import com.busanit501.bootpractice.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    private final FoodRepository foodRepository;

    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO) {
        // 화면에서 받은 데이터 DTO 타입 -> 엔티티 타입으로 변경,
        // replyDTO, bno 값이 존재.
        log.info("Registering new replyDTO: " + replyDTO);
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        Optional<Food> result = foodRepository.findById(replyDTO.getBno());
        Food food = result.orElseThrow();
        reply.changeFood(food);
        log.info("Registering new reply: " + reply);
        Long rno = replyRepository.save(reply).getRno();
        return rno;
    }

    @Override
    public ReplyDTO readOne(Long rno) {
        Optional<Reply> result = replyRepository.findById(rno);
        Reply reply = result.orElseThrow();
        ReplyDTO replyDTO = modelMapper.map(reply, ReplyDTO.class);
        return replyDTO;
    }

    @Override
    public void update(ReplyDTO replyDTO) { //ReplyDTO 에는 수정하려는 댓글의 정보가 들어가있음<화면전달>
        // 해당 댓글의 게시글 번호가 들어가있음.
        Optional<Reply> result = replyRepository.findById(replyDTO.getRno()); // db에있는 정보를 가져온것
        Reply reply = result.orElseThrow();
        reply.changeReplyText(replyDTO.getReplyText());
        replyRepository.save(reply);
    }

    @Override
    public void delete(Long rno) {
        replyRepository.deleteById(rno);
    }
}
