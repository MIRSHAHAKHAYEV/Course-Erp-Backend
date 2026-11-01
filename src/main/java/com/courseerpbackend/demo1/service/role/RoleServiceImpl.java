package com.courseerpbackend.demo1.service.role;

import com.courseerpbackend.demo1.entity.role.Role;
import com.courseerpbackend.demo1.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements IRoleService{
    private final static String OWNER = "OWNER";
    private final RoleRepository roleRepository;

    @Override
    public Role getDefaultRole() {
        return roleRepository.findByName(OWNER);
    }
}
