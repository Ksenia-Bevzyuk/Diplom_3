package model.Stellar_Burgers.client.clientModel;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Value;

@JsonIgnoreProperties
@Value
@AllArgsConstructor
public class User {
    private String name;
    private String email;
    private String password;
}