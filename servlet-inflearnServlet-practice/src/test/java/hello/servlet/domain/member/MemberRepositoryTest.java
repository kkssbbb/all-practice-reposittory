package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

        MemberRepository memberRepository = MemberRepository.getInstance();

        //테스트 초기화 메서드
        @AfterEach
        void afterEach(){
            memberRepository.clearStore();
        }

        @Test
        public void save(){
            //g
            Member member = new Member("hi",20);
            //w
            Member saveMember1 = memberRepository.save(member);

            //t
            Member findMember = memberRepository.findById(saveMember1.getId());
            Assertions.assertThat(saveMember1).isEqualTo(findMember);
        }

}