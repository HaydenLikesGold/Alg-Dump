Spectrum
========
NCNA Programming Problem

Swamp County Consulting has just been awarded a contract from a mysterious government agency to build a database to investigate connections between what they call “targets.” Your team has been sub-contracted to implement a system that stores target information and processes the commands listed below.
A target is represented by a string of up to 32 printing characters with no embedded spaces. A connection is a bi-directional relationship between two targets.

The hop count between a given target (called “target1”) and other targets is determined by the following rules: 
	1. Targets directly connected to target1 are 0 hops away.
	2. Targets directly connected to the 0 hop targets, and not already counted as a 0 hop target or the original target, are 1 hop targets.
	3. Similarly, targets directly connected to n hop targets, and not already counted in 0..n hop targets are n+1 hop targets.

There will be no more than 100,000 targets and no more than 500,000 connections.
