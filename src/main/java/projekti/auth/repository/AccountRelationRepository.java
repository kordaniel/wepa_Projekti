package projekti.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projekti.auth.model.Account;
import projekti.auth.model.AccountRelation;

public interface AccountRelationRepository extends JpaRepository<AccountRelation, Long> {
    AccountRelation findByFollowerAndFollowing(Account follower, Account following);
}
