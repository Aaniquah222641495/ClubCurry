package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.GeneralStaff;

@Repository
public interface GenStaffRepo extends JpaRepository<GeneralStaff, String> {

    public GeneralStaff findByUsername(String username);
}
