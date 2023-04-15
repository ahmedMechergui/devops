package com.esprit.examen.services;

import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SecteurActiviteServiceImplTest {

    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    private SecteurActiviteServiceImpl secteurActiviteService;

    private SecteurActivite secteurActivite;

    @BeforeEach
    public void setUp() {
        secteurActivite = new SecteurActivite(1L, "code1", "libelle1", new HashSet<>());
    }

    @Test
    public void testRetrieveAllSecteurActivite() {
        when(secteurActiviteRepository.findAll()).thenReturn(Arrays.asList(secteurActivite));

        List<SecteurActivite> secteurActivites = secteurActiviteService.retrieveAllSecteurActivite();

        assertNotNull(secteurActivites);
        assertEquals(1, secteurActivites.size());
        assertEquals(secteurActivite, secteurActivites.get(0));
        verify(secteurActiviteRepository, times(1)).findAll();
    }

    @Test
    public void testAddSecteurActivite() {
        when(secteurActiviteRepository.save(any(SecteurActivite.class))).thenReturn(secteurActivite);

        SecteurActivite result = secteurActiviteService.addSecteurActivite(secteurActivite);

        assertNotNull(result);
        assertEquals(secteurActivite, result);
        verify(secteurActiviteRepository, times(1)).save(secteurActivite);
    }

    @Test
    public void testDeleteSecteurActivite() {
        doNothing().when(secteurActiviteRepository).deleteById(1L);

        secteurActiviteService.deleteSecteurActivite(1L);


        verify(secteurActiviteRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateSecteurActivite() {
        when(secteurActiviteRepository.save(any(SecteurActivite.class))).thenReturn(secteurActivite);

        SecteurActivite result = secteurActiviteService.updateSecteurActivite(secteurActivite);

        assertNotNull(result);
        assertEquals(secteurActivite, result);
        verify(secteurActiviteRepository, times(1)).save(secteurActivite);
    }

    @Test
    public void testRetrieveSecteurActivite() {
        when(secteurActiviteRepository.findById(1L)).thenReturn(Optional.of(secteurActivite));

        SecteurActivite result = secteurActiviteService.retrieveSecteurActivite(1L);

        assertNotNull(result);
        assertEquals(secteurActivite, result);
        verify(secteurActiviteRepository, times(1)).findById(1L);
    }

    @Test
    public void testRetrieveSecteurActiviteNotFound() {
        when(secteurActiviteRepository.findById(1L)).thenReturn(Optional.empty());

        SecteurActivite result = secteurActiviteService.retrieveSecteurActivite(1L);
        assertNull(result);
        verify(secteurActiviteRepository, times(1)).findById(1L);
    }

}