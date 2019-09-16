# Back To Back SWE

An initiative to empower the software engineer to excel in the interview.

<b>Join our class:</b> https://codinginterviewclass.com <br>
<b>YouTube:</b> https://www.youtube.com/c/BackToBackSWE <br>

<b>The Exhaustive Topics A Software Engineer Needs To Know To Pass A Big N Interview:</b>
- Fundamentals of Computer Systems (just a general knowing how computers store information etc.)
- Big O Time & Space Complexity Computation
- Arrays
- Primitives
- Strings
- Dynamic Programming
- Recursion / Backtracking
- Graphs
- Greedy Algorithms
- Hashtables
- Linked Lists
- Sorting
- Searching
- Min/Max Heaps
- Stacks
- Queues
- Trees, Binary Trees, & Binary Search Trees
- System and OO design Principles (sometimes)

And that is pretty much it. These are the topics you need to know well to pass.

The channel's goal is to contribute to the community of people bridging
these topics to help engineers excel in the interview.

It has nothing to do with me. I am just here to communicate the ideas.

I am only a humble teacher, I am not perfect.

If even one person gets an offer from my work my day is complete.

## Contributing
I'd love any contributions from people to this repo. I just restructured it so that solutions in multiple languages can be added for a single problem.

Just make sure that you follow the style conventions of your respective language.

### Must Haves:
- The code has to pass all test cases (if from Leetcode or any other site). It'd even be helpful if in the code you notate what date all tests passed so readers can know when a file needs updating (if tests fail all of a sudden or if a Leetcode structure changed and nothing compiles)
- If the problem doesn't have an "origin website" that has test cases, make a small driver function that can call the solution code with example inputs so others can test and play with the code.

#### Small nitpicks:

| | ![no](https://i.ibb.co/znyRxBs/Screen-Shot-2019-09-15-at-10-53-44-PM.png) | ![yes](https://i.ibb.co/sRcxycg/Screen-Shot-2019-09-15-at-10-53-48-PM.png) | ![what?](https://i.ibb.co/M7QqqDC/Screen-Shot-2019-09-15-at-11-06-02-PM.png) |
| --- | :-------------: |:-------------:| --- |
| | ❌ | ✅ | Why? |
| Name Variables Obnoxiously Clear | `int tlor`      | `int topLeftOfRectangle` | People are learning unfamiliar concepts. The clearer the variables, the faster others can learn. You may get it, but the aim is to allow a 5 year old to understand. |
| Give Code Breathing Room | `if(x+3*4){`| `if (x + 3 * 4) {`      | Just general etiquette. |
| Use The Ternary Operator Sparingly | ...| ...      | Nested ternarys to save lines hurts readability. The ternary is just syntactic sugar & will be transformed into if-elses by compilers.  |
| No One Liners. Ever. | ... |    ...   | Please. |

#### Other Smaller Rules:
- Less lines of code is not always better. This is close to the ternary rule above, but generally syntax shortcuts will not change what a program ultimately is transformed to. *Balance understandabiltiy & brevity.*
- I like comments in teaching code. **This code is meant to instruct** and is not part of a huge codebase, so it is fine to go heavy on comments. Each file is disjoint, static, & ages individually, so stale comments are less of a worry. I do it a bit too much in older samples, but I think you can find the balance.

## Mistakes

I'm not perfect. I watch finished videos 2 times over. I read and test all code samples. But I still make errors.

If you see a mistake anywhere just open a pr & I'll merge it in.

## Outdated Code

Sometimes Leetcode function signatures change or the names on fields of objects change breaking the code. If you see this then just open an issue or pr if you want to fix it.

## Best Books For Prep

no affiliate links here

<b>Bare Beginner:</b> https://www.amazon.com/Cracking-Coding-Interview-Programming-Questions/dp/0984782850
<b>Medium/Advanced:</b> https://www.amazon.com/Elements-Programming-Interviews-Java-Insiders/dp/1517671272
<b>Advanced:</b> https://cses.fi/book/

My Comments: Cracking The Coding Interview is a good start but pales in comparison to how much Elements
of Programming Interviews (EPI) will prepare you for the interview. Everything that I do has been inspired
by this book. To date, I have read it nearly 5 times and skimmed it 4 times.
