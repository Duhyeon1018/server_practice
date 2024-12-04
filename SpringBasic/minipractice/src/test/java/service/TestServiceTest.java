package service;

import com.busanit501.springex.dto.TestDTO;
import com.busanit501.springex.service.TestService;
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
public class TestServiceTest {

    // 방법1
    @Autowired
    private TestService testService;

    //방법2
//    private final TodoService todoService;



    @Test
    public void testGetAll() {
        List<TestDTO> list = testService.getAll();
        for (TestDTO testDTO:list) {
            log.info("testDTO : " + testDTO);
        }
    } //

    @Test
    public void testGetOne() {
        TestDTO testDTO = testService.getOne(3L);
        log.info("testDTO : " + testDTO);

    } //

    @Test
    public void testDelete() {
        testService.delete(1L);
    } //



}//
