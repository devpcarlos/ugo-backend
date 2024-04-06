package com.ugo.services;

import com.ugo.dak.AppUserDAK;
import com.ugo.k.generated.repository.RoleRepository;
import com.ugo.payloads.RegisterAppUserPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppUserService {
    
    private final AppUserDAK appUserDAK;
    private final RoleRepository roleRepository;
    
    @Transactional
    public ResponseEntity register(
        final RegisterAppUserPayload registerAppUserPayload
    ) {
        registerAppUserPayload.validate();
        
        roleRepository.assertExistsById(
            registerAppUserPayload.getRoleId(),
            HttpStatus.NOT_FOUND,
            "Rol no encontrado"
        );
        
        appUserDAK.assertNotExistsByEmail(
            registerAppUserPayload.getEmail(),
            HttpStatus.BAD_REQUEST,
            "Este email ya se encuentra registrado"
        );
        
        appUserDAK.create(registerAppUserPayload);

        return ResponseEntity.ok().build();
    }
}