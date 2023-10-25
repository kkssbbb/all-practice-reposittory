package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // 빈 자동 등록
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext //스프링 엔티티메니저 생성후 여기로 인젝션
    private  final EntityManager em;

    public void save(Member member){  //회원 저장
        em.persist(member);
    }

    public Member findOne(Long id){  //한명 조회
        return em.find(Member.class,id);
    }

    public List<Member> findAll(){  //리스트 조회
        return em.createQuery("select m from Member m" , Member.class) //JSQL,클래스 타입
                .getResultList();

    }
    public List<Member> findByName(String name){ //특정회원 이름 조회
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
