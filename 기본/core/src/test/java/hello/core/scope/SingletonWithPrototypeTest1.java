package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);


    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);


        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean1.logic();
        assertThat(count2).isEqualTo(1);


    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean {
//        private final PrototypeBean prototypeBean; //생성 시점에 주입 값을 계속 공유하게 된다. count값을

//        @Autowired ApplicationContext applicationContext;

//        @Autowired private ObjectProvider<PrototypeBean> prototypeBeanProvider;
//        @Autowired private ObjectFactory<PrototypeBean> prototypeBeanProvider; Provider가 기능이 더 추가되었다.

//        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;
        private final Provider<PrototypeBean> prototypeBeanProvider;
//        @Autowired private Provider<PrototypeBean> prototypeBeanProvider;


        public int logic() {
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class); // 로직을 실행 할때 마다 새로운 prototype을 생성하게 된다. 하지만 x
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;


        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
