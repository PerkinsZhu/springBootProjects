package com.perkins.mybatisplusstudy.model.auto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends Model {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别 1:男 2:女
     */
    private Integer sex;


}
