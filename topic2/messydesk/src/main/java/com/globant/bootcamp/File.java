package com.globant.bootcamp;

import java.util.Date;

import lombok.*;

@Data
@Builder(builderMethodName = "hiddenBuilder")
@EqualsAndHashCode(of = "path")
public class File {
    
    @Getter private String path;
    
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
