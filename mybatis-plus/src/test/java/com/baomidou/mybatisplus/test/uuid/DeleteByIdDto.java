package com.baomidou.mybatisplus.test.uuid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteByIdDto<T> implements Serializable {

    private T id;

}
