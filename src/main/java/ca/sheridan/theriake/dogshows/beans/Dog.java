package ca.sheridan.theriake.dogshows.beans;

import ca.sheridan.theriake.dogshows.repositories.SecurityRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dog {

    private Long dogId;
    private String dogName;
    private String ownerName;
    private String breed;
    private String gender;
    private String pathway;

    public Dog(String ownername){
        this.ownerName = ownername;
    }
}
