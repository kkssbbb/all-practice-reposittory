package org.springex.gestbook.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springex.gestbook.dto.GuestbookDTO;
import org.springex.gestbook.dto.PageRequestDTO;
import org.springex.gestbook.dto.PageResultDTO;
import org.springex.gestbook.entity.GuestBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class GuestbookServiceTest {

    @Autowired
    private GuestbookService guestbookService;

    @Test
    public void testRegister() {

        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("test title")
                .content("test content")
                .writer("김승빈")
                .build();
        System.out.println("guestbookDTO : "+guestbookDTO);
        System.out.println(".register(guestbookDTO) : " + guestbookService.register(guestbookDTO));
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<GuestbookDTO, GuestBook> pageResultDTO = guestbookService.getList(pageRequestDTO);

        for (GuestbookDTO guestbookDTO : pageResultDTO.getDtoList()) {
            System.out.println(guestbookDTO);
        }

    }

/*    @Test
    @DisplayName("페이징 처리 테스트욤")
    public void testList(){

        PageRequestDTO pageRequestDTO  = PageRequestDTO.builder()
                .page(1).size(10).build();


    }*/


}