package com.example.lapxpertbe.Repository;

import com.example.lapxpertbe.Enity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMuc, Long> {
}