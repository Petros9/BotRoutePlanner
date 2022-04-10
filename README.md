**Ogólny pomysł**

Z uwagi na dość spory rozmiar grafu z drugiego przykładu zdecydowałem się na użycie 
pomocniczej mapy przechowującej obliczone już wartości (zwiększona złożoność pamięciowa,
ale przyspiesza wykonywanie obliczeń). Główna myśl -> algorytm Dijkstry, zachłanny.

Algorytm wykonywany jest w dwóch turach:
* od pozycji startowej do produktu, 
* od produktu do stacji odbiorczej

Pliki wejściowe powinny znajdować się w folderze `resources`, tam również będzie znajdował się plik z wynikami.

Złożoność algorytmu Dijkstry O(E * logV)

**Zadanie drugie**

Z uwagi na użycie hashMapy i obliczenie wartości w algorytmie dla wszystkich elementów,
to dodanie większej ilości stacji odbiorczych oznaczałoby konieczność przeiterowania wszystkich puntków i wybrania najmniejszej wartości
O(n), gdzie n - liczba stacji. Czyli finalnie O(E * logV + N).

