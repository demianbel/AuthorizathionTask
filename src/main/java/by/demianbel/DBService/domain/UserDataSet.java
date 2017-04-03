package by.demianbel.DBService.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
/*
(Database localhost:3306/task)
create database task;
use task;
create table if not exists users (id bigint auto_increment, login varchar(256), email varchar(256), imageURL varchar(256), score Bigint, onlineStatus varchar(256), primary key (id));
 */
@Entity(name = "users")
@Data
public class UserDataSet {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "imageURL")
    private String imageURL;

    @Column(name = "score")
    private Long score;

    @Column(name = "onlinestatus")
    @ColumnDefault(value = "Offline")
    private String onlineStatus;

}
