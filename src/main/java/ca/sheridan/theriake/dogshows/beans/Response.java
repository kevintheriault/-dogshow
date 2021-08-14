package ca.sheridan.theriake.dogshows.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//RESPONSE BEAN FOR DATA RETRIEVAL
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response {
    private boolean status;
    private String message;
    private List data;

}
