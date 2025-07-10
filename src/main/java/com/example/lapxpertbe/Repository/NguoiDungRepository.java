package com.example.lapxpertbe.Repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.lapxpertbe.Enity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
    Optional<NguoiDung> findByEmail(String email);

    Optional<NguoiDung> findByEmailOrSoDienThoai(String email, String soDienThoai);

}