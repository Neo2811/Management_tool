package am.hitech.model.dto.request;

import am.hitech.model.enums.Roles;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserRequestDto {

    private int id;

    @NotBlank(message = "first name cant be null")
    private String firstName;

    @NotBlank
    private String lastName;

    @Pattern(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")
    private String email;

    @Size(min = 3, max = 15, message = "password must be 3-15")
    private String password;

    @NotBlank
    private String status;

    @NotNull
    private Roles role;
}
