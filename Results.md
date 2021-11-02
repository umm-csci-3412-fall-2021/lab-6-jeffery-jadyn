# Experimental results

**Testing pumpkin.jpg:**
Pumpkins.jpg has 47.2 KB of data. Our stress-testing script ran with five concurrent calls to the pumpkin.jpg and took five seconds to complete its tasks. When tested with a hundred, it took eleven seconds to complete. We note that this is a nonlinear trend; we also expected it to run longer.

**Testing words.txt:**
Words has 93.9 KB of data. When running five concurrent processes, it took considerably longer than when we ran one hundred processes on pumpkins.jpg, 21 seconds. When running a hundred processes at once, it took nearly a minute, 57 seconds.

**Testing black.jpg:**
Black.jpg has a file size of 2.81 KB. We ran five concurrent processes, and it took two seconds. When we ran one hundred concurrent processes, it took six seconds.


**Conclusions:**
The size of the file is a greater determining factor in the run time than the number of processes. We also noticed that the relationship between the run time and size of the file has an exponetial relationship.


