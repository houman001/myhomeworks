# Please fill out this stencil and submit using the provided submission script.





## Problem 1
def myFilter(L, num): 
    return [l for l in L if (l % num) != 0 ]

print(str(myFilter([1, 2, 3, 4, 5, 6, 7], 2)))


## Problem 2
def myLists(L):
    return [[l + 1 for l in range(x)] for x in L]

print(str(myLists([1, 4, 6])))


## Problem 3
def myFunctionComposition(f, g): 
    return { x:g[f[x]] for x in f.keys() }

print(str(myFunctionComposition({0:'a', 1:'b'}, {'a':'apple', 'b':'banana'})))


## Problem 4
# Please only enter your numerical solution.

complex_addition_a = ... 
complex_addition_b = ...
complex_addition_c = ...
complex_addition_d = ...



## Problem 5
GF2_sum_1 = ...
GF2_sum_2 = ...
GF2_sum_3 = ...


## Problem 6
def mySum(L): pass



## Problem 7
def myProduct(L): pass



## Problem 8
def myMin(L): pass



## Problem 9
def myConcat(L): pass



## Problem 10
def myUnion(L): pass

