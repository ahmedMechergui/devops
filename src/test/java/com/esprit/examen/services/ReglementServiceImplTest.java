package com.esprit.examen.services;

import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.ReglementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReglementServiceImplTest {

    @Mock
    private FactureRepository factureRepository;

    @Mock
    private ReglementRepository reglementRepository;

    @InjectMocks
    private ReglementServiceImpl reglementService;

    private Reglement reglement;
    private Date date;

    @BeforeEach
    public void setUp() {
        date = new Date();
        reglement = new Reglement(1L, 100.0f, 0.0f, true, date, null);
    }

    @Test
    public void testRetrieveAllReglements() {
        when(reglementRepository.findAll()).thenReturn(Arrays.asList(reglement));

        List<Reglement> reglements = reglementService.retrieveAllReglements();

        assertNotNull(reglements);
        assertEquals(1, reglements.size());
        assertEquals(reglement, reglements.get(0));
        verify(reglementRepository, times(1)).findAll();
    }

    @Test
    public void testAddReglement() {
        when(reglementRepository.save(any(Reglement.class))).thenReturn(reglement);

        Reglement result = reglementService.addReglement(reglement);

        assertNotNull(result);
        assertEquals(reglement, result);
        verify(reglementRepository, times(1)).save(reglement);
    }

    @Test
    public void testRetrieveReglement() {
        when(reglementRepository.findById(1L)).thenReturn(Optional.of(reglement));

        Reglement result = reglementService.retrieveReglement(1L);

        assertNotNull(result);
        assertEquals(reglement, result);
        verify(reglementRepository, times(1)).findById(1L);
    }

    @Test
    public void testRetrieveReglementNotFound() {
        when(reglementRepository.findById(1L)).thenReturn(Optional.empty());

        Reglement result = reglementService.retrieveReglement(1L);

        assertNull(result);
        verify(reglementRepository, times(1)).findById(1L);
    }

    @Test
    public void testRetrieveReglementByFacture() {
        when(reglementRepository.retrieveReglementByFacture(1L)).thenReturn(Arrays.asList(reglement));

        List<Reglement> reglements = reglementService.retrieveReglementByFacture(1L);

        assertNotNull(reglements);
        assertEquals(1, reglements.size());
        assertEquals(reglement, reglements.get(0));
        verify(reglementRepository, times(1)).retrieveReglementByFacture(1L);
    }

    @Test
    public void testGetChiffreAffaireEntreDeuxDate() {
        Date startDate = new Date();
        Date endDate = new Date();

        when(reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(100.0f);

        float chiffreAffaire = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);

        assertEquals(100.0f, chiffreAffaire);
        verify(reglementRepository, times(1)).getChiffreAffaireEntreDeuxDate(startDate, endDate);
    }
}