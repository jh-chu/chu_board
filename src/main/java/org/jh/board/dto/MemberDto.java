package org.jh.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jh.board.entity.Member;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto{

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    public MemberDto(Member member) {
        id = member.getId();
        email = member.getEmail();
        password = member.getPassword();
        nickname = member.getNickname();
        createDate = member.getCreateDate();
        lastModifiedDate = member.getLastModifiedDate();
    }

}
