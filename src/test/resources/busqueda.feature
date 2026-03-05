

@busqueda
Feature: Buscar en youtube

  Scenario Outline: buscar una tematica usando <metodo>
    Given el usuario esta en la pagina de youtube 
    When hace click sobre caja de busqueda 
    And escribe una tematica "selenium"
    And confirma la busqueda usando "<metodo>"
    Then se muestran videos relacionados

    Examples:
      | metodo |
      | click  |
      | enter  |
  
  
