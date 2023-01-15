package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StateFullServiceTest {

    @Test
    void stateFullServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFullService stateFullService1 = ac.getBean(StateFullService.class);
        StateFullService stateFullService2 = ac.getBean(StateFullService.class);

        //ThreadA : A사용자 10000원 주문
//        stateFullService1.order("userA", 10000);
        List userA = stateFullService1.order("userA", 10000);

        //ThreadB : B사용자 10000원 주문
//        stateFullService2.order("userB", 20000);
        List userB = stateFullService2.order("userB", 20000);

        //ThreadA: A사용자가 주문 금액 조회
//        int price = stateFullService1.getPrice();

//        System.out.println("price = " + price);
        System.out.println("userA = " + userA);
        System.out.println("userB = " + userB);

        //10000원을 원했지만 싱글톤 패턴으로 20000원을 출력
//        Assertions.assertThat(stateFullService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig{

        @Bean
        public StateFullService stateFullService() {
            return new StateFullService();
        }
    }

}