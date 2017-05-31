package com.dmmsoft.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by milo on 26.05.17.
 */

@Entity
public class Activity {

    @Id
    @GeneratedValue
    private long id;
    private LocalDateTime eventDateTime;
    private String description;
    private String userLogin;






}
