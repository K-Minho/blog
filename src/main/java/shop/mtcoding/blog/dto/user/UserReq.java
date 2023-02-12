
package shop.mtcoding.blog.dto.user;

import lombok.Getter;
import lombok.Setter;

public class UserReq {

    @Setter
    @Getter
    public static class LoginReqDto {
        private String username;
        private String password;
    }

}