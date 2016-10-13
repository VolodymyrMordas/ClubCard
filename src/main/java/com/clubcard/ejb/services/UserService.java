package com.clubcard.ejb.services;

import com.clubcard.ejb.repository.UserRepository;
import com.clubcard.entities.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by volodymyrmordas on 9/17/15.
 */
@Stateless
public class UserService {

    @EJB
    UserRepository userRepository;

    public User find(Long id){
        return userRepository.find(id);
    }

//    @PersistenceContext
//    private EntityManager entityManager;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User find(String socnetId, short socnetType){
        return userRepository
                .find(socnetId, socnetType);
    }

    public User persist(User user){
        return userRepository.persist(user);
    }

    public User merge(User user){
        return userRepository.merge(user);
    }

    public User findByAuthKey(String authKey){
        return userRepository
                .findByAuthKey(authKey);
    }
}
