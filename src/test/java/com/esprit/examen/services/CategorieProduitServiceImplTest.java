package com.esprit.examen.services;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategorieProduitServiceImplTest {

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    private CategorieProduit categorieProduit;

    @BeforeEach
    void setUp() {
        categorieProduit = new CategorieProduit(1L, "EL", "Electronics", new HashSet<>());
    }

    @Test
    void retrieveAllCategorieProduits() {
        List<CategorieProduit> expectedCategories = Arrays.asList(categorieProduit);

        when(categorieProduitRepository.findAll()).thenReturn(expectedCategories);

        List<CategorieProduit> categories = categorieProduitService.retrieveAllCategorieProduits();

        assertEquals(expectedCategories, categories);
        verify(categorieProduitRepository, times(1)).findAll();
    }

    @Test
    void addCategorieProduit() {
        when(categorieProduitRepository.save(any(CategorieProduit.class))).thenReturn(categorieProduit);

        CategorieProduit result = categorieProduitService.addCategorieProduit(categorieProduit);

        assertEquals(categorieProduit, result);
        verify(categorieProduitRepository, times(1)).save(categorieProduit);
    }

    @Test
    void deleteCategorieProduit() {
        doNothing().when(categorieProduitRepository).deleteById(1L);

        categorieProduitService.deleteCategorieProduit(1L);

        verify(categorieProduitRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateCategorieProduit() {
        when(categorieProduitRepository.save(any(CategorieProduit.class))).thenReturn(categorieProduit);

        CategorieProduit result = categorieProduitService.updateCategorieProduit(categorieProduit);

        assertEquals(categorieProduit, result);
        verify(categorieProduitRepository, times(1)).save(categorieProduit);
    }

    @Test
    void retrieveCategorieProduit_found() {
        when(categorieProduitRepository.findById(1L)).thenReturn(Optional.of(categorieProduit));

        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(1L);

        assertEquals(categorieProduit, result);
        verify(categorieProduitRepository, times(1)).findById(1L);
    }

    @Test
    void retrieveCategorieProduit_notFound() {
        when(categorieProduitRepository.findById(1L)).thenReturn(Optional.empty());

        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(1L);

        assertNull(result);
        verify(categorieProduitRepository, times(1)).findById(1L);
    }
}