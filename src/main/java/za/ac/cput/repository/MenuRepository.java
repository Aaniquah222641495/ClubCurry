package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {
}
