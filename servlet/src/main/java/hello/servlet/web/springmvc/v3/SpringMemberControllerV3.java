package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form")
    public String newForm() {
        // ViewName을 반환하는 것이 아니라, 실제 뷰로 바로 이동한다.
        return "new-form";
    }

    @PostMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {
        // 요청 파라미터의 이름이 변수 이름과 같으면 @RequestParam(name="username") String membername 처럼 생략할 수 있다.
        Member member = new Member(username, age);
        memberRepository.save(member);

        // Model에 데이터를 보관한다.
        model.addAttribute("member", member);
        return "save-result";
    }

    @GetMapping
    public String members(Model model) {
        System.out.println(memberRepository.findAll());
        model.addAttribute("members", memberRepository.findAll());
        return "members";
    }

}
