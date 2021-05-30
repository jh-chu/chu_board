package org.jh.board.service;

import org.jh.board.dto.MemberDto;

public interface MemberService {

    Long register(MemberDto dto);
    void remove(Long id);
    void modify(MemberDto dto);

    MemberDto getMember(Long id);

    //중복 체크
    boolean isExistsEmail(String email);
    boolean isExistsNickname(String nickname);

    MemberDto logIn(String id, String password);

}
