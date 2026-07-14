package com.sudabKardex.ms_kardex.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudabKardex.ms_kardex.Model.Kardex;


@Repository
public interface KardexRepository extends JpaRepository<Kardex, Long> {

    Optional<Kardex> findByProducto_IdProducto(Long idProducto);

    boolean existsByProductoIdProducto(Long idProducto);

}
