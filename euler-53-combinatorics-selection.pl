:- use_module(library(clpfd)).

factorial(0, 1). % Base case if N is 0
factorial(N, F) :- N > 0, PrevN is N - 1, factorial(PrevN, PrevF), F is N * PrevF. % Recursive factorial function

combination(N, R, C) :- factorial(N, FN), factorial(R, FR), factorial(N-R, FNR), C is FN / (FR * FNR). % Combinatoric formula

distinct_combinations(N, Count) :-
    aggregate_all(count, (between(1, N, I), between(1, I, R), combination(I, R, Result), Result > 1000000), Count). % Go through each combination of n and r and keep track of the number of outputs over 1 million

main(N) :-
    distinct_combinations(N, Count),
    write(Count), nl.