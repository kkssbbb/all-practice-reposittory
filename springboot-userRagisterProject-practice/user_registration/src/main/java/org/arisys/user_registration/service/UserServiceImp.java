package org.arisys.user_registration.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.arisys.user_registration.dto.PageRequestDTO;
import org.arisys.user_registration.dto.PageResultDTO;
import org.arisys.user_registration.dto.UserDTO;

import org.arisys.user_registration.entity.QUsersEntity;
import org.arisys.user_registration.entity.UsersEntity;
import org.arisys.user_registration.repository.UserlistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동주입
public class UserServiceImp implements UserService {

    private final UserlistRepository userlistRepository;

    //@RequiredArgsConstructor 은 final이나 @NotNull 이붙은  필드의 생성자를
    // 자동으로 의존성 주입을 해주기 떄문에 final로 선언
    @Override
    public Long register(UserDTO userDTO) {

        log.info("========register() 호출 로그========");

        UsersEntity usersEntity = dtoToEntity(userDTO);

        log.info("///==== usersEntity =====///" + usersEntity);

        userlistRepository.save(usersEntity);

        return userDTO.getUserId();
    }

    //목록처리
    @Override
    public PageResultDTO<UserDTO, UsersEntity> getList(PageRequestDTO pageRequestDTO) {
        log.info("===============UserServiceImp- getList() 호출로그==============");

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("userId").descending());
        log.info("pageable : " + pageable);

        //검색 조건 처리, QueryDSL 사용
        BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);

        Page<UsersEntity> result = userlistRepository.findAll(booleanBuilder, pageable);
/*  QueryDSL 사용전
        Page<UsersEntity> result = userlistRepository.findAll(pageable);
        log.info("result : " + result);
*/

        Function<UsersEntity, UserDTO> fn = (entity -> entityToDTO(entity));
        log.info("fn : " + fn);

        return new PageResultDTO<>(result, fn);
    }

    //수정

    @Override
    public void modify(UserDTO userDTO) {

    }

    //삭제
    @Override
    public void remove(Long userId) {
        userlistRepository.deleteById(userId);
    }

    //업데이트
    @Override
    public void updateUser(Long userId, UserDTO userDTO) {
        UsersEntity updateUserEntity = userlistRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        updateUserEntity.updateUserE(userDTO);
        log.info("updateUserEntity : " + updateUserEntity);
        userlistRepository.save(updateUserEntity);

    }

    //검색 메서드 쿼리 dsl 처리
    private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {

        String type = pageRequestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QUsersEntity qUsersEntity = QUsersEntity.usersEntity;

        String keyword = pageRequestDTO.getKeyword();

        BooleanExpression booleanExpression = qUsersEntity.userId.gt(0L);
        //gt ==  > ,it ==  <
        //userId > 0 조건만 생성

        booleanBuilder.and(booleanExpression);

        //검색 조건 없는 경우 조건문
        if (type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }


        //검색 조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if (type.contains("n")) {
            conditionBuilder.or(qUsersEntity.name.contains(keyword)
            );
        }

        if (type.contains("g")) {
            conditionBuilder.or(qUsersEntity.gender.contains(keyword));
        }

        if (type.contains("j")) {
            conditionBuilder.or(qUsersEntity.job.contains(keyword));
        }

        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);


        return booleanBuilder;

    }


    //유저 리스트 엑셀 다운로드
    @Override
    public void dbToExcel() {

        String filePath = "C:\\dev\\spring\\dbtoexcel";
        String fileNm = "db_to_excel_test.xlsx";

        // 빈 Workbook 생성
        XSSFWorkbook workbook = new XSSFWorkbook();

        // 빈 Sheet를 생성
        XSSFSheet sheet = workbook.createSheet("employee data");

        // 데이터베이스에서 사용자 리스트 조회
        List<UsersEntity> userList = userlistRepository.findAll(Sort.by(Sort.Direction.DESC, "userId"));

        // Sheet에 데이터 채우기
        int rownum = 0;
        Row headerRow = sheet.createRow(rownum++);
        headerRow.createCell(0).setCellValue("user_id");
        headerRow.createCell(1).setCellValue("name");
        headerRow.createCell(2).setCellValue("gender");
        headerRow.createCell(3).setCellValue("job");
        headerRow.createCell(4).setCellValue("age");
        headerRow.createCell(5).setCellValue("modDate");
        headerRow.createCell(6).setCellValue("regDate");

        for (UsersEntity user : userList) {
            Row dataRow = sheet.createRow(rownum++);
            dataRow.createCell(0).setCellValue(user.getUserId());
            dataRow.createCell(1).setCellValue(user.getName());
            dataRow.createCell(2).setCellValue(user.getGender());
            dataRow.createCell(3).setCellValue(user.getJob());
            dataRow.createCell(4).setCellValue(user.getAge());
            dataRow.createCell(5).setCellValue(user.getModDate());
            dataRow.createCell(6).setCellValue(user.getRegDate());
        }

        try {
            FileOutputStream out = new FileOutputStream(new File(filePath, fileNm));
            workbook.write(out);
            out.close();
            log.info("Excel 파일 생성 완료");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }

