package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // fixme - ThreadA : A 사용자가 10,000 원 주문
        int userAPrice = statefulService1.order("userA", 10000);

        // fixme - ThreadB : B 사용자가 20,000 원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // fixme - ThreadA : 그리고 A 사용자가 주문 금액을 조회하면 얼마가 나올까?
        // int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

        // Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    // fixme - statefulService 전용으로 Config 하나를 만들게요.
    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}