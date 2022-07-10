package com.library.book.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddBookDto {
    @Size(max = 100)
    @NotNull
    public String title;
    @Size(max = 100)
    @NotNull
    public String author;
    @Size(max = 1000)
    @NotNull
    public String description;
    @Size(max = 1000)
    @NotNull
    public String imageUrl;
}
