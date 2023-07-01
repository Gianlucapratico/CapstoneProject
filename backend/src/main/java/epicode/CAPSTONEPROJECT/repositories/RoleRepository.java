package epicode.CAPSTONEPROJECT.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.CAPSTONEPROJECT.entities.Role;

@Repository

public interface RoleRepository extends JpaRepository<Role, UUID> {

	Optional<Role> findByNome(String nome);

}
