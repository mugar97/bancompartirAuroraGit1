
Feature: Comprar producto online

Scenario: Añadir al carrito un producto existente
    Given que me encuentro en portal exito
    When haga una busqueda del producto a comprar
    And añada al carrito de compras
    Then el producto debe existir en el carrito