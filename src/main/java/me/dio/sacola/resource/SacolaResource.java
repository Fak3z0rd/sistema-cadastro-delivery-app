package me.dio.sacola.resource;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.model.Item;
import me.dio.sacola.model.Sacola;
import me.dio.sacola.resource.dto.ItemDTO;
import me.dio.sacola.service.SacolaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ifood-devweek/sacolas")
@RequiredArgsConstructor
public class SacolaResource {
    private final SacolaService sacolaService;

    @PostMapping
    public Item adicionarItemNaSacola(@RequestBody ItemDTO itemDTO) {
        return sacolaService.adicionarItemNaSacola(itemDTO);
    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id){
        return sacolaService.verSacola(id);
    }

    @PatchMapping("/fecharsacola/{sacolaID}")
    public Sacola fecharSacola(@PathVariable("sacolaID") Long sacolaID, @RequestParam("formaPagamento") int formaPagamento){
        return sacolaService.fecharSacola(sacolaID, formaPagamento);
    }
}
