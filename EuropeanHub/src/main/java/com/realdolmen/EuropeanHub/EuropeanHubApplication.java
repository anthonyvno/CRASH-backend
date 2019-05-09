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

    @Component
    class DataSetup implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) throws Exception {
            Insurer ins1 = Insurer.builder().name("AG Insurance").country("BELGIUM").build();
            insurerRepository.save(ins1);
            insurerRepository.save(Insurer.builder().name("Baloise NV").country("BELGIUM").build());
            insurerRepository.save(Insurer.builder().name("Ethias").country("BELGIUM").build());
            insurerRepository.save(Insurer.builder().name("EthiasDE").country("GERMANY").build());

            License l1 = License.builder().category("B").expires("05/26").licenseNumber("123465798").build();
            License l2 = License.builder().category("B").expires("05/26").licenseNumber("123465798").build();
            License l3 = License.builder().category("B").expires("05/26").licenseNumber("123465798").build();
            License l4 = License.builder().category("B").expires("05/26").licenseNumber("123465798").build();
            licenseRepository.save(l1);
            licenseRepository.save(l2);
            licenseRepository.save(l3);
            licenseRepository.save(l4);

            Insurance i1 = Insurance.builder().emailAgency("agent@bombeke.be").expires(new Date()).greenCardNumber("893469").insuranceNumber("96392").insurer(ins1).phoneAgency("0473878009").build();
            Insurance i2 = Insurance.builder().emailAgency("agent@bombeke.be").expires(new Date()).greenCardNumber("893469").insuranceNumber("96392").insurer(ins1).phoneAgency("0473878009").build();
            Insurance i3 = Insurance.builder().emailAgency("agent@bombeke.be").expires(new Date()).greenCardNumber("893469").insuranceNumber("96392").insurer(ins1).phoneAgency("0473878009").build();
            Insurance i4 = Insurance.builder().emailAgency("agent@bombeke.be").expires(new Date()).greenCardNumber("893469").insuranceNumber("96392").insurer(ins1).phoneAgency("0473878009").build();
            Insurance i5 = Insurance.builder().emailAgency("agent@bombeke.be").expires(new Date()).greenCardNumber("893469").insuranceNumber("96392").insurer(ins1).phoneAgency("0473878009").build();
            insuranceRepository.save(i1);
            insuranceRepository.save(i2);
            insuranceRepository.save(i3);
            insuranceRepository.save(i4);
            insuranceRepository.save(i5);

            Vehicle v1 = Vehicle.builder().country("Belgie").licensePlate("age-123").brand("Mercedes").model("Benz").type("Car").insurance(i1).build();
            Vehicle v2 = Vehicle.builder().country("Belgie").licensePlate("age-123").brand("Mercedes").model("Benz").type("Car").insurance(i2).build();
            Vehicle v3 = Vehicle.builder().country("Belgie").licensePlate("age-123").brand("Mercedes").model("Benz").type("Car").insurance(i3).build();
            Vehicle v4 = Vehicle.builder().country("Belgie").licensePlate("age-123").brand("Mercedes").model("Benz").type("Car").insurance(i4).build();
            Vehicle v5 = Vehicle.builder().country("Belgie").licensePlate("age-123").brand("Mercedes").model("Benz").type("Car").insurance(i5).build();

            List<Vehicle> vehicles1 = new ArrayList<>();
            List<Vehicle> vehicles2 = new ArrayList<>();
            List<Vehicle> vehicles3 = new ArrayList<>();
            List<Vehicle> vehicles4 = new ArrayList<>();
            vehicleRepository.save(v1);
            vehicleRepository.save(v2);
            vehicleRepository.save(v3);
            vehicleRepository.save(v4);
            vehicleRepository.save(v5);
            vehicles1.add(v1);
            vehicles2.add(v2);
            vehicles3.add(v3);
            vehicles4.add(v4);
            vehicles4.add(v5);

            List<ProfileEU> profiles1 = new ArrayList<>();
            ProfileEU p1 = ProfileEU.builder().firstName("Jan").lastName("Peeters").email("jan.peeters@telenet.be").license(l1).vehicles(vehicles1).build();
            ProfileEU p2 = ProfileEU.builder().firstName("Piet").lastName("Janssens").email("piet.janssens@telenet.be").license(l2).vehicles(vehicles2).build();

            profileRepository.save(p1);
            profileRepository.save(p2);
            profiles1.add(p1);
            profiles1.add(p2);

            List<ProfileEU> profiles2 = new ArrayList<>();
            ProfileEU p3 = ProfileEU.builder().firstName("John").lastName("Peterson").email("John.Peterson@hotmail.com").license(l3).vehicles(vehicles3).build();
            ProfileEU p4 = ProfileEU.builder().firstName("Pete").lastName("Jones").email("Pete.Jones@hotmail.com").license(l4).vehicles(vehicles4).build();

            profileRepository.save(p3);
            profileRepository.save(p4);

            profiles2.add(p3);
            profiles2.add(p4);

            reportRepository.save(Report.builder().profiles(profiles1).build());
            reportRepository.save(Report.builder().profiles(profiles2).build());

        }

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
