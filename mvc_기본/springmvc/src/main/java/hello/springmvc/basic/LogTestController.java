package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
    //@Slf4j가 자동으로 입력해줌
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        //로그 레벨 순서 trace > debug > info > warn > error
        //개발서버는 debug 운영서버는 info를 주로 사용함
        //log.trace("trace log = {}" + name); +로 연결될 경우 호출되지 않는경우에도 연산을 하기 때문에 사용하지 않는다.

        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info(" info log = {}", name);
        log.warn(" warn log = {}", name);
        log.error("error log = {}", name);

        return "ok";
    }
}
