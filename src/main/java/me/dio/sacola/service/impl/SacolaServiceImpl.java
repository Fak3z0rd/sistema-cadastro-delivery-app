package me.dio.sacola.service.impl;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.enumeration.FormaPagamento;
import me.dio.sacola.model.Item;
import me.dio.sacola.model.Restaurante;
import me.dio.sacola.model.Sacola;
import me.dio.sacola.repository.ProdutoRepository;
import me.dio.sacola.repository.SacolaRepository;
import me.dio.sacola.resource.dto.ItemDTO;
import me.dio.sacola.service.SacolaService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;


    @Override
    public Sacola verSacola(Long id) {
        return sacolaRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Essa sacola não existe");
        });
    }
    @Override
    public Item adicionarItemNaSacola(ItemDTO itemDTO) {
        Sacola sacola = verSacola(itemDTO.getSacolaID());
        if (sacola.isFechada()) throw new RuntimeException("Esta sacola está fechada!");

        Item item = Item.builder()
                .quantidade(itemDTO.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDTO.getProdutoID()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Este produto não existe!");
                        }
                ))
                .build();

        List<Item> itensDaSacola = sacola.getItens();
        if (itensDaSacola.isEmpty()) {
            itensDaSacola.add(item);
        } else {
            Restaurante restauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante();
            Restaurante restauranteParaAdicionar = item.getProduto().getRestaurante();
            if (restauranteAtual.equals(restauranteParaAdicionar)) {
                itensDaSacola.add(item);
            } else {
                throw new RuntimeException("Não é possível adicionar produtos de restaurantes diferentes. Feche a sacola ou esvazie.");
            }
        }

        Double valorTotalSacola = 0d;
        for (Item itemDaSacola : itensDaSacola) {
            double valorItem = itemDaSacola.getProduto().getValorUnitario() * itemDaSacola.getQuantidade();
            valorTotalSacola += valorItem;
        }
        sacola.setValorTotal(valorTotalSacola);
        sacolaRepository.save(sacola);
        return item;
    }

    @Override
    public Sacola fecharSacola(Long id, int numeroformaPagamento) {
        Sacola sacola = verSacola(id);
        if (sacola.getItens().isEmpty()) throw new RuntimeException("Inclua itens na sacola");
        FormaPagamento formaPagamento = numeroformaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;
        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);
        return sacolaRepository.save(sacola);
    }
}
