<img height="100" alt="Logo Unifor" width="100" align="left" src="https://github.com/user-attachments/assets/e539a346-8d74-4cf0-a52a-bc5d786202b9" />
**UNIVERSIDADE DE FORTALEZA**<br>
CENTRO DE CIÊNCIAS TECNOLÓGICAS<br>
CURSO: CIÊNCIA DA COMPUTAÇÃO


---

## Análise de Desempenho de Algoritmos de Busca em Ambientes Concorrentes e Paralelos: Um Estudo Comparativo em Java

**Autor 1:** BIANCA ORIÁ 

**Autor 2:** LORENNA AGUIAR

---

**Palavras-chave:** Paralelização. Desempenho. Algoritmos de Ordenação

---

## Resumo

Este trabalho propõe uma análise detalhada do desempenho de diferentes algoritmos de busca em ambientes seriais e paralelos, utilizando a linguagem de programação *Java*. A busca por eficiência computacional é essencial em diversas aplicações, e entender como diferentes algoritmos se comportam em diferentes cenários de processamento é de suma importância. Neste estudo, serão abordados quatro algoritmos de busca populares — *BubbleSort, CountingSort, MergeSort e QuickSort* — , tanto em suas implementações sequenciais quanto em versões paralelizadas. Serão realizadas análises comparativas utilizando uma variedade de conjuntos de dados de entrada e ajustando o número de threads. Os resultados serão registrados em arquivos CSV, permitindo uma análise visual através de gráficos utilizando *Java*.

---

## Introdução

No contexto atual da computação e a demanda por cada vez mais desempenho, a utilização de técnicas de paralelização tornou-se essencial para melhorar o desempenho de programas. Neste contexto, este trabalho tem como objetivo analisar e comparar o desempenho de diferentes algoritmos de ordenação implementados em *Java*, tanto em versões seriais quanto paralelas. Foram escolhidos quatro métodos clássicos: *BubbleSort, CountingSort, MergeSort e QuickSort*, devido às suas diferentes características de complexidade e comportamento em ambientes concorrentes e paralelos.
A análise busca compreender como a divisão de tarefas entre múltiplas threads influencia o tempo total de execução e identificar os cenários em que a paralelização realmente oferece ganhos significativos. O estudo também contribui para reforçar conceitos de programação paralela e concorrente e otimização de desempenho em sistemas.

---

## Metodologia

Os algoritmos foram implementados em *Java*, nas versões serial e paralela, seguindo boas práticas de programação concorrente e paralela. Cada algoritmo foi executado para diferentes tamanhos de conjuntos de dados (1.000, 10.000, 100.000, 200.000, 100.000.000  elementos), variando o número de threads (2, 6, 10). Os tempos de execução foram armazenados em arquivos CSV e posteriormente analisados por meio de gráficos comparativos gerados.
A análise estatística considerou o tempo médio de execução de cada versão, permitindo identificar padrões de desempenho e relacionar o ganho de eficiência com o aumento da carga de processamento. Essa abordagem possibilitou uma avaliação dos efeitos da paralelização sobre cada algoritmo.


---

## Resultados e Discussão

Inserir os resultados e a discussão *(Arial, 11, espaçamento 1,5)* – Atenção para não ultrapassar as margens laterais.

---

## Conclusão

Com base nos experimentos realizados, conclui-se que a paralelização pode trazer benefícios consideráveis em algoritmos adequados à divisão de tarefas, como o MergeSort e o QuickSort, e na versão ajustada do BubbleSort. A análise também evidenciou a importância de ajustar o número de threads e o tamanho dos dados para otimizar o desempenho.
Este trabalho reforça a relevância da computação paralela no contexto atual, em que o aproveitamento eficiente dos recursos multicore é essencial. Como continuidade, sugere-se expandir o estudo para ambientes distribuídos e explorar outros algoritmos de ordenação ou estruturas híbridas.


---

## Referências

cu


---

## Anexos

Códigos das implementações disponíveis em: https://github.com/loreslei/AlgoritmosOrdenacaoParalelos


