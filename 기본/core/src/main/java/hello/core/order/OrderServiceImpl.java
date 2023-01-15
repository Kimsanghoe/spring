package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // lombok라이브러리를 이용해 생성자를 자동으로 만들어준다.
public class OrderServiceImpl implements OrderService{

    //저장소
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    //할인 정책
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); DIP, OCP 위반
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

   /* @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    //lombok 라이브러리를 이용해 생성자 자동으로 생성성
    /* @Autowired  //이와 같이 생성자가 하나만 있는경우에는 @Autowired를 생략해도 된다.
    // 같은 타입 2개 이상으로 빈이 자동 주입이 안될경우 @Qulifier를 통해 해결 가능
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
