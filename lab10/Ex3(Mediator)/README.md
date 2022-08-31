# Ex3

## 1) O Problema

Numa linha de comboios apenas existe uma via de circulação. Esta situação impede os comboios de circularem nos dois sentidos ao mesmo tempo no mesmo troço de linha. Cada comboio estacionado numa estação precisa de saber se a linha à sua frente está disponível para avançar, isto é se a linha está livre ou se o comboio que nela circula circula no mesmo sentido que o seu. No entanto é insuportável contactar todos os maquinistas em atividade na linha para saber se o troço está livre.

## 2) A Solução

A solução passa por implementar a uma Central de Controlo o padrão Mediator. Criada a interface Mediator implementamo-la à classe CentralControlo que irá orientar as partidas de todos os comboios na linha. Esta classe verificará se o troço de linha à frente de cada comboio está em condições do mesmo avançar, e em caso afirmativo dar-lhe à permissão para o fazer. Deste modo ao invés dos maquinistas terem de contactar entre si apenas têm de esperar por ordens da CentralControlo para avançar após o pedido. De notar que não está definida qualquer prioridade de avanço entre comboios. A central dá ordem para avançar caso o comboio que faz o pedido tenha o troço de linha à sua frente livre ou o comboio que nele circula fá-lo no mesmo sentido que este.

## 3) Referências

Slides teóricos "Behavior Patterns"\
[W3Schools](https://www.w3schools.com/java/java_threads.asp)
