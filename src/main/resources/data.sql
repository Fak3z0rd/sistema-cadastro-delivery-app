INSERT INTO restaurante (id, cep, complemento, nome) VALUES
(1L, '0000001', 'Complemento restaurante 1', 'Restaurante 1'),
(2L, '0000002', 'Complemento restaurante 2', 'Restaurante 2');

INSERT INTO cliente (id, cep, complemento, nome) VALUES
(1L, '0000001', 'Complemento cliente 1', 'Restaurante 1');

INSERT INTO produto (id, disponivel, nome, valor_unitario, restaurante_id) VALUES
(1l, true, 'Produto 1', 5.0, 1l),
(2l, true, 'Produto 2', 5.0, 1l),
(3l, true, 'Produto 3', 5.0, 2l);

INSERT INTO sacola (id, forma_pagamento, fechada, valor_total, cliente_id) VALUES
(1L, 0, false, 0.0, 1L);