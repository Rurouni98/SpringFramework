package com.bit.springboard.service;

import com.bit.springboard.dto.MemberDto;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MemberServiceRun {
    public static void main(String[] args) {
        AbstractApplicationContext factory =
                new GenericXmlApplicationContext("root-context.xml");

        MemberService memberService = factory.getBean("memberServiceImpl", MemberService.class);

        MemberDto memberDto = new MemberDto();

        memberDto.setUsername("bitcamp3");
        memberDto.setPassword("dkdlxl");
        memberDto.setNickname("비트캠프6");
        memberDto.setEmail("bitcamp@bit.co.kr");
        memberDto.setTel("010-1111-1111");

        memberService.join(memberDto);

        memberService.getMembers().forEach(member -> {
            System.out.println(member);
        });

        MemberDto memberDto2 = new MemberDto();
        memberDto2.setUsername("bitcamp");

        System.out.println(memberService.getMemberByUsername(memberDto2));

        factory.close();
    }
}
