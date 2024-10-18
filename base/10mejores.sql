CREATE OR REPLACE VIEW northwind."Diez mejores pedidos por importe de ventas"
 AS
 SELECT DISTINCT "Subtotales de pedidos".subtotal AS "ImporteVentas",
    "Pedidos"."Id de pedido" AS idpedido,
    to_char("Pedidos"."Fecha de pedido", 'dd/mm/YYYY'::text)::date AS fechapedido,
    "Clientes ampliados"."Compañía" AS nombrecompania,
    to_char("Pedidos"."Fecha de envío", 'dd/mm/YYYY'::text)::date AS fechaenvio
   FROM northwind."Clientes ampliados"
     JOIN northwind."Pedidos" ON "Clientes ampliados"."Id" = "Pedidos"."Id de cliente"
     JOIN northwind."Subtotales de pedidos" ON "Pedidos"."Id de pedido" = "Subtotales de pedidos"."Id de pedido"
  ORDER BY "Subtotales de pedidos".subtotal DESC
 LIMIT 10;