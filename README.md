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

## Todo list
[Trello board](https://trello.com/b/2dPLIZ0c/scotland-yard-game)

## Scotland Yard resources

* [Rules
  (Ravensburger)](https://ravensburger01.webtrekk.net/558884580617240/re.pl?t=http%3A%2F%2Fwww.ravensburger.com%3A80%2Fspielanleitungen/ecm/Spielanleitungen/231250%20anl%201326321.pdf&c=de.Service.Spielanleitung.Scotland%20Yard)
* [Rules (Globetrotter
games)](http://globetrotter-games.com/index.htm?E&game/rules/eScotYd1.htm)
* [Scotland Yard
  (Wiki)](http://en.wikipedia.org/wiki/Scotland_Yard_(board_game))
