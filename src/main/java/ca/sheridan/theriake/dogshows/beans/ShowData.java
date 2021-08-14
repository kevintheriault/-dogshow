package ca.sheridan.theriake.dogshows.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//SHOW LIST DATA BEAN
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShowData {

    private String breedType;
    private Long numDogs;
    private String breed;
    private Long numMales;
    private Long numFem;
    private Long numSpec;
    private Long numClass;
}
