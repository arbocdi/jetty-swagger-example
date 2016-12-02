package net.sf.arbocdi.jw.data;

import lombok.Data;

/**
 *
 * @author arbocdi
 */
@Data
public class Person {
    
    private String pw;
    private String fio;
    private String email;

    public Person(String pw, String fio, String email) {
        this.pw = pw;
        this.fio = fio;
        this.email = email;
    }
    
}
