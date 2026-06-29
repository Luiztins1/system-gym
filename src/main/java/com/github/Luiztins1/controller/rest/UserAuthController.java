package com.github.Luiztins1.controller.rest;

import com.github.Luiztins1.controller.dtos.UserAuthDTO;
import com.github.Luiztins1.model.entity.UserAuth;
import com.github.Luiztins1.model.mapper.UserAuthMapper;
import com.github.Luiztins1.service.UserAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserAuthDTO> registerUserAuth(@RequestBody @Valid UserAuthDTO userAuthDTO){
        var user =  userAuthService.registerUserAuth(userAuthDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).body(UserAuthMapper.toDto(user));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<UserAuthDTO>> findAll(){
        List<UserAuthDTO> userAuthDTOList = userAuthService.findAll()
                .stream()
                .map(UserAuthMapper::toDto)
                .toList();

        if(userAuthDTOList.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(userAuthDTOList);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserAuthDTO> updateUserAuth(@PathVariable UUID id, @RequestBody @Valid UserAuthDTO userAuthDTO){
        Optional<UserAuth> userAuthOptional = userAuthService.updateUserAuth(id, userAuthDTO);

        if(userAuthOptional.isPresent()) return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> cancelUserAuth(@PathVariable UUID id){
        userAuthService.cancelUserAuth(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserAuthDTO> findById(@PathVariable UUID id){
        return userAuthService.findByid(id)
                .map(UserAuthMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{login}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserAuthDTO> findByLogin(@PathVariable String login){
        Optional<UserAuth> user = userAuthService.findByLogin(login);

        if(user.isPresent()) return ResponseEntity.ok().build();

        return ResponseEntity.noContent().build();
    }
}
