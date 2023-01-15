package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(
       /* basePackages = "hello.core", //hello에 core아래부터 확인한다.
        basePackageClasses = AutoAppConfig.class, // */
        //위 두개를 지정하지 않을시 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
        //프로젝트 최상단에 만드는 방법을 권장한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
