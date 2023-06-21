package com.example.sparepartsdistributor.service;

import com.example.sparepartsdistributor.entity.Part;
import com.example.sparepartsdistributor.entity.User;
import com.example.sparepartsdistributor.repository.PartRepository;
import com.example.sparepartsdistributor.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PartServiceTest {
    @Mock private PartRepository partRepository;
    private PartService underTest;

    @BeforeEach
    void setUp(){
        underTest = new PartServiceImpl(partRepository);
    }

    @Test
    void canAddPart(){
        // given
        var part = Part.builder()
                .partNumber("9323A350G")
                .name("HP-Pump")
                .brand("Delphi")
                .category("HP-Pumps")
                .price(BigDecimal.valueOf(84566.16))
                .deliveryTime(5)
                .image("image.jpeg")
                .build();
        // when
        underTest.save(part);

        // then
        var partArgumentCaptor =
                ArgumentCaptor.forClass(Part.class);
        verify(partRepository)
                .save(partArgumentCaptor.capture());

        var capturedPart = partArgumentCaptor.getValue();

        assertThat(capturedPart).isEqualTo(part);
    }
}