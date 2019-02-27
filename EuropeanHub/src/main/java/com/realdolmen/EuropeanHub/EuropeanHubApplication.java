package com.realdolmen.EuropeanHub;

import com.realdolmen.EuropeanHub.insurer.Insurer;
import com.realdolmen.EuropeanHub.insurer.InsurerRepository;
import com.realdolmen.EuropeanHub.profile.Profile;
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

@SpringBootApplication
public class EuropeanHubApplication {

    @Autowired
    private InsurerRepository insurerRepository;
    
    @Autowired
    private ReportRepository reportRepository;
    

    @Component
    class DataSetup implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) throws Exception {
            insurerRepository.save(Insurer.builder().name("AG Insurance").country("BELGIUM").build());
            insurerRepository.save(Insurer.builder().name("Baloise NV").country("BELGIUM").build());
            insurerRepository.save(Insurer.builder().name("Ethias").country("BELGIUM").build());
            insurerRepository.save(Insurer.builder().name("Ethias").country("GERMANY").build());
            
            List<Profile> profiles1 = new ArrayList<>();
            profiles1.add(Profile.builder().firstName("Jan").lastName("Peeters").email("jan.peeters@telenet.be").build());
            profiles1.add(Profile.builder().firstName("Piet").lastName("Janssens").email("piet.janssens@telenet.be").build());

            List<Profile> profiles2 = new ArrayList<>();
            profiles2.add(Profile.builder().firstName("John").lastName("Peterson").email("John.Peterson@hotmail.com").build());
            profiles2.add(Profile.builder().firstName("Pete").lastName("Jones").email("Pete.Jones@hotmail.com").build());   
            
            reportRepository.save(Report.builder().profiles(profiles1).build());
            reportRepository.save(Report.builder().profiles(profiles2).build());


        }

    }

    public static void main(String[] args) {
        SpringApplication.run(EuropeanHubApplication.class, args);
    }

}
