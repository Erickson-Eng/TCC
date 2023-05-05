package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Membership;
import br.com.quatty.backend.business.entity.pk.MembershipPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership, MembershipPK> {

    @Query("SELECT m from Membership m join m.athlete a join a.user u where u.username = :username")
    List<Membership> findAllMembershipByUsername(@Param("username") String username);
}