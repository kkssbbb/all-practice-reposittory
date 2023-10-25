package com.firstwebservice.firstwebservice.web;

import com.firstwebservice.firstwebservice.service.PostsService;
import com.firstwebservice.firstwebservice.web.dto.PostsResponseDto;
import com.firstwebservice.firstwebservice.web.dto.PostsSaveRequestDto;
import com.firstwebservice.firstwebservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;

@RequiredArgsConstructor // 생성자로 Bean 객체를 주입받기위 해서 사용  ,생성자를 직접 안 쓰고 롬북 어노테이션을 사용하는이유는 해당 클래스의 의존성과 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결하기 위함
    @RestController
public class PostsApiController {


        private final PostsService postsService;

        @PostMapping("/api/v1/posts")
        public Long save(@RequestBody PostsSaveRequestDto requestDto){

            return postsService.save(requestDto);
        }


    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }


    @GetMapping("/api/v1/posts/{id}")
public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }



    @DeleteMapping("/api/v1/posts/{id}")
    public Long delet(@PathVariable Long id){
            postsService.delete(id);
            return id;
    }


}



