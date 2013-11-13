package com.test.atlassian.iteration;

public class MyFunction2 implements Function2<Integer, Long, Long>
{
    public Long apply(Integer t, Long u)
    {
        return u + t;
    }
}
