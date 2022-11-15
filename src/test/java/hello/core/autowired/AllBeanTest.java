package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        
        // fixme - AutoAppConfig 와 DiscountService 를 스프링 빈으로 등록
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);

        // fixme - member
        Member member = new Member(1L, "userA", Grade.VIP);

        // fixme - fixDiscountPolicy 를 넣어준 경우 - 빈 이름을 fix 로 바꾸면 조금 더 단순해질 수도 있다.
        int fixdiscountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(fixdiscountPrice).isEqualTo(1000);

        // fixme - rateDiscountPolicy 를 넣어준 경우 - 빈 이름을 rate 로 바꾸면 조금 더 단순해질 수도 있다.
        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");

        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    // fixme - 생성자가 하나이기 때문에 @Autowired 생략해도됨
    // fixme - 기존 OrderService 를 손대면 너무 복잡해지니깐 DiscountService 를 새로 만들겠음.
    static class DiscountService {

        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            return discountPolicy.discount(member, price);
        }
    }
}
