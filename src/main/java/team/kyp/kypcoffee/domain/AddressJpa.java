package team.kyp.kypcoffee.domain;

import javax.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class AddressJpa {

    private String ad;

    protected AddressJpa() {

    }

    public AddressJpa(String ad) {
        this.ad = ad;
    }
}
