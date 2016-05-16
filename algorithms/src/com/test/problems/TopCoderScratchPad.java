package com.test.problems;

public class TopCoderScratchPad {
    public class ArrayHash
    {
        public int getHash(String[] input)
        {
            int hash = 0;
            for (int stringIndex = 0; stringIndex < input.length; stringIndex++)
            {
                String inputString = input[stringIndex].toLowerCase();
                for (int charIndex = 0; charIndex < inputString.length(); charIndex++)
                {
                    char c = inputString.charAt(charIndex);
                    int charHash = c - 'a';
                    hash += (stringIndex + charHash + charIndex);
                }
            }
            return hash;
        }
    }
}
