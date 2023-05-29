package me.dio.sacola.resource.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Embeddable
@NoArgsConstructor
public class ItemDTO {
    private Long produtoID;
    private int quantidade;
    private Long sacolaID;
}
