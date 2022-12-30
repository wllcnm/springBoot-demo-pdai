package com.example.springbootdemo.pojo;


import com.example.springbootdemo.annotation.NullOrNotNull;
import com.example.springbootdemo.annotation.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleParam {

    /*
     * 这里的id字段数据类型为Long，校验注解使用@NotEmpty报错了，
     * 是因为@NotEmpty一般作用在集合类或者数组类上
     * ，而针对数值类型校验一般使用@NotNull，
     * 更换为@NotNull判非空之后就不报错了。
     * */

    //更改分组,方便业务操作
    //添加操作不需要id,且必须为空,故可以把他分组到AddValidationGroup.class
    //修改操作需要id,且不能为空,可以分组到EditValidationGroup.class
    @NotNull(message = "id不能为空",groups =EditValidationGroup.class )
    @Null(message = "id必须为空",groups = AddValidationGroup.class)
    private Integer pId;

    @NotNull(message = "姓名不能为空",groups =AddValidationGroup.class )
    private String pName;

    @NotNull(groups =AddValidationGroup.class)
    @Range(min = 0, max = 2, message = "性别只能从[0,1,2]选",groups = AddValidationGroup.class)
    private String pGender;

    @NotNull(groups =AddValidationGroup.class)
    @Range(min = 0, max = 100, message = "年龄范围为0-100",groups = AddValidationGroup.class)
    private Integer pAge;

    @Email
    private String email;

    private String[] pHobby;

    @NotNull(message = "手机号不为空",groups =AddValidationGroup.class)
    @PhoneNumber(groups = AddValidationGroup.class)
    private String telephone;


    public interface AddValidationGroup{

    }

    public interface EditValidationGroup{

    }
}
