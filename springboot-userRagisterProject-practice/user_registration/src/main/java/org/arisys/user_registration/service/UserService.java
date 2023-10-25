package org.arisys.user_registration.service;

import org.arisys.user_registration.dto.PageRequestDTO;
import org.arisys.user_registration.dto.PageResultDTO;
import org.arisys.user_registration.dto.UserDTO;
import org.arisys.user_registration.entity.UsersEntity;

public interface UserService {

    Long register(UserDTO userDTO);

    PageResultDTO<UserDTO, UsersEntity> getList(PageRequestDTO pageRequestDTO);

    //DTO To Entity 메서드
    default UsersEntity dtoToEntity(UserDTO userDTO) {
        UsersEntity usersEntity = UsersEntity.builder()
                .userId(userDTO.getUserId())
                .name(userDTO.getName())
                .age(userDTO.getAge())
                .gender(userDTO.getGender())
                .job(userDTO.getJob())
                .build();
        return usersEntity;
    }

    //entity to dto method

    default UserDTO entityToDTO(UsersEntity usersEntity) {
        UserDTO userDTO = UserDTO.builder()
                .userId(usersEntity.getUserId())
                .name(usersEntity.getName())
                .age(usersEntity.getAge())
                .gender(usersEntity.getGender())
                .job(usersEntity.getJob())
                .build();

        return userDTO;



    }
    //삭제
    void remove(Long userId);

    //수정
    void modify(UserDTO userDTO);

    //업데이트
    void updateUser(Long userId, UserDTO userDTO);

    //엑셀 다운로드
    void dbToExcel();

}
