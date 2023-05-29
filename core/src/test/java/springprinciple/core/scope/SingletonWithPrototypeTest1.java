package springprinciple.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

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
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;
        // Provider 는 실제 요청이 왔을 때, 새로운 프로토타입 빈을 생성하는 기능을 제공한다.
        // Provider 를 사용하면 프로토타입 빈의 생성 시점을 원하는 시점으로 조절할 수 있다.
        // ObjectProvider 는 지금 당장 필요한 의존관계를 대신 주입받는 방법이다.
        // 지금 당장은 필요 없고, 과거에 주입 받을 때만 필요한 경우, @Autowired 대신 사용할 수 있다.
        // getObject() 를 호출하는 시점에서야 빈을 생성하고, 의존관계 주입도 처리한다.
        // 지금까지 우리가 학습한 대부분의 기능은 getObject() 를 호출하는 시점에 이미 대부분 처리가 완료되어 있다.
        // 그래서 대부분의 경우 의존관계 주입 시점에 싱글톤 빈이 주입된다.
        // Provider 를 사용하면, DL 의존관계 조회를 위해 getBean() 을 호출하는 시점에 프로토타입 빈을 생성한다.
        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
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
