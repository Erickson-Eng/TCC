package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Membership;
import br.com.quatty.backend.business.entity.pk.MembershipPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, MembershipPK> {
}