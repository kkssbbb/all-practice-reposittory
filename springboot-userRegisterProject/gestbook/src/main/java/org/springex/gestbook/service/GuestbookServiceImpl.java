package org.springex.gestbook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springex.gestbook.dto.GuestbookDTO;
import org.springex.gestbook.dto.PageRequestDTO;
import org.springex.gestbook.dto.PageResultDTO;
import org.springex.gestbook.entity.GuestBook;
import org.springex.gestbook.repository.GuestbookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService{

    private final GuestbookRepository guestbookRepository;


    @Override
    public Long register(GuestbookDTO guestbookDTO) {
        log.info("=====================register() 호출 로그=====================");

        GuestBook guestBookeEntity = dtoToEntity(guestbookDTO);

        log.info("guestBookeEntity : " + guestBookeEntity);

        guestbookRepository.save(guestBookeEntity);


        return guestBookeEntity.getGno();
    }

    @Override
    public PageResultDTO<GuestbookDTO, GuestBook> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable  = pageRequestDTO.getPageable(Sort.by("gno"));
        log.info("=====================getList() 호출 로그=====================");
        log.info("pageable : " + pageable);
        Page<GuestBook> result = guestbookRepository.findAll(pageable);

        Function<GuestBook, GuestbookDTO> fn = (entity -> entityToDto(entity));
        log.info("result : " + result);
        log.info("fn : " + fn);
        return new PageResultDTO<>(result, fn);

    }
}
