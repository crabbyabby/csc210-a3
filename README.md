# A3 Iteration and Copy-Style Slicing

## Basic Information

Your name: Abigail Lei

Other students you worked with, including TAs: Esther, Claire

If anyone was particularly helpful, please give them a shout-out here:


## References

Any references or resources used besides JavaDoc and course materials:
https://stackoverflow.com/questions/1816458/getting-hold-of-the-outer-class-object-from-the-inner-class-object

If you used generative AI, how did you use it? What role did it play in your learning?
Used AI to figure out why code wouldn't compile in autograder when it did in VSCode & what the error was in Timer.java


## Questions to Answer

1. Which got slower faster as `N` increased: `get(mid)` or `add(0, x)` on `SLL`? Why?
get(mid) got much slower than add + remove(0), likely because get(mid) requires the iterator to iterate through the linked list, whereas adding and removing from the head are O(n) time.

2. Compare `splitCopy` vs `splitTransfer` for `DynamicArray` and `SLL`: what dominates the runtime in each?
splitCopy and splitTransfer are much faster for DynamicArray, likely because it is easier to split arrays when you know the indicies compared to for SLL.

## Reflection

Please provide a brief reflection about your experience with this assignment. What was easiest? What was hardest? How did your understanding of iteration and cost models evolve?

This was hard to start, but I think I realized it wasn't that bad as I was writing it. It was hard to turn the iterator from my mind to the actual implementation but it got much easier after implementing it for the DynamicArray. I think my implementation is very slow because it is taking a very long time to run the Timer.java and what shocked me most was that the add + remove for SLL and get(mid) for DynamicArray took no longer and even shorter per operation when comparing 1000 list size vs. 5000. It shocked me how O(n) the O(n) functions are. Ok coming back I am having a lot of trouble making it fast enough for the autograder.
