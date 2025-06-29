package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoForm {
    @NotBlank(message = "タイトルは必須です")
    private String title;

    private String description;

    // ここを primitive boolean → Boolean に変更する
    private Boolean status;

    private Long id;
}
