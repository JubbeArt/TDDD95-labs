# Spidermans workout

* sum of all climbs = 0

* recursivly doing all states

* DP (dynamic programming) - used decision problems, do problem substructes and solve them optimally. Lastly combine them

* brute froce, with state (height and index)

```java
// cache solutin in array
solve(int i, int height) {
  if (i >= heights.length) {
    return height
  }

  if (dp[i][h]) {
    return dp[i][h]
  }

  d = solve(i+1, h + heighs[i])
  d2 = solve(i+1, height - heights[i])

  int best = min(d, d2) 
  dp[i][h] = best

  return best
}
```

* #states = 1000 * 40

# Help!

* use map
* see slides

# Ljutnja

* find the kind with most candies
* can use sort (and go from heighest to lowest) or binary search

# Aspen avenue

* dp and memoziation
* sort, try all solutions
* greedy (mine) does not work

# knapsack

* DP problem
* dp[i][w]
