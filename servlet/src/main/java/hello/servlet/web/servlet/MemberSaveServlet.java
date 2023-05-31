package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w = response.getWriter();
        w.write("<html>\n");
        w.write("<head>\n");
        w.write(" <meta charset=\"UTF-8\">\n");
        w.write(" <title>Title</title>\n");
        w.write("</head>\n");
        w.write("<body>\n");
        w.write("성공\n");
        w.write("<ul>\n");
        w.write(" <li>id=" + member.getId() + "</li>\n");
        w.write(" <li>username=" + member.getUsername() + "</li>\n");
        w.write(" <li>age=" + member.getAge() + "</li>\n");
        w.write("</ul>\n");
        w.write("<a href=\"/index.html\">메인</a>\n");
        w.write("</body>\n");
        w.write("</html>\n");
    }
}
