package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Admin;
import za.ac.cput.repository.AdminRepo;
import za.ac.cput.service.interfaces.IAdminService;

import java.util.List;

/**
 * Service implementation for Admin-related operations.
 */
@Service
public class AdminService implements IAdminService {
    private AdminRepo adminRepo;

    /**
     * Constructor-based dependency injection of AdminRepo.
     *
     * @param adminRepo the Admin repository instance
     */
    @Autowired
    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    /**
     * Saves an Admin object to the database.
     *
     * @param obj the Admin object to save
     * @return the saved Admin object
     */
    @Override
    public Admin save(Admin obj) {
        return adminRepo.save(obj);
    }

    /**
     * Reads an Admin object from the database by ID.
     *
     * @param s the ID of the Admin
     * @return the Admin object if found; otherwise, null
     */
    @Override
    public Admin read(String s) {
        return adminRepo.findById(s).orElse(null);
    }

    /**
     * Updates an existing Admin object in the database.
     *
     * @param obj the Admin object to update
     * @return the updated Admin object if it exists; otherwise, null
     */
    @Override
    public Admin update(Admin obj) {
        if (adminRepo.existsById(obj.getId())) {
            return adminRepo.save(obj);
        }
        return null;
    }

    /**
     * Deletes an Admin object from the database by ID.
     *
     * @param s the ID of the Admin to delete
     * @return true if the Admin was deleted; otherwise, false
     */
    @Override
    public Boolean delete(String s) {
        if (adminRepo.existsById(s)) {
            adminRepo.deleteById(s);
            return true;
        }
        return false;
    }

    /**
     * Retrieves all Admin objects from the database.
     *
     * @return a list of all Admin objects
     */
    @Override
    public List<Admin> getAll() {
        return adminRepo.findAll();
    }

    /**
     * Checks if an Admin exists by their username.
     *
     * @param username the username to check
     * @return true if an Admin with the given username exists; otherwise, false
     */
    @Override
    public Boolean findAdminByUsername(String username) {
        return adminRepo.findAdminByUsername(username) != null;
    }
}
