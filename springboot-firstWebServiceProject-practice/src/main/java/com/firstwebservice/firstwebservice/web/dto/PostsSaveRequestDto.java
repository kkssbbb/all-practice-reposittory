package com.firstwebservice.firstwebservice.web.dto;


import com.firstwebservice.firstwebservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// Entity 클래스와 거의 유사한 형태임에도 추가 이유는 절대로 Entity클래스를 Request/Response 클래스로 사용해서는 안 된다.
// Request/Response 용 DTO는 View를 위한 클래스라 자주변경이 필요하다. 따라서 ViewLayer와 DBLayer는 역할을 분리하는게 좋다.
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
