package DTO;

import java.util.List;

public record BooksResponse(List<Item> items) {
    public record Item(volumeInfo volumeInfo) {}

}
