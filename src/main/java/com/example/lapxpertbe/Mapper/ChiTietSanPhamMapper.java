package com.example.lapxpertbe.Mapper;

import com.example.lapxpertbe.DTO.ChiTietSanPhamDto; // Đảm bảo đúng package của ChiTietSanPhamDto
import com.example.lapxpertbe.Enity.SanPhamChiTiet; // Đảm bảo đúng package của SanPhamChiTiet Entity
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
// import org.mapstruct.factory.Mappers; // Không cần thiết nếu dùng componentModel="spring"

import java.util.List;

@Mapper(componentModel = "spring") // Giúp Spring tự động inject (Autowired) Mapper này
public interface ChiTietSanPhamMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "giaBan", target = "giaBan")
    @Mapping(source = "giaKhuyenMai", target = "giaKhuyenMai")

    // Ánh xạ các thuộc tính từ các entity liên quan (Ram, MauSac, Cpu, Gpu, BoNho)
    // Đảm bảo tên trường ở đây khớp với cấu trúc của SanPhamChiTiet Entity của bạn
    // Ví dụ: SanPhamChiTiet có @ManyToOne tới Ram, MauSac, Cpu, Gpu, BoNho

    // Ram
    @Mapping(source = "ram.id", target = "ramId")
    @Mapping(source = "ram.moTaRam", target = "moTaRam")

    // MauSac
    @Mapping(source = "mauSac.id", target = "mauSacId")
    @Mapping(source = "mauSac.moTaMauSac", target = "moTaMauSac")

    // Cpu
    @Mapping(source = "cpu.id", target = "cpuId")
    @Mapping(source = "cpu.moTaCpu", target = "moTaCpu")

    // Gpu
    @Mapping(source = "gpu.id", target = "gpuId")
    @Mapping(source = "gpu.moTaGpu", target = "moTaGpu")

    // BoNho
    @Mapping(source = "boNho.id", target = "boNhoId")
    @Mapping(source = "boNho.moTaBoNho", target = "moTaBoNho")

        // Nếu bạn muốn thêm các thông tin từ SanPham (như hinhAnh, tenSanPham, moTaSanPham)
        // thì ChiTietSanPhamDto của bạn cần có các trường đó và bạn cần thêm @Mapping tương ứng.
        // Ví dụ:
        // @Mapping(source = "sanPham.hinhAnh", target = "hinhAnh")
        // @Mapping(source = "sanPham.tenSanPham", target = "tenSanPham")
        // @Mapping(source = "sanPham.moTa", target = "moTaSanPham")


    ChiTietSanPhamDto toDto(SanPhamChiTiet entity);

    // Phương thức để chuyển đổi từ danh sách Entity sang danh sách DTO
    List<ChiTietSanPhamDto> toDtoList(List<SanPhamChiTiet> entities);

    // Nếu bạn không dùng MapStruct để chuyển từ DTO sang Entity, không cần phương thức này.
    // SanPhamChiTiet toEntity(ChiTietSanPhamDto dto);
}