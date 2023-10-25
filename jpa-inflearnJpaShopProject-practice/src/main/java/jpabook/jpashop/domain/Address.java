package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
//유저테이블에 주소데이터를 하나의 주소관련데이터로 만들어 사용한다는 의미이다. ->유저테이블의 아래의 데이터도 같이 생성된다.
// (사실@Embeddable 어노테이션은 생략해도 된다.)
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){}

    public Address(String city, String street, String zipcode){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
