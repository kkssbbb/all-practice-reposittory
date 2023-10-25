package org.springex.gestbook.service;

import org.springex.gestbook.dto.GuestbookDTO;
import org.springex.gestbook.dto.PageRequestDTO;
import org.springex.gestbook.dto.PageResultDTO;
import org.springex.gestbook.entity.GuestBook;


public interface GuestbookService {

    //방명록 등록 시나리오
    Long register(GuestbookDTO guestbookDTO);

    default GuestBook dtoToEntity(GuestbookDTO guestbookDTO) {
        GuestBook guestBookEntity = GuestBook.builder()
                .gno(guestbookDTO.getGno())
                .title(guestbookDTO.getTitle())
                .content(guestbookDTO.getContent())
                .writer(guestbookDTO.getWriter())
                .build();
        return guestBookEntity;
    }

    PageResultDTO<GuestbookDTO, GuestBook> getList(PageRequestDTO pageRequestDTO);

    default GuestbookDTO entityToDto(GuestBook guestBookEntity) {

        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .gno(guestBookEntity.getGno())
                .title(guestBookEntity.getTitle())
                .content(guestBookEntity.getContent())
                .writer(guestBookEntity.getWriter())
                .modDate(guestBookEntity.getModDate())
                .regDate(guestBookEntity.getRegDate())
                .build();

        return guestbookDTO;
    }
}
