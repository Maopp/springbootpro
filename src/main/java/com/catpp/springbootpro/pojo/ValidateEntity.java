package com.catpp.springbootpro.pojo;

import com.catpp.springbootpro.annotation.validate.OneToTen;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * com.catpp.springbootpro.pojo
 *
 * @Author cat_pp
 * @Date 2018/10/19
 * @Description
 */
@Data
public class ValidateEntity implements Serializable {
    private static final long serialVersionUID = 5285947505643474930L;

    @NotBlank
    @Length(min = 2, max = 20)
    private String name;

    @Min(1)
    private Integer age;

    @NotBlank
    @Email
    private String email;

    @OneToTen(values = "1,2,3,4,5,6,7,8,9,10")
    private String customParam;
}
