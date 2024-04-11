package nongsansach.service;

import nongsansach.Entity.RolesEntity;
import nongsansach.repository.RoleRepository;
import nongsansach.service.Imp.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements RoleServiceImp {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public RolesEntity find_by_name(String name) {
        return roleRepository.findByName(name);

    }
}
