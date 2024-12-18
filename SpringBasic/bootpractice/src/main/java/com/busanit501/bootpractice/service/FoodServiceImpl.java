package com.busanit501.bootpractice.service;

import com.busanit501.bootpractice.domain.Food;
import com.busanit501.bootpractice.dto.FoodDTO;
import com.busanit501.bootpractice.dto.FoodListReplyCountDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;
import com.busanit501.bootpractice.repository.FoodRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class FoodServiceImpl implements FoodService {

    //맵퍼에게 의존 해야함.
    // 디비 작업 도구,
    private final FoodRepository foodRepository;
    // DTO <-> Entity class
    private final ModelMapper modelMapper;

    @Override
    public Long register(FoodDTO foodDTO) {
        Food food = modelMapper.map(foodDTO, Food.class);
        Long bno = foodRepository.save(food).getBno();
        return bno;
    }

    @Override
    public FoodDTO readOne(Long bno) {
        Optional<Food> result = foodRepository.findById(bno);
        Food food = result.orElseThrow();
        FoodDTO dto = modelMapper.map(food, FoodDTO.class);
        return dto;
    }

    @Override
    public void update(FoodDTO foodDTO) {
        Optional<Food> result = foodRepository.findById(foodDTO.getBno());
        Food food = result.orElseThrow();
        food.changeTitleContent(foodDTO.getTitle(), foodDTO.getContent());
        foodRepository.save(food);
    }

    @Override
    public void delete(Long bno) {
        foodRepository.deleteById(bno);
    }

    @Override
    public PageResponseDTO<FoodDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Food> result = foodRepository.searchAll(types, keyword, pageable);
        // list -> PageResponseDTO 타입으로 변경 필요.

        // result.getContent() -> 페이징된 엔티티 클래스 목록
        List<FoodDTO> dtoList = result.getContent().stream()
                .map(food -> modelMapper.map(food, FoodDTO.class))
                .collect(Collectors.toList());


        return PageResponseDTO.<FoodDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();

    } // list

    @Override
    public PageResponseDTO<FoodListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        // 수정1
        Page<FoodListReplyCountDTO> result = foodRepository.searchWithReplyCount(types,keyword,pageable);
        // list -> PageResponseDTO 타입으로 변경 필요.

        // result.getContent() -> 페이징된 엔티티 클래스 목록
        // Projection.bean 이용해서, 데이터 조회시 , 바로 dto 변환을 다했음.
        // 변환 작업이 필요가 없음.
//        List<FoodDTO> dtoList = result.getContent().stream()
//                .map(food ->modelMapper.map(food, FoodDTO.class))
//                .collect(Collectors.toList());


        return PageResponseDTO.<FoodListReplyCountDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.getContent())
                .total((int) result.getTotalElements())
                .build();
    }
}

