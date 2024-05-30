package com.example.APIProyectoBDII.Persistence.Impl;

import com.example.APIProyectoBDII.Repository.IAdministrador;

    import com.example.APIProyectoBDII.Persistence.IAdministradorDAO;
    import lombok.RequiredArgsConstructor;
    import org.apache.catalina.User;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Component;

    @Component
    @RequiredArgsConstructor
    public class AdministradorDAOImpl implements IAdministradorDAO {

        private final IAdministrador administradorRepository;

    @Override
    public int checkExistence(Integer ci) {
        return administradorRepository.checkExistence(ci);
    }

}
