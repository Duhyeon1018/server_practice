package sample.mapper;

import com.busanit501.springex.domain.TestVO;
import com.busanit501.springex.mapper.TestMapper;
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
public class TestMapperTest {

    // 해당 인스턴스가 없다면, 널로 받을게.
    // 기본값은 required = true
    @Autowired(required = false)
    private TestMapper testMapper;

    @Test
    public void testGetTime() {
        log.info("getTime : " + testMapper.getTime());
    }

    @Test
    public void testInsert(){
        // 더미데이터 만들어서 TodoVO담아서 진행
        TestVO testVO = TestVO.builder()
                .title("샘플테스트")
                .dueDate(LocalDate.now())
                .writer("강두현")
                .build();
        testMapper.insert(testVO);
    }

    @Test
    public void testSelectAll() {
        List<TestVO> lists = testMapper.selectAll();
        for (TestVO testVO:lists ) {
            log.info("testVO : " + testVO);
        }
    }

    @Test
    public void testSelectOne() {
        TestVO  testVo = testMapper.selectOne(1L);
        log.info("testVo : " + testVo);
    }

    @Test
    public void testDelete() {
        testMapper.delete(2L);

    }


}