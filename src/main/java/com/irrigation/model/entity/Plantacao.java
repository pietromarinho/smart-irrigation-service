package com.irrigation.model.entity;

import com.irrigation.model.dto.PlantacaoDTO;
import com.irrigation.model.enumerations.PlantGroup;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "plantacao")
public class Plantacao extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String name;

    @ManyToMany()
    private List<Plant> plants;

    @Column(nullable = false, name = "interval")
    private int interval;

    @Column(name = "activationTime")
    private LocalDateTime activationTime;

    @Column(name = "ip")
    private String ip;

    @Enumerated(EnumType.STRING)
    private PlantGroup plantGroup;

    public Plantacao() {
    }

    public Plantacao(PlantacaoDTO dto) {
        if (dto.getId() != null) {
            super.setId(dto.getId());
        }
        setName(dto.getName());
        setInterval(dto.getInterval());
        setActivationTime(dto.getActivationTime());
        setIp(dto.getIp());
        setPlants(dto.getPlants().stream().map(plant -> new Plant(plant)).collect(Collectors.toList()));

    }
}
