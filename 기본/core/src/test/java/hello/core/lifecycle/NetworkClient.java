package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.util.List;

public class NetworkClient /*implements InitializingBean, DisposableBean*/ {

    private String url;

    public NetworkClient() {
        System.out.println("생성자를 호출, url = " + url);
       /* connect();
        call("초기화 연결 메시지");*/
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + "message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }






    /* @Override
     public void afterPropertiesSet() throws Exception {
         System.out.println("NetworkClient.afterPropertiesSet");
         connect();
         call("초기화 연결 메시지");
     }

     @Override
     public void destroy() throws Exception {
         System.out.println("NetworkClient.destroy");
         disconnect();
     }*/

    @PostConstruct //@PostConstruct와 @PreDestory가 가장 많이 쓰이며 스프링에서 추천하는 방법이다.
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
