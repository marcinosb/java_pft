package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

/**
 * Created by user on 27.12.2016.
 */
public class Primes {

  public static boolean isPrime(int n) {
    for (int i = 2; i < n / 2; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
  public static boolean isPrime(long n) {
    for(long i = 2; i < n; i++) {
      if(n % i == 0){
        return false;
      }
    }
    return true;
  }
}
