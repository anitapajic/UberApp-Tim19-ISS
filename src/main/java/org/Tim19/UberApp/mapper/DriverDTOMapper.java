package org.Tim19.UberApp.mapper;

import org.Tim19.UberApp.dto.DriverDTO;
import org.Tim19.UberApp.model.Driver;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DriverDTOMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public DriverDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Driver fromDTOtoDriver(DriverDTO dto) {
        return modelMapper.map(dto, Driver.class);
    }

    public static DriverDTO fromDrivertoDTO(Driver dto) {
        return modelMapper.map(dto, DriverDTO.class);
    }

}
