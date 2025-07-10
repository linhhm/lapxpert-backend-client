package com.example.lapxpertbe.Service;

import com.example.lapxpertbe.DTO.ChiTietSanPhamDto;
import com.example.lapxpertbe.Enity.DanhMuc;
import com.example.lapxpertbe.Enity.SanPham;
import com.example.lapxpertbe.Enity.SanPhamChiTiet;
import com.example.lapxpertbe.Mapper.ChiTietSanPhamMapper;
import com.example.lapxpertbe.Repository.DanhMucRepository;
import com.example.lapxpertbe.Repository.SanPhamChiTietRepository;
import com.example.lapxpertbe.Repository.SanPhameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChiTietSanPhamService {
    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;
    @Autowired
    private SanPhameRepository sanPhamRepository;


    @Autowired
    private ChiTietSanPhamMapper chiTietSanPhamMapper;

    @Autowired
    private DanhMucRepository danhMucRepository;

    public List<ChiTietSanPhamDto> getBySanPhamId(Long sanPhamId) {
        List<SanPhamChiTiet> list = sanPhamChiTietRepository.findBySanPham_Id(sanPhamId);
        return list.stream()
                .map(ChiTietSanPhamDto::fromEntity)
                .toList();
    }
    public List<ChiTietSanPhamDto> getAllChiTietSanPhams() {
        System.out.println("Trong ChiTietSanPhamService - Đang lấy tất cả sản phẩm chi tiết.");
        List<SanPhamChiTiet> chiTietSanPhams = sanPhamChiTietRepository.findAll();
        if (chiTietSanPhams.isEmpty()) {
            System.out.println("Không tìm thấy bất kỳ sản phẩm chi tiết nào.");
        } else {
            System.out.println("Tìm thấy " + chiTietSanPhams.size() + " tổng số sản phẩm chi tiết.");
        }

        return chiTietSanPhamMapper.toDtoList(chiTietSanPhams);
    }



    public List<ChiTietSanPhamDto> getBySanPhamId1(Long sanPhamId) {
        System.out.println("Trong ChiTietSanPhamService - Đang tìm chi tiết sản phẩm theo SanPham ID: " + sanPhamId);
        List<SanPhamChiTiet> chiTietSanPhams = sanPhamChiTietRepository.findBySanPham_Id(sanPhamId);
        if (chiTietSanPhams.isEmpty()) {
            System.out.println("Không tìm thấy chi tiết sản phẩm nào cho SanPham ID: " + sanPhamId);
        } else {
            System.out.println("Tìm thấy " + chiTietSanPhams.size() + " chi tiết sản phẩm cho SanPham ID: " + sanPhamId);
        }
        return chiTietSanPhamMapper.toDtoList(chiTietSanPhams);
    }

    public List<ChiTietSanPhamDto> getByDanhMucId(Long danhMucId) {
        System.out.println("Trong ChiTietSanPhamService - Đang tìm sản phẩm (chi tiết) theo danh mục ID: " + danhMucId);

        List<SanPham> sanPhams = sanPhamRepository.findByDanhMucs_Id(danhMucId);


        List<SanPhamChiTiet> chiTietSanPhams = sanPhams.stream()
                .flatMap(sanPham -> sanPham.getChiTietSanPhams().stream())
                .collect(Collectors.toList());

        if (chiTietSanPhams.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm chi tiết nào cho danh mục ID: " + danhMucId);
        } else {
            System.out.println("Tìm thấy " + chiTietSanPhams.size() + " sản phẩm chi tiết cho danh mục ID: " + danhMucId);
        }

        return chiTietSanPhamMapper.toDtoList(chiTietSanPhams);
    }


    public List<DanhMuc> getAllDanhMucs() {
        System.out.println("Trong ChiTietSanPhamService - Đang lấy tất cả danh mục.");
        List<DanhMuc> danhMucs = danhMucRepository.findAll();
        System.out.println("Tìm thấy " + danhMucs.size() + " danh mục.");
        return danhMucs;
    }
}
