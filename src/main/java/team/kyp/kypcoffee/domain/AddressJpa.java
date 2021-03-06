package team.kyp.kypcoffee.domain;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class AddressJpa {

    @Column(name = "memberaddress")
    private String memberAddress;

    public AddressJpa(String memberAddress) {
        this.memberAddress = memberAddress;
    }
}
