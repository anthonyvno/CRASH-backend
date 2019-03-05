package com.realdolmen.EuropeanHub;

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

@SpringBootApplication
public class EuropeanHubApplication {

    @Autowired
    private InsurerRepository insurerRepository;
    
    @Autowired
    private ReportRepository reportRepository;
    
    @Autowired
    private ProfileEURepository profileRepository;
    
    @Autowired
    private LicenseRepository licenseRepository;
    

    @Component
    class DataSetup implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) throws Exception {
            insurerRepository.save(Insurer.builder().name("AG Insurance").country("BELGIUM").build());
            insurerRepository.save(Insurer.builder().name("Baloise NV").country("BELGIUM").build());
            insurerRepository.save(Insurer.builder().name("Ethias").country("BELGIUM").build());
            insurerRepository.save(Insurer.builder().name("Ethias").country("GERMANY").build());
            
            License l1 = License.builder().category("B").expires("05/26").licenseNumber("123465798").build();
            License l2 = License.builder().category("B").expires("05/26").licenseNumber("123465798").build();
            License l3 = License.builder().category("B").expires("05/26").licenseNumber("123465798").build();
            License l4 = License.builder().category("B").expires("05/26").licenseNumber("123465798").build();
            licenseRepository.save(l1);
            licenseRepository.save(l2);
            licenseRepository.save(l3);
            licenseRepository.save(l4);
            
            List<ProfileEU> profiles1 = new ArrayList<>();
            ProfileEU p1 = ProfileEU.builder().firstName("Jan").lastName("Peeters").email("jan.peeters@telenet.be").license(l1).build();
            ProfileEU p2 = ProfileEU.builder().firstName("Piet").lastName("Janssens").email("piet.janssens@telenet.be").license(l2).build();

            profileRepository.save(p1);
            profileRepository.save(p2);
            profiles1.add(p1);
            profiles1.add(p2);

            List<ProfileEU> profiles2 = new ArrayList<>();
            ProfileEU p3 = ProfileEU.builder().firstName("John").lastName("Peterson").email("John.Peterson@hotmail.com").license(l3).build();
            ProfileEU p4 = ProfileEU.builder().firstName("Pete").lastName("Jones").email("Pete.Jones@hotmail.com").license(l4).build();

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

}
