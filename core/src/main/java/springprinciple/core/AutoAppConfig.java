package springprinciple.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import springprinciple.core.member.MemberRepository;
import springprinciple.core.member.MemoryMemberRepository;

@Configuration
@ComponentScan(

        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // 수동 등록 AppConfig를 제외
)
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository") 스프링빈 수동주입
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
