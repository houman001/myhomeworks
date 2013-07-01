package com.test

import scala.collection.SortedSet

object WordGuesser extends App {

  def wordLength = 6
  def charsToUse = "ipehfipjybuz".toList
  def lengthToSearch = wordLength
  def positionKnown = 0
  def charKnown = ' '
    
  def whatWeKnow(l: List[Char]): List[Char] =
    if (lengthToSearch == wordLength)
      l
    else
      insertInTheMiddle(l, positionKnown, charKnown)
  def insertInTheMiddle(l: List[Char], pos: Int, c: Char): List[Char] =
    l.slice(0, pos - 1) ::: c :: l.slice(pos - 1, l.length)

  val lines = io.Source.fromFile("dictionary.txt").getLines.toList
  def suitableWords = lines.filter(_.length == wordLength)

  println("Dictionary filtered: " + suitableWords.length)

  def removeFirstFromList[T](list: List[T], t: T): List[T] = {
    def index = list.indexOf(t)
    list.slice(0, index) ::: list.drop(index + 1)
  }
  
  def combinations[T](sym: List[T], size: Int): List[List[T]] = {
    if (size == 0)
      List(List())
    else {
      for {
        x <- sym
        xs <- combinations(removeFirstFromList(sym, x), size - 1)
      } yield x :: xs
    }
  }

  def possibleWords = combinations(charsToUse, lengthToSearch).map(whatWeKnow).map(l => l.mkString)
  def possibleWordsSet = SortedSet.empty[String] ++ possibleWords
  println("Possible words found: " + possibleWordsSet.size)
  
  possibleWordsSet.foreach(x => if (suitableWords.contains(x)) println(x))
  
}