package com.oreilly.mockito;

import java.util.List;

public class AddingMachine
{
  // Dependency of type List<Integer>
  private final List<Integer> numbers;

  public AddingMachine(List<Integer> numbers)
  {
    this.numbers = numbers;
  }

  @SuppressWarnings("ForLoopReplaceableByForEach")
  public int getTotalUsingLoop()
  {
    int total = 0;
    int count = numbers.size();
    for (int i = 0; i < count; i++)
    {
      total += numbers.get(i);
    }
    return total;
  }

  public int getTotalUsingIterable()
  {
    int total = 0;
    for (int n : numbers)
    {
      total += n;
    }
    return total;
  }

  public int getTotalUsingStream()
  {
    return numbers.stream().mapToInt(Integer::valueOf).sum();
  }
}

