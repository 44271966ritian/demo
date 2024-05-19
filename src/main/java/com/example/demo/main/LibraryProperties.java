package com.example.demo.main;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Data
@Component
@ConfigurationProperties(prefix = "library")
@Validated
public class LibraryProperties {
    @NotBlank(message = "地址不能为空")
    private String location;
    @NotEmpty
    private List<Book> books;

    @Email(message = "邮箱格式不正确")
    @NotEmpty
    private String email;

    @Setter
    @Getter
    @ToString
    static class Book {
        String name;
        String description;
    }

}
