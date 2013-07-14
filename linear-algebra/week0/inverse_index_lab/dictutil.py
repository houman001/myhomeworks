## Task 2
def dict2list(dct, keylist): return [ dct[key] for key in keylist ]

def list2dict(L, keylist): return { x:y for (x, y) in list(zip(keylist, L)) }

## Task 3
def listrange2dict(L):
    return dict(list(enumerate(L)))

