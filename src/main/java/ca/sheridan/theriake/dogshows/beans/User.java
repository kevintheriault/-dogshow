package ca.sheridan.theriake.dogshows.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Long userId;
    private String userName;
    private String encryptedPassword;
}
