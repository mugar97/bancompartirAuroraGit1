# language : es
@Aurora=ESB
@Requerimiento=691-RQ-ESB-CONSULTA_PRODUCTO
@Módulo=Parámetros_Generales
@Servicio=SER008
@Tipo_de_servicio=MQ
Característica: 691-RQ-ESB-CONSULTA_PRODUCTO
  Permitir la consulta de la parametrización de los productos

  Escenario: 0045-CPG-ESB-Validar datos de entrada_WS Consulta Producto
    Validar los datos de entrada al Servicio SER008 - Consulta de Producto

    Dado que se consume el servicio base
    Cuando se verifica el estado
    Entonces se valida que el campo tenga un valor
