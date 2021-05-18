package org.jh.board.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(exclude = "boardList")
@Builder
public class Member extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    private String email;
    private String password;

    private String nickname;

    @OneToMany(mappedBy = "member")
    private List<Board> boardList = new ArrayList<>();
}
