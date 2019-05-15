package models.guest;

import lombok.Data;

@Data
public class GuestTokenRequest {

    private String grant_type;

    private String scope;

    public GuestTokenRequest(String grant_type, String scope) {
        this.grant_type = grant_type;
        this.scope = scope;
    }
}
