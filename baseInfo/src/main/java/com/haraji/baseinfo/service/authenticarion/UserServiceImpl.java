package com.haraji.baseinfo.service.authenticarion;

import com.haraji.baseinfo.constant.RoleEnum;
import com.haraji.baseinfo.database.entity.authentication.PersonEntity;
import com.haraji.baseinfo.database.repository.authentication.PersonRepository;
import com.haraji.baseinfo.database.repository.authentication.UserRepository;
import com.haraji.baseinfo.database.entity.authentication.UserEntity;
import com.haraji.baseinfo.exception.authentication.UserNotCreatedException;
import com.haraji.baseinfo.mapper.authentication.UserMapper;
import com.haraji.baseinfo.mapper.authentication.PersonMapper;
import com.haraji.baseinfo.exception.authentication.UserNotFoundException;
import com.haraji.baseinfo.model.authentication.User;
import com.haraji.baseinfo.model.authentication.Person;
import com.haraji.baseinfo.model.authentication.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Slf4j
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final UserMapper userMapper;
    private final PersonMapper personMapper;

    public UserServiceImpl(UserRepository userRepository, PersonRepository personRepository, UserMapper userMapper, PersonMapper personMapper) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.userMapper = userMapper;
        this.personMapper = personMapper;
    }

    @Override
    public List<User> getAll() {
        try {
            List<UserEntity> stateEntityList = userRepository.findAll();
            if (!stateEntityList.isEmpty())
                return userMapper.toModelList(stateEntityList);
            return null;
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for userService.getAll()", e.getMessage());
            throw new UserNotFoundException();
        }
    }

    @Override
    public User getByName(String username) {
        try {
            UserEntity userEntity = userRepository.findByUsername(username).get();
            return userMapper.toModel(userEntity);
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for userService.getAll()", e.getMessage());
            throw new UserNotFoundException();
        }
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public User createUser(UserRequest userRequest) {
        try {

            PersonEntity personEntity = personMapper.toEntity(userRequest);
            personRepository.save(personEntity);

            UserEntity userEntity = userMapper.toEntity(userRequest);
            userEntity.setPerson(personEntity);
            userEntity.setRole(RoleEnum.VIEWER);

            userRepository.save(userEntity);
            User user = userMapper.toModel(userEntity);
            Person person = personMapper.toModel(personEntity);
            user.setPerson(person);
            return user;
        } catch (Exception e) {
            log.info("\nThe exception '{}' was thrown for userService.createUser()", e.getMessage());
            throw new UserNotCreatedException();
        }
    }
}
