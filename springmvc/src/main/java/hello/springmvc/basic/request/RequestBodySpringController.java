package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodySpringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 메시지 바디의 내용을 바이트 코드로 얻을 수 있다.
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        // InputStream(Reader) : HTTP 요청 메시지 바디의 내용을 직접 조회
        // OutputStream(Writer) : HTTP 응답 메시지의 바디에 직접 결과 출력
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);

        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        // HttpEntity는 HTTP header, body 정보를 편리하게 조회
        // HttpEntity는 응답에도 사용 가능
        // 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok");
    }

    @PostMapping("/request-body-string-v4")
    public HttpEntity<String> requestBodyStringV4(@RequestBody String messageBody) throws IOException {
        // @RequestBody : 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
        // HttpEntity는 응답에도 사용 가능
        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-string-v5")
    public String requestBodyStringV5(@RequestBody String messageBody) throws IOException {
        // @RequestBody : 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
        // @ResponseBody : 응답 결과를 HTTP 메시지 바디에 직접 담아서 전달
        // HttpEntity는 응답에도 사용 가능
        log.info("messageBody={}", messageBody);

        return "ok";
    }
}
