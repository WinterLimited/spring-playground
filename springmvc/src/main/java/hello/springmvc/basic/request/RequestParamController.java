package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(HttpServletResponse response,
                               @RequestParam("username") String memberName,
                               @RequestParam("age") int memberAge) throws IOException {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                               @RequestParam int age) throws IOException {
        // @RequestParam 의 이름과 변수 이름이 같으면 생략 가능

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) throws IOException {
        // @RequestParam도 생략 가능
        // String, int, Integer 같은 단순 타입이면 @RequestParam 도 생략 가능
        // @RequestParam마저 빼버리면 직관적이지 않으니까 생략하지 말자

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age) throws IOException {
        // required = true 면 반드시 파라미터가 있어야 한다. 없으면 400 오류
        // required = false 면 없어도 된다. 기본값이다.
        // Integer는 null이 들어갈 수 있어서 Integer로 선언해야 한다.

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "-1") Integer age) throws IOException {
        // required = true는 기본설정이라 생략 가능
        // defaultValue를 설정해주면 값이 없을 때 기본값을 설정할 수 있다.
        // defaultValue는 빈 문자도 허용한다. (빈 문자는 null이 아니다.)

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam java.util.Map<String, Object> paramMap) throws IOException {
        // Map으로 한번에 받을 수 있다.
        // Map의 key는 파라미터 이름으로 자동으로 매핑된다.
        // Map의 value는 String[] 이다.

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    // @ModelAttribute
    // 자료형이 안맞으면 바인딩 오류가 발생한다.
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) throws IOException {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData); // toString()이 자동으로 호출됨
        return "ok";
    }

    // @ModelAttribute 생략 가능
    // 생략하면 String, int 같은 단순 타입은 @RequestParam으로, 나머지는 @ModelAttribute로 처리한다.
    // 따라서 이 코드는 @RequestParam으로 인식해서 동작한다.
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) throws IOException {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData); // toString()이 자동으로 호출됨
        return "ok";
    }

}
