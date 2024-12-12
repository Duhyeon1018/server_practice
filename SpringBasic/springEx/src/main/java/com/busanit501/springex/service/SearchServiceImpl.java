package com.busanit501.springex.service;

import com.busanit501.springex.domain.SearchVO;
import com.busanit501.springex.dto.PageRequestDTO;
import com.busanit501.springex.dto.PageResponseDTO;
import com.busanit501.springex.dto.SearchDTO;
import com.busanit501.springex.mapper.SearchMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{

    private final SearchMapper searchMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(SearchDTO searchDTO) {
        SearchVO searchVO = modelMapper.map(searchDTO, SearchVO.class);
        searchMapper.insert(searchVO);

    }

    // mapper , TodoVO 타입 요소로 목록을 받아옴.
    // 화면에 전달하는 타입, TodoDTO 타입으로 변환
    @Override
    public List<SearchDTO> getAll() {
        List<SearchDTO> list = searchMapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo,SearchDTO.class))
                .collect(Collectors.toList());
        return list;
    }



    @Override
    public SearchDTO getOne(Long tno) {
        SearchVO searchVO= searchMapper.selectOne(tno);
        SearchDTO searchDTO = modelMapper.map(searchVO,SearchDTO.class);
        return searchDTO;
    }

    @Override
    public void delete(Long tno) {
        searchMapper.delete(tno);
    }

    @Override
    public void update(SearchDTO searchDTO) {
        SearchVO searchVO = modelMapper.map(searchDTO,SearchVO.class);
        searchMapper.update(searchVO);

    }

    @Override
    public PageResponseDTO<SearchDTO> selectList(PageRequestDTO pageRequestDTO) {
        // 준비물,
        // 서버 -> 웹 , 전달한 준비물 1) 전체갯수 2) 페이징 처리 todo 목록, 3) pageRequestDTO
//        1) 전체갯수
        int total = searchMapper.getCount(pageRequestDTO);
//        2) 페이징 처리 todo 목록 , TodoVO -> TodoDTO 변환해서 전달.
        List<SearchDTO> dtoList = searchMapper.selectList(pageRequestDTO).stream()
                .map(vo -> modelMapper.map(vo,SearchDTO.class))
                .collect(Collectors.toList());
        // 준비물을 가지고 , 응답할 PageResponseDTO, 생성자 통해서 초기화 작업.
        PageResponseDTO<SearchDTO> pageResponseDTO = PageResponseDTO.<SearchDTO>withAll()
                .total(total)
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .build();
        // 기존 방법1, 빌더 패턴
//        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.builder()
//                .total(total)
//                .dtoList(dtoList)
//                .pageRequestDTO(pageRequestDTO)
//                .build();

        return pageResponseDTO;
    }



}
