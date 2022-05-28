package team.kyp.kypcoffee.domain;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Embeddable
public class MemberInfoJpa {

    @Column(name = "membertype")
    private int memberType;

    public MemberInfoJpa(int memberType) {
        this.memberType = memberType;
    }
}