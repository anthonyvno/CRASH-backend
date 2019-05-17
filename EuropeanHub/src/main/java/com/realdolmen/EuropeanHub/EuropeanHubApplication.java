package com.realdolmen.EuropeanHub;

import com.realdolmen.EuropeanHub.profile.Insurance;
import com.realdolmen.EuropeanHub.profile.InsuranceRepository;
import com.realdolmen.EuropeanHub.insurer.Insurer;
import com.realdolmen.EuropeanHub.insurer.InsurerRepository;
import com.realdolmen.EuropeanHub.profile.License;
import com.realdolmen.EuropeanHub.profile.LicenseRepository;
import com.realdolmen.EuropeanHub.profile.ProfileEU;
import com.realdolmen.EuropeanHub.report.Report;
import com.realdolmen.EuropeanHub.report.ReportRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import com.realdolmen.EuropeanHub.profile.ProfileEURepository;
import com.realdolmen.EuropeanHub.profile.Vehicle;
import com.realdolmen.EuropeanHub.profile.VehicleRepository;
import java.util.Date;
import java.util.Properties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@Import({BasicAuthConfiguration.class})
public class EuropeanHubApplication {

    @Autowired
    private InsurerRepository insurerRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ProfileEURepository profileRepository;

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private VehicleRepository vehicleRepository;
   

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("info.europeanhub@gmail.com");
        mailSender.setPassword("Realdolmen1");

        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.test-connection", "true");
        return mailSender;
    }

    public static void main(String[] args) {
        SpringApplication.run(EuropeanHubApplication.class, args);
    }
  @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("*");
            }
        };
    }
}
