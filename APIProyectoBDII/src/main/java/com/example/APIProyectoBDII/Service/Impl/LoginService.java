package com.example.APIProyectoBDII.Service.Impl;

import com.example.APIProyectoBDII.Entities.Login;
import com.example.APIProyectoBDII.Persistence.ILoginDAO;
import com.example.APIProyectoBDII.Service.ILoginService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService implements ILoginService {

    @Autowired
    private ILoginDAO loginDAO;

    @Override
    public String getContrasenia(Integer ci) {
        return loginDAO.getContrasenia(ci);
    }

    @Override
    public List<Login> findAll() {
        return (List<Login>) loginDAO.findAll();
    }

    @Override
    public Optional<Login> findById(Integer ci) {
        return loginDAO.findById(ci);
    }

    @Override
    public boolean save(Integer ci, String hashMD5) {
        return loginDAO.save(ci, hashMD5);
    }
}
