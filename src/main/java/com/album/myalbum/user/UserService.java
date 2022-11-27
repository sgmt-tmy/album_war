package com.album.myalbum.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "User with id '" + id + "' does not exists"
                ));
                
        return user;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

}
