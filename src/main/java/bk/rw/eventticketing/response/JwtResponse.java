package bk.rw.eventticketing.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private Long id;
    private String email;
    private String token;
    private String type = "Bearer";

    public JwtResponse(Long id, String email, String token) {
        this.id = id;
        this.email = email;
        this.token = token;
    }
}
