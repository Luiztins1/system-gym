package com.github.Luiztins1.validator;

import com.github.Luiztins1.exceptions.DuplicateException;
import com.github.Luiztins1.exceptions.NotFoundException;
import com.github.Luiztins1.model.entity.Student;
import com.github.Luiztins1.model.entity.UserAuth;
import com.github.Luiztins1.repository.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserAuthValidator {

    private final UserAuthRepository userAuthRepository;

    public UserAuth validateSource(UUID id){
        return userAuthRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
    }

    public void validateStudentDuplicate(UserAuth userAuth){
        if(duplicateStudent(userAuth)) throw new DuplicateException("Usuário já cadastrado no sistema.");
    }

    public UserAuth validateFindByLogin(String login){
        var user = userAuthRepository.findByLogin(login);
        validateSource(user.getId());
        return user;
    }

    private boolean duplicateStudent(UserAuth userAuth){
        return userAuthRepository.existsByLogin(userAuth.getLogin());
    }
}
