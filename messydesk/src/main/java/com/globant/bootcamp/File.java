package com.globant.bootcamp;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.AccessLevel;

@Data
@Builder(builderMethodName = "hiddenBuilder")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class File {
    
    @EqualsAndHashCode.Include private String path;
    
    @Setter(AccessLevel.NONE)
    @Builder.Default private Date created = new Date();
    
    @Setter(AccessLevel.NONE)
    private Date accessTime;
    
    private String content;
    
    public void read(){
        accessTime = new Date();
    }
    
    public static FileBuilder builder(String path) {
        return hiddenBuilder().path(path);
    }
}
