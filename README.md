# README
## Development strategy
I propose we use a branching development model being very strict about keeping
master clean and only allowing working code inside of it. We should work on the
same branch and then when we merge we should notify they other (by email if not
in person) once we have a completed part.

I also suggest we also try to keep our commit history clean so we can try things
out and incrementally commit them to git. If our experiments don't pan out we
should rewrite the history:
[Rewriting git history](http://git-scm.com/book/ch6-4.html)

### Testing
Now in an ideal world I'd suggest we test everything and use mock objects. Given
that we only have two weeks to implement a full game this isn't going to happen.
We'll have to compromise. Let's only test meaty bits of code. Thankfully eclipse
has fairly good refactoring support so I think we should still refactor
aggressively. Let's not let cruft enter the repo, otherwise changing it will be
difficult
