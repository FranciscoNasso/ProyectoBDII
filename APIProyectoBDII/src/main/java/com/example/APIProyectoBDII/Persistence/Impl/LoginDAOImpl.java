package com.example.APIProyectoBDII.Persistence.Impl;

import com.example.APIProyectoBDII.Entities.Login;
import com.example.APIProyectoBDII.Persistence.ILoginDAO;
import com.example.APIProyectoBDII.Repository.ILogin;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoginDAOImpl implements ILoginDAO {

    private final ILogin loginRepository;

    @Override
    public String getContrasenia(Integer ci) {
        return loginRepository.getContrasenia(ci);
    }

    @Override
    public Optional<Login> findById(Integer ci) {
       return loginRepository.getloginById(ci);
    }

    @Override
    public List<Login> findAll() {
        return (List<Login>) loginRepository.findAlllogin();
    }

}
