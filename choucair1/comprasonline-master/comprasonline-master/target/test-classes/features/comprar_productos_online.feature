
Feature: Comprar producto online

Scenario: A�adir al carrito un producto existente
    Given que me encuentro en portal exito
    When haga una busqueda del producto a comprar
    And a�ada al carrito de compras
    Then el producto debe existir en el carrito