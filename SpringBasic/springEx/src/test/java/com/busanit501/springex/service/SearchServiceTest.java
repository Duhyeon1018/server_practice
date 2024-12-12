package com.busanit501.springex.service;

import com.busanit501.springex.dto.PageRequestDTO;
import com.busanit501.springex.dto.PageResponseDTO;
import com.busanit501.springex.dto.SearchDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class) //JUnit5 테스트 설정.
//JUnit4 테스트 설정. @Runwith
// 설정 파일의 경로를 지정.
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
//방법2
//@RequiredArgsConstructor
public class SearchServiceTest {

    // 방법1
    @Autowired
    private SearchService searchService;

    //방법2
//    private final SearchService searchService;

    @Test
    public void testRegister() {
        SearchDTO searchDTO = SearchDTO.builder()
                .title("디비테스트에서 입력")
                .dueDate(LocalDate.now())
                .writer("강두현")
                .build();
        searchService.register(searchDTO);
    } //

    @Test
    public void testGetAll() {
        List<SearchDTO> list = searchService.getAll();
        for (SearchDTO searchDTO : list) {
            log.info("searchDTO : " + searchDTO);
        }
    } //

    @Test
    public void testGetOne() {
        SearchDTO searchDTO = searchService.getOne(9L);
        log.info("searchDTO : " + searchDTO);

    } //

    @Test
    public void testDelete() {
        searchService.delete(8182L);
    } //

    @Test
    public void testUpdate() {
        // 업데이트 할 더미 데이터 필요, SearchVO
        SearchDTO searchDTO = SearchDTO.builder()
                .tno(5L)
                .title("수정 제목")
                .dueDate(LocalDate.now())
                .finished(true)
                .build();

        searchService.update(searchDTO);
    }

    @Test
    public void testPageList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .keyword("검색찾기용")
                .types(new String[]{"t","w"})
                .from(LocalDate.of(2024,12,05))
                .to(LocalDate.of(2024,12,06))
                .finished2(true)
                .build();
        // PageResponseDTO, 안에는 , page, size, skip, start,end,
        // prev, next,  페이징된 목록 요소들
        PageResponseDTO<SearchDTO> list = searchService.selectList(pageRequestDTO);
        list.getDtoList().stream().forEach(dto -> log.info("dto : " + dto));
        log.info("list total : " + list.getTotal());
        log.info("list prev : " + list.isPrev());
        log.info("list next : " + list.isNext());
        log.info("list start : " + list.getStart());
        log.info("list end : " + list.getEnd());

    }

}//
