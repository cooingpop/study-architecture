package com.example.threetier.repository;

import com.example.threetier.entity.ConferenceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ConferenceRepositoryImplTest {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Test
    public void testSaveAndFindByEmail() {
        String email = "test@example.com";
        ConferenceEntity entity = new ConferenceEntity(email);

        ConferenceEntity savedEntity = conferenceRepository.save(entity);

        assertNotNull(savedEntity.getId());
        assertEquals(email, savedEntity.getEmail());

        Optional<ConferenceEntity> foundEntity = conferenceRepository.findByEmail(email);

        assertTrue(foundEntity.isPresent());
        assertEquals(email, foundEntity.get().getEmail());
    }

    @Test
    public void testExistsByEmail() {
        String email = "exists@example.com";
        ConferenceEntity entity = new ConferenceEntity(email);
        conferenceRepository.save(entity);

        assertTrue(conferenceRepository.existsByEmail(email));
        assertFalse(conferenceRepository.existsByEmail("nonexistent@example.com"));
    }

    @Test
    public void testFindAll() {
        ConferenceEntity entity1 = new ConferenceEntity("one@example.com");
        ConferenceEntity entity2 = new ConferenceEntity("two@example.com");
        conferenceRepository.save(entity1);
        conferenceRepository.save(entity2);

        List<ConferenceEntity> entities = conferenceRepository.findAll();

        assertFalse(entities.isEmpty());
        assertTrue(entities.size() >= 2);
    }
}
