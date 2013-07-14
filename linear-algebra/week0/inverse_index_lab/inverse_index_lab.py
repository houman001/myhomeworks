from random import randint
from dictutil import *

## Task 1
def movie_review(name):
    """
    Input: the name of a movie
    Output: a string (one of the review options), selected at random using randint
    """
    review_options = ["See it!", "A gem!", "Ideological claptrap!"]
    return review_options[randint(0,2)]

## Tasks 2 and 3 are in dictutil.py

## Task 4    
def makeInverseIndex(strlist):
    """
    Input: a list of documents as strings
    Output: a dictionary that maps each word in any document to the set consisting of the
            document ids (ie, the index in the strlist) for all documents containing the word.

    Note that to test your function, you are welcome to use the files stories_small.txt
      or stories_big.txt included in the download.
    """
    d = [ (x, y) for (x, y) in list(enumerate(ss.split() for ss in strlist)) ]
    print(str(d))
    t = [ dict(zip(y, [x] * len(y))) for (x, y) in d ]
    print(str(t))
    w = list(set(sum(list(b for a,b in d),[])))
    print(str(w))
    p = { word:{ item for item in { dictInst[word] if word in dictInst else None for dictInst in t } if item != None } for word in w }
    return p

## Task 5
def orSearch(inverseIndex, query):
    """
    Input: an inverse index, as created by makeInverseIndex, and a list of words to query
    Output: the set of document ids that contain _any_ of the specified words
    """
    return set(item for q in query for item in inverseIndex[q])
#     return set(item for item in inverseIndex[query.split()])

## Task 6
def andSearch(inverseIndex, query):
    """
    Input: an inverse index, as created by makeInverseIndex, and a list of words to query
    Output: the set of all document ids that contain _all_ of the specified words
    """
    c = [ (y, 1) for q in query for y in inverseIndex[q]]
    return { x for x,u in [ (x,sum([y for v,y in c if v == x])) for x,z in c ]  if u == len(query)}

# ridx = makeInverseIndex(['a b c', 'a c d', 'b c a', 'x c b'])
# print(str(ridx))
# print(str(orSearch(ridx, ['b', 'd', 'x'])))
# print(str(andSearch(ridx, ['a', 'b'])))
