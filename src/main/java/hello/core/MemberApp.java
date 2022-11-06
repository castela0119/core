package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        
        // fixme - 내가 생성한 member 의 Name 과
        System.out.println("member.getName() = " + member.getName());
        
        // fixme - 내가 생성한 member 를 join 한후 memberService 로 find 한 member 의 name 을 비교
        System.out.println("findMember = " + findMember.getName());
    }
}
