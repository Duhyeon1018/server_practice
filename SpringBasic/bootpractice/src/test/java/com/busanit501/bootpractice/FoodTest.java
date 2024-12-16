package com.busanit501.bootpractice;

import com.busanit501.bootpractice.dto.FoodDTO;
import com.busanit501.bootpractice.service.FoodService;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class FoodTest {
    @Autowired
    private FoodService foodService;
    @Test

    public void testRegister(){
        FoodDTO foodDTO = FoodDTO.builder()
                .title("등록해보기")
                .content("대머리")
                .writer("강두현")
                .build();

        foodService.register(foodDTO);


    }
}
