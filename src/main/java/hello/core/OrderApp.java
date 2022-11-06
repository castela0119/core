package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

        // fixme - memberService 만들고, orderService 만들어주자.
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        // fixme - member 를 저장해주기 위해 memberId 를 만들어주고
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        // fixme - memberService 를 통해서 member 를 메모리 객체에 넣자. 그래야 주문해서 찾아 쓸 수 있으므로
        memberService.join(member);

        // fixme - order 생성 (itemA 라는 상품, 10000원 짜리)
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // fixme - order 를 출력해보자.
        System.out.println("order = " + order);

        // fixme - calculatePrice 를 출력해보자.
        System.out.println("order.calculatePrice() = " + order.calculatePrice());

    }
}
