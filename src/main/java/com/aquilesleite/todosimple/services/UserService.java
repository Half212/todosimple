package com.aquilesleite.todosimple.services;

import com.aquilesleite.todosimple.models.User;
import com.aquilesleite.todosimple.repositories.UserRepository;
import com.aquilesleite.todosimple.services.exceptions.DataBindingViolationException;
import com.aquilesleite.todosimple.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;



    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado ! ID: " + id + ", Tipo: " + User.class.getName()));
    }

    @Transactional
    public User create (User obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User update (User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

   public void delete(Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataBindingViolationException("não é possivel excluir,pois ha entidades relacionadas ");

       }
   }

}
