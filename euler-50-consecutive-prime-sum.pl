isPrime(2).
isPrime(3).
isPrime(P) :- integer(P), P > 3, P mod 2 =\= 0, \+ has_factor(P,3).

has_factor(N,F) :-
    N mod F =:= 0.
has_factor(N,F) :-
    F * F < N,
    F2 is F + 2,
    has_factor(N,F2).

sieve_of_eratosthenes(N, Primes) :-
    findall(P, (between(2, N, P), isPrime(P)), Primes). % Find all P for which P is between 2 and N and P is a prime. Place them in the list, Primes

longest_sum_of_primes(N, MaxSum) :-
    sieve_of_eratosthenes(N, Primes),
    longest_sum_of_primes(Primes, 0, 0, 0, MaxSum).

longest_sum_of_primes([], _, _, MaxSum, MaxSum).
longest_sum_of_primes([H|T], Sum, Count, MaxCount, MaxSum) :-
   NewSum is Sum + H,
   (  NewSum > 1000000
   -> longest_sum_of_primes(T, 0, 0, MaxCount, MaxSum)
   ;  (  isPrime(NewSum)
       -> (  NewCount is Count + 1
           -> (  NewCount > MaxCount
               -> longest_sum_of_primes(T, NewSum, NewCount, NewCount, MaxSum)
               ;  longest_sum_of_primes(T, NewSum, NewCount, MaxCount, MaxSum)
               )
           ;  longest_sum_of_primes(T, NewSum, Count, MaxCount, MaxSum)
           )
       ;  longest_sum_of_primes(T, NewSum, Count, MaxCount, MaxSum)
       )
   ).