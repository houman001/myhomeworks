# # Task 1
minutes_in_week = 7 * 24 * 60

# # Task 2
remainder_without_mod = 2304811 - (2304811 // 47) * 47

# # Task 3
divisible_by_3 = (673 + 909) % 3 == 0

# # Task 4
x = -9
y = 1 / 2
statement_val = 2 ** (y + 1 / 2) if x + 10 < 0 else 2 ** (y - 1 / 2)

# # Task 5
first_five_squares = { x ** 2 for x in {1, 2, 3, 4, 5} }
print(str(first_five_squares))

# # Task 6
first_five_pows_two = { 2 ** x for x in {0, 1, 2, 3, 4} }
print(str(first_five_pows_two))

# # Task 7: enter in the two new sets
X1 = { 1, 2, 3 }
Y1 = { 1, 4, 5 }
print(str({x * y for x in X1 for y in Y1}));

# # Task 8: enter in the two new sets
X2 = { 1, 2, 4 }
Y2 = { 3, 6, 12 }
print(str({x * y for x in X2 for y in Y2}));

# # Task 9
base = 10
digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
three_digits_set = { x * base * base + y * base + z for x in digits for y in digits for z in digits }
print(str(three_digits_set));

# # Task 10
S = {1, 2, 3, 4}
T = {3, 4, 5, 6}
S_intersect_T = { x for x in S for y in T if x == y }
print(str(S_intersect_T));

# # Task 11
listToSum = [20, 10, 15, 75]
L_average = sum(listToSum) / len(listToSum)
print(str(L_average));

# # Task 12
LofL = [[.25, .75, .1], [-1, 0], [4, 4, 4, 4]]
LofL_sum = sum([sum(x) for x in LofL ])
print(str(LofL_sum));

# # Task 13
cartesian_product = [ [x, y] for x in ['A', 'B', 'C'] for y in [1, 2, 3] ]
print(str(cartesian_product));

# # Task 14
S = {-4, -2, 1, 2, 5, 0}
zero_sum_list = [ (x, y, z) for x in S for y in S for z in S if (x + y + z) == 0 ] 
print(str(zero_sum_list));

# # Task 15
exclude_zero_list = [ (x, y, z) for x in S for y in S for z in S if (x + y + z) == 0 and (x, y, z) != (0, 0, 0) ] 
print(str(exclude_zero_list));

# # Task 16
first_of_tuples_list = [ (x, y, z) for x in S for y in S for z in S if (x + y + z) == 0 and (x, y, z) != (0, 0, 0) ][0] 
print(str(first_of_tuples_list));

# # Task 17
L1 = [ 1, 2, 1 ]  # <-- want len(L1) != len(list(set(L1)))
print('L1' + str(L1));
L2 = [ 1, 3, 2 ]  # <-- same len(L2) == len(list(set(L2))) but L2 != list(set(L2))
print('L2' + str(L2));

# # Task 18
odd_num_list_range = { x for x in range(100) if x % 2 == 1 }
print(str(odd_num_list_range))

# # Task 19
L = ['A', 'B', 'C', 'D', 'E']
range_and_zip = list(zip(range(5), L))
print(str(range_and_zip))

# # Task 20
list_sum_zip = [sum(x) for x in zip([10, 25, 40], [1, 15, 20])]
print(str(list_sum_zip))

# # Task 21
dlist = [{'James':'Sean', 'director':'Terence'}, {'James':'Roger', 'director':'Lewis'}, {'James':'Pierce', 'director':'Roger'}]
k = 'James'
value_list = [dictInst[k] for dictInst in dlist]
print(str(value_list))

# # Task 22
dlist = [{'Bilbo':'Ian', 'Frodo':'Elijah'}, {'Bilbo':'Martin', 'Thorin':'Richard'}]
k = 'Bilbo'
value_list_modified_1 = [dictInst[k] if k in dictInst else 'NOT PRESENT' for dictInst in dlist]
print(str(value_list_modified_1))
k = 'Frodo'
value_list_modified_2 = [dictInst[k] if k in dictInst else 'NOT PRESENT' for dictInst in dlist]
print(str(value_list_modified_2))

# # Task 23
square_dict = {x:x ** 2 for x in range(100)}
print(str(square_dict))

# # Task 24
D = {'red', 'white', 'blue'}
identity_dict = {x:x for x in D}
print(str(identity_dict))

# # Task 25
base = 10
digits = set(range(10))
representation_dict = {x * base ** 2 + y * base + z:[x, y, z] for x in digits for y in digits for z in digits}
print(str(representation_dict))

# # Task 26
d = {0:1000.0, 1:1200.50, 2:990}
names = ['Larry', 'Curly', 'Moe']
listdict2dict = { name:salary for (name, salary) in list(zip(names, d.values())) }
print(str(listdict2dict))

# # Task 27
def nextInts(L): return [ l + 1 for l in L ]

# # Task 28
def cubes(L): return [ l ** 3 for l in L ] 

# # Task 29
def dict2list(dct, keylist): return [ dct[key] for key in keylist ]

# # Task 30 
def list2dict(L, keylist): return { x:y for (x, y) in list(zip(keylist, L)) } 

