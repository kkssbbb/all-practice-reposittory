package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest{
    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;


    @Test
    public void 회원가입테스트() throws Exception{
        //g
        Member m = new Member();
        m.setName("승빈");
        //w
        Long mId = memberService.join(m);

        //t
        Assert.assertEquals(m,memberRepository.findOne(mId));
    }

    @Test(expected = IllegalStateException.class)
    public void 회원중복테스트(){
        //g
        Member m = new Member();
        m.setName("홍길동");
        Member m1 = new Member();
        m1.setName("홍길동");
        //v
        Long member_ID = memberService.join(m);
        Long member1_ID = memberService.join(m1);

        //t
        Assert.fail("예외가 발생해야 한다.");

    }


}