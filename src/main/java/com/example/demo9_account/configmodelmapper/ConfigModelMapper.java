package com.example.demo9_account.configmodelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

// đánh dấu class dưới là 1 special component
// khi spring chạy lên, sẽ quét để tạo các bean để sử dụng
@RestController
public class ConfigModelMapper {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
