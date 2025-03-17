package model.site.stellar.burgers.client.clientModel;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Value;

@JsonIgnoreProperties
@Value
@AllArgsConstructor
public class Credentials {
    private String email;
    private String password;
}
