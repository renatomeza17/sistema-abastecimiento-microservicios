package com.sudabLogin.ms_login.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.sudabLogin.ms_login.model.RolModulo;
 
public interface RolModuloRepository extends JpaRepository<RolModulo, Integer> {
    List<RolModulo> findByRol_IdRol(Long idRol);
}
 