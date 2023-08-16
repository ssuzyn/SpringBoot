package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatelessServiceTest {
    @Test
    void statelessServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatelessService statelessService1 = ac.getBean("statelessService", StatelessService.class);
        StatelessService statelessService2 = ac.getBean("statelessService", StatelessService.class);

        // ThreadA: 사용자 A 10000원 주문
        int userAPrice = statelessService1.order("userA", 10000);

        // ThreadB: 사용자 B 20000원 주문
        int userBPrice = statelessService2.order("userB", 20000);

        // ThreadA: 사용자 A, B 주문 금액 조회
        System.out.println("price = " + userAPrice);
        System.out.println("price = " + userBPrice);

    }
    static class TestConfig {
        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }
}
