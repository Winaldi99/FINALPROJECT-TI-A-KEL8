module aplikasiManagementKaraoke {
    requires spring.context;
    requires spring.beans;
    requires java.sql;
    requires org.slf4j;
    opens aplikasiManagementKaraoke;
    opens aplikasiManagementKaraoke.config;
    opens aplikasiManagementKaraoke.entities;
    opens aplikasiManagementKaraoke.repositories;
    opens aplikasiManagementKaraoke.services;
    opens aplikasiManagementKaraoke.views;
}