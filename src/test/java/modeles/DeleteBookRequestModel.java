package modeles;

import lombok.Data;

@Data
public class DeleteBookRequestModel {
    private String isbn;
    private String userId;
}
