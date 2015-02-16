##Light Switches

Given a string of blinking lights with N bulbs, that follows a specific pattern, determine if a given bulb is on or off depending on the following rule at a given time.

When time is 0, all bulbs are off, but in each integral time, bulbs go from on to off or off to on depending on the state they are in. If the light is a multiple of the current time t, it will toggle. So at t=1, all lights toggle, at t=2, even lights toggle. The only other note is that when the time is equal to the length of the string of bulbs, all bulbs are turned off, and the pattern restarts at t=N+1.

The input is multiple lines, each with the number of Bulbs, N, the time since they were turned on, t, and the bulb number we are interested in, b.

You can use the makefile to run the test file provided. Simply run the command

`make test`
