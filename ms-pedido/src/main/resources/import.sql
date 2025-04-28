INSERT INTO tb_pedido(nome, cpf, data, status) VALUES ('Jon Snow', '12345678965', '2025-04-28', 'REALIZADO')

INSERT INTO tb_pedido(nome, cpf, data, status) VALUES ('Half-Horse Half-Man', '12345678965', '2025-12-22', 'PAGO')
INSERT INTO tb_pedido(nome, cpf, data, status) VALUES ('Silverback Gorilla', '12345678965', '2025-04-28', 'CANCELADO')

INSERT INTO tb_item_do_pedido(quantidade, descricao, valor_unitario, pedido_id) VALUES (10, 'Wooting 60He', 1200, 1)
INSERT INTO tb_item_do_pedido(quantidade, descricao, valor_unitario, pedido_id) VALUES (1, 'Morto da silva', 0, 3)
INSERT INTO tb_item_do_pedido(quantidade, descricao, valor_unitario, pedido_id) VALUES (3, 'Corda grande e grossa', 10000, 2)
