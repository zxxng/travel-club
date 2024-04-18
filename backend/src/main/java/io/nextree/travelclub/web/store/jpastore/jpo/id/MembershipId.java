package io.nextree.travelclub.web.store.jpastore.jpo.id;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class MembershipId implements Serializable {
    private Long clubId;
    private String memberEmail;
}
