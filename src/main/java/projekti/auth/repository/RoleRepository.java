package projekti.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projekti.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
