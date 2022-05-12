//package com.song.demo.config;
//
//
//import org.flywaydb.core.Flyway;
//import org.flywaydb.core.api.configuration.FluentConfiguration;
//import org.flywaydb.core.api.migration.BaseJavaMigration;
//import org.flywaydb.core.api.migration.Context;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//
//public class FlywayMigration extends BaseJavaMigration {
//
//
//    @Autowired
//    DatabaseCredentials databaseCredentials;
//
//    @Autowired
//    DataSource dataSource;
//
////    @Override
////    public void migrate(Context context) throws Exception {
////        FluentConfiguration fluentConfiguration;
////        Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
////        flyway.migrate();
////    }
//}
