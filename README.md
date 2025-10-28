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

Eu odeio esse trabalho!

| Qtd. de Threads | Qtd. de Dados | Bubble Sort Serial | Bubble Sort Paralelo | Diferença |
|-----------------|----------------|------------------------|---------------------------|----------------|
| 2  | 1.000 | 0,005782 | 0,046787 | 0,041005 |
| 2  | 10.000 | 0,044001 | 0,126471 | 0,082470 |
| 2  | 100.000 | 9,567576 | 3,839086 | 5,728490 |
| 2  | 200.000 | 40,820434 | 14,990656 | 25,829778 |
| 6  | 1.000 | 0,000496 | 0,006099 | 0,005603 |
| 6  | 10.000 | 0,048866 | 0,129387 | 0,080521 |
| 6  | 100.000 | 9,902936 | 4,521435 | 5,381501 |
| 6  | 200.000 | 39,953707 | 14,659607 | 25,294100 |
| 10 | 1.000 | 0,000548 | 0,006113 | 0,005565 |
| 10 | 10.000 | 0,041540 | 0,123993 | 0,082453 |
| 10 | 100.000 | 9,647983 | 4,134529 | 5,513454 |
| 10 | 200.000 | 40,044101 | 14,084067 | 25,960034 |



| Qtd. de Threads | Qtd. de Dados | Counting Sort Serial  | Counting Sort Paralelo | Diferença  |
|-----------------|----------------|---------------------------|-----------------------------|----------------|
| 2  | 1.000 | 0,014961 | 0,070853 | 0,055892 |
| 2  | 10.000 | 0,023905 | 0,043092 | 0,019187 |
| 2  | 100.000 | 0,018021 | 0,048928 | 0,030907 |
| 2  | 200.000 | 0,022957 | 0,049037 | 0,026080 |
| 2  | 100.000.000 | 2,045129 | 1,572382 | 0,472747 |
| 6  | 1.000 | 0,011711 | 0,071411 | 0,059700 |
| 6  | 10.000 | 0,010671 | 0,069446 | 0,058775 |
| 6  | 100.000 | 0,015103 | 0,084856 | 0,069753 |
| 6  | 200.000 | 0,029322 | 0,084963 | 0,055641 |
| 6  | 100.000.000 | 2,420321 | 1,651858 | 0,768463 |
| 10 | 1.000 | 0,026133 | 0,108421 | 0,082288 |
| 10 | 10.000 | 0,039272 | 0,108010 | 0,068738 |
| 10 | 100.000 | 0,012613 | 0,150239 | 0,137626 |
| 10 | 200.000 | 0,024120 | 0,161579 | 0,137459 |
| 10 | 100.000.000 | 2,156008 | 1,183859 | 0,972149 |

| Qtd. de Threads | Qtd. de Dados | Merge Sort Serial | Merge Sort Paralelo | Diferença |
|-----------------|----------------|------------------------|---------------------------|----------------|
| 2  | 1.000 | 0,00154 | 0,00316 | 0,00162 |
| 2  | 10.000 | 0,00135 | 0,00308 | 0,00173 |
| 2  | 100.000 | 0,01699 | 0,01126 | 0,00573 |
| 2  | 200.000 | 0,03005 | 0,01584 | 0,01421 |
| 2  | 100.000.000 | 20,17215 | 12,04038 | 8,13177 |
| 6  | 1.000 | 0,00009 | 0,00144 | 0,00135 |
| 6  | 10.000 | 0,00326 | 0,00352 | 0,00026 |
| 6  | 100.000 | 0,01250 | 0,00524 | 0,00726 |
| 6  | 200.000 | 0,02780 | 0,00980 | 0,01800 |
| 6  | 100.000.000 | 17,97769 | 6,11711 | 11,86058 |
| 10 | 1.000 | 0,00007 | 0,00155 | 0,00148 |
| 10 | 10.000 | 0,00085 | 0,00154 | 0,00069 |
| 10 | 100.000 | 0,01113 | 0,00484 | 0,00629 |
| 10 | 200.000 | 0,02340 | 0,00722 | 0,01618 |
| 10 | 100.000.000 | 17,98048 | 4,36510 | 13,61538 |

| Qtd. de Threads | Qtd. de Dados | Quick Sort Serial| Quick Sort Paralelo | Diferença  |
|-----------------|----------------|-------------------------|---------------------------|----------------|
| 2 | 10.000 | 0,00199 | 0,00379 | 0,0018 |
| 2 | 50.000 | 0,00074 | 0,00123 | 0,0005 |
| 2 | 100.000 | 0,00663 | 0,00503 | 0,0016 |
| 2 | 500.000 | 0,01366 | 0,00964 | 0,0040 |
| 2 | 1.000.000 | 10,32257 | 8,28727 | 2,0353 |
| 6 | 10.000 | 0,00007 | 0,00111 | 0,0010 |
| 6 | 50.000 | 0,00058 | 0,00138 | 0,0008 |
| 6 | 100.000 | 0,00715 | 0,00614 | 0,0010 |
| 6 | 500.000 | 0,01418 | 0,00630 | 0,0079 |
| 6 | 1.000.000 | 9,61337 | 4,71663 | 4,8967 |
| 10 | 10.000 | 0,00005 | 0,00145 | 0,0014 |
| 10 | 50.000 | 0,00058 | 0,00162 | 0,0010 |
| 10 | 100.000 | 0,00736 | 0,00621 | 0,0011 |
| 10 | 500.000 | 0,01376 | 0,00617 | 0,0076 |
| 10 | 1.000.000 | 9,62271 | 3,74886 | 5,8738 |

---

## Conclusão

Com base nos experimentos realizados, conclui-se que a paralelização pode trazer benefícios consideráveis em algoritmos adequados à divisão de tarefas, como o MergeSort e o QuickSort, e na versão ajustada do BubbleSort. A análise também evidenciou a importância de ajustar o número de threads e o tamanho dos dados para otimizar o desempenho.
Este trabalho reforça a relevância da computação paralela no contexto atual, em que o aproveitamento eficiente dos recursos multicore é essencial. Como continuidade, sugere-se expandir o estudo para ambientes distribuídos e explorar outros algoritmos de ordenação ou estruturas híbridas.


---

## Referências

ORACLE. *Java Platform, Standard Edition Documentation.* Disponível em: https://docs.oracle.com/javase/.

DEV MEDIA. Threads: paralelizando tarefas com os diferentes recursos do Java. Disponível em: https://www.devmedia.com.br/threads-paralelizando-tarefas-com-os-diferentes-recursos-do-java/34309. Acesso em: 28 out. 2025.

NORÕES, Izequiel. Aula 11 – Computação Paralela e Concorrente. Fortaleza: Universidade de Fortaleza – UNIFOR, 2024. Material de aula (Sistemas Operacionais).


---

## Anexos

Códigos das implementações disponíveis em: https://github.com/loreslei/AlgoritmosOrdenacaoParalelos


