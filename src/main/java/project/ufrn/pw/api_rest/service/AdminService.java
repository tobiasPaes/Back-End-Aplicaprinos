package project.ufrn.pw.api_rest.service;


import org.springframework.stereotype.Service;
import project.ufrn.pw.api_rest.domain.Admin;
import project.ufrn.pw.api_rest.repository.AdminRepository;

@Service
public class AdminService extends GenericService<Admin, AdminRepository> {
    
    public AdminService(AdminRepository repository){
        super(repository);
    }
}
