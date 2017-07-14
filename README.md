# Macchina-Data-Flow

Progetto in Java per la parte di laboratorio dell'esame di Sistemi Operativi.


Si realizzi un programma che simula il funzionamento di una macchina data flow per la valutazioni di espressioni aritmetiche. L’espressione è memorizzata in un file in forma polacca (notazione prefissa). Il formato è il seguente:
<EXP>    ::= <OP> <EXP> <EXP> | <NUMBER>
<OP>     ::= + | * | / | -
<NUMBER> ::= real in java external format
Almeno uno spazio o newline è richiesto come delimitatore dei numeri è operatori. Si consiglia di utilizzare la libreria java.util.Scanner
Ad esempio
* + 3.14 3.67 / 4.56 22.4
produce 1.38632142
Realizzare il programma esplicitando il massimo parallelismo. Il programma stampa oltre che al risultato della valutazione, la differenza tra i timestamp prima e dopo la valutazione e il numero di threads utilizzati per la valutazione. 
Il progetto va discusso e sviluppato singolarmente.
