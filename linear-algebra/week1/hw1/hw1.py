# Please fill out this stencil and submit using the provided submission script.

from GF2 import one
from math import pow
from itertools import combinations
from math import sqrt
from plotting import plot

# # Problem 1
p1_u = [ 0, 4]
p1_v = [-1, 3]
p1_v_plus_u = [v + u for (u, v) in zip(p1_u, p1_v)]
p1_v_minus_u = [v - u for (u, v) in zip(p1_u, p1_v)]
p1_three_v_minus_two_u = [3 * v - 2 * u for (u, v) in zip(p1_u, p1_v)]


# # Problem 2
p2_u = [-1, 1, 1]
p2_v = [ 2, -1, 5]
p2_v_plus_u = [v + u for (u, v) in zip(p2_u, p2_v)]
p2_v_minus_u = [v - u for (u, v) in zip(p2_u, p2_v)]
p2_two_v_minus_u = [2 * v - u for (u, v) in zip(p2_u, p2_v)]
p2_v_plus_two_u = [v + 2 * u for (u, v) in zip(p2_u, p2_v)]



# # Problem 3
# Write your answer using GF2's one instead of the number 1
u_111 = [one, one, one]
v_011 = [0, one, one]
p3_vector_sum_1 = [v + u for (u, v) in zip(u_111, v_011)]
p3_vector_sum_2 = [v + u + u for (u, v) in zip(u_111, v_011)]


# # Problem 4
# Please express your solution as a set of the letters corresponding to the solutions.
# For example, {'a','b','c'} is the subset consisting of:
#   a (1100000), b (0110000), and c (0011000).
# Leave an empty set if it cannot be expressed in terms of the other vectors.

u_0010010 = {'c', 'd', 'e'}
u_0100010 = {'c', 'd', 'b', 'e'}



# # Problem 5
# Use the same format as the previous problem

def sumGF2s(zero, S):
    if len(S) < 1:
        return None
    current = zero
    for x in S:
        current = [v + u for (u, v) in zip(current, x)]
    return current

def findExpected(zero, S, expected):
    for L in range(0, len(S) + 1):
        for subset in combinations(S, L):
            if sumGF2s(z, [gf2 for (symbol, gf2) in subset]) == expected:
                return {symbol for (symbol, gf2) in subset}
    return set()


z = [0, 0, 0, 0, 0, 0, 0]
a = ('a', [one, one, one, 0, 0, 0, 0])
b = ('b', [0, one, one, one, 0, 0, 0])
c = ('c', [0, 0, one, one, one, 0, 0])
d = ('d', [0, 0, 0, one, one, one, 0])
e = ('e', [0, 0, 0, 0, one, one, one])
f = ('f', [0, 0, 0, 0, 0, one, one])
all_gf2s = [a, b, c, d, e, f]

v_0010010 = findExpected(z, all_gf2s, [0, 0, one, 0, 0, one, 0])
v_0100010 = findExpected(z, all_gf2s, [0, one, 0, 0, 0, one, 0])



# # Problem 6

def dotProduct(a, b):
     return sum([v * u for (u, v) in zip(a, b)])
 
print(str(dotProduct([1, 0], [5, 4321])))
 
uv_a = dotProduct([1, 0], [5, 4321])
uv_b = dotProduct([0, 1], [12345, 6])
uv_c = dotProduct([-1, 3], [5, 7])
uv_d = dotProduct([-sqrt(2) / 2, sqrt(2) / 2], [sqrt(2) / 2, -sqrt(2) / 2])



# # Problem 7
# use 'one' instead of '1'
x_gf2 = [0, one, one, one]



# # Problem 8
v1 = [2, 3, -4, 1]
v2 = [1, -5, 2, 0]
v3 = [4, 1, -1, -1]

def plotLine(p1, p2, points):
    x1, y1 = p1
    x2, y2 = p2
    points = [(a * x1 + (1 - a) * x2, a * y1 + (1 - a) * y2) for a in [r / points for r in range(points)]]
    plot(points)
    pass

plotLine((-1.5, 2), (3, 0), 20)
plotLine((2, 1), (-2, 2), 100)
