package com.khalil.wdcar.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalBean {
    private String columName;
    private String Value;
    private String tableName;
    private Long idColum;
}
