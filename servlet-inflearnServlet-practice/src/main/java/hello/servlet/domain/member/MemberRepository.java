package hello.servlet.domain.member;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//참고 동시성 문제가 고려되지않았다, 실무에서는 ConcurrentHasMap, AtomicLong 사용을 고려 해야한다.
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    //싱글톤이기/떄문에 기본생성자 Private
    private MemberRepository(){
    }

    //저장
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    //단일 찾기
    public Member findById(Long id){
        return store.get(id);
    }

    //전부 찾기
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();;
    }

}
