package modeles;

import lombok.Data;

import java.util.List;

@Data
public class AddBookRequestModel {
    private String userId;
    private List<IsbnModel> collectionOfIsbns;
}
