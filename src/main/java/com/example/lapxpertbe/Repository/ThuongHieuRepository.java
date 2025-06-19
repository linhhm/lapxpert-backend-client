package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu, Long> {
}

