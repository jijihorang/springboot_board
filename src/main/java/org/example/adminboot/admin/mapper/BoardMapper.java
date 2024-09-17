package org.example.adminboot.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.adminboot.admin.dto.BoardRegisterDTO;

public interface BoardMapper {

    int insert(BoardRegisterDTO registerDTO);

    int insertAttach(@Param("bno") Long bno,
                     @Param("fileName") String fileName,
                     @Param("ord") int ord);

}
